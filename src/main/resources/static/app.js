// CampusGigs Frontend Client Application
const API_BASE = '';

let currentUser = null;
let currentToken = localStorage.getItem('cg_token') || null;
let allServices = [];

// DOM Elements
const servicesGrid = document.getElementById('services-grid');
const searchInput = document.getElementById('search-input');
const filterCategoria = document.getElementById('filter-categoria');
const toastContainer = document.getElementById('toast-container');
const modalAuth = document.getElementById('modal-auth');
const sessionBadge = document.getElementById('session-badge');
const sessionLabel = document.getElementById('session-label');
const btnOpenAuth = document.getElementById('btn-open-auth');
const btnLogout = document.getElementById('btn-logout');

// Initial Setup
document.addEventListener('DOMContentLoaded', () => {
    initTabs();
    initAuthModal();
    initForms();
    checkSession();
    loadServices();
});

// Toast Notifications
function showToast(message, type = 'info') {
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerHTML = `<span>${message}</span>`;
    toastContainer.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateX(100%)';
        setTimeout(() => toast.remove(), 300);
    }, 4000);
}

// Tab Navigation
function initTabs() {
    const navBtns = document.querySelectorAll('.nav-btn');
    const panels = document.querySelectorAll('.tab-panel');

    navBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            const targetTab = btn.getAttribute('data-tab');

            navBtns.forEach(b => b.classList.remove('active'));
            panels.forEach(p => p.classList.remove('active'));

            btn.classList.add('active');
            document.getElementById(`panel-${targetTab}`).classList.add('active');

            if (targetTab === 'meus-servicos') {
                loadMyActivities();
            }
        });
    });

    document.getElementById('btn-reload-services').addEventListener('click', loadServices);
    searchInput.addEventListener('input', renderFilteredServices);
    filterCategoria.addEventListener('change', renderFilteredServices);
}

// Session Management
function checkSession() {
    if (currentToken) {
        try {
            // Parse JWT Payload
            const payloadBase64 = currentToken.split('.')[1];
            const decodedJson = atob(payloadBase64);
            const claims = JSON.parse(decodedJson);

            currentUser = {
                email: claims.sub,
                role: claims.role || 'USER'
            };

            sessionBadge.className = `user-badge authenticated ${claims.role === 'ADMIN' ? 'admin' : ''}`;
            sessionLabel.textContent = `${currentUser.email} (${currentUser.role})`;
            btnOpenAuth.classList.add('hidden');
            btnLogout.classList.remove('hidden');
        } catch (e) {
            logout();
        }
    } else {
        sessionBadge.className = 'user-badge guest';
        sessionLabel.textContent = 'Visitante (Não logado)';
        btnOpenAuth.classList.remove('hidden');
        btnLogout.classList.add('hidden');
        currentUser = null;
    }
}

function logout() {
    currentToken = null;
    currentUser = null;
    localStorage.removeItem('cg_token');
    checkSession();
    showToast('Sessão encerrada com sucesso.', 'info');
    loadServices();
}

// Auth Modal
function initAuthModal() {
    btnOpenAuth.addEventListener('click', () => modalAuth.classList.remove('hidden'));
    document.getElementById('modal-auth-close').addEventListener('click', () => modalAuth.classList.add('hidden'));
    btnLogout.addEventListener('click', logout);

    const tabLoginBtn = document.getElementById('tab-login-btn');
    const tabRegisterBtn = document.getElementById('tab-register-btn');
    const subpanelLogin = document.getElementById('subpanel-login');
    const subpanelRegister = document.getElementById('subpanel-register');

    tabLoginBtn.addEventListener('click', () => {
        tabLoginBtn.classList.add('active');
        tabRegisterBtn.classList.remove('active');
        subpanelLogin.classList.add('active');
        subpanelRegister.classList.remove('active');
    });

    tabRegisterBtn.addEventListener('click', () => {
        tabRegisterBtn.classList.add('active');
        tabLoginBtn.classList.remove('active');
        subpanelRegister.classList.add('active');
        subpanelLogin.classList.remove('active');
    });

    // Quick Login buttons
    document.getElementById('quick-login-enzo').addEventListener('click', () => {
        document.getElementById('login-email').value = 'enzo@email.com';
        document.getElementById('login-senha').value = '123456';
    });

    document.getElementById('quick-login-admin').addEventListener('click', () => {
        document.getElementById('login-email').value = 'admin@campusgigs.com';
        document.getElementById('login-senha').value = 'admin123';
    });

    // Auto ViaCEP on registration
    const regCep = document.getElementById('reg-cep');
    const cepFeedback = document.getElementById('cep-feedback');

    regCep.addEventListener('blur', async () => {
        const clean = regCep.value.replace(/\D/g, '');
        if (clean.length === 8) {
            cepFeedback.textContent = 'Consultando ViaCEP...';
            try {
                const res = await fetch(`https://viacep.com.br/ws/${clean}/json/`);
                const data = await res.json();
                if (data.erro) {
                    cepFeedback.textContent = '⚠️ CEP não encontrado na base dos Correios.';
                    cepFeedback.style.color = '#f87171';
                } else {
                    cepFeedback.textContent = `📍 ${data.localidade} - ${data.uf}`;
                    cepFeedback.style.color = '#34d399';
                }
            } catch (err) {
                cepFeedback.textContent = 'Erro ao consultar CEP.';
                cepFeedback.style.color = '#f87171';
            }
        }
    });
}

// Forms Handling
function initForms() {
    // Login
    document.getElementById('form-login').addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('login-email').value;
        const senha = document.getElementById('login-senha').value;

        try {
            const res = await fetch(`${API_BASE}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, senha })
            });

            if (res.ok) {
                const data = await res.json();
                currentToken = data.token;
                localStorage.setItem('cg_token', currentToken);
                checkSession();
                modalAuth.classList.add('hidden');
                showToast('Login realizado com sucesso!', 'success');
                loadServices();
            } else {
                const err = await res.json();
                showToast(err.detail || 'Falha ao realizar login. Verifique as credenciais.', 'error');
            }
        } catch (err) {
            showToast('Erro de conexão ao autenticar.', 'error');
        }
    });

    // Register
    document.getElementById('form-register').addEventListener('submit', async (e) => {
        e.preventDefault();
        const nome = document.getElementById('reg-nome').value;
        const email = document.getElementById('reg-email').value;
        const senha = document.getElementById('reg-senha').value;
        const role = document.getElementById('reg-role').value;
        const cep = document.getElementById('reg-cep').value;

        try {
            const res = await fetch(`${API_BASE}/usuario`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ nome, email, senha, role, cep })
            });

            if (res.ok) {
                showToast('Cadastro realizado! Faça login para começar.', 'success');
                document.getElementById('tab-login-btn').click();
                document.getElementById('login-email').value = email;
            } else {
                const err = await res.json();
                const msg = err.detail || (err.errors ? Object.values(err.errors).join(', ') : 'Erro ao cadastrar.');
                showToast(msg, 'error');
            }
        } catch (err) {
            showToast('Erro de conexão ao cadastrar.', 'error');
        }
    });

    // Publicar Freela
    document.getElementById('form-publicar-servico').addEventListener('submit', async (e) => {
        e.preventDefault();
        if (!currentToken) {
            showToast('Você precisa estar autenticado para publicar um freela.', 'error');
            modalAuth.classList.remove('hidden');
            return;
        }

        const titulo = document.getElementById('servico-titulo').value;
        const categoria = document.getElementById('servico-categoria').value;
        const preco = parseFloat(document.getElementById('servico-preco').value);
        const descricao = document.getElementById('servico-descricao').value;

        try {
            const res = await fetch(`${API_BASE}/servico`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${currentToken}`
                },
                body: JSON.stringify({ titulo, categoria, preco, descricao })
            });

            if (res.ok) {
                showToast('Freela publicado com sucesso!', 'success');
                document.getElementById('form-publicar-servico').reset();
                document.getElementById('tab-btn-explorar').click();
                loadServices();
            } else {
                const err = await res.json();
                showToast(err.detail || 'Erro ao publicar serviço.', 'error');
            }
        } catch (err) {
            showToast('Erro de comunicação com o servidor.', 'error');
        }
    });
}

// Load Services
async function loadServices() {
    servicesGrid.innerHTML = '<div class="loading-state">Carregando freelas disponíveis...</div>';
    try {
        const res = await fetch(`${API_BASE}/servico`);
        if (res.ok) {
            allServices = await res.json();
            renderFilteredServices();
        } else {
            servicesGrid.innerHTML = '<div class="error-state">Falha ao carregar serviços.</div>';
        }
    } catch (err) {
        servicesGrid.innerHTML = '<div class="error-state">Servidor fora do ar ou inacessível.</div>';
    }
}

// Render Services
function renderFilteredServices() {
    const query = searchInput.value.toLowerCase();
    const cat = filterCategoria.value;

    const filtered = allServices.filter(s => {
        const matchText = s.titulo.toLowerCase().includes(query) || s.descricao.toLowerCase().includes(query);
        const matchCat = !cat || s.categoria === cat;
        return matchText && matchCat;
    });

    if (filtered.length === 0) {
        servicesGrid.innerHTML = '<div class="empty-state">Nenhum freela encontrado para os filtros selecionados.</div>';
        return;
    }

    servicesGrid.innerHTML = filtered.map(servico => {
        const isAtivo = servico.situacao === 'ATIVO';
        const isOwner = currentUser && servico.prestador && servico.prestador.email.toLowerCase() === currentUser.email.toLowerCase();

        return `
            <div class="service-card glassmorphism">
                <div>
                    <div class="card-top">
                        <span class="category-badge">${servico.categoria}</span>
                        <span class="status-pill ${servico.situacao.toLowerCase()}">${servico.situacao}</span>
                    </div>
                    <h3 class="service-title">${servico.titulo}</h3>
                    <p class="service-desc">${servico.descricao}</p>
                </div>
                <div>
                    <div class="card-footer">
                        <div>
                            <div class="price-tag">R$ ${servico.preco.toFixed(2)}</div>
                            <div class="provider-info">Por: ${servico.prestador ? servico.prestador.nome : 'Anônimo'}</div>
                        </div>
                        <div>
                            ${isAtivo && !isOwner ? `
                                <button class="btn btn-primary btn-sm" onclick="contratarServico(${servico.id})">Contratar</button>
                            ` : isOwner ? `
                                <span class="badge-tag">Seu Freela</span>
                            ` : `
                                <span class="status-pill encerrado">Encerrado</span>
                            `}
                        </div>
                    </div>
                </div>
            </div>
        `;
    }).join('');
}

// Contratar Serviço
window.contratarServico = async function(servicoId) {
    if (!currentToken) {
        showToast('Faça login para poder contratar este serviço.', 'info');
        modalAuth.classList.remove('hidden');
        return;
    }

    if (!confirm('Deseja realmente contratar este freela?')) return;

    try {
        const res = await fetch(`${API_BASE}/contratacao`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${currentToken}`
            },
            body: JSON.stringify({ servicoId })
        });

        if (res.ok) {
            showToast('Serviço contratado com sucesso! Acompanhe em Minhas Atividades.', 'success');
            loadServices();
        } else {
            const err = await res.json();
            showToast(err.detail || 'Não foi possível contratar este serviço.', 'error');
        }
    } catch (err) {
        showToast('Erro de conexão ao contratar serviço.', 'error');
    }
};

// Load My Activities
async function loadMyActivities() {
    const listMeusServicos = document.getElementById('list-meus-servicos');
    const listMinhasContratacoes = document.getElementById('list-minhas-contratacoes');

    if (!currentToken) {
        listMeusServicos.innerHTML = '<p class="text-dim">Faça login para visualizar seus serviços.</p>';
        listMinhasContratacoes.innerHTML = '<p class="text-dim">Faça login para visualizar suas contratações.</p>';
        return;
    }

    // Meus Freelas (filtrados da lista de serviços)
    const meus = allServices.filter(s => s.prestador && s.prestador.email.toLowerCase() === currentUser.email.toLowerCase());
    document.getElementById('count-meus-servicos').textContent = meus.length;

    if (meus.length === 0) {
        listMeusServicos.innerHTML = '<p class="text-dim">Você ainda não publicou nenhum freela.</p>';
    } else {
        listMeusServicos.innerHTML = meus.map(s => `
            <div class="activity-card">
                <div class="activity-info">
                    <h4>${s.titulo}</h4>
                    <span class="activity-meta">R$ ${s.preco.toFixed(2)} • ${s.categoria} • Status: <strong>${s.situacao}</strong></span>
                </div>
                <div class="activity-actions">
                    ${s.situacao === 'ATIVO' ? `
                        <button class="btn btn-outline btn-xs" onclick="encerrarServico(${s.id})">Encerrar</button>
                    ` : ''}
                    <button class="btn btn-danger btn-xs" onclick="excluirServico(${s.id})">Excluir</button>
                </div>
            </div>
        `).join('');
    }

    // Minhas Contratações
    try {
        const res = await fetch(`${API_BASE}/contratacao`, {
            headers: { 'Authorization': `Bearer ${currentToken}` }
        });
        if (res.ok) {
            const contratacoes = await res.json();
            const minhas = contratacoes.filter(c => c.contratante && c.contratante.email.toLowerCase() === currentUser.email.toLowerCase());
            document.getElementById('count-minhas-contratacoes').textContent = minhas.length;

            if (minhas.length === 0) {
                listMinhasContratacoes.innerHTML = '<p class="text-dim">Você não contratou nenhum serviço ainda.</p>';
            } else {
                listMinhasContratacoes.innerHTML = minhas.map(c => `
                    <div class="activity-card">
                        <div class="activity-info">
                            <h4>${c.servico ? c.servico.titulo : 'Serviço'}</h4>
                            <span class="activity-meta">Prestador: ${c.servico && c.servico.prestador ? c.servico.prestador.nome : '-'} • Situação: <strong>${c.situacao}</strong></span>
                        </div>
                    </div>
                `).join('');
            }
        }
    } catch (err) {
        listMinhasContratacoes.innerHTML = '<p class="text-dim">Falha ao buscar contratações.</p>';
    }
}

window.encerrarServico = async function(id) {
    if (!confirm('Deseja realmente encerrar este serviço?')) return;

    try {
        const res = await fetch(`${API_BASE}/servico/${id}/encerrar`, {
            method: 'PATCH',
            headers: { 'Authorization': `Bearer ${currentToken}` }
        });
        if (res.ok) {
            showToast('Serviço encerrado com sucesso!', 'success');
            await loadServices();
            loadMyActivities();
        } else {
            const err = await res.json();
            showToast(err.detail || 'Erro ao encerrar serviço.', 'error');
        }
    } catch (err) {
        showToast('Erro de conexão ao encerrar serviço.', 'error');
    }
};

window.excluirServico = async function(id) {
    if (!confirm('Deseja realmente excluir este serviço permanentemente?')) return;

    try {
        const res = await fetch(`${API_BASE}/servico/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': `Bearer ${currentToken}` }
        });
        if (res.ok || res.status === 204) {
            showToast('Serviço excluído com sucesso!', 'success');
            await loadServices();
            loadMyActivities();
        } else {
            const err = await res.json();
            showToast(err.detail || 'Erro ao excluir serviço.', 'error');
        }
    } catch (err) {
        showToast('Erro de conexão ao excluir serviço.', 'error');
    }
};

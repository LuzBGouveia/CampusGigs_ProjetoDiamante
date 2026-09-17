package fiap.com.br.campusgigs.contratacao;

import fiap.com.br.campusgigs.contratacao.dto.ContratacaoRequest;
import fiap.com.br.campusgigs.contratacao.dto.ContratacaoResponse;
import fiap.com.br.campusgigs.validations.ContratacaoValidator;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.ServicoRepository;
import fiap.com.br.campusgigs.usuario.Usuario;
import fiap.com.br.campusgigs.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratacaoService {
    private final ContratacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;
    private final ContratacaoValidator validator;

    public List<ContratacaoResponse> findAll() {
        return repository.findAll().stream()
                .map(ContratacaoResponse::fromEntity)
                .toList();
    }

    public ContratacaoResponse findById(Long id) {
        return ContratacaoResponse.fromEntity(findContratacaoById(id));
    }

    public ContratacaoResponse save(ContratacaoRequest request, Authentication authentication) {
        var servico = findServicoById(request.servicoId());
        var usuarioLogado = findUsuarioByEmail(authentication.getName());

        validator.validate(servico, usuarioLogado);

        var contratacao = request.toEntity(servico, usuarioLogado);
        return ContratacaoResponse.fromEntity(repository.save(contratacao));
    }

    public ContratacaoResponse update(Long id, ContratacaoRequest request, Authentication authentication) {
        var contratacaoExistente = findContratacaoById(id);
        validarPermissao(contratacaoExistente, authentication, "editar");

        var servico = findServicoById(request.servicoId());
        if (!contratacaoExistente.getServico().getId().equals(servico.getId())) {
            validator.validate(servico, contratacaoExistente.getUsuario());
        }

        contratacaoExistente.setServico(servico);
        return ContratacaoResponse.fromEntity(repository.save(contratacaoExistente));
    }

    public void delete(Long id, Authentication authentication) {
        var contratacao = findContratacaoById(id);
        validarPermissao(contratacao, authentication, "excluir");
        repository.delete(contratacao);
    }

    private void validarPermissao(Contratacao contratacao, Authentication authentication, String acao) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean isContratante = contratacao.getUsuario().getEmail().equalsIgnoreCase(authentication.getName());
        boolean isPrestador = contratacao.getServico().getUsuario().getEmail().equalsIgnoreCase(authentication.getName());

        if (!isAdmin && !isContratante && !isPrestador) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para " + acao + " esta contratação."
            );
        }
    }

    private Contratacao findContratacaoById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratação não encontrada.")
        );
    }

    private Servico findServicoById(Long id) {
        return servicoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")
        );
    }

    private Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário autenticado não encontrado.")
        );
    }
}

package fiap.com.br.campusgigs.contratacao;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.contratacao.dto.ContratacaoRequest;
import fiap.com.br.campusgigs.servico.ServicoRepository;
import fiap.com.br.campusgigs.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratacaoService {
    private final ContratacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;

    public List<Contratacao> findAll() {
        return repository.findAll();
    }

    public Contratacao findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratação não encontrado.")
        );
    }

    public Contratacao save(ContratacaoRequest request, Authentication authentication) {
        var servico = servicoRepository.findById(request.servico().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));

        var usuarioLogado = usuarioRepository.findByNome(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário logado não encontrado."));

        if (servico.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você não pode contratar o seu próprio serviço.");
        }

        var contratacao = new Contratacao();
        contratacao.setServico(servico);
        contratacao.setUsuario(usuarioLogado);
        contratacao.setSituacao(Situacao.SOLICITADA); // Ou o status inicial padrão

        return repository.save(contratacao);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

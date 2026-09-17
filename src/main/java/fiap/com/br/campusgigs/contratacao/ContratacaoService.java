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

    public void delete(Long id) {
        var contratacao = findContratacaoById(id);
        repository.delete(contratacao);
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

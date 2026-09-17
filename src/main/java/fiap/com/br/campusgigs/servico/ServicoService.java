package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.dto.ServicoRequest;
import fiap.com.br.campusgigs.servico.dto.ServicoResponse;
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
public class ServicoService {
    private final ServicoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public List<ServicoResponse> findAll() {
        return repository.findAll().stream()
                .map(ServicoResponse::fromEntity)
                .toList();
    }

    public ServicoResponse findById(Long id) {
        return ServicoResponse.fromEntity(findServicoById(id));
    }

    public ServicoResponse save(ServicoRequest request, Authentication authentication) {
        var usuario = findUsuarioByEmail(authentication.getName());
        var servico = request.toEntity(usuario);
        return ServicoResponse.fromEntity(repository.save(servico));
    }

    public ServicoResponse update(Long id, ServicoRequest request, Authentication authentication) {
        var servicoExistente = findServicoById(id);
        validarPermissao(servicoExistente, authentication, "editar");

        var servico = request.toEntity(servicoExistente.getUsuario());
        servico.setId(id);
        servico.setSituacao(servicoExistente.getSituacao());

        return ServicoResponse.fromEntity(repository.save(servico));
    }

    public ServicoResponse encerrar(Long id, Authentication authentication) {
        var servico = findServicoById(id);
        validarPermissao(servico, authentication, "encerrar");

        servico.setSituacao(SituacaoServico.ENCERRADO);
        return ServicoResponse.fromEntity(repository.save(servico));
    }

    public void delete(Long id, Authentication authentication) {
        var servico = findServicoById(id);
        validarPermissao(servico, authentication, "excluir");

        repository.delete(servico);
    }

    private Servico findServicoById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")
        );
    }

    private Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário autenticado não encontrado.")
        );
    }

    private void validarPermissao(Servico servico, Authentication authentication, String acao) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean isOwner = servico.getUsuario().getEmail().equalsIgnoreCase(authentication.getName());

        if (!isAdmin && !isOwner) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não tem permissão para " + acao + " este serviço."
            );
        }
    }
}

package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.dto.ServicoRequest;
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
public class ServicoService {
    private final ServicoRepository repository;
    private final UsuarioRepository  usuarioRepository;

    public List<Servico> findAll() {
        return repository.findAll();
    }

    public Servico findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")
        );
    }

    public Servico save(ServicoRequest request, Authentication authentication) {
        var usuario = usuarioRepository.findByNome(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário logado não encontrado."));

        var servico = request.toEntity();
        servico.setUsuario(usuario);

        return repository.save(servico);
    }

    public void delete(Long id, Authentication authentication) {
        var servico = findById(id);

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !servico.getUsuario().getNome().equals(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para excluir este serviço.");
        }

        repository.delete(servico);
    }
}

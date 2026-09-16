package fiap.com.br.campusgigs.usuario;

import fiap.com.br.campusgigs.usuario.dto.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário, não encontrado.")
        );
    }

    public Usuario save(UsuarioRequest request) {
        return repository.save(request.toEntity());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

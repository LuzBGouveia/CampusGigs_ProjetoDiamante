package fiap.com.br.campusgigs.usuario;

import fiap.com.br.campusgigs.endereco.EnderecoResponse;
import fiap.com.br.campusgigs.endereco.EnderecoService;
import fiap.com.br.campusgigs.usuario.dto.UsuarioRequest;
import fiap.com.br.campusgigs.usuario.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EnderecoService enderecoService;

    public List<UsuarioResponse> findAll() {
        return repository.findAll().stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }

    public UsuarioResponse findById(Long id) {
        return UsuarioResponse.fromEntity(findUsuarioById(id));
    }

    public UsuarioResponse save(UsuarioRequest request) {
        var endereco = fetchEnderecoByCep(request.cep());
        var usuario = request.toEntity(passwordEncoder, endereco);
        return UsuarioResponse.fromEntity(repository.save(usuario));
    }

    public UsuarioResponse update(Long id, UsuarioRequest request) {
        findUsuarioById(id);
        var endereco = fetchEnderecoByCep(request.cep());
        var usuario = request.toEntity(passwordEncoder, endereco);
        usuario.setId(id);
        return UsuarioResponse.fromEntity(repository.save(usuario));
    }

    public void delete(Long id) {
        var usuario = findUsuarioById(id);
        repository.delete(usuario);
    }

    private Usuario findUsuarioById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.")
        );
    }

    public Usuario findUsuarioByEmail(String email) {
        return repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario com email " + email + " nao encontrado."));
    }

    private EnderecoResponse fetchEnderecoByCep(String cep) {
        var endereco = enderecoService.getEnderecoPorCep(cep.replaceAll("\\D", ""));
        if (Boolean.TRUE.equals(endereco.erro())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP não encontrado.");
        }
        return endereco;
    }
}

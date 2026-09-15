package fiap.com.br.campusgigs.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Long, Usuario> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmailIgnoreCase(String email);
}

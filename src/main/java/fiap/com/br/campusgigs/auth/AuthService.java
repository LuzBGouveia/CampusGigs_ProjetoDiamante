package fiap.com.br.campusgigs.auth;

import fiap.com.br.campusgigs.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = usuarioRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email)
        );

        return User
                .withUsername(user.getEmail())
                .password(user.getSenha())
                .roles(user.getRole().name())
                .build();
    }
}


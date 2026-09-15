package fiap.com.br.campusgigs.auth;

import fiap.com.br.campusgigs.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;

    String generateToken(String email){
        var user = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        Instant now = Instant.now();
        JwtClaimsSet param = JwtClaimsSet.builder()
                .subject("campusgigs-api")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .claim("role", user.getRole())
                .build();

        JwtEncoderParameters jwtClaimsSet = JwtEncoderParameters.from(param);
        return jwtEncoder.encode(jwtClaimsSet).getTokenValue();
    }

}
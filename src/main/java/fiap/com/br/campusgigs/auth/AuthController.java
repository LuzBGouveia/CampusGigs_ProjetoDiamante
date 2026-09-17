package fiap.com.br.campusgigs.auth;

import fiap.com.br.campusgigs.usuario.Usuario;
import fiap.com.br.campusgigs.usuario.UsuarioService;
import fiap.com.br.campusgigs.usuario.dto.UsuarioResponse;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    record LoginRequest(@Email String email, String senha) {}
    record LoginResponse(String token, UsuarioResponse usuario) {}

    @PostMapping({"/login", "/campusgigs/login"})
    public LoginResponse login(@RequestBody LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var jwt = tokenService.generateToken(authentication.getName());

        Usuario usuario = usuarioService.findUsuarioByEmail(authentication.getName());

        return new LoginResponse(jwt, UsuarioResponse.fromEntity(usuario));
    }

}
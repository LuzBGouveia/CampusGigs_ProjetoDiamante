package fiap.com.br.campusgigs.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    record LoginRequest(String nome, String senha) {}
    record LoginResponse(String token) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.nome(), request.senha());

        var authentication = authenticationManager.authenticate(authenticationToken);

        var jwt = tokenService.generateToken(authentication.getName());

        return new LoginResponse(jwt);
    }

}
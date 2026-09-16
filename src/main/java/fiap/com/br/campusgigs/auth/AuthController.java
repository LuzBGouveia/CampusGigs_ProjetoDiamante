package fiap.com.br.campusgigs.auth;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    record LoginRequest(String nome, String senha) {}
    record LoginResponse(String token) {}

    @PostMapping("/login")
    public LoginResponse login(Authentication authentication){
        var jwt = tokenService.generateToken(authentication.getName());
        return new LoginResponse(jwt);
    }

}
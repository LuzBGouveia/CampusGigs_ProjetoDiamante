package fiap.com.br.campusgigs.usuario.dto;

import fiap.com.br.campusgigs.endereco.EnderecoResponse;
import fiap.com.br.campusgigs.usuario.Usuario;
import fiap.com.br.campusgigs.usuario.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public record UsuarioRequest (
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        UsuarioRole role,

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "O CEP deve conter 8 dígitos numéricos")
        String cep
) {
    public Usuario toEntity(PasswordEncoder encoder, EnderecoResponse endereco) {
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(encoder.encode(senha))
                .role(role != null ? role : UsuarioRole.USER)
                .cep(cep)
                .cidade(endereco.localidade())
                .uf(endereco.uf())
                .build();
    }
}

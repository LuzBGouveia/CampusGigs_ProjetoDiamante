package fiap.com.br.campusgigs.usuario.dto;

import fiap.com.br.campusgigs.usuario.Usuario;

public record UsuarioRequest (
        String nome,
        String email,
        String senha,
        String role,
        String cep
) {
    public Usuario toEntity() {
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .role(role)
                .build();
    }
}

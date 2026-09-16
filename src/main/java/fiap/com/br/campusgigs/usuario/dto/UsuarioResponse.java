package fiap.com.br.campusgigs.usuario.dto;

import fiap.com.br.campusgigs.usuario.Usuario;

public record UsuarioResponse (
        String nome,
        String email,
        String senha,
        String role
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }
}

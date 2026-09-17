package fiap.com.br.campusgigs.usuario.dto;

import fiap.com.br.campusgigs.usuario.Usuario;
import fiap.com.br.campusgigs.usuario.UsuarioRole;

public record UsuarioResponse (
        Long id,
        String nome,
        String email,
        UsuarioRole role,
        String cep,
        String cidade,
        String uf
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getCep(),
                usuario.getCidade(),
                usuario.getUf()
        );
    }
}

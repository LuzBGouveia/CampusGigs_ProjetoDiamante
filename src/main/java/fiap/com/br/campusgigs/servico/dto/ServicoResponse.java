package fiap.com.br.campusgigs.servico.dto;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.Situacao;
import fiap.com.br.campusgigs.usuario.Usuario;

public record ServicoResponse(
        String titulo,
        String descricao,
        String categoria,
        Double preco,
        Usuario usuario,
        Situacao situacao
) {
    public static ServicoResponse fromEntity(Servico servico) {
        return new ServicoResponse(
                servico.getTitulo(),
                servico.getDescricao(),
                servico.getCategoria(),
                servico.getPreco(),
                servico.getUsuario(),
                servico.getSituacao()
        );
    }
}

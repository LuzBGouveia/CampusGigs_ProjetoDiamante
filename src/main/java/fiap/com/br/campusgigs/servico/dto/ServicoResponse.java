package fiap.com.br.campusgigs.servico.dto;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.SituacaoServico;
import fiap.com.br.campusgigs.usuario.dto.UsuarioResponse;

public record ServicoResponse(
        Long id,
        String titulo,
        String descricao,
        String categoria,
        Double preco,
        SituacaoServico situacao,
        UsuarioResponse prestador
) {
    public static ServicoResponse fromEntity(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getTitulo(),
                servico.getDescricao(),
                servico.getCategoria(),
                servico.getPreco(),
                servico.getSituacao(),
                UsuarioResponse.fromEntity(servico.getUsuario())
        );
    }
}

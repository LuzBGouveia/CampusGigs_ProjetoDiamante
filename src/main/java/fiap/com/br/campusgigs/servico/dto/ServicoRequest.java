package fiap.com.br.campusgigs.servico.dto;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.SituacaoServico;
import fiap.com.br.campusgigs.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ServicoRequest(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotBlank(message = "A categoria é obrigatória")
        String categoria,

        @NotNull(message = "O preço é obrigatório")
        @PositiveOrZero(message = "O preço não pode ser negativo")
        Double preco
) {
    public Servico toEntity(Usuario usuario) {
        return Servico.builder()
                .titulo(titulo)
                .descricao(descricao)
                .categoria(categoria)
                .preco(preco)
                .usuario(usuario)
                .situacao(SituacaoServico.ATIVO)
                .build();
    }
}

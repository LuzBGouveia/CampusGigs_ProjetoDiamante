package fiap.com.br.campusgigs.servico.dto;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.Situacao;
import fiap.com.br.campusgigs.usuario.Usuario;

public record ServicoRequest(
        String titulo,
        String descricao,
        String categoria,
        Double preco,
        Usuario usuario,
        Situacao situacao
) {
    public Servico toEntity() {
        return Servico.builder()
                .titulo(titulo)
                .descricao(descricao)
                .categoria(categoria)
                .preco(preco)
                .usuario(usuario)
                .situacao(situacao)
                .build();
    }
}

package fiap.com.br.campusgigs.contratacao.dto;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.contratacao.Situacao;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.usuario.Usuario;

public record ContratacaoRequest(
        Servico servico,
        Usuario usuario,
        Situacao situacao
) {
    public Contratacao toEntity() {
        return Contratacao.builder()
                .servico(servico)
                .usuario(usuario)
                .situacao(situacao)
                .build();
    }
}

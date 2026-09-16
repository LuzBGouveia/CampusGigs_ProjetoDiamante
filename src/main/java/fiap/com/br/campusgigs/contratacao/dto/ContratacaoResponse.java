package fiap.com.br.campusgigs.contratacao.dto;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.contratacao.Situacao;
import fiap.com.br.campusgigs.usuario.Usuario;

public record ContratacaoResponse(
        Servico servico,
        Usuario usuario,
        Situacao situacao
) {
    public static ContratacaoResponse fromEntity(Contratacao contratacao) {
        return new ContratacaoResponse(
                contratacao.getServico(),
                contratacao.getUsuario(),
                contratacao.getSituacao()
        );
    }
}

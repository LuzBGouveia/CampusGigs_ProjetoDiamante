package fiap.com.br.campusgigs.contratacao.dto;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.contratacao.SituacaoContratacao;
import fiap.com.br.campusgigs.servico.dto.ServicoResponse;
import fiap.com.br.campusgigs.usuario.dto.UsuarioResponse;

public record ContratacaoResponse(
        Long id,
        ServicoResponse servico,
        UsuarioResponse contratante,
        SituacaoContratacao situacao
) {
    public static ContratacaoResponse fromEntity(Contratacao contratacao) {
        return new ContratacaoResponse(
                contratacao.getId(),
                ServicoResponse.fromEntity(contratacao.getServico()),
                UsuarioResponse.fromEntity(contratacao.getUsuario()),
                contratacao.getSituacao()
        );
    }
}

package fiap.com.br.campusgigs.contratacao.dto;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.contratacao.SituacaoContratacao;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

public record ContratacaoRequest(
        @NotNull(message = "O ID do serviço é obrigatório")
        Long servicoId
) {
    public Contratacao toEntity(Servico servico, Usuario usuario) {
        return Contratacao.builder()
                .servico(servico)
                .usuario(usuario)
                .situacao(SituacaoContratacao.SOLICITADA)
                .build();
    }
}

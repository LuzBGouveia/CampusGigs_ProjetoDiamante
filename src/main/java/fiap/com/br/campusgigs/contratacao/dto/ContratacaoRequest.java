package fiap.com.br.campusgigs.contratacao.dto;

import jakarta.validation.constraints.NotNull;

public record ContratacaoRequest(
        @NotNull(message = "O ID do serviço é obrigatório")
        Long servicoId
) {
}

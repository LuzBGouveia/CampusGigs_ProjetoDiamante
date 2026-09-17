package fiap.com.br.campusgigs.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoResponse(
        String localidade,
        String uf,
        Boolean erro
) {
}

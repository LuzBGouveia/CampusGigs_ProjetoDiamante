package fiap.com.br.campusgigs.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoResponse(
        String logradouro,
        String bairro,
        String localidade,
        String estado,
        Boolean erro
) {
}

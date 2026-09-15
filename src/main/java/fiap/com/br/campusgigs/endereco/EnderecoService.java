package fiap.com.br.campusgigs.endereco;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(
        url = "https://viacep.com.br/ws",
        accept = "application/json"
)
public interface EnderecoService {
    @GetExchange("/{cep}/json")
    EnderecoResponse getEnderecoPorCep(@PathVariable("cep") String cep);
}

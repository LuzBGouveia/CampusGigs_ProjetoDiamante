package fiap.com.br.campusgigs.contratacao;

import fiap.com.br.campusgigs.contratacao.dto.ContratacaoRequest;
import fiap.com.br.campusgigs.contratacao.dto.ContratacaoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/contratacao")
public class ContratacaoController {
    private final ContratacaoService service;

    @GetMapping
    public List<ContratacaoResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ContratacaoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContratacaoResponse add(@RequestBody @Valid ContratacaoRequest request, Authentication authentication) {
        return service.save(request, authentication);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

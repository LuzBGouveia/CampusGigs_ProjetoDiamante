package fiap.com.br.campusgigs.contratacao;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.contratacao.dto.ContratacaoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contratacao")
public class ContratacaoController {
    private final ContratacaoService service;

    @GetMapping
    public List<Contratacao> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Contratacao findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Contratacao add(@RequestBody ContratacaoRequest request, Authentication authentication) {
        return service.save(request, authentication);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable Long id) {
        service.delete(id);
    }

}

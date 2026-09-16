package fiap.com.br.campusgigs.contratacao;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Contratacao> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id));
    }

}

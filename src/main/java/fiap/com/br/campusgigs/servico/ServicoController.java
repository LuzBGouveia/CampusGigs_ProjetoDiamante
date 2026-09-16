package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servico")
public class ServicoController {
    private final ServicoService service;

    @GetMapping
    public List<Servico> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id));
    }
}

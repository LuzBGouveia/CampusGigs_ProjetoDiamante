package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.ServicoService;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.dto.ServicoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Servico findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Servico add(@RequestBody ServicoRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable Long id) {
        service.delete(id);
    }
}

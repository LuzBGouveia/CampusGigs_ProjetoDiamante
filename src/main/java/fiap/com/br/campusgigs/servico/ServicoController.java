package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.dto.ServicoRequest;
import fiap.com.br.campusgigs.servico.dto.ServicoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/servico")
public class ServicoController {
    private final ServicoService service;

    @GetMapping
    public List<ServicoResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServicoResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponse add(@RequestBody @Valid ServicoRequest request, Authentication authentication) {
        return service.save(request, authentication);
    }

    @PutMapping("/{id}")
    public ServicoResponse update(@PathVariable Long id, @RequestBody @Valid ServicoRequest request, Authentication authentication) {
        return service.update(id, request, authentication);
    }

    @PatchMapping("/{id}/encerrar")
    public ServicoResponse encerrar(@PathVariable Long id, Authentication authentication) {
        return service.encerrar(id, authentication);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Authentication authentication) {
        service.delete(id, authentication);
    }
}

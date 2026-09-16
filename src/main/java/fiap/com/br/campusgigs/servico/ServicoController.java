package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.ServicoService;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.dto.ServicoRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Servico add(@RequestBody ServicoRequest request, Authentication authentication) {
        return service.save(request, authentication);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void  delete(@PathVariable Long id, Authentication authentication) {
        service.delete(id, authentication);
    }
}

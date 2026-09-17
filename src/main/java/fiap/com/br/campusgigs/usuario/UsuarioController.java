package fiap.com.br.campusgigs.usuario;

import fiap.com.br.campusgigs.usuario.dto.UsuarioRequest;
import fiap.com.br.campusgigs.usuario.dto.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/campusgigs/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public List<UsuarioResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse add(@RequestBody @Valid UsuarioRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public UsuarioResponse update(@PathVariable Long id, @RequestBody @Valid UsuarioRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package fiap.com.br.campusgigs.usuario;

import fiap.com.br.campusgigs.usuario.dto.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public List<Usuario> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Usuario add(@RequestBody UsuarioRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable Long id) {
        service.delete(id);
    }
}

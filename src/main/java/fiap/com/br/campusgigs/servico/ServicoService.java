package fiap.com.br.campusgigs.servico;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.dto.ServicoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository repository;

    public List<Servico> findAll() {
        return repository.findAll();
    }

    public Servico findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")
        );
    }

    public Servico save(ServicoRequest request) {
        return repository.save(request.toEntity());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

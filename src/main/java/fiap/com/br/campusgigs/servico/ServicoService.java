package fiap.com.br.campusgigs.servico;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository repository;

    public List<Servico> findAll() {
        return repository.findAll();
    }

    public Optional<Servico> findById(Long id) {
        return repository.findById(id);
    }
}

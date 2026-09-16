package fiap.com.br.campusgigs.contratacao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratacaoService {
    private final ContratacaoRepository repository;

    public List<Contratacao> findAll() {
        return repository.findAll();
    }

    public Optional<Contratacao> findById(Long id) {
        return repository.findById(id);
    }
}

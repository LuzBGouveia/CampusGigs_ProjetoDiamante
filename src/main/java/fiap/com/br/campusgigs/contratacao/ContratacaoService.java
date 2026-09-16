package fiap.com.br.campusgigs.contratacao;

import fiap.com.br.campusgigs.contratacao.Contratacao;
import fiap.com.br.campusgigs.contratacao.dto.ContratacaoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratacaoService {
    private final ContratacaoRepository repository;

    public List<Contratacao> findAll() {
        return repository.findAll();
    }

    public Contratacao findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado.")
        );
    }

    public Contratacao save(ContratacaoRequest request) {
        return repository.save(request.toEntity());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

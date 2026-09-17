package fiap.com.br.campusgigs.validations;

import fiap.com.br.campusgigs.contratacao.ContratacaoRepository;
import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.servico.SituacaoServico;
import fiap.com.br.campusgigs.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ContratacaoValidator {
    private final ContratacaoRepository repository;

    public void validate(Servico servico, Usuario usuarioLogado) {
        if (servico.getSituacao() != SituacaoServico.ATIVO) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Tentativa de contratar um serviço que não está ativo."
            );
        }

        if (servico.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Você não pode contratar o seu próprio serviço."
            );
        }

        if (repository.existsByServicoId(servico.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Este serviço já possui uma contratação registrada."
            );
        }
    }
}

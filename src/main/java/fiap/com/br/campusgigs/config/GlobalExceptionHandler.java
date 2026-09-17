package fiap.com.br.campusgigs.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Erro de validação nos campos informados."
        );
        problemDetail.setTitle("Requisição Inválida");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/bad-request"));
        problemDetail.setProperty("timestamp", Instant.now());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Valor inválido enviado para um dos campos (Enum) ou JSON mal formatado."
        );
        problemDetail.setTitle("Corpo da Requisição Inválido");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/bad-request"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                "E-mail ou registro já cadastrado no sistema."
        );
        problemDetail.setTitle("Conflito de Dados");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/conflict"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ProblemDetail handleRestClientResponseException(RestClientResponseException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "O serviço externo de CEP rejeitou a requisição. Verifique o formato do CEP."
        );
        problemDetail.setTitle("Erro na Consulta Externa de CEP");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/external-service-bad-request"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ProblemDetail handleResourceAccessException(ResourceAccessException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                "O serviço de consulta de CEP está indisponível ou demorou para responder."
        );
        problemDetail.setTitle("Serviço Externo Indisponível");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/external-service-timeout"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleResponseStatusException(ResponseStatusException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                ex.getStatusCode(),
                ex.getReason() != null ? ex.getReason() : "Erro no processamento da requisição."
        );
        problemDetail.setTitle(ex.getStatusCode().toString());
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/status-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                "Acesso negado: você não possui permissão para executar esta operação."
        );
        problemDetail.setTitle("Acesso Negado");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/forbidden"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED,
                "Falha na autenticação: credenciais inválidas ou token expirado."
        );
        problemDetail.setTitle("Não Autorizado");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/unauthorized"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno inesperado no servidor."
        );
        problemDetail.setTitle("Erro Interno");
        problemDetail.setType(URI.create("https://campusgigs.fiap.com.br/errors/internal-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}

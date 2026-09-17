package fiap.com.br.campusgigs.contratacao;

import fiap.com.br.campusgigs.servico.Servico;
import fiap.com.br.campusgigs.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contratacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "servico_id", unique = true, nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private SituacaoContratacao situacao;

    public Contratacao(Servico servico, Usuario usuario) {
        this.servico = servico;
        this.usuario = usuario;
        this.situacao = SituacaoContratacao.SOLICITADA;
    }
}

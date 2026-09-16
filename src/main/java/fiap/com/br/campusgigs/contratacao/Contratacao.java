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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Servico servico;

    @OneToOne(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;
}

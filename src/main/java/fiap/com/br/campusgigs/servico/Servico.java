package fiap.com.br.campusgigs.servico;

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
public class Servico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String categoria;
    private Double preco;

    @ManyToOne
    private Usuario usuario;

    private Situacao situacao;
}

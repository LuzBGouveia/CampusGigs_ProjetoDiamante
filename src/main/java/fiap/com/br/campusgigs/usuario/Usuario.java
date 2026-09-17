package fiap.com.br.campusgigs.usuario;

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
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String cep;
    private String cidade;
    private String uf;
    
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UsuarioRole role = UsuarioRole.USER;
}

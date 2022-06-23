package br.faccat.g2seguranca.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "trabalhoG2", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer code;
    private String name;
    private String email;
    private String telefone;
    private String enderecoWeb;
    @Column(name = "professional_exp")
    private String professionalExperience;

}

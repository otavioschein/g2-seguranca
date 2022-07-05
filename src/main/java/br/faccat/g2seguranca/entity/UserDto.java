package br.faccat.g2seguranca.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;

}

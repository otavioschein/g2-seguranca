package br.faccat.g2seguranca.mapper;

import br.faccat.g2seguranca.entity.User;
import br.faccat.g2seguranca.entity.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public List<UserDto> toUserDto(List<User> users) {
        return users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setName(user.getName());
                    userDto.setEmail(user.getEmail());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

}

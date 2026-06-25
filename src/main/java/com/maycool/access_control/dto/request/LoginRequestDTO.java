package com.maycool.access_control.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {
//    @NotEmpty(message = "O email é obrigatório")
//    @Email(message = "Formato de email inválido")
    private String username;
    private String password;


}

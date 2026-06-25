package com.maycool.access_control.dto.response;

import com.maycool.access_control.dto.request.LoginRequestDTO;
import com.maycool.access_control.entity.User;
import com.maycool.access_control.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;

}

package br.com.projback.projetoback.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty(message="Usuario nao pode ser vazio")
    private String username;

    @NotEmpty(message="Senha nao pode ser vazio")
    private String password;

    private int profile_id;
}

package br.com.projback.projetoback.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnableLojaRequest {
    @NotNull(message = "Habilitação não fornecida")
    private Boolean enabled;

    @NotBlank(message = "Campo nome de usuário responsável pela ativação não fornecido")
    private String userNameAtivacao;
}

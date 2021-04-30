package com.dio.springbootapipessoas.dto;

import com.dio.springbootapipessoas.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @NotEmpty
    @Size(min = 13, max = 14)
    private String numero;

}

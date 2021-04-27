package com.dio.springbootapipessoas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTelefone {

    FIXO("Fixo"),
    MOVEL("Movel"),
    COMERCIAL("Comercial");

    private String descricao;

}

package com.msvc.boleta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Schema (description = "Informacion de error cuando un dato es invalido")
public class ErrorDTO {

    private int status;
    private LocalDate localDate;
    private Map<String, String> errors;

    @Override
    public String toString(){
        return "{" +
                "status="+status+
                ",date=" +localDate+
                "errors= "+errors+
                "}";
    }

}

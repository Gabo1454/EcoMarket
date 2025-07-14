package com.msvc.cliente.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class ErrorDTO {
    private int status;
    private LocalDate localDate ;
    private Map<String, String> errors;

    @Override
    public String toString(){
        return "{" +
                "status="+status+
                "date=" + localDate +
                "errors= "+errors+
                "}";
    }
}

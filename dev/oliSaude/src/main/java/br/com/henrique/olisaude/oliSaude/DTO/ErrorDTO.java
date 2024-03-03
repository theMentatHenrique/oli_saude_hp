package br.com.henrique.olisaude.oliSaude.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    String message;
    private LocalDateTime timeStamp;
}

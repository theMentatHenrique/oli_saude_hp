package br.com.henrique.olisaude.oliSaude.Exception;

import br.com.henrique.olisaude.oliSaude.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler(ClientExistentException.class)
    public ResponseEntity<?> ClientAlreadyExistent() {
        ErrorDTO errorDTO = createErrorDTO("Client already existent.");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFound() {
        ErrorDTO errorDTO = createErrorDTO("Client not found.");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processValidationErrors(MethodArgumentNotValidException ex) {
        BindingResult bindResult = ex.getBindingResult();
        List<FieldError> list = bindResult.getFieldErrors();
        StringBuilder sb = new StringBuilder("Invalid values for label(s):");
        for(FieldError fieldError : list) {
            sb.append(" ");
            sb.append(fieldError.getField());
        }
        ErrorDTO errorDTO = createErrorDTO(sb.toString());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    private ErrorDTO createErrorDTO(String message) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);
        errorDTO.setTimeStamp(LocalDateTime.now());
        return  errorDTO;
    }
}

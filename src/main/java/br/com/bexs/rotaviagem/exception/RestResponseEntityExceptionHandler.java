package br.com.bexs.rotaviagem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ErroValidarRotaException.class})
    protected ResponseEntity<Object> erroValidarRota(RuntimeException ex, WebRequest request) {
        StringBuilder retorno = new StringBuilder();
        retorno.append("{\n\terro: ");
        retorno.append(ex.getMessage());
        retorno.append("\n}");
        return handleExceptionInternal(ex, retorno.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
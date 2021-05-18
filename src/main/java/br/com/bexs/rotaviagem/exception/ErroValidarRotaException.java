package br.com.bexs.rotaviagem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ErroValidarRotaException extends RuntimeException{
    public ErroValidarRotaException(String message){
        super(message);
    }
}

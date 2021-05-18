package br.com.bexs.rotaviagem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroAoCriarRotaException extends Exception {
    public ErroAoCriarRotaException(String message){
        super(message);
    }
}

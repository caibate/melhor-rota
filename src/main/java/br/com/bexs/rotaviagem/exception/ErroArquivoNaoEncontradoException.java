package br.com.bexs.rotaviagem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErroArquivoNaoEncontradoException extends Exception {
    public ErroArquivoNaoEncontradoException(String message){
        super(message);
    }
}

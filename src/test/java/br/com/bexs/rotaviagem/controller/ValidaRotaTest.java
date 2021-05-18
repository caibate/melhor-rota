package br.com.bexs.rotaviagem.controller;

import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.exception.ErroValidarRotaException;
import br.com.bexs.rotaviagem.validacao.RotaValidacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidaRotaTest {

    @Test
   public void validarSigla_Falha(){
        RotaValidacao rotaValidacao = new RotaValidacao();
        Throwable exception = Assertions.assertThrows(ErroValidarRotaException.class, () -> {
            rotaValidacao.validaSigla(new RotaDTO("SC", "RS", 1));
        });
        String msg = "Rota sigla fora do padrão";
        String msgException = exception.getMessage();
        assertEquals(msg, msgException);
    }

    @Test
    public void validarRotaDuplica_Falha(){
        RotaValidacao rotaValidacao = new RotaValidacao();
        Throwable exception = Assertions.assertThrows(ErroValidarRotaException.class, () -> {
            List<RotaDTO> listRotaDTO =new ArrayList();
            RotaDTO rotaDTO = new RotaDTO("BRA", "ARG", 1);
            listRotaDTO.add(rotaDTO);
            rotaValidacao.validaRotaDuplicada(rotaDTO, listRotaDTO);
        });
        String msg = "Rota nao pode ser duplicada";
        String msgException = exception.getMessage();
        assertEquals(msg, msgException);
    }

    @Test
    public void validarOrigemDestinoIgual_Falha(){
        RotaValidacao rotaValidacao = new RotaValidacao();
        Throwable exception = Assertions.assertThrows(ErroValidarRotaException.class, () -> {
            RotaDTO rotaDTO = new RotaDTO("GRU", "GRU", 1);
            rotaValidacao.validaOrigemDestinoIgual(rotaDTO);
        });
        String msg = "Origem e destino não pode ser igual";
        String msgException = exception.getMessage();
        assertEquals(msg, msgException);
    }

    @Test
    public void validarObjetoEmBranco_Falha(){
        RotaValidacao rotaValidacao = new RotaValidacao();
        Throwable exception = Assertions.assertThrows(ErroValidarRotaException.class, () -> {
            RotaDTO rotaDTO = null;
            rotaValidacao.validarIsEmpty(rotaDTO);
        });
        String msg = "Objeto esta vazio";
        String msgException = exception.getMessage();
        assertEquals(msg, msgException);
    }

    @Test
    public void validarLinhaDigitadaListaRotaDTO_Sucesso(){
        RotaValidacao rotaValidacao = new RotaValidacao();
        List<RotaDTO> listRotaDTO = new LinkedList<>();
        rotaValidacao.linhaCSVDTO(listRotaDTO, "GRU,CDG,1");
        RotaDTO rotaDTO = new RotaDTO("GRU", "CDG", 1);
        assertEquals(rotaDTO, listRotaDTO.get(0));
    }

    @Test
    public void validarLinhaDigitadaRotaDTO_Sucesso(){
        RotaValidacao rotaValidacao = new RotaValidacao();
        RotaDTO rotaCriada = rotaValidacao.linhaDigitada("GRU-CDG-1");
        RotaDTO rotaDTO = new RotaDTO("GRU", "CDG", null);
        assertEquals(rotaDTO, rotaCriada);
    }
}

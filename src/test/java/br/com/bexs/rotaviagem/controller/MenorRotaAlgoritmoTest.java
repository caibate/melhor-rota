package br.com.bexs.rotaviagem.controller;

import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.exception.ErroValidarRotaException;
import br.com.bexs.rotaviagem.negocio.AlgoritmoMenorRota;
import br.com.bexs.rotaviagem.negocio.Nodo;
import br.com.bexs.rotaviagem.validacao.RotaValidacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenorRotaAlgoritmoTest {

    @Test
   public void menorRotaAlgoritmo_Falha() {
        AlgoritmoMenorRota menorRota = new AlgoritmoMenorRota();
        Nodo nodo = new Nodo("Teste");

        Throwable exception = Assertions.assertThrows(ErroValidarRotaException.class, () -> {
            RotaDTO rotaDTO = null;
            menorRota.calculo(nodo, "XYZ");
        });
        String msg = "Destino pesquisado nao existe";
        String msgException = exception.getMessage();
        assertEquals(msg, msgException);
    }

    @Test
    public void menorRotaAlgoritmo_Sucesso() {
        AlgoritmoMenorRota menorRota = new AlgoritmoMenorRota();
        Nodo nodoGRU = new Nodo("GRU");
        Nodo nodoBRC = new Nodo("BRC");
        Nodo nodoSCL = new Nodo("SCL");
        Nodo nodoORL = new Nodo("ORL");
        Nodo nodoCDG = new Nodo("CDG");

        nodoGRU.adicionarDestino(nodoBRC, 10);
        nodoBRC.adicionarDestino(nodoSCL, 5);
        nodoGRU.adicionarDestino(nodoCDG, 75);
        nodoGRU.adicionarDestino(nodoSCL, 20);
        nodoGRU.adicionarDestino(nodoORL, 56);
        nodoORL.adicionarDestino(nodoCDG, 5);
        nodoSCL.adicionarDestino(nodoORL, 20);

          String retorno =  menorRota.calculo(nodoGRU, "CDG");

        String msg = "GRU-BRC-SCL-ORL-CDG -> 40";
        assertEquals(msg, retorno);
    }
}

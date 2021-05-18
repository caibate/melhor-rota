package br.com.bexs.rotaviagem.controller;

import br.com.bexs.rotaviagem.componente.ArquivoCSV;
import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.exception.ErroValidarRotaException;
import br.com.bexs.rotaviagem.validacao.RotaValidacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArquivoCSVTest {

    @Test
   public void deveLerCsv_Falha(){
        ArquivoCSV arquivoCSV = new ArquivoCSV();
        List<RotaDTO> listRotaDTO = new LinkedList<>();
        String localArquivo = "Qualquer";
        arquivoCSV.ler(localArquivo, listRotaDTO);
        assertTrue(listRotaDTO.isEmpty());
    }

    @Test
    public void deveEscreverCsv_Sucesso(){
        ArquivoCSV arquivoCSV = new ArquivoCSV();
        String localArquivo = "/home/caiba/IdeaProjects/rota-viagem/rotas.csv";
        boolean gravou = arquivoCSV.escrever(localArquivo, new RotaDTO("GRU","CHI",11));
        assertTrue(gravou);
    }
}

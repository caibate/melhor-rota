package br.com.bexs.rotaviagem.controller;

import br.com.bexs.rotaviagem.dto.RotaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, args = "--arquivo=/home/caiba/IdeaProjects/rota-viagem/routes.csv")
public class MelhorRotaControlerTest {

    @Autowired
    private MelhorRotaController melhorRotaController;

    @Test
    public void buscarMelhorRota(){
        //TODO implementar com rest assured
    }

    @Test
    public void inclurNovaRota(){
        //TODO implementar com rest assured
    }

}

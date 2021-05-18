package br.com.bexs.rotaviagem.recursos;

import br.com.bexs.rotaviagem.controller.MelhorRotaController;
import br.com.bexs.rotaviagem.dto.RotaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/rota")
public class RotaViagemResource {
    private static final Logger log = LoggerFactory.getLogger(RotaViagemResource.class);

    @Autowired
    private MelhorRotaController melhorRotaController;

    @GetMapping
    public ResponseEntity buscarRota(@RequestParam String origem,
                                     @RequestParam String destino){
        RotaDTO rotaDTO = new RotaDTO();
        rotaDTO.setOrigem(origem);
        rotaDTO.setDestino(destino);
        String result = melhorRotaController.pesquisarRota(rotaDTO);
       return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity incluirRota(@RequestBody RotaDTO rotaDTO){
        melhorRotaController.escrever(rotaDTO);
        return ResponseEntity.ok(rotaDTO);
    }
}

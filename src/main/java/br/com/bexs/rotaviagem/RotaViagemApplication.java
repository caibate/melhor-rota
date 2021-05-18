package br.com.bexs.rotaviagem;

import br.com.bexs.rotaviagem.controller.MelhorRotaController;
import br.com.bexs.rotaviagem.dto.RotaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RotaViagemApplication implements ApplicationRunner{

    @Value("${arquivo}")
    private String arquivo;
    @Autowired
    private MelhorRotaController melhorRotaController;

    public static void main(String[] args)  {
        SpringApplication.run(RotaViagemApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        melhorRotaController.run(arquivo);
        List<RotaDTO> list =melhorRotaController.getListRotasDTO();
    }

}

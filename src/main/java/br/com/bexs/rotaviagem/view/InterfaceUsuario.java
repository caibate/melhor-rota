package br.com.bexs.rotaviagem.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class InterfaceUsuario {
    private static final Logger log = LoggerFactory.getLogger(InterfaceUsuario.class);

    public String perguntaRota() {
        String rotaPesquisada = "";
        while (rotaPesquisada.isBlank()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nPor favor entre com a rota: ");
            rotaPesquisada =scanner.nextLine();
        }
        return rotaPesquisada;
    }

    public void resultado(String resultado) {
        System.out.print("\nMelhor rota: " + resultado);
    }
}


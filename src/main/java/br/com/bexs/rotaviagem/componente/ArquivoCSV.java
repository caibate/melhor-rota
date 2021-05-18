package br.com.bexs.rotaviagem.componente;

import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.validacao.RotaValidacao;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Component
@Data
public class ArquivoCSV {
    private static final Logger log = LoggerFactory.getLogger(ArquivoCSV.class);
    @Autowired
    private RotaValidacao rotaValidacao;

    //Ler arquivo csv
    public void ler(String localArquivo, List<RotaDTO> listRotaDTO) {
        try{
            log.info(localArquivo);
            BufferedReader csvReader = new BufferedReader(new FileReader(localArquivo));
            String linha = "";
            while (true){
                linha = csvReader.readLine();
                if(Optional.ofNullable(linha).isPresent()) {
                    log.info("Linha: " +linha);
                    rotaValidacao.linhaCSVDTO(listRotaDTO, linha);
                } else
                    break;
            }
            csvReader.close();
        } catch (Exception e) {
            log.error("O arquivo não existe ou é invalido.");

        }
    }
    //escrever no arquivo csv
    public static boolean escrever(String localArquivo, RotaDTO rotaDTO){
        try {
            FileWriter csvWriter = new FileWriter(localArquivo, true);
            csvWriter.append("\n");
            csvWriter.append( rotaDTO.getOrigem());
            csvWriter.append(",");
            csvWriter.append(rotaDTO.getDestino());
            csvWriter.append(",");
            csvWriter.append( rotaDTO.getValor().toString());
            csvWriter.close();
            return true;
        }catch (Exception e){
            log.error("Não foi possivel salvar a rota no arquivo");
            return false;
        }
    }
}
package br.com.bexs.rotaviagem.controller;

import br.com.bexs.rotaviagem.negocio.Grafo;
import br.com.bexs.rotaviagem.componente.ArquivoCSV;
import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.validacao.RotaValidacao;
import br.com.bexs.rotaviagem.view.InterfaceUsuario;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Service
public class MelhorRotaController {
    private static final Logger log = LoggerFactory.getLogger(MelhorRotaController.class);
    @Autowired
    private ArquivoCSV arquivoCSV;
    private String localArquivo;
    private List<RotaDTO> listRotasDTO = new ArrayList();
    @Autowired
    private RotaValidacao rotaValidacao;
    @Autowired
    private Grafo grafo;

    //Le csv, inicia iteracao por cmd
    public void run(String localarquivo) {
        setLocalArquivo(localarquivo);
        lerArquivo();
        pesquisarRota();
    }

    //ler o csv
    protected void lerArquivo(){
        arquivoCSV.ler(localArquivo, getListRotasDTO());
    }

    protected String calculaMelhorRota(RotaDTO rotaDTO) {
        return grafo.calcular(rotaDTO.getOrigem(), rotaDTO.getDestino(), getListRotasDTO());
    }
    @Autowired
    private InterfaceUsuario ui;

    //scanner cmd
    private void pesquisarRota(){
        RotaDTO rotaDTO = validaRotaDigitada(ui.perguntaRota());
        try {
            resultado(calculaMelhorRota(rotaDTO));
        }catch (Exception exception){
            log.error(exception.getMessage());
            pesquisarRota();
        }

    }
    //exibe o resultado e ativa scanner
    private void resultado(String resultado) {
        ui.resultado(resultado);
        pesquisarRota();
    }

    //valida rota digitada
    private RotaDTO validaRotaDigitada(String rotaDigitada) {
        RotaDTO rotaDTO = null;
        try {
            log.debug("Valida rota: " + rotaDigitada);
            rotaDTO = getRotaValidacao().linhaDigitada(rotaDigitada);
        }catch (Exception e){
            System.out.println("Rota pesquisada invalida");
            pesquisarRota();
        }
        return rotaDTO;
    }

    public String pesquisarRota(RotaDTO rotaDTO){
        validarRotaDesejada(rotaDTO);
        return calculaMelhorRota(rotaDTO);
    }

    private void validarRotaDesejada(RotaDTO rotaDTO) {
        getRotaValidacao().validarRotaPesquisada(rotaDTO);
    }

    public void escrever(RotaDTO rotaDTO){

        getRotaValidacao().validar(rotaDTO, this.getListRotasDTO());
        getArquivoCSV().escrever(getLocalArquivo(), rotaDTO);
        getListRotasDTO().add(rotaDTO);
    }
}
package br.com.bexs.rotaviagem.validacao;

import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.exception.ErroValidarRotaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RotaValidacao {
    private static final Logger log = LoggerFactory.getLogger(RotaValidacao.class);

    public void validar(RotaDTO rotaDTO, List<RotaDTO> listRotaDTO) {
        validarIsEmpty(rotaDTO);
        validarIsBlank(rotaDTO);
        validaOrigemDestinoIgual(rotaDTO);
        validaRotaDuplicada(rotaDTO, listRotaDTO);
        validaSigla(rotaDTO);
    }

    public void linhaCSVDTO(List<RotaDTO> listRotaDTO, String linha) {
        RotaDTO rotaDTO = null;
        try {
            String linhas[];
            linhas = linha.split(",");
            rotaDTO = new RotaDTO(linhas[0], linhas[1], Integer.parseInt(linhas[2]));
            validar(rotaDTO, listRotaDTO);
            listRotaDTO.add(rotaDTO);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    public RotaDTO linhaDigitada(String linha) {
        RotaDTO rotaDTO;
        String linhas[];
        linhas = linha.split("-");
        rotaDTO = new RotaDTO();
        rotaDTO.setOrigem(linhas[0]);
        rotaDTO.setDestino(linhas[1]);

        validarRotaPesquisada(rotaDTO);
        return rotaDTO;
    }

    public void validarRotaPesquisada(RotaDTO rotaDTO) {
        validarOrigemDestinoIsEmpty(rotaDTO);
        validarIsBlank(rotaDTO);
        validaOrigemDestinoIgual(rotaDTO);
        validaSigla(rotaDTO);
    }

    public void validarIsEmpty(RotaDTO rota) {
        if (Optional.ofNullable(rota).isEmpty()
                || Optional.ofNullable(rota.getOrigem()).isEmpty()
                || Optional.ofNullable(rota.getDestino()).isEmpty()
                || Optional.ofNullable(rota.getValor()).isEmpty())
            throw new ErroValidarRotaException("Objeto esta vazio");
    }

    public void validarOrigemDestinoIsEmpty(RotaDTO rotaDTO) {
        if (Optional.ofNullable(rotaDTO).isEmpty()
        || Optional.ofNullable(rotaDTO.getOrigem()).isEmpty()
        || Optional.ofNullable(rotaDTO.getDestino()).isEmpty())
            throw new ErroValidarRotaException("Objeto nao pode ser vazio");
    }

    public void validarIsBlank(RotaDTO rota) {
        if (rota.getOrigem().isBlank()
                || rota.getDestino().isBlank())
            throw new ErroValidarRotaException("Objeto esta em branco");
    }

    public void validaOrigemDestinoIgual(RotaDTO rotaDTO) {
        if ((rotaDTO.getOrigem()).equalsIgnoreCase(rotaDTO.getDestino()))
        throw new ErroValidarRotaException("Origem e destino não pode ser igual");
    }

    public void validaRotaDuplicada(RotaDTO rotaDTO, List<RotaDTO> listRotaDTO) {
        if (listRotaDTO.contains(rotaDTO))
        throw new ErroValidarRotaException("Rota nao pode ser duplicada");
    }

    public void validaSigla(RotaDTO rotaDTO) {
        if (rotaDTO.getOrigem().length() !=3
        ||  !rotaDTO.getOrigem().matches( "^[a-zA-Z]*$")
        ||  rotaDTO.getDestino().length() !=3
        || !rotaDTO.getDestino().matches( "^[a-zA-Z]*$"))
            throw new ErroValidarRotaException("Rota sigla fora do padrão");
    }

}
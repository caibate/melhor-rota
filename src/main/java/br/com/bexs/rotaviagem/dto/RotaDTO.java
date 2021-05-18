package br.com.bexs.rotaviagem.dto;

import br.com.bexs.rotaviagem.exception.ErroAoCriarRotaException;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RotaDTO {
    private String origem;
    private String destino;
    private Integer valor;
}
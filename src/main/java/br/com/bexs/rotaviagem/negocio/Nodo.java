package br.com.bexs.rotaviagem.negocio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter @Setter @EqualsAndHashCode
public class Nodo {
    private String nome;
    @EqualsAndHashCode.Exclude
    private List<Nodo> caminhoCurto = new LinkedList<>();
    @EqualsAndHashCode.Exclude
    private Integer distancia = Integer.MAX_VALUE;
    @EqualsAndHashCode.Exclude
    Map<Nodo, Integer> nodosAdjacentes = new HashMap<>();

    public Nodo(String nome){
        this.nome = nome;
    }

    public void adicionarDestino(Nodo destino, Integer distancia){
        nodosAdjacentes.put(destino, distancia);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        getCaminhoCurto().forEach(c-> {builder.append(c.getNome() + "-");});
        return builder.append(getNome()+ " -> "+getDistancia()).toString();
    }
}

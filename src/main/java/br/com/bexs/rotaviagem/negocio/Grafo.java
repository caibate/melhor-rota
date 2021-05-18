package br.com.bexs.rotaviagem.negocio;

import br.com.bexs.rotaviagem.dto.RotaDTO;
import br.com.bexs.rotaviagem.exception.ErroValidarRotaException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Getter @Setter
public class Grafo {
    private Set<Nodo> nodos;

    @Autowired
    private AlgoritmoMenorRota algoritmoMenorRota;

    public String calcular(String nmOrigem, String nmDestino, List<RotaDTO> listRotasDTO){
        nodos = new HashSet<>();
        adicionarNodos(listRotasDTO);
        adicionarNodosAdjacentes(listRotasDTO);
        Nodo nodoOrigem = validaOrigemDestino(nmOrigem, nmDestino);
        return AlgoritmoMenorRota.calculo(nodoOrigem, nmDestino);
    }

    private void adicionarNodosAdjacentes(List<RotaDTO> listRotasDTO) {
        //percorre todos nodos
        for(Nodo nodo: nodos){
            //filtra por origem
            List<RotaDTO> listRotaFiltrada = (List) listRotasDTO.stream().filter(r-> r.getOrigem().equalsIgnoreCase(nodo.getNome())).
                    collect(Collectors.toList());
            //adiciona adjacente
            listRotaFiltrada.forEach( lf ->{
                   Optional<Nodo> nd = nodos.stream().filter(n->n.getNome().equalsIgnoreCase(lf.getDestino())).findAny();
                   nodo.adicionarDestino(nd.get(), lf.getValor());
            });
        }
    }

    private void adicionarNodos(List<RotaDTO> listRotasDTO) {
        //Adiciona cada rota um nodo origem e destino
        for(RotaDTO rotaDTO: listRotasDTO) {
            Nodo nOrigem = new Nodo(rotaDTO.getOrigem());
            Nodo nDestino = new Nodo(rotaDTO.getDestino());
            nodos.add(nOrigem);
            nodos.add(nDestino);
        }
    }

    private Nodo validaOrigemDestino(String nmOrigem, String nmDestino) {

        ///valida se existe o nodo destino pesquisado
        Optional<Nodo> nodoDestino = nodos.stream().filter(nodo -> nodo.getNome().equalsIgnoreCase(nmDestino)).findAny();
        if (nodoDestino.isEmpty())
            throw new ErroValidarRotaException("Destino pesquisada nao existe");

        ///valida se existe o nodo origem pesquisado
        Optional<Nodo> nodoOrigem = nodos.stream().filter(nodo -> nodo.getNome().equalsIgnoreCase(nmOrigem)).findAny();
        if (nodoOrigem.isEmpty())
            throw new ErroValidarRotaException("Origem pesquisada nao existe");
        return nodoOrigem.get();
    }
}

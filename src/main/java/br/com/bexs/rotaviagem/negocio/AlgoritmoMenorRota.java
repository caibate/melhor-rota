package br.com.bexs.rotaviagem.negocio;

import br.com.bexs.rotaviagem.exception.ErroValidarRotaException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AlgoritmoMenorRota {

    public static String calculo(Nodo origem, String nmDestino){
        //distancia da origem sempre 0
        origem.setDistancia(0);

        //criar Set de nodoResolvidos/naoresolvidos
        Set<Nodo> nodoVisitado = new HashSet<>();
        Set<Nodo> nodoNaoVisitado = new HashSet<>();

        //insere origem como nao resolvido
        nodoNaoVisitado.add(origem);

        while (nodoNaoVisitado.size() != 0){
            //nodo de menor distancia vira nodo corrente.
            Nodo nodoCorrente = menorDistancia(nodoNaoVisitado);
            nodoNaoVisitado.remove(nodoCorrente);

            for(Map.Entry < Nodo, Integer> parAdjacente: nodoCorrente.getNodosAdjacentes().entrySet()){
                Nodo nodoAdjacente = parAdjacente.getKey();
                Integer custo = parAdjacente.getValue();

                if(!nodoVisitado.contains(nodoAdjacente)){
                    calcularMenorDistancia(nodoAdjacente, custo, nodoCorrente);
                    nodoNaoVisitado.add(nodoAdjacente);
                }
            }
            nodoVisitado.add(nodoCorrente);
        }
        Optional<Nodo> destino=nodoVisitado.stream().filter(nodo -> nodo.getNome().equalsIgnoreCase(nmDestino)).findAny();
        if(destino.isEmpty())
            throw new ErroValidarRotaException("Destino pesquisado nao existe");
        return destino.get().toString();
    }

    private static void calcularMenorDistancia(Nodo nodoAdjacente, Integer custo, Nodo nodoCorrente) {
        Integer distanciaCorrente = nodoCorrente.getDistancia();
        if(distanciaCorrente + custo < nodoAdjacente.getDistancia()){
            nodoAdjacente.setDistancia(distanciaCorrente + custo);
            LinkedList<Nodo>  listNodoCaminhoMaisCurto = new LinkedList<>(nodoCorrente.getCaminhoCurto());
            listNodoCaminhoMaisCurto.add(nodoCorrente);
            nodoAdjacente.setCaminhoCurto(listNodoCaminhoMaisCurto);
        }
    }

    /**
     * Passa lista de nodos nao resolvidos retorna nodo mais proximo.
     *
     * @param nodosNaoResolvidos
     * @return
     */
    private static Nodo menorDistancia(Set<Nodo> nodosNaoResolvidos) {

        Nodo nodoMaisProximo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for(Nodo nodo: nodosNaoResolvidos){
            int dNodo = nodo.getDistancia();
            if(dNodo < menorDistancia){
                menorDistancia = dNodo;
                nodoMaisProximo =nodo;
            }
        }
        return nodoMaisProximo;
    }
}

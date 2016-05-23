package br.com.carmaix.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 20/05/16.
 */
public class AvaliacaoService {

    public static List<Avaliacao> returnListAvaliacao(String tipo) {

        ArrayList<Avaliacao> avaliacaoArrayList = new ArrayList<>();
        for(int i = 0; i <=20; i ++) {
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setId(i);
            avaliacao.setNome(tipo+" - Nome: João - ID: "+i);
            avaliacaoArrayList.add(avaliacao);
        }
        return  avaliacaoArrayList;
    }

}

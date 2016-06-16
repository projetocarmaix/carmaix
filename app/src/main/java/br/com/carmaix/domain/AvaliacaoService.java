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
            avaliacao.setAvaliacaoId(Integer.toString(i));
            avaliacao.setAvaliacaoNome("João"+i);
            avaliacao.setAvaliacaoAno("1984");
            avaliacao.setAvaliacaoAvaliacao("R$ 120.000,00");
            avaliacao.setAvaliacaoClasse("A");
            avaliacao.setAvaliacaoMarca("Ford");
            avaliacao.setAvaliacaoData("11/05/2014");
            avaliacao.setAvaliacaoModelo("fiesta");
            avaliacao.setAvaliacaoPlaca("JJK-1321");
            avaliacaoArrayList.add(avaliacao);
        }
        return  avaliacaoArrayList;
    }

}

package br.com.carmaix.spinnerStaticValues;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.carmaix.utils.Utils;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 18/07/16.
 */
public class SpinnerStaticValues {

    public static ArrayList<ValueLabelDefault> listPortas(Context context){
        ArrayList<ValueLabelDefault> listPortas = Utils.createArrayDefault(new PortasReturn(context));

        PortasReturn[] portas = {new PortasReturn(),new PortasReturn(),new PortasReturn()};
        portas[0].setId("");
        portas[0].setDescricao("2");
        portas[1].setId("");
        portas[1].setDescricao("3");
        portas[2].setId("");
        portas[2].setDescricao("4");

        listPortas.addAll(Arrays.asList(portas));

        return listPortas;
    }

    public static ArrayList<ValueLabelDefault> listClassificacao(Context context){
        ArrayList<ValueLabelDefault> listClassificacao = Utils.createArrayDefault(new ClassificacaoReturn(context));

        ClassificacaoReturn[] classificacaoReturns = {new ClassificacaoReturn(), new ClassificacaoReturn(), new ClassificacaoReturn(), new ClassificacaoReturn()};

        classificacaoReturns[0].setId("");
        classificacaoReturns[0].setDescricao("AA");
        classificacaoReturns[1].setId("");
        classificacaoReturns[1].setDescricao("A");
        classificacaoReturns[2].setId("");
        classificacaoReturns[2].setDescricao("B");
        classificacaoReturns[3].setId("");
        classificacaoReturns[3].setDescricao("C");
        listClassificacao.addAll(Arrays.asList(classificacaoReturns));
        return listClassificacao;
    }
}

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
        portas[0].setId("2");
        portas[0].setDescricao("2");
        portas[1].setId("3");
        portas[1].setDescricao("3");
        portas[2].setId("4");
        portas[2].setDescricao("4");

        listPortas.addAll(Arrays.asList(portas));

        return listPortas;
    }

    public static ArrayList<ValueLabelDefault> listAcessorios(Context context){
        ArrayList<ValueLabelDefault> listAcessorios = Utils.createArrayDefault(new AcessorioReturn(context));

        AcessorioReturn[] acessorioReturns = {new AcessorioReturn(), new AcessorioReturn()};

        acessorioReturns[0].setId("Básico");
        acessorioReturns[0].setDescricao("Básico");
        acessorioReturns[1].setId("Completo");
        acessorioReturns[1].setDescricao("Completo");

        listAcessorios.addAll(Arrays.asList(acessorioReturns));
        return listAcessorios;
    }

    public static ArrayList<ValueLabelDefault> listNota(Context context){
        ArrayList<ValueLabelDefault> listNota = Utils.createArrayDefault(new NotaReturn(context));

        NotaReturn[] notaReturns = new NotaReturn[11];

        for(Integer i = 0; i<11; i++) {
            notaReturns[i] = new NotaReturn();
            notaReturns[i].setId(i.toString());
            notaReturns[i].setDescricao(i.toString());
        }


        listNota.addAll(Arrays.asList(notaReturns));
        return listNota;
    }

    public static ArrayList<ValueLabelDefault> listUf(Context context){
        ArrayList<ValueLabelDefault> listUf = Utils.createArrayDefault(new UfReturn(context));

        UfReturn[] ufReturns = new UfReturn[27];
        ufReturns[0] = new UfReturn();
        ufReturns[0].setId("1");
        ufReturns[0].setDescricao("AC");

        ufReturns[1] = new UfReturn();
        ufReturns[1].setId("2");
        ufReturns[1].setDescricao("AL");

        ufReturns[2] = new UfReturn();
        ufReturns[2].setId("4");
        ufReturns[2].setDescricao("AM");

        ufReturns[3] = new UfReturn();
        ufReturns[3].setId("3");
        ufReturns[3].setDescricao("AP");

        ufReturns[4] = new UfReturn();
        ufReturns[4].setId("5");
        ufReturns[4].setDescricao("BA");

        ufReturns[5] = new UfReturn();
        ufReturns[5].setId("6");
        ufReturns[5].setDescricao("CE");

        ufReturns[6] = new UfReturn();
        ufReturns[6].setId("7");
        ufReturns[6].setDescricao("DF");

        ufReturns[7] = new UfReturn();
        ufReturns[7].setId("8");
        ufReturns[7].setDescricao("ES");

        ufReturns[8] = new UfReturn();
        ufReturns[8].setId("9");
        ufReturns[8].setDescricao("GO");

        ufReturns[9] = new UfReturn();
        ufReturns[9].setId("10");
        ufReturns[9].setDescricao("MA");

        ufReturns[10] = new UfReturn();
        ufReturns[10].setId("13");
        ufReturns[10].setDescricao("MG");

        ufReturns[11] = new UfReturn();
        ufReturns[11].setId("12");
        ufReturns[11].setDescricao("MS");

        ufReturns[12] = new UfReturn();
        ufReturns[12].setId("11");
        ufReturns[12].setDescricao("MT");

        ufReturns[13] = new UfReturn();
        ufReturns[13].setId("14");
        ufReturns[13].setDescricao("PA");

        ufReturns[14] = new UfReturn();
        ufReturns[14].setId("15");
        ufReturns[14].setDescricao("PB");

        ufReturns[15] = new UfReturn();
        ufReturns[15].setId("17");
        ufReturns[15].setDescricao("PE");

        ufReturns[16] = new UfReturn();
        ufReturns[16].setId("18");
        ufReturns[16].setDescricao("PI");

        ufReturns[17] = new UfReturn();
        ufReturns[17].setId("16");
        ufReturns[17].setDescricao("PR");

        ufReturns[18] = new UfReturn();
        ufReturns[18].setId("19");
        ufReturns[18].setDescricao("RJ");

        ufReturns[19] = new UfReturn();
        ufReturns[19].setId("20");
        ufReturns[19].setDescricao("RN");

        ufReturns[20] = new UfReturn();
        ufReturns[20].setId("22");
        ufReturns[20].setDescricao("RO");

        ufReturns[21] = new UfReturn();
        ufReturns[21].setId("23");
        ufReturns[21].setDescricao("RR");

        ufReturns[22] = new UfReturn();
        ufReturns[22].setId("21");
        ufReturns[22].setDescricao("RS");

        ufReturns[23] = new UfReturn();
        ufReturns[23].setId("24");
        ufReturns[23].setDescricao("SC");

        ufReturns[24] = new UfReturn();
        ufReturns[24].setId("26");
        ufReturns[24].setDescricao("SE");

        ufReturns[25] = new UfReturn();
        ufReturns[25].setId("25");
        ufReturns[25].setDescricao("SP");

        ufReturns[26] = new UfReturn();
        ufReturns[26].setId("27");
        ufReturns[26].setDescricao("TO");


        listUf.addAll(Arrays.asList(ufReturns));
        return listUf;
    }
}

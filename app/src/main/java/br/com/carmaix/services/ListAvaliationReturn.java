package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 10/06/2016.
 */
public class ListAvaliationReturn {

    @SerializedName("data")
    private ArrayList<AvaliationReturn> avaliationReturns;

    public ArrayList<AvaliationReturn> getAvaliationReturns() {
        return avaliationReturns;
    }

    public void setAvaliationReturns(ArrayList<AvaliationReturn> avaliationReturns) {
        this.avaliationReturns = avaliationReturns;
    }
}

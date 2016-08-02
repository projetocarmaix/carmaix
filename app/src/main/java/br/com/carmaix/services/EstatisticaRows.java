package br.com.carmaix.services;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 01/08/16.
 */
public class EstatisticaRows {
    @SerializedName("quant")
    @Nullable
    private EstatisticaColumns quant;

    @SerializedName("max")
    @Nullable
    private EstatisticaColumns max;

    @SerializedName("med")
    @Nullable
    private EstatisticaColumns med;

    @SerializedName("min")
    @Nullable
    private EstatisticaColumns min;

    @SerializedName("dias")
    @Nullable
    private EstatisticaColumns dias;

    @SerializedName("rep")
    @Nullable
    private EstatisticaColumns rep;


    @Nullable
    public EstatisticaColumns getQuant() {
        return quant;
    }

    public void setQuant(@Nullable EstatisticaColumns quant) {
        this.quant = quant;
    }

    @Nullable
    public EstatisticaColumns getMax() {
        return max;
    }

    public void setMax(@Nullable EstatisticaColumns max) {
        this.max = max;
    }

    @Nullable
    public EstatisticaColumns getMed() {
        return med;
    }

    public void setMed(@Nullable EstatisticaColumns med) {
        this.med = med;
    }

    @Nullable
    public EstatisticaColumns getMin() {
        return min;
    }

    public void setMin(@Nullable EstatisticaColumns min) {
        this.min = min;
    }

    @Nullable
    public EstatisticaColumns getDias() {
        return dias;
    }

    public void setDias(@Nullable EstatisticaColumns dias) {
        this.dias = dias;
    }

    @Nullable
    public EstatisticaColumns getRep() {
        return rep;
    }

    public void setRep(@Nullable EstatisticaColumns rep) {
        this.rep = rep;
    }
}

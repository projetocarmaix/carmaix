package br.com.carmaix.services;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 19/06/16.
 */
public class ValorMedioReturn {
    @SerializedName("num")
    private String num;
    @SerializedName("max")
    private String max;
    @SerializedName("med")
    private String med;
    @SerializedName("min")
    private String min;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }
}

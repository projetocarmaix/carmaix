package br.com.carmaix.services;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 01/08/16.
 */
public class FipePassecarrosReturn {
    @SerializedName("num")
    @Nullable
    private String quant;

    @SerializedName("max")
    @Nullable
    private String max;

    @SerializedName("med")
    @Nullable
    private String med;

    @SerializedName("min")
    @Nullable
    private String min;

    @Nullable
    public String getQuant() {
        return quant;
    }

    public void setQuant(@Nullable String quant) {
        this.quant = quant;
    }

    @Nullable
    public String getMax() {
        return max;
    }

    public void setMax(@Nullable String max) {
        this.max = max;
    }

    @Nullable
    public String getMed() {
        return med;
    }

    public void setMed(@Nullable String med) {
        this.med = med;
    }

    @Nullable
    public String getMin() {
        return min;
    }

    public void setMin(@Nullable String min) {
        this.min = min;
    }
}

package br.com.carmaix.utils;

import java.util.ArrayList;

/**
 * Created by fernando on 17/07/16.
 */
public class BinderSpinner {
    public String value;
    public String label;

    public BinderSpinner(String value, String label) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

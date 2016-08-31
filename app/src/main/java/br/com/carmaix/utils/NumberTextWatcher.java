package br.com.carmaix.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;

/**
 * Created by fernando on 30/08/16.
 */
public class NumberTextWatcher implements TextWatcher {

    private boolean isUpdating;
    private NumberFormat nf = NumberFormat.getCurrencyInstance();
    private EditText campo;
    public NumberTextWatcher(EditText editText) {
        campo = editText;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {
        if (isUpdating) {
            isUpdating = false;
            return;
        }
        isUpdating = true;
        String str = s.toString();

        boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str.indexOf(".") > -1 || str.indexOf(",") > -1));

        if (hasMask) {
            str = str.replaceAll("[R$]", "").replaceAll("[,]", "").replaceAll("[.]", "");
        }

        try {
            str = nf.format(Double.parseDouble(str) / 100);
            campo.setText(str);
            campo.setSelection(campo.getText().length());
        } catch (NumberFormatException e) {
            s = "";
        }
    }


    @Override
    public void beforeTextChanged(CharSequence cs, int start, int count, int after) {
        // Não iremos utilizar...
    }

    @Override
    public void afterTextChanged(Editable e) {
        // Não iremos utilizar...
    }

}




package br.com.carmaix.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by fernando on 21/08/16.
 */
public class MaskValidations implements TextWatcher{
    private EditText editText;
    private Context context;
    private boolean isUpdating;
    private String value;
    public MaskValidations(EditText editText, Context context)  {
        this.editText = editText;
        this.context = context;

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (isUpdating) {
            isUpdating = false;
            return;
        }
        isUpdating = true;

        if(charSequence.toString().substring(0,2).contains("123456789")){
            for(int j =0; j < charSequence.length(); j++) {
                if("1234567890".contains(String.valueOf(charSequence.charAt(j)))) {
                    value = charSequence.toString().replace(String.valueOf(charSequence.charAt(j)),"");
                }
            }
        }

        this.editText.setText(value);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}

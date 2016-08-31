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
    private boolean isUpdating= true;
    private String value;
    public MaskValidations(EditText editText, Context context)  {
        this.editText = editText;
        this.context = context;

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

    }



    @Override
    public void afterTextChanged(Editable editable) {

    }
}

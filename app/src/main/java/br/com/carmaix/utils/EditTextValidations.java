package br.com.carmaix.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by fernando on 21/08/16.
 */
public class EditTextValidations implements TextWatcher{
    private EditText editText;
    private Context context;

    public EditTextValidations(EditText editText, Context context)  {
        this.editText = editText;
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            String numberTyped =  editable.toString();
            String numbers = "0123456789";
            if(!numberTyped.isEmpty()) {
                editText.removeTextChangedListener(this);
                String numberTypedFormated = "";
                numberTyped = numberTyped.replace(",", "");
                numberTyped = numberTyped.replace(".", "");

                int length = numberTyped.length();

                if (length == 1) {
                    numberTypedFormated = numberTyped + ",00";
                } else if (length == 2) {
                    numberTypedFormated = numberTyped.substring(0, length - 1) + "," + numberTyped.substring(length - 1, length) + "0";
                } else if (length == 3) {
                    numberTypedFormated = numberTyped.substring(0, length - 2) + "," + numberTyped.substring(length - 2, length);
                }else if (length > 3) {
                    String numberWithoutDecimal = numberTyped.substring(0, length - 2);
                    String numberWithNotation = "";
                    for(int i =1; i <= (numberWithoutDecimal.length()/3)-1;i++) {
                        numberWithNotation = numberWithoutDecimal.substring(0,numberWithoutDecimal.length()-(3*i))+"."
                                +numberWithoutDecimal.substring(numberWithoutDecimal.length() - (3*i),numberWithoutDecimal.length());
                    }

                    numberTypedFormated = numberWithNotation + "," + numberTyped.substring(length - 2, length);
                }

                editText.setText(numberTypedFormated);
                editText.addTextChangedListener(this);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

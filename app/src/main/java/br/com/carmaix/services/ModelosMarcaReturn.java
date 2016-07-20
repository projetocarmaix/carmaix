package br.com.carmaix.services;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 19/06/16.
 */
public class ModelosMarcaReturn extends ValueLabelDefault {
    public ModelosMarcaReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_modelos_marca));
    }
}

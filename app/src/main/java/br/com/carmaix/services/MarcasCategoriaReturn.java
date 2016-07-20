package br.com.carmaix.services;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 19/06/16.
 */
public class MarcasCategoriaReturn extends ValueLabelDefault {
    public MarcasCategoriaReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_marcas_categoria));
    }
}

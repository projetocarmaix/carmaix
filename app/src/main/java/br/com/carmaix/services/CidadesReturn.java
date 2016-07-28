package br.com.carmaix.services;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 21/07/16.
 */
public class CidadesReturn extends ValueLabelDefault{
    public CidadesReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_cidade));
    }
}

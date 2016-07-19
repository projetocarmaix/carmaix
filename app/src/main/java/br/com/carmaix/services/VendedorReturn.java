package br.com.carmaix.services;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 19/06/16.
 */
public class VendedorReturn extends ValueLabelDefault {
    public VendedorReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_vendedor));
    }
}

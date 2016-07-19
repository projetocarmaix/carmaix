package br.com.carmaix.spinnerStaticValues;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 18/07/16.
 */
public class UfReturn extends ValueLabelDefault {
    public UfReturn() {
        super();
    }

    public UfReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_uf));
    }
}

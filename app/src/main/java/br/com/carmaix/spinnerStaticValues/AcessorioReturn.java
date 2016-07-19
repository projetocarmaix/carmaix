package br.com.carmaix.spinnerStaticValues;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 18/07/16.
 */
public class AcessorioReturn extends ValueLabelDefault {
    public AcessorioReturn() {
        super();
    }

    public AcessorioReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_acessorios));
    }
}

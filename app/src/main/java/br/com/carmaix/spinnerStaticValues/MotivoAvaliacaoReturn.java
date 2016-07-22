package br.com.carmaix.spinnerStaticValues;

import android.content.Context;

import br.com.carmaix.R;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 18/07/16.
 */
public class MotivoAvaliacaoReturn extends ValueLabelDefault {
    public MotivoAvaliacaoReturn() {
        super();
    }

    public MotivoAvaliacaoReturn(Context context) {
        this.setValueDefault(context.getResources().getString(R.string.value_default_motivo_avaliacao));
    }
}
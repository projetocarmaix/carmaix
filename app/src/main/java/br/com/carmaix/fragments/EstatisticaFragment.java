package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.carmaix.R;

/**
 * Created by fernando on 21/07/16.
 */
public class EstatisticaFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.estatistica_fragment,container,false);
        ImageView imagePassecarros =  (ImageView)v.findViewById(R.id.table_image_passecarros);
        ImageView imageFipe =  (ImageView)v.findViewById(R.id.table_image_fipe);

        Picasso.with(fragmentActivity).load(R.drawable.tabela_passecarros).into(imagePassecarros);
        Picasso.with(fragmentActivity).load(R.drawable.fipe).into(imageFipe);
        return v;
    }

}

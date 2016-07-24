package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import br.com.carmaix.R;

/**
 * Created by fernando on 21/07/16.
 */
public class MecanicaFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mecanica_fragment,container,false);
        final EditText editReparos = (EditText)v.findViewById(R.id.edit_reparos);
        editReparos.setClickable(true);
        editReparos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(fragmentActivity);
                LayoutInflater inflater = LayoutInflater.from(fragmentActivity);
                View options = inflater.inflate(R.layout.dialog_options,null);
            }
        });

        editReparos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return v;
    }

}

package br.com.carmaix.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.DecimalFormat;

import br.com.carmaix.R;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/07/16.
 */
public class FotosFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fotos_fragment, container, false);
        return view;
    }

}

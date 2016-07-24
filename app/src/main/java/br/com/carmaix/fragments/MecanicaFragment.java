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
import android.widget.Toast;

import java.text.DecimalFormat;

import br.com.carmaix.R;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/07/16.
 */
public class MecanicaFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mecanica_fragment,container,false);
        final EditText editReparos = (EditText)v.findViewById(R.id.edit_reparos);
        editReparos.setText("0,00");
        editReparos.setClickable(true);
        editReparos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(fragmentActivity);
                LayoutInflater inflater = LayoutInflater.from(fragmentActivity);
                View dialogView = inflater.inflate(R.layout.dialog_tabela_reparos,null);

                ImageButton martelinhoAdd = (ImageButton)dialogView.findViewById(R.id.image_buttom_martelinho_add);
                ImageButton martelinhoRemove = (ImageButton)dialogView.findViewById(R.id.image_buttom_martelinho_remove);

                ImageButton pintarPortaAdd = (ImageButton)dialogView.findViewById(R.id.image_buttom_pintar_porta_add);
                ImageButton pintarPortaRemove = (ImageButton)dialogView.findViewById(R.id.image_buttom_pintar_porta_remove);

                ImageButton pneus13Add = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_13_add);
                ImageButton pneus13Remove = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_13_remove);

                ImageButton pneusR15Add = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_r15_add);
                ImageButton pneusR15Remove = (ImageButton)dialogView.findViewById(R.id.image_buttom_pneus_r15_remove);

                final EditText editReparosDialog = (EditText)dialogView.findViewById(R.id.edit_reparos_dialog);
                editReparosDialog.setText(editReparos.getText().toString());

                martelinhoAdd.setOnClickListener(addReparo(editReparosDialog,Constants.MARTELINHO));
                martelinhoRemove.setOnClickListener(removeReparo(editReparosDialog,Constants.MARTELINHO));

                pintarPortaAdd.setOnClickListener(addReparo(editReparosDialog,Constants.PINTAR_PORTA));
                pintarPortaRemove.setOnClickListener(removeReparo(editReparosDialog,Constants.PINTAR_PORTA));

                pneus13Add.setOnClickListener(addReparo(editReparosDialog,Constants.PNEUS_13));
                pneus13Remove.setOnClickListener(removeReparo(editReparosDialog,Constants.PNEUS_13));

                pneusR15Add.setOnClickListener(addReparo(editReparosDialog,Constants.PNEUS_R15));
                pneusR15Remove.setOnClickListener(removeReparo(editReparosDialog,Constants.PNEUS_R15));

                alerBuilder.setView(dialogView);
                alerBuilder.setPositiveButton(fragmentActivity.getResources().getString(R.string.label_dialog_confirmar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editReparos.setText(editReparosDialog.getText().toString());
                    }
                });

                alerBuilder.setNegativeButton(fragmentActivity.getResources().getString(R.string.label_dialog_cancelar),null);

                alerBuilder.create().show();
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



    private android.view.View.OnClickListener addReparo(final EditText editReparosDialog, final Integer value) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueReparos = editReparosDialog.getText().toString();
                Float valueSumReparos = null;
                if(!valueReparos.isEmpty()) {
                    valueSumReparos = Float.parseFloat(valueReparos.replace(",",".")) + value;
                }else {
                    valueSumReparos = Float.valueOf(value);
                }
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                editReparosDialog.setText((decimalFormat.format(valueSumReparos)).toString());

            }
        };
    }

    private android.view.View.OnClickListener removeReparo(final EditText editReparosDialog, final Integer value) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueReparos = editReparosDialog.getText().toString();
                Float valueSumReparos = null;
                if (!valueReparos.isEmpty()) {
                    valueSumReparos = Float.parseFloat(valueReparos.replace(",", ".")) - value;
                } else {
                    valueSumReparos = Float.valueOf(value);
                }

                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                if (valueSumReparos < 0) {
                    valueSumReparos = 0f;
                }

                editReparosDialog.setText((decimalFormat.format(valueSumReparos)).toString());

            }
        };
    }

}

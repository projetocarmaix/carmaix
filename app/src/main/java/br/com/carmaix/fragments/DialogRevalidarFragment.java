package br.com.carmaix.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.activities.DialogRevalidarActivity;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.VendedorReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 21/07/16.
 */
public class DialogRevalidarFragment extends BaseFragment {

    private Spinner spinnerVendedor;
    private ArrayList<ValueLabelDefault> vendedorReturns = null;
    private ArrayAdapter vendedorSpinnerAdapter;
    private ProgressBar progressBarVendedor;
    private String vendedorId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_revalidar,container,false);

        TextView optionAvaliacaoModelo = (TextView)view.findViewById(R.id.dialog_modelo);
        optionAvaliacaoModelo.setText(String.format(fragmentActivity.getResources().getString(R.string.modelo), ((DialogRevalidarActivity)fragmentActivity).getModelo() ));

        TextView optionAvaliacaoMarca = (TextView)view.findViewById(R.id.dialog_marca);
        optionAvaliacaoMarca.setText(String.format(fragmentActivity.getResources().getString(R.string.marca), ((DialogRevalidarActivity)fragmentActivity).getMarca() ));

        TextView optionAvaliacao = (TextView)view.findViewById(R.id.dialog_avaliacao);
        optionAvaliacao.setText(String.format(fragmentActivity.getResources().getString(R.string.avaliacao), ((DialogRevalidarActivity)fragmentActivity).getAvaliacaoId() ));

        TextView optionPlaca = (TextView)view.findViewById(R.id.dialog_placa);
        optionPlaca.setText(String.format(fragmentActivity.getResources().getString(R.string.placa), ((DialogRevalidarActivity)fragmentActivity).getPlaca() ));

        spinnerVendedor = (Spinner)view.findViewById(R.id.spinner_dialog_revalidar_vendedor);
        progressBarVendedor = (ProgressBar)view.findViewById(R.id.progressbar_revalidar_vendedor);


        Button buttonCancelar = (Button)view.findViewById(R.id.button_dialog_revalidar_cancelar);
        Button buttonRevalidar = (Button)view.findViewById(R.id.button_dialog_revalidar_confirmar);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentActivity.finish();
            }
        });

        buttonRevalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendedorId = ((VendedorReturn)spinnerVendedor.getSelectedItem()).getId();
                if(vendedorId.isEmpty()) {
                    Toast.makeText(fragmentActivity,fragmentActivity.getResources().getString(R.string.value_default_vendedor),Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(fragmentActivity, AvaliarActivity.class);
                    intent.putExtra("avaliacaoId",((DialogRevalidarActivity)fragmentActivity).getAvaliacaoId());
                    intent.putExtra("vendedorId",vendedorId);
                    intent.putExtra("action",Constants.ACTION_REVALIDAR);
                    startActivity(intent);
                }
            }
        });

        runBackground(fragmentActivity.getResources().getString(R.string.carregando),true,true, 0);
        return view;
    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        progressBarVendedor.setVisibility(View.VISIBLE);
        spinnerVendedor.setVisibility(View.GONE);
        vendedorReturns = CallService.listVendedor(fragmentActivity);
        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        vendedorSpinnerAdapter = new ArrayAdapter(fragmentActivity, R.layout.spinner_item, vendedorReturns);
        spinnerVendedor.setAdapter(vendedorSpinnerAdapter);
        loadValues();

        progressBarVendedor.setVisibility(View.GONE);
        spinnerVendedor.setVisibility(View.VISIBLE);

        super.onEndBackgroundRun(action);
    }

    private void loadValues() {
        for (int i = 0; i < spinnerVendedor.getCount(); i++) {
            VendedorReturn vendedorReturn = (VendedorReturn) vendedorSpinnerAdapter.getItem(i);
            if (vendedorReturn != null && (vendedorReturn.getId()).equals(((DialogRevalidarActivity)fragmentActivity).getVendedorId())) {
                spinnerVendedor.setSelection(i);
                break;
            }
        }
    }
}


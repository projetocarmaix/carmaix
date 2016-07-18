package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.utils.BinderSpinner;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.VendedorReturn;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 12/07/16.
 */
public class VeiculoClienteFragment extends BaseFragment {
    ArrayList<VendedorReturn> vendedorReturns = null;
    private Spinner spinnerVendedor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.veiculo_cliente_fragment, container, false);
        spinnerVendedor = (Spinner)view.findViewById(R.id.spinner_vendedor);
        runBackground("",false,true, Constants.ACTION_LIST);
        return view;

    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            try {
                vendedorReturns = CallService.listVendedor(fragmentActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.backgroundMethod(action);
    }

    private ArrayList<VendedorReturn> getVendedorReturns() {
        return vendedorReturns;
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            ArrayAdapter vendedorSpinnerAdapter = new ArrayAdapter(fragmentActivity,android.R.layout.simple_spinner_item,vendedorReturns);
            spinnerVendedor.setAdapter(vendedorSpinnerAdapter);
            spinnerVendedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    VendedorReturn vs = (VendedorReturn)spinnerVendedor.getSelectedItem();
                    String label = vs.getId();
                    String value = vs.getDescricao();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        super.onEndBackgroundRun(action);
    }
}

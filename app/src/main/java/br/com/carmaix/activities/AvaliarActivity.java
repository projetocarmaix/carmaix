package br.com.carmaix.activities;

import android.os.Bundle;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliarFragmentTab;
import br.com.carmaix.services.CallService;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 07/07/16.
 */
public class AvaliarActivity extends BaseActivityHomeAsUp{
    private String avaliacaoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            avaliacaoId = extras.getString("avaliacaoId");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_home_as_up, new AvaliarFragmentTab()).commit();
        runBackground("",false,true, Constants.ACTION_LIST);
    }

    public String getAvaliacaoId() {
        return avaliacaoId;
    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            CallService.listInformacaoAvaliacao(this,avaliacaoId);
        }

        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {

        }
        super.onEndBackgroundRun(action);
    }
}

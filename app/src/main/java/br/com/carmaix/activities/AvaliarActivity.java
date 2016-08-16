package br.com.carmaix.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliarFragmentTab;
import br.com.carmaix.fragments.FotosFragment;
import br.com.carmaix.fragments.MecanicaFragment;
import br.com.carmaix.fragments.OpcionaisFragment;
import br.com.carmaix.fragments.VeiculoClienteFragment;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.AvaliationSend;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.CombustiveisReturn;
import br.com.carmaix.services.InformacoesAvaliacaoReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.services.ServiceDefault;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 07/07/16.
 */
public class AvaliarActivity extends BaseActivityHomeAsUp{
    String imageFrente = "";
    String imageTraseira= "";
    String imageLateralE= "";
    String imageLateralD= "";
    String imageInterior= "";
    String imageOdometro= "";
    String imagePneu= "";
    String imageDetalhe= "";
    String imageEstepe= "";
    String imageDocumento= "";
    String directoryPath= "";

    VeiculoClienteFragment veiculoClienteFragment = null;
    OpcionaisFragment opcionaisFragment = null;
    MecanicaFragment mecanicaFragment = null;
    FotosFragment fotoFragment = null;

    private String avaliacaoId;
    private InformacoesAvaliacaoReturn informacoesAvaliacaoReturn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            avaliacaoId = extras.getString("avaliacaoId");
        }

        runBackground("",false,true, Constants.ACTION_LIST);
    }

    public String getAvaliacaoId() {
        return avaliacaoId;
    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {
        if(action == Constants.ACTION_LIST) {
            informacoesAvaliacaoReturn = CallService.listInformacaoAvaliacao(this,avaliacaoId);
        }else if(action == Constants.ACTION_SEND_IMAGE_FILES) {
            CallService.getImagePath(this,imageTraseira);
        }

        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container_home_as_up, new AvaliarFragmentTab()).commit();
        }else if(action == Constants.ACTION_SEND_IMAGE_FILES) {

        }
        super.onEndBackgroundRun(action);
    }

    public HashMap<String,String> getEstatisticaParams() {

        List<Fragment> fragmentList = getFragmentList();
        HashMap<String,String> values = new HashMap<>();

        Spinner spinnerAnoModelo = null;
        Spinner spinnerCombustivel = null;
        Spinner spinnerModelosMarca = null;
        String idModelo = null;
        String idCombustivel = null;
        String idAno = null;

        for(Fragment fragment : fragmentList) {
            if(fragment instanceof VeiculoClienteFragment) {
                spinnerAnoModelo  = ((VeiculoClienteFragment)fragment).getSpinnerAnoModelo();
                spinnerCombustivel = ((VeiculoClienteFragment)fragment).getSpinnerCombustivel();
                spinnerModelosMarca =((VeiculoClienteFragment)fragment).getSpinnerModelosMarca();
                break;
            }
        }
        if(spinnerModelosMarca != null && spinnerCombustivel != null && spinnerAnoModelo != null) {

            ModelosMarcaReturn modelo = (ModelosMarcaReturn) spinnerModelosMarca.getSelectedItem();
            if(modelo != null) {
                idModelo = modelo.getId();
            }

            CombustiveisReturn combustivel = (CombustiveisReturn) spinnerCombustivel.getSelectedItem();
            if(combustivel != null) {
                idCombustivel = combustivel.getId();
            }
            AnoModeloReturn ano = (AnoModeloReturn) spinnerAnoModelo.getSelectedItem();
            if(ano != null) {
                idAno = ano.getId();
            }

            values.put("modelo",idModelo);
            values.put("combustivel",idCombustivel);
            values.put("ano",idAno);

        }

        return values;

    }

    public InformacoesAvaliacaoReturn getInformacoesAvaliacaoReturn() {
        return informacoesAvaliacaoReturn;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_avaliar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save) {
            sendImageData();
            return true;
        }
        return false;
    }

    public List<Fragment> getFragmentList() {
        AvaliarFragmentTab avaliarFragmentTab = (AvaliarFragmentTab)getSupportFragmentManager().getFragments().get(0);
        List<Fragment> fragmentList = avaliarFragmentTab.getChildFragmentManager().getFragments();
        return fragmentList;
    }

    private void loadFragmentsTabs() {
        List<Fragment> fragmentList = getFragmentList();
        for(Fragment fragment : fragmentList) {
            if(fragment instanceof FotosFragment) {
                fotoFragment = (FotosFragment) fragment;
            }else if(fragment instanceof VeiculoClienteFragment) {
                veiculoClienteFragment = (VeiculoClienteFragment) fragment;
            } else if (fragment instanceof OpcionaisFragment) {
                opcionaisFragment = (OpcionaisFragment) fragment;
            }else if(fragment instanceof MecanicaFragment) {
                mecanicaFragment = (MecanicaFragment) fragment;
            }
        }
    }


    private Boolean sendData() {
        loadFragmentsTabs();
        AvaliationSend avaliationSend = new AvaliationSend();

        avaliationSend.setAvaliador_id(veiculoClienteFragment.getAvaliadorId());
        avaliationSend.setVendedor_id(veiculoClienteFragment.getSpinnerVendedorReturn());
        avaliationSend.setNome(veiculoClienteFragment.getEditTextAvaliadorReturn());
        avaliationSend.setTelefone(veiculoClienteFragment.getEditTextTelefoneReturn());
        avaliationSend.setEstado_id(veiculoClienteFragment.getSpinnerUfReturn());
        avaliationSend.setCidade_id(veiculoClienteFragment.getSpinnerCidadesReturn());
        avaliationSend.setSituacao(veiculoClienteFragment.getEditTextSituacaoReturn());
        avaliationSend.setObservacao(mecanicaFragment.getEditObservacoes());
        avaliationSend.setObservacoes_adicionais(mecanicaFragment.getEditObservacoesAdicionais());
        avaliationSend.setMotivo_avaliacao(veiculoClienteFragment.getMotivoAvaliacaoReturn());
        avaliationSend.setTipo_compra(veiculoClienteFragment.getTipoCompraReturn());
        avaliationSend.setPlaca(veiculoClienteFragment.getEditTextPlacaReturn());
        avaliationSend.setModelo_id(veiculoClienteFragment.getSpinnerModeloReturn());
        avaliationSend.setCategoria_id(veiculoClienteFragment.getSpinnerCategoriasReturn());
        avaliationSend.setMarca_id(veiculoClienteFragment.getSpinnerMarcaReturn());
        avaliationSend.setAno_fabricacao(veiculoClienteFragment.getSpinnerAnoFabricacaoReturn());
        avaliationSend.setAno_modelo(veiculoClienteFragment.getSpinnerAnoModeloValueReturn());
        avaliationSend.setCombustivel_id(veiculoClienteFragment.getSpinnerCombustivelValueReturn());
        avaliationSend.setPortas(veiculoClienteFragment.getSpinnerPortasReturn());
        avaliationSend.setCor(veiculoClienteFragment.getCorReturn());
        avaliationSend.setChassi(veiculoClienteFragment.getEditTextChassiReturn());
        avaliationSend.setRenavam(veiculoClienteFragment.getEditTextRenavamReturn());
        avaliationSend.setAcessorio(veiculoClienteFragment.getSpinnerAcessoriosReturn());
        avaliationSend.setAro(opcionaisFragment.getAro());
        avaliationSend.setKm(veiculoClienteFragment.getKmReturn());
        avaliationSend.setGarantia_fabrica(veiculoClienteFragment.getGarantiaDeFabricaReturn());
        avaliationSend.setNota(veiculoClienteFragment.getNotaReturn());
        avaliationSend.setClassificacao(veiculoClienteFragment.getSpinnerClassificacaoReturnValue());
        avaliationSend.setValor(mecanicaFragment.getEditValor());
        avaliationSend.setFranquia_reparos(mecanicaFragment.getEditReparos());
        avaliationSend.setOpcionais(opcionaisFragment.getOpcionais());
        avaliationSend.setItens(opcionaisFragment.getItens());

        avaliationSend.setMec_motor(mecanicaFragment.getMecMotorValue());
        avaliationSend.setMec_susp_dianteira(mecanicaFragment.getMecSuspDianteiraValue());
        avaliationSend.setMec_homocinetica(mecanicaFragment.getMecHomocineticaValue());
        avaliationSend.setMec_cambio(mecanicaFragment.getMecCambioValue());
        avaliationSend.setMec_susp_traseira(mecanicaFragment.getMecSuspTraseiraValueValue());
        avaliationSend.setMec_rolamentos(mecanicaFragment.getMecRolamentosValue());
        avaliationSend.setMec_embreagem(mecanicaFragment.getMecEmbreagemValue());
        avaliationSend.setMec_cx_direcao(mecanicaFragment.getMecCxDirecaoValue());
        avaliationSend.setMec_pneus_diant(mecanicaFragment.getMecPneusDiantValue());
        avaliationSend.setMec_freios(mecanicaFragment.getMecFreiosValue());
        avaliationSend.setMec_escapamento(mecanicaFragment.getMecEscapamentoValue());
        avaliationSend.setMec_pneus_tras(mecanicaFragment.getMecPneusTrasValue());
        avaliationSend.setMec_diferencial(mecanicaFragment.getMecDiferencialValue());

        avaliationSend.setEst_lataria(mecanicaFragment.getEstLatariaValue());
        avaliationSend.setEst_parachoque_diant(mecanicaFragment.getEstParachoqueDiantValue());
        avaliationSend.setEst_pintura(mecanicaFragment.getEstPinturaValue());
        avaliationSend.setEst_parachoque_tras(mecanicaFragment.getEstParachoqueTrasValue());
        avaliationSend.setEst_carroceria(mecanicaFragment.getEstCarroceriaValue());
        avaliationSend.setEst_parabrisa(mecanicaFragment.getEstParabrisaValue());
        avaliationSend.setEst_porta_malas(mecanicaFragment.getEstPortaMalasValue());
        avaliationSend.setEst_estofamento(mecanicaFragment.getEstEstofamentoValue());
        avaliationSend.setEst_motor(mecanicaFragment.getEstMotorValue());
        avaliationSend.setEst_farol(mecanicaFragment.getEstFarolValue());
        avaliationSend.setColisao(mecanicaFragment.getColisao());

        return false;
    }

    private Boolean sendImageData() {
        List<Fragment> fragmentList = getFragmentList();

        for(Fragment fragment : fragmentList) {
            if(fragment instanceof FotosFragment) {
                imageFrente = ((FotosFragment)fragment).getImageFrente();
                imageTraseira = ((FotosFragment)fragment).getImageTraseira();
                imageLateralE = ((FotosFragment)fragment).getImageLateralE();
                imageLateralD = ((FotosFragment)fragment).getImageLateralD();
                imageInterior = ((FotosFragment)fragment).getImageInterior();
                imageOdometro = ((FotosFragment)fragment).getImageOdometro();
                imagePneu = ((FotosFragment)fragment).getImagePneu();
                imageDetalhe = ((FotosFragment)fragment).getImageDetalhe();
                imageEstepe = ((FotosFragment)fragment).getImageEstepe();
                imageDocumento = ((FotosFragment)fragment).getImageDocumento();
                directoryPath = ((FotosFragment) fragment).getDirectoryPath();
                break;
            }
        }


        runBackground(this.getResources().getString(R.string.enviando_imagens), true, true, Constants.ACTION_SEND_IMAGE_FILES);

        return false;
    }


}


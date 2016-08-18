package br.com.carmaix.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliarFragmentTab;
import br.com.carmaix.fragments.FotosFragment;
import br.com.carmaix.fragments.MecanicaFragment;
import br.com.carmaix.fragments.OpcionaisFragment;
import br.com.carmaix.fragments.VeiculoClienteFragment;
import br.com.carmaix.services.AnoModeloReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.EvaluationSend;
import br.com.carmaix.services.CombustiveisReturn;
import br.com.carmaix.services.InformacoesAvaliacaoReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.services.ServiceDefault;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 07/07/16.
 */
public class AvaliarActivity extends BaseActivityHomeAsUp{
    private String imageFrente = "";
    private String imageTraseira= "";
    private String imageLateralE= "";
    private String imageLateralD= "";
    private String imageInterior= "";
    private String imageOdometro= "";
    private String imagePneu= "";
    private String imageDetalhe= "";
    private String imageEstepe= "";
    private String imageDocumento= "";
    private String directoryPath= "";

    private String imageFrentePath  = "";
    private String imageTraseiraPath = "";
    private String imageLateralEPath = "";
    private String imageLateralDPath = "";
    private String imageInteriorPath = "";
    private String imageOdometroPath = "";
    private String imagePneuPath = "";
    private String imageDetalhePath = "";
    private String imageEstepePath = "";
    private String imageDocumentoPath = "";


    private EvaluationSend evaluationSend = new EvaluationSend();

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
            imageFrentePath    = CallService.getImagePath(AvaliarActivity.this,imageFrente);
            imageTraseiraPath  = CallService.getImagePath(AvaliarActivity.this,imageTraseira);
            imageLateralEPath  = CallService.getImagePath(AvaliarActivity.this,imageLateralE);
            imageLateralDPath  = CallService.getImagePath(AvaliarActivity.this,imageLateralD);
            imageInteriorPath  = CallService.getImagePath(AvaliarActivity.this,imageInterior);
            imageOdometroPath  = CallService.getImagePath(AvaliarActivity.this,imageOdometro);
            imagePneuPath      = CallService.getImagePath(AvaliarActivity.this,imagePneu);
            imageDetalhePath   = CallService.getImagePath(AvaliarActivity.this,imageDetalhe);
            imageEstepePath    = CallService.getImagePath(AvaliarActivity.this,imageEstepe);
            imageDocumentoPath = CallService.getImagePath(AvaliarActivity.this,imageDocumento);
        }else if(action == Constants.ACTION_SEND_DATA) {
            ServiceDefault.atualizacaoAvaliacao(this,getAvaliacaoId(),evaluationSend);
        }

        super.backgroundMethod(action);
    }

    @Override
    protected void onEndBackgroundRun(int action) {
        if(action == Constants.ACTION_LIST) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container_home_as_up, new AvaliarFragmentTab()).commit();
        }else if(action == Constants.ACTION_SEND_IMAGE_FILES) {
            evaluationSend.setAvaliador_id(veiculoClienteFragment.getAvaliadorId());
            evaluationSend.setVendedor_id(veiculoClienteFragment.getSpinnerVendedorReturn());
            evaluationSend.setNome(veiculoClienteFragment.getEditTextAvaliadorReturn());
            evaluationSend.setTelefone(veiculoClienteFragment.getEditTextTelefoneReturn());
            evaluationSend.setEstado_id(veiculoClienteFragment.getSpinnerUfReturn());
            evaluationSend.setCidade_id(veiculoClienteFragment.getSpinnerCidadesReturn());
            evaluationSend.setSituacao(veiculoClienteFragment.getEditTextSituacaoReturn());
            evaluationSend.setObservacao(mecanicaFragment.getEditObservacoes());
            evaluationSend.setObservacoes_adicionais(mecanicaFragment.getEditObservacoesAdicionais());
            evaluationSend.setMotivo_avaliacao(veiculoClienteFragment.getMotivoAvaliacaoReturn());
            evaluationSend.setTipo_compra(veiculoClienteFragment.getTipoCompraReturn());
            evaluationSend.setPlaca(veiculoClienteFragment.getEditTextPlacaReturn());
            evaluationSend.setModelo_id(veiculoClienteFragment.getSpinnerModeloReturn());
            evaluationSend.setCategoria_id(veiculoClienteFragment.getSpinnerCategoriasReturn());
            evaluationSend.setMarca_id(veiculoClienteFragment.getSpinnerMarcaReturn());
            evaluationSend.setAno_fabricacao(veiculoClienteFragment.getSpinnerAnoFabricacaoReturn());
            evaluationSend.setAno_modelo(veiculoClienteFragment.getSpinnerAnoModeloValueReturn());
            evaluationSend.setCombustivel_id(veiculoClienteFragment.getSpinnerCombustivelValueReturn());
            evaluationSend.setPortas(veiculoClienteFragment.getSpinnerPortasReturn());
            evaluationSend.setCor(veiculoClienteFragment.getCorReturn());
            evaluationSend.setChassi(veiculoClienteFragment.getEditTextChassiReturn());
            evaluationSend.setRenavam(veiculoClienteFragment.getEditTextRenavamReturn());
            evaluationSend.setAcessorio(veiculoClienteFragment.getSpinnerAcessoriosReturn());
            evaluationSend.setAro(opcionaisFragment.getAro());
            evaluationSend.setKm(veiculoClienteFragment.getKmReturn());
            evaluationSend.setGarantia_fabrica(veiculoClienteFragment.getGarantiaDeFabricaReturn());
            evaluationSend.setNota(veiculoClienteFragment.getNotaReturn());
            evaluationSend.setClassificacao(veiculoClienteFragment.getSpinnerClassificacaoReturnValue());
            evaluationSend.setValor(mecanicaFragment.getEditValor());
            evaluationSend.setFranquia_reparos(mecanicaFragment.getEditReparos());
            evaluationSend.setOpcionais(opcionaisFragment.getOpcionais());
            evaluationSend.setItens(opcionaisFragment.getItens());

            evaluationSend.setMec_motor(mecanicaFragment.getMecMotorValue());
            evaluationSend.setMec_susp_dianteira(mecanicaFragment.getMecSuspDianteiraValue());
            evaluationSend.setMec_homocinetica(mecanicaFragment.getMecHomocineticaValue());
            evaluationSend.setMec_cambio(mecanicaFragment.getMecCambioValue());
            evaluationSend.setMec_susp_traseira(mecanicaFragment.getMecSuspTraseiraValueValue());
            evaluationSend.setMec_rolamentos(mecanicaFragment.getMecRolamentosValue());
            evaluationSend.setMec_embreagem(mecanicaFragment.getMecEmbreagemValue());
            evaluationSend.setMec_cx_direcao(mecanicaFragment.getMecCxDirecaoValue());
            evaluationSend.setMec_pneus_diant(mecanicaFragment.getMecPneusDiantValue());
            evaluationSend.setMec_freios(mecanicaFragment.getMecFreiosValue());
            evaluationSend.setMec_escapamento(mecanicaFragment.getMecEscapamentoValue());
            evaluationSend.setMec_pneus_tras(mecanicaFragment.getMecPneusTrasValue());
            evaluationSend.setMec_diferencial(mecanicaFragment.getMecDiferencialValue());

            evaluationSend.setEst_lataria(mecanicaFragment.getEstLatariaValue());
            evaluationSend.setEst_parachoque_diant(mecanicaFragment.getEstParachoqueDiantValue());
            evaluationSend.setEst_pintura(mecanicaFragment.getEstPinturaValue());
            evaluationSend.setEst_parachoque_tras(mecanicaFragment.getEstParachoqueTrasValue());
            evaluationSend.setEst_carroceria(mecanicaFragment.getEstCarroceriaValue());
            evaluationSend.setEst_parabrisa(mecanicaFragment.getEstParabrisaValue());
            evaluationSend.setEst_porta_malas(mecanicaFragment.getEstPortaMalasValue());
            evaluationSend.setEst_estofamento(mecanicaFragment.getEstEstofamentoValue());
            evaluationSend.setEst_motor(mecanicaFragment.getEstMotorValue());
            evaluationSend.setEst_farol(mecanicaFragment.getEstFarolValue());
            evaluationSend.setColisao(mecanicaFragment.getColisao());

            evaluationSend.setFrente(imageFrentePath);
            evaluationSend.setTraseira(imageTraseiraPath);
            evaluationSend.setLat_esquerda(imageLateralEPath);
            evaluationSend.setLat_direita(imageLateralDPath);
            evaluationSend.setInterior(imageInteriorPath);
            evaluationSend.setOdometro(imageOdometroPath);
            evaluationSend.setPneu(imagePneuPath);
            evaluationSend.setDetalhe(imageDetalhePath);
            evaluationSend.setEstepe(imageEstepePath);
            evaluationSend.setDocumento(imageDocumentoPath);

            runBackground("Enviando os dados da avaliação...",true,true, Constants.ACTION_SEND_DATA);

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
            loadSendClass();
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


    private void loadSendClass() {
        loadFragmentsTabs();
        sendData();
    }

    private void sendData() {
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

        runBackground("Enviando as imagens...",true,true, Constants.ACTION_SEND_IMAGE_FILES);

    }

}


/*
"field": "nome",
"message": "Campo obrigatório."

"field": "cor",
"message": "Campo obrigatório."

"field": "situacao",
"message": "Campo obrigatório."

"field": "observacao",
"message": "Campo obrigatório."

"field": "observacoes_adicionais",
"message": "Campo obrigatório."

"field": "valor",
"message": "Campo obrigatório."*/

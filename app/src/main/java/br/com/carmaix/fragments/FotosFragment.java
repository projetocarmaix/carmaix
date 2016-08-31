package br.com.carmaix.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliarActivity;
import br.com.carmaix.services.Fotos;
import br.com.carmaix.services.InformacoesAvaliacaoReturn;
import br.com.carmaix.services.Veiculo;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/07/16.
 */
public class FotosFragment extends BaseFragment {
    private ImageView imageFrente;
    private ImageView imageTraseira;
    private ImageView imageLateralE;
    private ImageView imageLateralD;
    private ImageView imageInterior;
    private ImageView imageOdometro;
    private ImageView imagePneu;
    private ImageView imageDetalhe;
    private ImageView imageEstepe;
    private ImageView imageDocumento;

    private Button buttonFrente;
    private Button buttonTraseira;
    private Button buttonLateralE;
    private Button buttonLateralD;
    private Button buttonInterior;
    private Button buttonOdometro;
    private Button buttonPneu;
    private Button buttonDetalhe;
    private Button buttonEstepe;
    private Button buttonDocumento;
    private InformacoesAvaliacaoReturn informacoesAvaliacaoReturn;
    private String directoryPath = "";
    private int actionParam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fotos_fragment, container, false);
        actionParam = ((AvaliarActivity)fragmentActivity).getActionParam();
        setupImageViews(view);
        setupButtons(view);
        return view;
    }

    private void setupButtons(View view) {
        buttonFrente    = (Button)view.findViewById(R.id.button_frente);
        buttonFrente.setOnClickListener(clickButtonListener("1"));

        buttonTraseira  = (Button)view.findViewById(R.id.button_traseira);
        buttonTraseira.setOnClickListener(clickButtonListener("2"));

        buttonLateralE  = (Button)view.findViewById(R.id.button_lateral_e);
        buttonLateralE.setOnClickListener(clickButtonListener("3"));

        buttonLateralD  = (Button)view.findViewById(R.id.button_lateral_d);
        buttonLateralD.setOnClickListener(clickButtonListener("4"));

        buttonInterior  = (Button)view.findViewById(R.id.button_interior);
        buttonInterior.setOnClickListener(clickButtonListener("5"));

        buttonOdometro  = (Button)view.findViewById(R.id.button_odometro);
        buttonOdometro.setOnClickListener(clickButtonListener("6"));

        buttonPneu      = (Button)view.findViewById(R.id.button_pneu);
        buttonPneu.setOnClickListener(clickButtonListener("7"));

        buttonDetalhe   = (Button)view.findViewById(R.id.button_detalhe);
        buttonDetalhe.setOnClickListener(clickButtonListener("8"));

        buttonEstepe    = (Button)view.findViewById(R.id.button_estepe);
        buttonEstepe.setOnClickListener(clickButtonListener("9"));

        buttonDocumento = (Button)view.findViewById(R.id.button_documento);
        buttonDocumento.setOnClickListener(clickButtonListener("10"));
    }


    private void setupImageViews(View view) {
        imageFrente    = (ImageView)view.findViewById(R.id.image_frente);
        imageTraseira  = (ImageView)view.findViewById(R.id.image_traseira);
        imageLateralE  = (ImageView)view.findViewById(R.id.image_lateral_e);
        imageLateralD  = (ImageView)view.findViewById(R.id.image_lateral_d);
        imageInterior  = (ImageView)view.findViewById(R.id.image_interior);
        imageOdometro  = (ImageView)view.findViewById(R.id.image_odometro);
        imagePneu      = (ImageView)view.findViewById(R.id.image_pneu);
        imageDetalhe   = (ImageView)view.findViewById(R.id.image_detalhe);
        imageEstepe    = (ImageView)view.findViewById(R.id.image_estepe);
        imageDocumento = (ImageView)view.findViewById(R.id.image_documento);

        if(actionParam == Constants.ACTION_AVALIAR) {
            informacoesAvaliacaoReturn = ((AvaliarActivity) fragmentActivity).getInformacoesAvaliacaoReturn();
            Fotos fotos = informacoesAvaliacaoReturn.getVeiculo().getFotos();

            inicializaImages(imageFrente, fotos.getFrente());
            inicializaImages(imageTraseira, fotos.getTraseira());
            inicializaImages(imageLateralE, fotos.getLat_esquerda());
            inicializaImages(imageLateralD, fotos.getLat_direita());
            inicializaImages(imageInterior, fotos.getInterior());
            inicializaImages(imageOdometro, fotos.getOdometro());
            inicializaImages(imagePneu, fotos.getPneu());
            inicializaImages(imageDetalhe, fotos.getDetalhe());
            inicializaImages(imageEstepe, fotos.getEstepe());
            inicializaImages(imageDocumento, fotos.getDocumento());
        }else {
            inicializaImages(imageFrente, "");
            inicializaImages(imageTraseira, "");
            inicializaImages(imageLateralE, "");
            inicializaImages(imageLateralD, "");
            inicializaImages(imageInterior, "");
            inicializaImages(imageOdometro, "");
            inicializaImages(imagePneu, "");
            inicializaImages(imageDetalhe, "");
            inicializaImages(imageEstepe, "");
            inicializaImages(imageDocumento, "");
        }
    }

    private void inicializaImages(ImageView imageView, String urlImg) {
        if(urlImg.isEmpty()) {
            Picasso.with(fragmentActivity).load(R.drawable.no_image_box).fit().centerInside().into(imageView);
            imageView.setTag(Constants.NO_IMAGE);
        }else {
            Picasso.with(fragmentActivity).load(urlImg).fit().centerInside().into(imageView);
            imageView.setTag(urlImg);
        }
    }

    private View.OnClickListener clickButtonListener(final String requestode) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(fragmentActivity);
                String opcoes[] = {fragmentActivity.getResources().getString(R.string.label_title_dialog_camera),fragmentActivity.getResources().getString(R.string.label_title_dialog_galeria)};
                alertBuilder.setTitle(fragmentActivity.getResources().getString(R.string.label_title_dialog_opcoes));
                alertBuilder.setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String req = requestode;
                        if(i == 0) {
                            req = req + "1";
                            callCamera(req);
                        }else if(i == 1) {
                            req = req + "2";
                            callGallery(req);
                        }
                    }
                });

                alertBuilder.show();
            }
        };
        return onClickListener;
    }

    private void callCamera(String requestode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragmentActivity. getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Integer.parseInt(requestode));
        }
    }

    private void callGallery(String requestode) {
        Intent pictureActionIntent = null;
        pictureActionIntent = new Intent(Intent.ACTION_PICK);
        pictureActionIntent.setType("image/*");
        startActivityForResult(pictureActionIntent, Integer.parseInt(requestode));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == fragmentActivity.RESULT_OK) {
            Bundle extras = data.getExtras();

            String fullCode = String.valueOf(requestCode);
            String codeImageView;
            String codeOption;
            if(fullCode.length() <= 2) {
                codeImageView = String.valueOf(fullCode.charAt(0));
                codeOption = String.valueOf(fullCode.charAt(1));
            }else {
                codeImageView = String.valueOf(fullCode.substring(0,2));
                codeOption = String.valueOf(fullCode.charAt(2));
            }

            if (codeOption.equals("1")) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                String path = escreveImagem(imageBitmap);

                if (!path.isEmpty()) {
                    ImageView imageView = getImageViewByCode(codeImageView);
                    imageView.setTag(path);
                    Picasso.with(fragmentActivity).load(new File(path)).fit().centerInside().into(imageView);
                }
            }else if(codeOption.equals("2")) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = fragmentActivity.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                if (!picturePath.isEmpty()) {
                    String path = copiaArquivo(new File(picturePath));
                    File f = new File(path);
                    ImageView imageView = getImageViewByCode(codeImageView);
                    imageView.setTag(path);
                    Picasso.with(fragmentActivity).load(f).fit().centerInside().into(imageView);

                }
            }
        }
    }

    private ImageView getImageViewByCode(String codeImageView) {
        if(codeImageView.equals("1"))
            return imageFrente;
        else if(codeImageView.equals("2"))
            return imageTraseira;
        else if(codeImageView.equals("3"))
            return imageLateralE;
        else if(codeImageView.equals("4"))
            return imageLateralD;
        else if(codeImageView.equals("5"))
            return imageInterior;
        else if(codeImageView.equals("6"))
            return  imageOdometro;
        else if(codeImageView.equals("7"))
            return  imagePneu;
        else if(codeImageView.equals("8"))
            return  imageDetalhe;
        else if(codeImageView.equals("9"))
            return  imageEstepe;
        else if(codeImageView.equals("10"))
            return  imageDocumento;

        return null;
    }

    private String escreveImagem(Bitmap bmp){
        String nomeArquivo = "";
        createFolder();
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            nomeArquivo = directoryPath+"/"+timeStamp+".jpg";
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nomeArquivo;
    }

    private void createFolder() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"carmaix"+"/"+informacoesAvaliacaoReturn.getId());
        directoryPath = f.getAbsolutePath();
        if(!f.exists()) {
            f.mkdirs();
        }
    }

    public String copiaArquivo(File fileInput) {
        String nomeArquivo = "";
        createFolder();
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            nomeArquivo = directoryPath+"/"+timeStamp+".jpg";

            File fileOutput = new File(nomeArquivo);
            if(!fileOutput.exists()) {
                fileOutput.createNewFile();
            }

            OutputStream o = new FileOutputStream(fileOutput);

            int fileSize = (int)fileInput.length();
            byte[] buf = new byte[fileSize];
            InputStream i = new FileInputStream(fileInput);
            int len;
            while((len = i.read(buf)) > 0) {
                o.write(buf,0,len);
            }
            o.flush();
            o.close();
            i.close();
        }catch (Exception e ) {
            e.printStackTrace();
        }

        return nomeArquivo;

    }

    public String getImageFrente() {
        return (String)imageFrente.getTag();
    }

    public String getImageTraseira() {
        return (String)imageTraseira.getTag();
    }

    public String getImageLateralE() {
        return (String)imageLateralE.getTag();
    }

    public String getImageLateralD() {
        return (String)imageLateralD.getTag();
    }

    public String getImageInterior() {
        return (String)imageInterior.getTag();
    }

    public String getImageOdometro() {
        return (String)imageOdometro.getTag();
    }

    public String getImagePneu() {
        return (String)imagePneu.getTag();
    }

    public String getImageDetalhe() {
        return (String)imageDetalhe.getTag();
    }

    public String getImageEstepe() {
        return (String)imageEstepe.getTag();
    }

    public String getImageDocumento() {
        return (String)imageDocumento.getTag();
    }

    public String getDirectoryPath() {
        return directoryPath;
    }
}




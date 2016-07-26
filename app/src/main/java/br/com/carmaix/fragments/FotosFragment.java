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
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.carmaix.R;

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

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_PICTURE = 0;
    static final int REQUEST_TAKE_PHOTO = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fotos_fragment, container, false);
        setupImageViews(view);
        setupButtons(view);
        return view;
    }

    private void setupButtons(View view) {
        buttonFrente    = (Button)view.findViewById(R.id.button_frente);
        buttonFrente.setOnClickListener(clickButtonListener());
        buttonTraseira  = (Button)view.findViewById(R.id.button_traseira);
        buttonLateralE  = (Button)view.findViewById(R.id.button_lateral_e);
        buttonLateralD  = (Button)view.findViewById(R.id.button_lateral_d);
        buttonInterior  = (Button)view.findViewById(R.id.button_interior);
        buttonOdometro  = (Button)view.findViewById(R.id.button_odometro);
        buttonPneu      = (Button)view.findViewById(R.id.button_pneu);
        buttonDetalhe   = (Button)view.findViewById(R.id.button_detalhe);
        buttonEstepe    = (Button)view.findViewById(R.id.button_estepe);
        buttonDocumento = (Button)view.findViewById(R.id.button_documento);
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

        inicializaImages(imageFrente);
        inicializaImages(imageTraseira);
        inicializaImages(imageLateralE);
        inicializaImages(imageLateralD);
        inicializaImages(imageInterior);
        inicializaImages(imageOdometro);
        inicializaImages(imagePneu);
        inicializaImages(imageDetalhe);
        inicializaImages(imageEstepe);
        inicializaImages(imageDocumento);
    }

    private void inicializaImages(ImageView imageView) {
        Picasso.with(fragmentActivity).load(R.drawable.no_image_box).fit().centerInside().into(imageView);
    }

    private View.OnClickListener clickButtonListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(fragmentActivity);
                String opcoes[] = {fragmentActivity.getResources().getString(R.string.label_title_dialog_camera),fragmentActivity.getResources().getString(R.string.label_title_dialog_galeria)};
                alertBuilder.setTitle(fragmentActivity.getResources().getString(R.string.label_title_dialog_opcoes));
                alertBuilder.setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0) {
                            callCamera();
                        }else if(i == 1) {
                            callGallery();
                        }
                    }
                });

                alertBuilder.show();
            }
        };
        return onClickListener;
    }

    private void callCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragmentActivity. getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void callGallery() {
        Intent pictureActionIntent = null;
        pictureActionIntent = new Intent(Intent.ACTION_PICK);
        pictureActionIntent.setType("image/*");
        startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == fragmentActivity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                String path = escreveImagem(imageBitmap);
                if (!path.isEmpty()) {
                    Picasso.with(fragmentActivity).load(new File(path)).fit().centerInside().into(imageFrente);
                }
            }else if(requestCode == GALLERY_PICTURE) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = fragmentActivity.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                if (!picturePath.isEmpty()) {
                    Picasso.with(fragmentActivity).load(new File(picturePath)).fit().centerInside().into(imageFrente);
                }
            }
        }
    }

    private String escreveImagem(Bitmap bmp){
        String nomeArquivo = "";
        createFolder();
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            nomeArquivo = Environment.getExternalStorageDirectory().getAbsolutePath()+"/carmaix/"+timeStamp+".jpg";
            FileOutputStream fos = new FileOutputStream(nomeArquivo);
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nomeArquivo;
    }

    private void createFolder() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"carmaix");
        if(!f.exists()) {
            f.mkdirs();
        }
    }
}

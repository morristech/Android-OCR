package cenkgun.com.android_ocr.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import cenkgun.com.android_ocr.Model.Fatura;
import cenkgun.com.android_ocr.R;
import cenkgun.com.android_ocr.Tools.Tools;

public class FaturaEkleActivity extends AppCompatActivity {

    String faturatipi, baslik, baslangictarihi, okunandeger;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private static final int CAMERA_PIC_REQUEST = 22;


    private ImageView ImgPhoto;


    EditText et_baslik ;
    EditText et_baslangicTarihi;
    EditText et_okunanDeger;

    ImageButton img_tarihSec;
    ImageButton img_tara;

    Spinner spinner;

    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";

    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura_ekle);

        String language = "eng";
        datapath = getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();

        checkFile(new File(datapath + "tessdata/"));

        mTess.init(datapath, language);

        et_baslik = (EditText) findViewById(R.id.et_faturaekle_baslik);
        et_baslangicTarihi = (EditText) findViewById(R.id.et_faturaekle_baslangictarihi);
        et_okunanDeger = (EditText) findViewById(R.id.et_faturaekle_okunandeger);

        img_tarihSec = (ImageButton) findViewById(R.id.img_faturaekle_tarih);
        img_tara = (ImageButton) findViewById(R.id.img_faturaekle_tara);
        spinner = (Spinner) findViewById(R.id.spinner);

        ImgPhoto = (ImageView) findViewById(R.id.kamera);



        spinner = (Spinner) findViewById(R.id.spinner);

        img_tara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Couldn't load photo", Toast.LENGTH_LONG).show();
                }

            }
        });

        img_tarihSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(FaturaEkleActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                et_baslangicTarihi.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Action Bar içinde kullanılacak menü öğelerini inflate edelim
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_faturaekle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar öğelerindeki basılmaları idare edelim
        switch (item.getItemId()) {
            case R.id.action_kaydet:

                faturatipi = spinner.getSelectedItem().toString();
                baslik = et_baslik.getText().toString();
                baslangictarihi = et_baslangicTarihi.getText().toString();
                okunandeger = et_okunanDeger.getText().toString();

                Fatura fatura = new Fatura(faturatipi,"konum",baslangictarihi,baslik, okunandeger);

                Tools.faturas.add(fatura);

                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        try {
            switch (requestCode) {
                case CAMERA_PIC_REQUEST:
                    if (resultCode == RESULT_OK) {
                        try {
                            photo = (Bitmap) data.getExtras().get("data");

                            ImgPhoto.setImageBitmap(photo);

                            image = photo;

                            processImage();



                        } catch (Exception e) {
                            Toast.makeText(this, "Couldn't load photo", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                default:
                    break;
            }



        } catch (Exception e) {
        }

    }


    public void processImage(){
        String OCRresult = null;
        mTess.setImage(image);
        OCRresult = mTess.getUTF8Text();

        et_okunanDeger.setText(OCRresult);

    }

    private void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);

            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    private void copyFiles() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getAssets();

            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }


            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

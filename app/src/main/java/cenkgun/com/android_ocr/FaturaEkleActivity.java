package cenkgun.com.android_ocr;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.Calendar;

import cenkgun.com.android_ocr.Model.Fatura;
import cenkgun.com.android_ocr.Tools.Tools;

public class FaturaEkleActivity extends AppCompatActivity {

    String faturatipi, baslik, baslangictarihi, okunandeger;

    private int mYear, mMonth, mDay, mHour, mMinute;


    EditText et_baslik ;
    EditText et_baslangicTarihi;
    EditText et_okunanDeger;

    ImageButton img_tarihSec;
    ImageButton img_tara;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura_ekle);

        et_baslik = (EditText) findViewById(R.id.et_faturaekle_baslik);
        et_baslangicTarihi = (EditText) findViewById(R.id.et_faturaekle_baslangictarihi);
        et_okunanDeger = (EditText) findViewById(R.id.et_faturaekle_okunandeger);

        img_tarihSec = (ImageButton) findViewById(R.id.img_faturaekle_tarih);
        img_tara = (ImageButton) findViewById(R.id.img_faturaekle_tara);
        spinner = (Spinner) findViewById(R.id.spinner);


        spinner = (Spinner) findViewById(R.id.spinner);

        img_tara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                Fatura fatura = new Fatura(faturatipi,"konum",baslangictarihi,baslik);

                Tools.faturas.add(fatura);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

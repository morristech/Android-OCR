package cenkgun.com.android_ocr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cenkgun.com.android_ocr.Model.Fatura;

public class FaturaAdapter extends RecyclerView.Adapter<FaturaAdapter.ViewHolder> {
    private ArrayList<Fatura> mFatura;

    public FaturaAdapter(ArrayList<Fatura> faturas) {
        this.mFatura = faturas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_anasayfa, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_baslik.setText(mFatura.get(i).getBaslik());
        viewHolder.tv_baslangictarihi.setText(mFatura.get(i).getBaslangicTarihi());


    }

    @Override
    public int getItemCount() {
        return mFatura.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_baslik;
        private TextView tv_baslangictarihi;
        private TextView tv_detay;
        private TextView tv_duzenle;


        public ViewHolder(View view) {
            super(view);

            tv_baslik = (TextView) view.findViewById(R.id.tv_fatura_baslik);
            tv_baslangictarihi = (TextView) view.findViewById(R.id.tv_fatura_tarih);
            tv_detay = (TextView) view.findViewById(R.id.tv_fatura_detay);
            tv_duzenle = (TextView) view.findViewById(R.id.tv_fatura_duzenle);
        }
    }

}

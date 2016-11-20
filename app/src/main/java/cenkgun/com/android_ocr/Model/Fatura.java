package cenkgun.com.android_ocr.Model;

/**
 * Created by Cenk on 20/11/2016.
 */
public class Fatura {

    private String faturaTipi;
    private String konum;
    private Object baslangicTarihi;
    private String baslik;

    public Fatura(String faturaTipi, String konum, Object baslangicTarihi, String baslik) {
        this.faturaTipi = faturaTipi;
        this.konum = konum;
        this.baslangicTarihi = baslangicTarihi;
        this.baslik = baslik;
    }

    public String getFaturaTipi() {
        return faturaTipi;
    }

    public void setFaturaTipi(String faturaTipi) {
        this.faturaTipi = faturaTipi;
    }

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

    public Object getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(Object baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }
}

package cenkgun.com.android_ocr.Model;

/**
 * Created by Cenk on 20/11/2016.
 */
public class Fatura {

    private String faturaTipi;
    private String konum;
    private String baslangicTarihi;
    private String baslik;



    private String okunandeger;

    public Fatura(String faturaTipi, String konum, String baslangicTarihi, String baslik, String okunandeger) {
        this.faturaTipi = faturaTipi;
        this.konum = konum;
        this.baslangicTarihi = baslangicTarihi;
        this.baslik = baslik;
        this.okunandeger = okunandeger;
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

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }
    public String getOkunandeger() {
        return okunandeger;
    }

    public void setOkunandeger(String okunandeger) {
        this.okunandeger = okunandeger;
    }
}

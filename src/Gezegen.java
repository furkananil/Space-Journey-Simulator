public class Gezegen {
    public String ad;
    public int gunSaatSayisi;
    public Zaman tarih;
    public int nufus = 0;

    public Gezegen(String ad, int gunSaatSayisi, Zaman tarih) {
        this.ad = ad;
        this.gunSaatSayisi = gunSaatSayisi;
        this.tarih = tarih;
    }

    public void zamanIlerle(int saat) {
        tarih.saatIlerle(saat, gunSaatSayisi);
    }

    public String getTarihStr() {
        return tarih.toString();
    }

    public void nufusSifirla() { nufus = 0; }
    public void nufusArttir() { nufus++; }
}

public class Gezegen {
    private String ad;
    private int gunSaatSayisi;
    private Zaman tarih;
    private int nufus = 0;

    // Bilgileri kontrollu bir sekilde al ve gezegen nesnesi olustur
    public Gezegen(String ad, int gunSaatSayisi, Zaman tarih) {
        setAd(ad);
        setGunSaatSayisi(gunSaatSayisi);
        setTarih(tarih);
    }

    // Zaman ilerletme işlemi, saat sayısını alır ve gezegenin tarihine göre günceller
    public void zamanIlerle(int saat) {
        tarih.saatIlerle(saat, gunSaatSayisi);
    }

    // Gezegenin tarihini alır ve string formatında döndürür
    public String getTarihStr() {
        return tarih.toString();
    }

    // Gezegenin nufusunu sıfırlar
    public void nufusSifirla() { nufus = 0; }

    // Gezegenin nufusunu arttırır
    // Bu metod, uzay aracındaki yolcuların gezegene varması durumunda çağrılır
    public void nufusArttir() { nufus++; }

    
    // Getter ve Setter metodları

    public String getAd() { return ad; }
    public int getGunSaatSayisi() { return gunSaatSayisi; }
    public Zaman getTarih() { return tarih; }
    public int getNufus() { return nufus; }

    public void setAd(String ad) { this.ad = ad; }
    public void setGunSaatSayisi(int gunSaatSayisi) { this.gunSaatSayisi = gunSaatSayisi; }
    public void setTarih(Zaman tarih) { this.tarih = tarih; }
    public void setNufus(int nufus) { this.nufus = nufus; }
}

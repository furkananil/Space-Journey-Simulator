import java.util.ArrayList;

public class UzayAraci {
    public String ad, cikis, varis;
    public Zaman cikisTarihi;
    public int mesafe, kalanMesafe;
    public boolean imha = false;
    public boolean kalkti = false;
    public ArrayList<Kisi> yolcular = new ArrayList<>();

    public UzayAraci(String ad, String cikis, String varis, Zaman cikisTarihi, int mesafe) {
        this.ad = ad;
        this.cikis = cikis;
        this.varis = varis;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
        this.kalanMesafe = mesafe;
    }

    public void saatIlerle() {
        if (!imha && kalanMesafe > 0 && kalkti) kalanMesafe--;

        yolcular.removeIf(k -> {
            k.yasat();
            return !k.hayattaMi();
        });

        if (yolcular.isEmpty()) imha = true;
    }

    public boolean varisYaptiMi() { return kalanMesafe <= 0; }

    public String getDurum() {
        if (imha) return "IMHA";
        if (varisYaptiMi()) return "Vardı";
        if (kalkti) return "Yolda";
        return "Bekliyor";
    }

    public String getVarisTarihi(Gezegen varisGezegeni) {
        if (imha) return "--";
        Zaman z = new Zaman(varisGezegeni.tarih.gun, varisGezegeni.tarih.ay, varisGezegeni.tarih.yil);
        z.saatIlerle(kalanMesafe, varisGezegeni.gunSaatSayisi);
        return z.toString();
    }
}
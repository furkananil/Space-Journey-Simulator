import java.util.ArrayList;

public class UzayAraci {
    private String ad, cikis, varis;
    private Zaman cikisTarihi;
    private int mesafe, kalanMesafe;
    private boolean imha = false;
    private boolean kalkti = false;
    private ArrayList<Kisi> yolcular = new ArrayList<>();
    private String varisTarihi = null;

    public UzayAraci(String ad, String cikis, String varis, Zaman cikisTarihi, int mesafe) {
        setAd(ad);
        setCikis(cikis);
        setVaris(varis);
        setCikisTarihi(cikisTarihi);
        setMesafe(mesafe);
        this.kalanMesafe = getMesafe();
    }

    public void saatIlerle() {
        if (!imha && kalkti && kalanMesafe > 0) {
            kalanMesafe--;
        }
    
        yolcular.removeIf(k -> {
            k.yasat();
            return !k.hayattaMi();
        });
    
        if (!imha && yolcular.isEmpty()) {
            imha = true;
        }
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
        if (varisYaptiMi()) {
            if (this.varisTarihi != null) return this.varisTarihi;
            this.varisTarihi = varisGezegeni.getTarihStr();
            return this.varisTarihi;
        }
        Zaman z = new Zaman(varisGezegeni.getTarih().getGun(), varisGezegeni.getTarih().getAy(), varisGezegeni.getTarih().getYil());
        z.saatIlerle(kalanMesafe, varisGezegeni.getGunSaatSayisi());
        return z.toString();
    }

    
    // Getter ve Setter metodları

    public String getAd() { return ad; }
    public String getCikis() { return cikis; }
    public String getVaris() { return varis; }
    public Zaman getCikisTarihi() { return cikisTarihi; }
    public int getMesafe() { return mesafe; }
    public int getKalanMesafe() { return kalanMesafe; }
    public boolean isImha() { return imha; }
    public boolean isKalkti() { return kalkti; }
    public ArrayList<Kisi> getYolcular() { return yolcular; }

    public void setAd(String ad) { this.ad = ad; }
    public void setCikis(String cikis) { this.cikis = cikis; }
    public void setVaris(String varis) { this.varis = varis; }
    public void setCikisTarihi(Zaman cikisTarihi) { this.cikisTarihi = cikisTarihi; }
    public void setMesafe(int mesafe) { this.mesafe = mesafe; }
    public void setKalanMesafe(int kalanMesafe) { this.kalanMesafe = kalanMesafe; }
    public void setImha(boolean imha) { this.imha = imha; }
    public void setKalkti(boolean kalkti) { this.kalkti = kalkti; }
    public void setYolcular(ArrayList<Kisi> yolcular) { this.yolcular = yolcular; }
}
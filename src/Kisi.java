public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String uzayAraci;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraci) {
        setIsim(isim);
        setYas(yas);
        setKalanOmur(kalanOmur);
        setUzayAraci(uzayAraci);
    }

    public void yasat() {
        if (kalanOmur > 0) kalanOmur--;
    }

    public boolean hayattaMi() {
        return kalanOmur > 0;
    }

    
    // Getter ve Setter metodları

    public String getIsim() { return isim; }
    public int getYas() { return yas; }
    public int getKalanOmur() { return kalanOmur; }
    public String getUzayAraci() { return uzayAraci; }

    public void setIsim(String isim) { this.isim = isim; }
    public void setYas(int yas) { this.yas = yas; }
    public void setKalanOmur(int kalanOmur) { this.kalanOmur = kalanOmur; }
    public void setUzayAraci(String uzayAraci) { this.uzayAraci = uzayAraci; }
}
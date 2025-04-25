public class Kisi {
    public String isim;
    public int yas, kalanOmur;
    public String uzayAraci;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraci) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraci = uzayAraci;
    }

    public void yasat() {
        if (kalanOmur > 0) kalanOmur--;
    }

    public boolean hayattaMi() {
        return kalanOmur > 0;
    }
}
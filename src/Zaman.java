public class Zaman {
    private int gun, ay, yil, saat;
    private final int[] AY_GUNLERI = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Zaman(int gun, int ay, int yil) {
        setGun(gun);
        setAy(ay);
        setYil(yil);
        this.saat = 0;
    }

    public void saatIlerle(int saatSayisi, int gununSaatSayisi) {
        saat += saatSayisi;
        while (saat >= gununSaatSayisi) {
            saat -= gununSaatSayisi;
            gun++;
            if (gun > ayGunSayisi()) {
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
        }
    }

    private int ayGunSayisi() {
        if (ay == 2 && artikYilMi()) return 29;
        return AY_GUNLERI[ay - 1];
    }

    private boolean artikYilMi() {
        return (yil % 4 == 0 && yil % 100 != 0) || (yil % 400 == 0);
    }

    
    // Getter ve Setter metodları

    public int getGun() { return gun; }
    public int getAy() { return ay; }
    public int getYil() { return yil; }
    public int getSaat() { return saat; }

    public void setGun(int gun) { this.gun = gun; }
    public void setAy(int ay) { this.ay = ay; }
    public void setYil(int yil) { this.yil = yil; }
    public void setSaat(int saat) { this.saat = saat; }

    public String toString() {
        return String.format("%02d.%02d.%04d", gun, ay, yil);
    }
}

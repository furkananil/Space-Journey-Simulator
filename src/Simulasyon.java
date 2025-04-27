import java.util.*;

public class Simulasyon {
    private ArrayList<Kisi> kisiler;
    private ArrayList<UzayAraci> araclar;
    private ArrayList<Gezegen> gezegenler;
    private HashMap<String, Gezegen> gezegenMap;

    public void baslat() throws Exception {
        kisiler = DosyaOkuma.kisileriYukle("Kisiler.txt");
        araclar = DosyaOkuma.uzayAraclariniYukle("Araclar.txt");
        gezegenler = DosyaOkuma.gezegenleriYukle("Gezegenler.txt");
        gezegenMap = new HashMap<>();
        for (Gezegen g : gezegenler) gezegenMap.put(g.getAd(), g);

        for (Kisi k : kisiler) {
            for (UzayAraci a : araclar) {
                if (a.getAd().equals(k.getUzayAraci())) a.getYolcular().add(k);
            }
        }

        int toplamSure = 0;
        boolean bitti = false;
        while (!bitti) {
            toplamSure++;
            for (Gezegen g : gezegenler) g.zamanIlerle(1);

            for (UzayAraci a : araclar) {
                Gezegen g = gezegenMap.get(a.getCikis());
                if (!a.isKalkti() && g.getTarih().getGun() == a.getCikisTarihi().getGun() &&
                        g.getTarih().getAy() == a.getCikisTarihi().getAy() &&
                        g.getTarih().getYil() == a.getCikisTarihi().getYil()) {
                    a.setKalkti(true);
                }
            }

            for (UzayAraci a : araclar) {
                a.saatIlerle(); // herkes için saat ilerletiyoruz
            }

            guncelleNufuslar();
            temizle();
            yazdirDurum();
            Thread.sleep(50);

            bitti = araclar.stream().allMatch(a -> a.varisYaptiMi() || a.isImha());
        }

        System.out.println("Simülasyon tamamlandı.");
        System.out.println("Toplam süre: " + toplamSure + " saat.");
    }

    private void temizle() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void guncelleNufuslar() {
        for (Gezegen g : gezegenler) {
            g.nufusSifirla();
        }
    
        for (UzayAraci a : araclar) {
            if (!a.isImha()) { // Sadece IMHA olmayan araçları dikkate al
                String bulundugu = a.isKalkti() ? a.getVaris() : a.getCikis();
                Gezegen g = gezegenMap.get(bulundugu);
    
                for (Kisi k : a.getYolcular()) {
                    if (k.hayattaMi()) {
                        g.nufusArttir();
                    }
                }
            }
        }
    }

    private void yazdirDurum() {
        System.out.println("\nGezegenler:");
    
        // Gezegen adları
        System.out.printf("%-17s", ""); 
        for (Gezegen g : gezegenler) {
            System.out.printf("--- %s ---          ", g.getAd()); 
        }
        System.out.println();
    
        // Gezegen tarihleri
        System.out.printf("%-17s", "Tarih");
        for (Gezegen g : gezegenler) {
            System.out.printf("%-20s", g.getTarihStr());
        }
        System.out.println();
    
        // Gezegen nüfusları
        System.out.printf("%-17s", "Nüfus");
        for (Gezegen g : gezegenler) {
            System.out.printf("%-20d", g.getNufus());
        }
        System.out.println("\n");
    
        // Uzay araçları başlık
        System.out.println("Uzay Araçları:");
        System.out.printf("%-18s %-12s %-10s %-12s %-22s %-25s\n",
                          "Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih");
    
        // Uzay araçları bilgileri
        for (UzayAraci a : araclar) {
            String durum = a.getDurum();
            String kalanSaat = durum.equals("IMHA") ? "--" : String.valueOf(a.getKalanMesafe());
            String tarih = durum.equals("IMHA") ? "--" : a.getVarisTarihi(gezegenMap.get(a.getVaris()));
            System.out.printf("%-18s %-12s %-10s %-12s %-22s %-25s\n",
            a.getAd(), durum, a.getCikis(), a.getVaris(), kalanSaat, tarih);
        }
    }
    
}

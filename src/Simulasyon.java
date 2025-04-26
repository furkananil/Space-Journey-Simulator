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
        for (Gezegen g : gezegenler) gezegenMap.put(g.ad, g);

        for (Kisi k : kisiler) {
            for (UzayAraci a : araclar) {
                if (a.ad.equals(k.uzayAraci)) a.yolcular.add(k);
            }
        }

        boolean bitti = false;
        while (!bitti) {
            for (Gezegen g : gezegenler) g.zamanIlerle(1);

            for (UzayAraci a : araclar) {
                Gezegen g = gezegenMap.get(a.cikis);
                if (!a.kalkti && g.tarih.gun == a.cikisTarihi.gun &&
                        g.tarih.ay == a.cikisTarihi.ay &&
                        g.tarih.yil == a.cikisTarihi.yil) {
                    a.kalkti = true;
                }
            }

            for (UzayAraci a : araclar) {
                if (!a.varisYaptiMi()) a.saatIlerle();
            }

            guncelleNufuslar();
            temizle();
            yazdirDurum();
            Thread.sleep(50);

            bitti = araclar.stream().allMatch(a -> a.varisYaptiMi() || a.imha);
        }

        System.out.println("Simülasyon tamamlandı.");
    }

    private void temizle() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void guncelleNufuslar() {
        for (Gezegen g : gezegenler) g.nufusSifirla();
        for (UzayAraci a : araclar) {
            if (!a.imha && (!a.kalkti || a.varisYaptiMi())) {
                String bulundugu = a.kalkti ? a.varis : a.cikis;
                Gezegen g = gezegenMap.get(bulundugu);
                for (Kisi k : a.yolcular) {
                    if (k.hayattaMi()) g.nufusArttir();
                }
            }
        }
    }

    private void yazdirDurum() {
        System.out.println("\nGezegenler:");
    
        // Gezegen adları
        System.out.printf("%-17s", ""); 
        for (Gezegen g : gezegenler) {
            System.out.printf("--- %s ---           ", g.ad); 
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
            System.out.printf("%-20d", g.nufus);
        }
        System.out.println("\n");
    
        // Uzay araçları başlık
        System.out.println("Uzay Araçları:");
        System.out.printf("%-18s %-12s %-10s %-12s %-22s %-25s\n",
                          "Araç Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih");
    
        // Uzay araçları bilgileri
        for (UzayAraci a : araclar) {
            String durum = a.getDurum();
            String kalanSaat = durum.equals("IMHA") ? "--" : String.valueOf(a.kalanMesafe);
            String tarih = durum.equals("IMHA") ? "--" : a.getVarisTarihi(gezegenMap.get(a.varis));
            System.out.printf("%-18s %-12s %-10s %-12s %-22s %-25s\n",
                    a.ad, durum, a.cikis, a.varis, kalanSaat, tarih);
        }
    }
    
}

import java.util.*;

public class Simulasyon {
    private ArrayList<Kisi> kisiler; // Kişi listesi
    private ArrayList<UzayAraci> araclar; // Uzay aracı listesi
    private ArrayList<Gezegen> gezegenler; // Gezegen listesi
    private HashMap<String, Gezegen> gezegenMap; // Gezegen adlarını anahtar olarak kullanan bir harita


    // Simülasyonu başlatan metod
    public void baslat() throws Exception {
        kisiler = DosyaOkuma.kisileriYukle("Kisiler.txt"); // Kişileri dosyadan yükle
        araclar = DosyaOkuma.uzayAraclariniYukle("Araclar.txt"); // Uzay araçlarını dosyadan yükle
        gezegenler = DosyaOkuma.gezegenleriYukle("Gezegenler.txt"); // Gezegenleri dosyadan yükle
        gezegenMap = new HashMap<>(); 
        for (Gezegen g : gezegenler) gezegenMap.put(g.getAd(), g); // Gezegen adlarını haritaya ekle

        // Kişileri ilgili uzay araçlarına ekle
        for (Kisi k : kisiler) {
            for (UzayAraci a : araclar) {
                if (a.getAd().equals(k.getUzayAraci())) a.getYolcular().add(k);
            }
        }

        boolean bitti = false;
        while (!bitti) {
            for (Gezegen g : gezegenler) g.zamanIlerle(1); // Gezegenlerin 1 saat ilerlet

            for (UzayAraci a : araclar) {
                a.saatIlerle(); // Uzay araçlarını kalktıysa ilerlet
            }

            // İlgili Gezegenin tarihi Uzay Aracın Çıkış tarihine eşit olduğunda Aracı kaldır
            for (UzayAraci a : araclar) {
                Gezegen g = gezegenMap.get(a.getCikis());
                if (!a.isKalkti() && g.getTarih().getGun() == a.getCikisTarihi().getGun() &&
                        g.getTarih().getAy() == a.getCikisTarihi().getAy() &&
                        g.getTarih().getYil() == a.getCikisTarihi().getYil()) {
                    a.setKalkti(true);
                }
            }

            guncelleNufuslar();
            temizle();
            yazdirDurum();
            Thread.sleep(30); // 30 ms bekle

            // Simülasyon bitiş kontrolü. eğer tüm araçlar varış yaptıysa veya imha olduysa simülasyon biter.
            bitti = araclar.stream().allMatch(a -> a.varisYaptiMi() || a.isImha());
        }
        System.out.println("Simülasyon tamamlandı.");
    }

    // Ekranı temizleme işlemi
    private void temizle() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    // Nüfus güncelleme işlemi
    private void guncelleNufuslar() {
        // Gezegenlerin nüfusunu sıfırla
        for (Gezegen g : gezegenler) {
            g.nufusSifirla();
        }
        
        // Uzay araçlarının yolcularını gezegenlere ekle
        for (UzayAraci a : araclar) {
            if (!a.isImha()) { 
                String bulundugu = a.isKalkti() ? a.getVaris() : a.getCikis(); // Bulunduğu gezegen, Araç kalktıysa varış gezegeni, değilse çıkış gezegeni
                Gezegen g = gezegenMap.get(bulundugu);
    
                // Yolcuları gezegene ekle
                for (Kisi k : a.getYolcular()) {
                    if (k.hayattaMi()) {
                        g.nufusArttir();
                    }
                }
            }
        }
    }


    // Bilgilerini konsola yazdırma işlemi
    private void yazdirDurum() {
        System.out.println("\nGezegenler:");
    
        // Gezegen adları
        System.out.printf("%-17s", ""); 
        for (Gezegen g : gezegenler) {
            System.out.printf("--- %s ---           ", g.getAd()); 
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

import java.io.*;
import java.util.*;

public class DosyaOkuma {
    // Dosya okuma işlemleri için gerekli olan metodlar

    // Kişi bilgileri: isim#yas#kalanOmur#uzayAraci
    public static ArrayList<Kisi> kisileriYukle(String path) throws IOException { // Kişileri girilen yola göre listeye ekle ve döndür
        ArrayList<Kisi> kisiler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("#"); // # ile ayır
            kisiler.add(new Kisi(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), p[3]));
        }
        br.close();
        return kisiler;
    }

    
    // Gezegen bilgileri: ad#gunSaatSayisi#tarih
    // Tarih bilgileri: gun.ay.yil
    public static ArrayList<Gezegen> gezegenleriYukle(String path) throws IOException { // Gezegenleri girilen yola göre listeye ekle ve döndür
        ArrayList<Gezegen> gezegenler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("#"); // # ile ayır
            String[] t = p[2].split("\\."); 
            gezegenler.add(new Gezegen(p[0], Integer.parseInt(p[1]), new Zaman(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]))));
        }
        br.close();
        return gezegenler;
    }


    // Uzay Aracı bilgileri: ad#cikis#varis#cikisTarihi#mesafe
    // Cikis Tarihi bilgileri: gun.ay.yil
    public static ArrayList<UzayAraci> uzayAraclariniYukle(String path) throws IOException { // Uzay Araçlarını girilen yola göre listeye ekle ve döndür
        ArrayList<UzayAraci> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split("#"); // # ile ayır
            String[] t = p[3].split("\\.");
            list.add(new UzayAraci(p[0], p[1], p[2], new Zaman(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2])), Integer.parseInt(p[4])));
        }
        br.close();
        return list;
    }
}

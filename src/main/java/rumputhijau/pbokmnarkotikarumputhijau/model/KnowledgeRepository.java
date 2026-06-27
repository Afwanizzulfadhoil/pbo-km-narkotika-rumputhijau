package rumputhijau.pbokmnarkotikarumputhijau.model;

import javax.xml.datatype.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan;

    public  KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Putusan putusan = new Putusan(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        Integer.parseInt(data[4]),
                        data[5],
                        Double.parseDouble(data[6]),
                        data[7],
                        data[8],
                        Integer.parseInt(data[9]),
                        Double.parseDouble(data[10]),
                        data[11]
                );
                daftarPutusan.add(putusan);
            }
        } catch (IOException e) {
            System.out.println("Gagal Membaca File CSV" + e.getMessage());
        }
    }

    public void tambahPutusan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }

    public List<Putusan> getSemuaPutusan() {
        return daftarPutusan;
    }

    public Putusan cariNomorPerkara(String nomorPerkara) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomorPerkara)) {
                return p;
            }
        }
        return null;
    }

    public List<Putusan> cariNamaTerdakwa(String namaTerdakwa) {
        ArrayList<Putusan> hasil =
                new ArrayList<>();

        for (Putusan p:daftarPutusan) {
            if (p.getNamaTerdakwa().toLowerCase().contains(namaTerdakwa.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public boolean updatePutusan(String nomorPerkara, Putusan dataBaru) {
        for (int i = 0; i < daftarPutusan.size(); i++) {
            if (daftarPutusan.get(i).getNomorPerkara().equalsIgnoreCase(nomorPerkara)) {
                daftarPutusan.set(i, dataBaru);
                return true;
            }
        }
        return false;
    }

    public boolean hapusPutusan(String nomorPerkara) {
        return daftarPutusan.removeIf(p -> p.getNomorPerkara().equalsIgnoreCase(nomorPerkara));
    }

    public int getJumlahData() {
        return daftarPutusan.size();
    }
}

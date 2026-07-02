package rumputhijau.pbokmnarkotikarumputhijau.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan;

    public  KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    public void loadFromCSV(String filePath) {
        daftarPutusan.clear();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openCsv(filePath)))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                List<String> data = parseCsvLine(line);
                if (data.size() < 12 ) {
                    continue;
                }

                Putusan putusan = new Putusan(
                        data.get(0),
                        data.get(2),
                        data.get(1),
                        data.get(3),
                        ambilAngkaPertama(data.get(4)),
                        data.get(6),
                        ambilBerat(data.get(4)),
                        data.get(8),
                        data.get(5),
                        ambilAngkaPertama(data.get(9)),
                        ambilNominal(data.get(10)),
                        data.get(11)
                );
                daftarPutusan.add(putusan);
            }
        } catch (IOException e) {
            System.out.println("Gagal Membaca File .CSV" + e.getMessage());
        }
    }

    private InputStream openCsv(String filePath) throws IOException {
        InputStream inputStream = KnowledgeRepository.class
                .getClassLoader()
                .getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IOException("Resurce tidak ditemukan: " + filePath);
        }
        return inputStream;
    }

    private List<String> parseCsvLine (String line) {
        List<String> result = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(value.toString().trim());
                value.setLength(0);
            } else {
                value.append(c);
            }
        }
        result.add(value.toString().trim());
        return result;
    }

    private int ambilAngkaPertama(String text) {
        String angka = text.replaceAll("[0-9].*$", "");

        if (angka.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(angka);
    }

    private double ambilBerat(String text) {
        String angka = text.replaceAll("[>0-9]", "");
        if (angka.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(angka);
    }

    private double ambilNominal(String text) {
        String angka = text.replaceAll("[>0-9]", "");
        if (angka.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(angka);
    }

    public void tambahPutusan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }
    public List<Putusan> getSemuaPutusan() {
        return daftarPutusan;
    }

    public Putusan cariNomorPerkara(String nomorPerkara) {
        for(Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomorPerkara)) {
                return p;
            }
        }
        return null;
    }

    public List<Putusan> cariNamaTerdakwa(String namaTerdakwa) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if(p.getNamaTerdakwa().toLowerCase().contains(namaTerdakwa.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public boolean updatePutusan(String nomorPerkara, Putusan dataBaru) {
        for(int i = 0; i < daftarPutusan.size(); i++) {
            if (daftarPutusan.get(i).getNomorPerkara().equalsIgnoreCase(nomorPerkara)) {
                daftarPutusan.set(i, dataBaru);
                return true;
            }
        }
        return  false;
    }
    public boolean hapusPutusan(String nomorPerkara) {
        return daftarPutusan.removeIf( p -> p.getNomorPerkara().equalsIgnoreCase(nomorPerkara));
        }
        public int getJumlahData() {
        return daftarPutusan.size();
    }
}

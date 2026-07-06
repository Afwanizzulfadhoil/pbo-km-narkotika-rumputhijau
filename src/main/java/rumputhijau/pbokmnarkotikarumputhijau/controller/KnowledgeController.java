package rumputhijau.pbokmnarkotikarumputhijau.controller;

import rumputhijau.pbokmnarkotikarumputhijau.model.KnowledgeRepository;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnowledgeController {
    public final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {
        this.repository = repository;
    }
    public void loadData (String path) {repository.loadFromCSV(path);}

    //Menambahkan putusan baru dari data form
    public boolean tambahPutusan(String[] data) {
        try {
            repository.tambahPutusan(buatPutusanDariForm(data));
            return true;
        } catch (Exception e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
            return false;
        }
    }

    private Putusan buatPutusanDariForm(String[] data) {
        return new Putusan(
                data[0],
                data[1],
                data[2],
                data[3],
                ambilAngkaPertama(data[4]),
                data[5],
                ambilBerat(data[6]),
                data[7],
                data[8],
                ambilAngkaPertama(data[9]),
                ambilNominal(data[10]),
                data[11]
        );
    }

    private double ambilBerat(String text) {
        String berat = text.trim().replace(",", ".").replaceAll("[^0-9.]", "");
        if (berat.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(berat);
    }
    private int ambilAngkaPertama(String text) {
        Matcher matcher = Pattern.compile("\\d+").matcher(text);
        if (!matcher.find()) {
            return 0;
        }
        return Integer.parseInt(matcher.group());
    }

    private double ambilNominal(String text) {
        String angka = text.replaceAll("[^0-9]", "");
        if (angka.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(angka);
    }

    public List<Putusan> tampilkanSemua() {
        return repository.getSemuaPutusan();
    }

    public Putusan cariNomorPerkara(String nomor) {
        return repository.cariNomorPerkara(nomor);
    }

    public List<Putusan> cariNama(String nama) {
        return repository.cariNamaTerdakwa(nama);
    }

    public boolean hapus(String nomor) {
        return repository.hapusPutusan(nomor);
    }

    public boolean update(String nomor, String[] baru) {
        try {
            return repository.updatePutusan(nomor, buatPutusanDariForm(baru));
        } catch (Exception e) {
            System.out.println("Gagal memperbarui data: " + e.getMessage());
            return false;
        }
    }

    public double rataRataVonis() {
        return StatistikPutusan.rataRataVonis(
                new ArrayList<>(repository.getSemuaPutusan())
        );
    }

    public Putusan vonisTerberat() {
        return StatistikPutusan.vonisTerberat(
                new ArrayList<>(repository.getSemuaPutusan())
        );
    }

    public int getJumlahData() {
        return repository.getJumlahData();
    }

    public List<Putusan> getSemuaPutusan() {
        return repository.getSemuaPutusan();
    }

    public double getRataRataVonis() {
        return rataRataVonis();
    }

    public Putusan getVonisTerberat() {
        return vonisTerberat();
    }
}

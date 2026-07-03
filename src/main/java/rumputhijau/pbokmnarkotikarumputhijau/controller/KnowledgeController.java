package rumputhijau.pbokmnarkotikarumputhijau.controller;

import rumputhijau.pbokmnarkotikarumputhijau.model.KnowledgeRepository;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeController {
    public final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {
        this.repository = repository;
    }
    public void loadData (String path) {repository.loadFromCSV(path);}

    //Menambahkan putusan baru dari data form
    public boolean tambahPutusan(String[] data) {
        try {

            Putusan putusan = new Putusan(
                    data[0],                        // nomorPerkara
                    data[1],                        // pengadilan
                    data[2],                        // tanggalPutusan
                    data[3],                        // namaTerdakwa
                    ambilAngkaPertama(data[4]),     // umurTerdakwa
                    data[5],                        // jenisNarkotika
                    ambilBerat(data[4]),            // beratBarangBukti
                    data[7],                        // pasalDilanggar
                    data[8],                        // peranTerdakwa
                    ambilAngkaPertama(data[9]),     // vonisHukuman
                    ambilNominal(data[10]),         // vonisDenda
                    data[11]                        // namaHakim
            );

            repository.tambahPutusan(putusan);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
            return false;
        }
    }

    private double ambilBerat(String text) {
        String berat = text.trim().replaceAll("[^0-9.,].*$", "");
        if (berat.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(berat);
    }
    private int ambilAngkaPertama(String text) {
        String angka = text.trim().replaceAll("[^0-9].*$", "");
        if (angka.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(angka);
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

    public boolean update(String nomor, Putusan baru) {
        return repository.updatePutusan(nomor, baru);
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
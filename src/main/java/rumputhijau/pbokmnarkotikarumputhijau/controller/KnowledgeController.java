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
                    Integer.parseInt(data[4]),      // umurTerdakwa
                    data[5],                        // jenisNarkotika
                    Double.parseDouble(data[6]),    // beratBarangBukti
                    data[7],                        // pasalDilanggar
                    data[8],                        // peranTerdakwa
                    Integer.parseInt(data[9]),      // vonisHukuman
                    Double.parseDouble(data[10]),   // vonisDenda
                    data[11]                        // namaHakim
            );

            repository.tambahPutusan(p);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
            return false;
        }
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
        repository.hapusPutusan(nomor);
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
}
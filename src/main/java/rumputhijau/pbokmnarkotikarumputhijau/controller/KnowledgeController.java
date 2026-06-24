package rumputhijau.pbokmnarkotikarumputhijau.controller;

import rumputhijau.pbokmnarkotikarumputhijau.model.KnowledgeRepository;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;

import java.util.ArrayList;

public class KnowledgeController {
    public final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {
        this.repository = repository;
    }

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

            repository.simpan(putusan);
            return true;
        } catch (Exception e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
            return false;
        }
    }
}

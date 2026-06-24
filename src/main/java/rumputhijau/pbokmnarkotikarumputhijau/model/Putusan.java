package rumputhijau.pbokmnarkotikarumputhijau.model;

public class Putusan {
    private String nomorPerkara;
    private String pengadilan;
    private String tanggalPutusan;
    private String namaTerdakwa;
    private int umurTerdakwa;
    private String jenisNarkotika;
    private double beratBarangBukti;
    private String pasalDilanggar;
    private String peranTerdakwa;
    private int vonisHukuman;
    private double vonisDenda;
    private String namaHakim;

    public Putusan(String nomorPerkara, String pengadilan, String tanggalPutusan, String namaTerdakwa,
                   int umurTerdakwa, String jenisNarkotika, double beratBarangBukti, String pasalDilanggar, String peranTerdakwa,
                   int vonisHukuman, double vonisDenda, String namaHakim) {
        this.nomorPerkara = nomorPerkara;
        this.pengadilan = pengadilan;
        this.tanggalPutusan = tanggalPutusan;
        this.namaTerdakwa = namaTerdakwa;
        this.umurTerdakwa = umurTerdakwa;
        this.jenisNarkotika = jenisNarkotika;
        this.beratBarangBukti = beratBarangBukti;
        this.pasalDilanggar = pasalDilanggar;
        this.peranTerdakwa = peranTerdakwa;
        this.vonisHukuman = vonisHukuman;
        this.vonisDenda = vonisDenda;
        this.namaHakim = namaHakim;
    }
    public String getNomorPerkara() {
        return nomorPerkara;
    }
    public String getNamaTerdakwa() {
        return namaTerdakwa;
    }
    public String getJenisNarkotika() {
        return jenisNarkotika;
    }
    public int getVonisHukuman() {
        return vonisHukuman;
    }
    public double getVonisDenda() {
        return vonisDenda;
    }

    @Override
    public  String toString() {
        return nomorPerkara + " | " + namaTerdakwa " | " + jenisNarkotika + " | " + vonisHukuman + " tahun ";
    }
}

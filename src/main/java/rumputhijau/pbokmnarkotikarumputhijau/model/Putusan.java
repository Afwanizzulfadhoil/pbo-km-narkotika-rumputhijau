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
    private static int jumlahDibuat = 0;

    public Putusan() {
        jumlahDibuat++;
    }

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

        jumlahDibuat++;
    }

    public void tampilkan() {
        System.out.println(this.toString());
    }

    public void tampilkan(boolean detail) {
        if(detail) {
            System.out.println("Nomor Perkara    : " + nomorPerkara);
            System.out.println("Terdakwa         : " + namaTerdakwa);
            System.out.println("Jenis Narkotika  : " + jenisNarkotika);
            System.out.println("Vonis            : " + vonisHukuman + "tahun");
        }
    }

    public String getNomorPerkara() {
        return nomorPerkara;
    }
    public void setNomorPerkara (String nomorPerkara) { this.nomorPerkara = nomorPerkara; }
    public String getNamaTerdakwa() {
        return namaTerdakwa;
    }
    public void setNamaTerdakwa(String namaTerdakwa) {
        this.namaTerdakwa = namaTerdakwa;
    }
    public String getJenisNarkotika() {
        return jenisNarkotika;
    }
    public void setJenisNarkotika (String jenisNarkotika) { this.jenisNarkotika = jenisNarkotika; }
    public int getVonisHukuman() {
        return vonisHukuman;
    }
    public void setVonisHukuman(int vonisHukuman) {
        this.vonisHukuman = vonisHukuman;
    }
    public double getVonisDenda() {
        return vonisDenda;
    }
    public void setVonisDenda(int vonisDenda) {this.vonisDenda = vonisDenda; }
    public String getNamaHakim() { return namaHakim;}
    public void setNamaHakim(String namaHakim) { this.namaHakim = namaHakim; }
    public int getUmurTerdakwa() { return umurTerdakwa;}
    public void setUmurTerdakwa(int umurTerdakwa) { this.umurTerdakwa = umurTerdakwa; }

    @Override
    public  String toString() {
        return nomorPerkara + " | " + namaTerdakwa + " | " + jenisNarkotika + " | " + vonisHukuman + " tahun ";
    }
}

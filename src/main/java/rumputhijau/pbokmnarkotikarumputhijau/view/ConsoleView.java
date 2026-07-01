package rumputhijau.pbokmnarkotikarumputhijau.view;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public int tampilkanMenu(){
        System.out.println("==================== Menu ======================");
        System.out.println("1. Tampilkan Daftar Putusan");
        System.out.println("2. Lihat Detail Putusan (Cari Berdasarkan No. Perkara)");
        System.out.println("3. Tampilkan Statistik Putusan");
        System.out.println("4. Tambah Data Putusan Baru");
        System.out.println("5. Keluar Aplikasi");

        int pilihan = -1;
        try{
            pilihan = scanner.nextInt();
        }catch (Exception e){
            tampilkanPesan(" [Error] Input harus berupa angka! ");
        }
        scanner.nextLine();
        return pilihan;
    }

    public void tampilkanDaftarPutusan(ArrayList<Putusan> list) {
        System.out.println("\n=======================================================================================");
        System.out.println("                                 DAFTAR PUTUSAN HUKUM                                  ");
        System.out.println("=======================================================================================");
        System.out.println("+----------------------+----------------------+----------------------+----------------+");
        System.out.println("| No. Perkara          | Nama Terdakwa        | Jenis Narkotika      | Vonis (Tahun)  |");
        System.out.println("+----------------------+----------------------+----------------------+----------------+");

        if (list == null || list.isEmpty()) {
            System.out.println("|                         Belum ada data putusan tersimpan.                           |");
        } else {
            for (Putusan p : list) {
                System.out.printf("| %-20s | %-20s | %-20s | %-14d |\n", p.getNomorPerkara(), p.getNamaTerdakwa(), p.getJenisNarkotika(), p.getVonisHukuman());
            }
        }
        System.out.println("+----------------------+----------------------+----------------------+----------------+");
        System.out.printf("Jumlah data: %d putusan.\n", list != null ? list.size() : 0);
    }

    public void tampilkanDetail(Putusan p){
        if (p == null){
            System.out.println(" [Error] Data putusan tidak ditemukan! ");
        }
        System.out.println("\n======================================================================");
        System.out.printf("                         DETAIL PUTUSAN %s\n", p.getNomorPerkara());
        System.out.println("======================================================================");
        System.out.println(" Nomer Perkara         : "  + p.getNamaTerdakwa());
        System.out.println("----------------------------------------------------------------------");
        System.out.println(" Nama Terdakwa         : "  + p.getNamaTerdakwa());
        System.out.println(" Jenis Narkotika       : "  + p.getJenisNarkotika());
        System.out.println(" Vonis Hukuman (Tahun) : "  + p.getVonisHukuman() + "Tahun");
        System.out.println(" Vonis Denda           :Rp "  + p.getVonisDenda());
        System.out.println("----------------------------------------------------------------------\n");
        System.out.println("======================================================================");
        System.out.print("[Tekan ENTER untuk kembali ke menu utama]");
        scanner.nextLine();
    }

    public void tampilkanStatistik(StatistikPutusan stat){
        System.out.println("\n======================================================================");
        System.out.println("                     STATISTIK PUTUSAN NARKOTIKA                      ");
        System.out.println("======================================================================");
        if(stat == null){
            System.out.println(" Data statistik belum tersedia! ");
        }else {
            System.out.println(" Total Dataset : 547 Dokumen Putusan");
            System.out.println("\n Kategori Vonis Terbanyak:");
            System.out.println("1. Penjara 1-5 Tahun   : ");
            System.out.println("2. Penjara 5-10 Tahun  : ");
        }
        System.out.println("======================================================================");
        System.out.print("[Tekan ENTER untuk kembali ke menu utama]");
        scanner.nextLine();
    }

    public void tampilkanPesan(String pesan){
        System.out.println("\n" + pesan);
    }

    public String[] inputFormPutusan(Scanner sc){
        System.out.println("\n======================================================================");
        System.out.println("                      INPUT DATA PUTUSAN BARU                         ");
        System.out.println("======================================================================");

        String[] data = new String[12];

        System.out.println("1. Nomor Perkara         : "); data[0] = sc.nextLine();
        System.out.println("2. Pengadilan            : "); data[1] = sc.nextLine();
        System.out.println("3. Tanggal Putusan       : "); data[2] = sc.nextLine();
        System.out.println("4. Nama Terdakwa         : "); data[3] = sc.nextLine();
        System.out.println("5. Umur Terdakwa         : "); data[4] = sc.nextLine();
        System.out.println("6. Jenis Narkotika       : "); data[5] = sc.nextLine();
        System.out.println("7. Berat Barang Bukti    : "); data[6] = sc.nextLine();
        System.out.println("8. Pasal Dilanggar       : "); data[7] = sc.nextLine();
        System.out.println("9. Peran Terdakwa        : "); data[8] = sc.nextLine();
        System.out.println("10. Vonis Hukuman (Tahun): "); data[9] = sc.nextLine();
        System.out.println("11. Vonis Denda          : "); data[10] = sc.nextLine();
        System.out.println("12. Nama Hakim           : "); data[11] = sc.nextLine();
        return data;
    }
}

// acuan
//+ tampilkanMenu() : int
//+ tampilkanDaftarPutusan(list : ArrayList<Putusan>) : void
//+ tampilkanDetail(p : Putusan) : void
//+ tampilkanStatistik(stat : StatistikPutusan) : void
//+ tampilkanPesan(pesan : String) : void
//+ inputFormPutusan(sc : Scanner) : String[]

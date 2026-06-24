package rumputhijau.pbokmnarkotikarumputhijau.view;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
    public int tampilkanMenu(){
        System.out.println("=== KMS PUTUSAN PENGADILAN NARKOTIKA ===");
        System.out.println("1. Tampilkan Daftar Putusan");
        System.out.println("2. Tampilkan Detail Putusan");
        System.out.println("3. Tampilkan Statistik");
        System.out.println("4. Tambah Data Putusan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");

        return 0;
    }

    public void tampilkanDaftarPutusan(ArrayList<Putusan> list){
        System.out.println("=== DAFTAR PUTUSAN ===");


    }

    public void tampilkanDetail(Putusan p){
        System.out.println("=== DETAIL PUTUSAN ===");

//        p.getIndentitasTerdakwa()
    }

    public void tampilkanStatistik(StatistikPutusan stat){
        System.out.println("=== STATISTIK PUTUSAN ===");
//        stat
    }

    public void tampilkanPesan(String pesan){
        System.out.println(pesan);
    }

    public String[] inputFormPutusan(Scanner sc){
        System.out.println("=== INPUT DATA PUTUSAN BARU ===");
        System.out.println("NAMA PUTUSAN: ");
        sc.nextLine();

        String[] data = new String[5];

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

public class ConsoleView {
}

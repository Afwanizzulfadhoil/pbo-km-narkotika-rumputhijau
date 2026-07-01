package rumputhijau.pbokmnarkotikarumputhijau.app;

import rumputhijau.pbokmnarkotikarumputhijau.controller.KnowledgeController;
import rumputhijau.pbokmnarkotikarumputhijau.model.KnowledgeRepository;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.view.ConsoleView;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        KnowledgeRepository repository =
                new KnowledgeRepository();

        KnowledgeController controller =
                new KnowledgeController(repository);

        ConsoleView view =
                new ConsoleView();

        Scanner sc = new Scanner(System.in);

        controller.loadData("resources/Putusan.csv");

        int pilihan;

        do {

            pilihan = view.tampilkanMenu();

            switch (pilihan) {

                case 1:

                    view.tampilkanDaftarPutusan(controller.getSemuaPutusan());

                    break;

                case 2:

                    System.out.print("Masukkan Nomor Perkara : ");

                    String nomor = sc.nextLine();

                    Putusan p = controller.cariNomorPerkara(nomor);

                    view.tampilkanDetail(p);

                    break;

                case 3:

                    System.out.println("Jumlah Data : "
                            + controller.getJumlahData());

                    System.out.println("Rata-rata Vonis : "
                            + controller.getRataRataVonis());

                    System.out.println("Vonis Terberat :");

                    System.out.println(controller.getVonisTerberat());

                    break;

                case 4:

                    String[] data = view.inputFormPutusan(sc);

                    if(controller.tambahPutusan(data)){

                        view.tampilkanPesan("Data berhasil ditambahkan.");

                    }

                    else{

                        view.tampilkanPesan("Data gagal ditambahkan.");

                    }

                    break;

                case 5:

                    System.out.println("Terima kasih.");

                    break;

                default:

                    System.out.println("Menu tidak tersedia.");

            }

        } while (pilihan != 5);

    }
    }
}
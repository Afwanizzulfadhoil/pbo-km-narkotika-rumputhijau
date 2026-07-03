package rumputhijau.pbokmnarkotikarumputhijau.util;

import java.util.Scanner;

public class InputHandler {
    public static int inputInt(String pesan, Scanner sc){
        while(true){
            try{
                System.out.println(pesan);
                return  Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Input harus berupa angka.");
            }
        }
    }
    public static double inputDouble(String pesan, Scanner sc) {

        while (true) {
            try {
                System.out.print(pesan);

                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input tidak boleh kosong.");
                    continue;
                }

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka desimal.");
            }
        }
    }
}

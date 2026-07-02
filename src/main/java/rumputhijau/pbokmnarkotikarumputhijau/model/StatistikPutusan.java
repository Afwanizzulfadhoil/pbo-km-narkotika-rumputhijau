package rumputhijau.pbokmnarkotikarumputhijau.model;
import java.util.ArrayList;

public class StatistikPutusan {
    public static double rataRataVonis(ArrayList<Putusan> data) {
        if (data.isEmpty())
            return 0;
        int total = 0;

        for (Putusan p : data) {
            total += p.getVonisHukuman();
        }
        return (double) total / data.size();
    }
    public static Putusan vonisTerberat (ArrayList<Putusan> data) {
        if (data.isEmpty())
            return null;
        Putusan terberat = data.get(0);

        for (Putusan p : data) {
            if (p.getVonisHukuman() > terberat.getVonisHukuman()) {
                terberat = p;
            }
        }
        return terberat;
    }
}

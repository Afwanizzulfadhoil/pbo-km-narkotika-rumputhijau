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

    }
}
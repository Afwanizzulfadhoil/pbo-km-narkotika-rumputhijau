package rumputhijau.pbokmnarkotikarumputhijau.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rumputhijau.pbokmnarkotikarumputhijau.controller.KnowledgeController;
import rumputhijau.pbokmnarkotikarumputhijau.model.KnowledgeRepository;
import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.view.JavaFXController;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        KnowledgeRepository repository = new KnowledgeRepository();
        KnowledgeController controller = new KnowledgeController(repository);
        controller.loadData("Putusan.csv");

        FXMLLoader fxmlLoader = new FXMLLoader(
                Launcher.class.getResource("/view/JavaFXView.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        JavaFXController viewController = fxmlLoader.getController();

        viewController.setListener(new JavaFXController.ViewListener() {
            @Override
            public void onCariPutusan(String noPerkara) {
                viewController.tampilkanDetail(controller.cariNomorPerkara(noPerkara));
            }

            @Override
            public void onTambahPutusan(String[] data) {
                if (controller.tambahPutusan(data)) {
                    viewController.tampilkanPesan("Data berhasil ditambahkan.");
                    viewController.tampilkanDaftarPutusan(controller.getSemuaPutusan());
                } else {
                    viewController.tampilkanPesan("[Error] Data gagal ditambahkan.");
                }
            }

            @Override
            public void onUpdatePutusan(String nomorPerkaraLama, String[] data) {
                if (controller.update(nomorPerkaraLama, data)) {
                    viewController.tampilkanPesan("Data berhasil diperbarui.");
                    viewController.tampilkanDaftarPutusan(controller.getSemuaPutusan());
                } else {
                    viewController.tampilkanPesan("[Error] Data gagal diperbarui.");
                }
            }

            @Override
            public void onHapusPutusan(String nomorPerkara) {
                if (controller.hapus(nomorPerkara)) {
                    viewController.tampilkanPesan("Data berhasil dihapus.");
                    viewController.tampilkanDaftarPutusan(controller.getSemuaPutusan());
                } else {
                    viewController.tampilkanPesan("[Error] Data gagal dihapus.");
                }
            }

            @Override
            public void onMenuSelected(int pilihan) {
                if (pilihan == 1) {
                    viewController.tampilkanDaftarPutusan(controller.getSemuaPutusan());
                } else if (pilihan == 3) {
                    viewController.tampilkanStatistik(
                            controller.getJumlahData(),
                            controller.getRataRataVonis(),
                            controller.getVonisTerberat()
                    );
                }
            }
        });

        viewController.tampilkanDaftarPutusan(controller.getSemuaPutusan());

        stage.setTitle("Putusan Narkotika");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
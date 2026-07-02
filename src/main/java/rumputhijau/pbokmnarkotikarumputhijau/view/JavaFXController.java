package rumputhijau.pbokmnarkotikarumputhijau.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;

import java.util.ArrayList;
import java.util.List;

public class JavaFXController {
    // navigation
    @FXML private  TabPane mainTabPane;
    @FXML private  Tab tabDaftar, tabDetail, tabStatistik, tabInput;

    //list of data putusan
    @FXML private TableView<Putusan> tablePutusan;
    @FXML private TableColumn<Putusan, String> colNoPerkara;
    @FXML private TableColumn<Putusan, String> colNamaTerdakwa;
    @FXML private TableColumn<Putusan, String> colJenisNarkotika;
    @FXML private TableColumn<Putusan, Integer> colVonis;
    @FXML private Label lblJumlahData;

    // search and detail
    @FXML private TextField txtCariNoPerkara;
    @FXML private Label lblDetailNoPerkara, lblDetailTerdakwa, lblDetailNarkotika, lblDetailVonis, lblDetailDenda;

    // Statistics
    @FXML private Label lblTotalDataset;
    @FXML private Label lblKategoriVonis;

    // Input
    @FXML private TextField tfNoPerkara, tfPengadilan, tfTglPutusan, tfNamaTerdakwa, tfUmur;
    @FXML private TextField tfJenisNarkotika, tfBeratBB, tfPasal, tfPeran, tfVonisHukuman, tfVonisDenda, tfNamaHakim;

    private ViewListener listener;

    public interface ViewListener{
        void onCariPutusan(String noPerkara);
        void onTambahPutusan(String[] data);
        void onMenuSelected(int pilihan);
    }

    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    @FXML
    public void initialize(){
        // Inisialisasi kolom tabel agar membaca property dari model Putusan
        colNoPerkara.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));
        colNamaTerdakwa.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));
        colJenisNarkotika.setCellValueFactory(new PropertyValueFactory<>("jenisNarkotika"));
        colVonis.setCellValueFactory(new PropertyValueFactory<>("vonisHukuman"));

        // Listener saat user berpindah tab (Menggantikan fungsi menu di console)
        mainTabPane.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (listener != null) {
                // Konversi index tab menjadi nomor menu (1-3)
                listener.onMenuSelected(newValue.intValue() + 1);
            }
        });
    }

    public void tampilkanDaftarPutusan(List<Putusan> list){
        if(list == null || list.isEmpty()){
            tablePutusan.setPlaceholder(new Label("Belum ada data putusan tersimpan."));
            tablePutusan.setItems(FXCollections.observableArrayList());
            lblJumlahData.setText("Jumlah data: 0 putusan.");
        }else {
            ObservableList<Putusan> dataTable = FXCollections.observableArrayList(list);
            tablePutusan.setItems(dataTable);
            lblJumlahData.setText("Jumlah data: " + list.size() + " putusan.");
        }
    }

    public void tampilkanDetail(Putusan p) {
        if (p == null) {
            tampilkanPesan("[Error] Data putusan tidak ditemukan!");
            lblDetailNoPerkara.setText("-");
            lblDetailTerdakwa.setText("-");
            lblDetailNarkotika.setText("-");
            lblDetailVonis.setText("-");
            lblDetailDenda.setText("-");
        } else {
            mainTabPane.getSelectionModel().select(tabDetail); // Pindah ke tab detail otomatis
            lblDetailNoPerkara.setText(p.getNomorPerkara());
            lblDetailTerdakwa.setText(p.getNamaTerdakwa());
            lblDetailNarkotika.setText(p.getJenisNarkotika());
            lblDetailVonis.setText(p.getVonisHukuman() + " Tahun");
            lblDetailDenda.setText("Rp " + p.getVonisDenda());
        }
    }

    public void tampilkanStatistik(int totalData, double rataRataVonis, Putusan vonisTerberat) {
        if (totalData == 0) {
            lblTotalDataset.setText("Data statistik belum tersedia!");
            lblKategoriVonis.setText("");
        } else {
            lblTotalDataset.setText("Total Dataset : " + totalData + " Dokumen Putusan");
            lblKategoriVonis.setText(
                    "Rata-rata Vonis: " + String.format("%.2f", rataRataVonis) + " tahun\n"
                            + "Vonis Terberat: " + vonisTerberat
            );
        }
    }

    public void tampilkanPesan(String pesan) {
        Alert alert = new Alert(pesan.contains("[Error]") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    @FXML
    private void handleSimpanPutusan() {
        String[] data = new String[12];
        data[0] = tfNoPerkara.getText();
        data[1] = tfPengadilan.getText();
        data[2] = tfTglPutusan.getText();
        data[3] = tfNamaTerdakwa.getText();
        data[4] = tfUmur.getText();
        data[5] = tfJenisNarkotika.getText();
        data[6] = tfBeratBB.getText();
        data[7] = tfPasal.getText();
        data[8] = tfPeran.getText();
        data[9] = tfVonisHukuman.getText();
        data[10] = tfVonisDenda.getText();
        data[11] = tfNamaHakim.getText();

        if (data[0].trim().isEmpty() || data[3].trim().isEmpty()) {
            tampilkanPesan("[Error] Nomor Perkara dan Nama Terdakwa tidak boleh kosong!");
            return;
        }

        if (listener != null) {
            listener.onTambahPutusan(data);
            clearFormInput();
        }
    }

    @FXML
    private void handleCariPutusan() {
        String keyword = txtCariNoPerkara.getText();
        if (listener != null && !keyword.trim().isEmpty()) {
            listener.onCariPutusan(keyword);
        } else {
            tampilkanPesan("[Error] Masukkan nomor perkara terlebih dahulu!");
        }
    }

    private void clearFormInput() {
        TextField[] fields = {tfNoPerkara, tfPengadilan, tfTglPutusan, tfNamaTerdakwa, tfUmur,
                tfJenisNarkotika, tfBeratBB, tfPasal, tfPeran, tfVonisHukuman, tfVonisDenda, tfNamaHakim};
        for (TextField f : fields) f.clear();
    }
}
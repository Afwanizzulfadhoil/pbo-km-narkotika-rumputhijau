package rumputhijau.pbokmnarkotikarumputhijau.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import rumputhijau.pbokmnarkotikarumputhijau.model.Putusan;
import rumputhijau.pbokmnarkotikarumputhijau.model.StatistikPutusan;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class JavaFXController {
    @FXML private  TabPane mainTabPane;
    @FXML private  Tab tabDaftar, tabDetail, tabStatistik, tabInput;

    //list of data putusan
    @FXML private TableView<Putusan> tablePutusan;
    @FXML private TableColumn<Putusan, String> colNoPerkara;
    @FXML private TableColumn<Putusan, String> colPengadilan;
    @FXML private TableColumn<Putusan, String> colTahunPerkara;
    @FXML private TableColumn<Putusan, String> colNamaTerdakwa;
    @FXML private TableColumn<Putusan, Integer> colUmur;
    @FXML private TableColumn<Putusan, String> colPeran;
    @FXML private TableColumn<Putusan, String> colJenisNarkotika;
    @FXML private TableColumn<Putusan, String> colBeratBB;
    @FXML private TableColumn<Putusan, String> colPasal;
    @FXML private TableColumn<Putusan, Integer> colVonis;
    @FXML private TableColumn<Putusan, Double> colDenda;
    @FXML private TableColumn<Putusan, String> colNamaHakim;
    @FXML private Label lblJumlahData;

    // search and detail
    @FXML private TextField txtCariNoPerkara;
    @FXML private Label lblDetailNoPerkara, lblDetailTerdakwa, lblDetailNarkotika, lblDetailVonis, lblDetailDenda;
    @FXML private Label lblDetailPasal, lblDetailHakim, lblDetailPeran, lblDetailBeratBB, lblDetailPengadilan, lblDetailTahun, lblDetailUmur;

    // Statistics
    @FXML private Label lblTotalDataset;
    @FXML private Label lblKategoriVonis;

    // Input
    @FXML private TextField tfNoPerkara, tfPengadilan, tfTglPutusan, tfNamaTerdakwa, tfUmur;
    @FXML private TextField tfJenisNarkotika, tfBeratBB, tfPasal, tfPeran, tfVonisHukuman, tfVonisDenda, tfNamaHakim;

    private ViewListener listener;
    private String nomorPerkaraDipilih;
    private final DecimalFormat formatRupiah = buatFormatRupiah();

    public interface ViewListener{
        void onCariPutusan(String noPerkara);
        void onTambahPutusan(String[] data);
        void onUpdatePutusan(String nomorPerkaraLama, String[] data);
        void onHapusPutusan(String nomorPerkara);
        void onMenuSelected(int pilihan);
    }

    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    @FXML
    public void initialize(){
        // Inisialisasi kolom tabel agar membaca property dari model Putusan
        colNoPerkara.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));
        colPengadilan.setCellValueFactory(new PropertyValueFactory<>("pengadilan"));
        colTahunPerkara.setCellValueFactory(new PropertyValueFactory<>("tanggalPutusan"));
        colNamaTerdakwa.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));
        colUmur.setCellValueFactory(new PropertyValueFactory<>("umurTerdakwa"));
        colPeran.setCellValueFactory(new PropertyValueFactory<>("peranTerdakwa"));
        colJenisNarkotika.setCellValueFactory(new PropertyValueFactory<>("jenisNarkotika"));
        colBeratBB.setCellValueFactory(new PropertyValueFactory<>("beratBarangBukti"));
        colPasal.setCellValueFactory(new PropertyValueFactory<>("pasalDilanggar"));
        colVonis.setCellValueFactory(new PropertyValueFactory<>("vonisHukuman"));
        colDenda.setCellValueFactory(new PropertyValueFactory<>("vonisDenda"));
        colNamaHakim.setCellValueFactory(new PropertyValueFactory<>("namaHakim"));
        colDenda.setCellFactory(column -> new TableCell<Putusan, Double>() {
            @Override
            protected void updateItem(Double denda, boolean empty) {
                super.updateItem(denda, empty);
                setText(empty || denda == null ? null : formatDenda(denda));
            }
        });

        tablePutusan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, putusan) -> {
            if (putusan != null) {
                nomorPerkaraDipilih = putusan.getNomorPerkara();
            }
        });

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
            nomorPerkaraDipilih = p.getNomorPerkara();
            lblDetailNoPerkara.setText(p.getNomorPerkara());
            lblDetailTerdakwa.setText(p.getNamaTerdakwa());
            lblDetailNarkotika.setText(p.getJenisNarkotika());
            lblDetailVonis.setText(p.getVonisHukuman() + " Tahun");
            lblDetailDenda.setText(formatDenda(p.getVonisDenda()));
            lblDetailPasal.setText(p.getPasalDilanggar());
            lblDetailHakim.setText(p.getNamaHakim());
            lblDetailPeran.setText(p.getPeranTerdakwa());
            lblDetailBeratBB.setText(p.getBeratBarangBukti());
            lblDetailPengadilan.setText(p.getPengadilan());
            lblDetailTahun.setText(p.getTanggalPutusan());
            lblDetailUmur.setText(p.getUmurTerdakwa() + " Tahun");
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

    @FXML
    private void handleUpdatePutusan() {
        String nomorTarget = getNomorTarget();
        if (nomorTarget == null || nomorTarget.trim().isEmpty()) {
            tampilkanPesan("[Error] Pilih putusan dari tabel atau cari putusan terlebih dahulu!");
            return;
        }

        String[] data = getFormData();
        if (data[0].trim().isEmpty() || data[3].trim().isEmpty()) {
            tampilkanPesan("[Error] Nomor Perkara dan Nama Terdakwa tidak boleh kosong!");
            return;
        }

        if (listener != null) {
            listener.onUpdatePutusan(nomorTarget, data);
        }
    }

    @FXML
    private void handleHapusPutusan() {
        String nomorTarget = getNomorTarget();
        if (nomorTarget == null || nomorTarget.trim().isEmpty()) {
            tampilkanPesan("[Error] Pilih putusan dari tabel atau isi Nomor Perkara terlebih dahulu!");
            return;
        }

        Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION);
        konfirmasi.setHeaderText(null);
        konfirmasi.setContentText("Hapus putusan dengan nomor perkara: " + nomorTarget + "?");
        if (konfirmasi.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK && listener != null) {
            listener.onHapusPutusan(nomorTarget);
        }
    }

    @FXML
    private void handleEditTerpilih() {
        Putusan putusan = tablePutusan.getSelectionModel().getSelectedItem();
        if (putusan == null) {
            tampilkanPesan("[Error] Pilih data putusan pada tabel terlebih dahulu!");
            return;
        }

        isiFormInput(putusan);
        mainTabPane.getSelectionModel().select(tabInput);
    }

    private void clearFormInput() {
        TextField[] fields = {tfNoPerkara, tfPengadilan, tfTglPutusan, tfNamaTerdakwa, tfUmur,
                tfJenisNarkotika, tfBeratBB, tfPasal, tfPeran, tfVonisHukuman, tfVonisDenda, tfNamaHakim};
        for (TextField f : fields) f.clear();
    }



    private String[] getFormData() {
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
        return data;
    }

    private void isiFormInput(Putusan p) {
        nomorPerkaraDipilih = p.getNomorPerkara();
        tfNoPerkara.setText(p.getNomorPerkara());
        tfPengadilan.setText(p.getPengadilan());
        tfTglPutusan.setText(p.getTanggalPutusan());
        tfNamaTerdakwa.setText(p.getNamaTerdakwa());
        tfUmur.setText(String.valueOf(p.getUmurTerdakwa()));
        tfJenisNarkotika.setText(p.getJenisNarkotika());
        tfBeratBB.setText(p.getBeratBarangBukti());
        tfPasal.setText(p.getPasalDilanggar());
        tfPeran.setText(p.getPeranTerdakwa());
        tfVonisHukuman.setText(String.valueOf(p.getVonisHukuman()));
        tfVonisDenda.setText(formatDendaTanpaRp(p.getVonisDenda()));
        tfNamaHakim.setText(p.getNamaHakim());
    }

    private String getNomorTarget() {
        if (nomorPerkaraDipilih != null && !nomorPerkaraDipilih.trim().isEmpty()) {
            return nomorPerkaraDipilih;
        }
        if (tfNoPerkara.getText() != null && !tfNoPerkara.getText().trim().isEmpty()) {
            return tfNoPerkara.getText();
        }
        Putusan putusan = tablePutusan.getSelectionModel().getSelectedItem();
        return putusan == null ? null : putusan.getNomorPerkara();
    }

    private DecimalFormat buatFormatRupiah() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,##0", symbols);
        formatter.setGroupingUsed(true);
        return formatter;
    }

    private String formatDenda(double denda) {
        return "Rp " + formatRupiah.format(denda);
    }

    private String formatDendaTanpaRp(double denda) {
        return formatRupiah.format(denda);
    }
}
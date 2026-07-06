# pbo-km-narkotika-rumputhijau

## Deskripsi Proyek
Aplikasi Manajemen Data Putusan Narkotika adalah perangkat lunak berbasis Java yang dirancang untuk mendata, mengelola, dan menampilkan dokumen putusan pengadilan terkait kasus narkotika. Proyek ini dibangun dengan menerapkan desain arsitektur **MVC (Model-View-Controller)** yang terstruktur.

Aplikasi ini menyediakan dua jenis antarmuka (*user interface*):
1. **Console (CLI)**: Antarmuka berbasis teks untuk navigasi yang ringan dan cepat.
2. **GUI (JavaFX)**: Antarmuka visual yang interaktif dan *user-friendly*.

**Fitur Utama:**
* Menampilkan daftar putusan (Dataset minimal 50 dokumen).
* Fitur CRUD (Create, Read, Update, Delete) untuk data putusan.
* Pencarian detail putusan berdasarkan Nomor Perkara.
* Menampilkan ringkasan statistik putusan (total dataset, rata-rata vonis, dll).
* Format *output* yang rapi, termasuk format Rupiah untuk denda.

## Cara Kompilasi
Karena proyek ini menggunakan antarmuka grafis **JavaFX**, proses kompilasi sangat bergantung pada *environment* yang digunakan. Berikut adalah rekomendasi kompilasi menggunakan IDE:

**Menggunakan IDE (IntelliJ IDEA / NetBeans / Eclipse):**
1. *Clone* repositori ini ke komputer lokal Anda:
   `git clone https://github.com/Afwanizzulfadhoil/pbo-km-narkotika-rumputhijau.git`
2. Buka proyek melalui IDE pilihan Anda.
3. Pastikan JDK (minimal Java 11 atau yang lebih disarankan menggunakan jdk terbaru) sudah terkonfigurasi pada *Project Structure*.
4. Pastikan *library* JavaFX SDK sudah ditambahkan ke dalam *Module Dependencies* atau Anda telah mengatur *build tool* (seperti Maven/Gradle) agar mengunduh *dependency* JavaFX secara otomatis.
5. Lakukan proses *Build Project* pada menu IDE.

## Cara Menjalankan
1. Setelah proses kompilasi berhasil tanpa *error*, cari *class* utama (*Main Class*) yang berisi fungsi `public static void main(String[] args)`.
2. Klik kanan pada *class* tersebut lalu pilih **Run 'Main'**.
3. Jika menggunakan JavaFX modular yang tidak terkonfigurasi melalui *build tool*, pastikan Anda menambahkan *VM Options* berikut saat menjalankan:
   `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml`
4. Aplikasi akan terbuka. Anda bisa mulai berinteraksi dengan tabel putusan atau menu yang tersedia.

## Video Demo Aplikasi
*(Link video presentasi YouTube/Google Drive kelompok rumputhijau)*
- [Tonton Video Demo Aplikasi di Sini](https://youtu.be/LUnKEWXFCJA)

## Daftar Anggota Kelompok

| Nama anggota                             | NIM             | Kelas Anggota |
| ---------------------------------------- | ----------------| ------------- |
| Afwan Izzul Fadhoil                      | 202510370110082 | C             |
| Ibrahim Ba Ahmad Nizham Putra Wananta    | 202510370110084 | C             |
| Firjatullah Mahrus Triadi                | 202510370110107 | C             |

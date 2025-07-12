package pbo1.uas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class Buku untuk menyimpan informasi tentang buku
class Buku {
    private String judul;
    private String penulis;
    private boolean tersedia;
    private final int id;

    // Constructor untuk inisialisasi buku
    public Buku(int id, String judul, String penulis) {
        this.id = id;  // Menambahkan ID unik untuk buku
        this.judul = judul;
        this.penulis = penulis;
        this.tersedia = true; // Status default: tersedia
    }

    // Getter dan Setter untuk atribut
    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    // Method untuk menampilkan informasi buku
    public String tampilkanInfo() {
        return "ID: " + id + "\nJudul: " + judul + "\nPenulis: " + penulis + "\nTersedia: " + (tersedia ? "Ya" : "Tidak");
    }
}

// Class Perpustakaan untuk mengelola koleksi buku
class Perpustakaan {
    private final List<Buku> bukuList;
    private int counterBuku;

    public Perpustakaan() {
        bukuList = new ArrayList<>();
        counterBuku = 1;  // Untuk menghasilkan ID unik bagi setiap buku
    }

    // Method untuk menambah buku ke perpustakaan
    public void tambahBuku(String judul, String penulis) {
        bukuList.add(new Buku(counterBuku++, judul, penulis));  // Menambahkan ID buku
        System.out.println("Buku berhasil ditambahkan!");
    }

    // Method untuk menghapus buku berdasarkan judul
    public void hapusBuku(String judul) {
        Buku bukuUntukDihapus = null;
        for (Buku buku : bukuList) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                bukuUntukDihapus = buku;
                break;
            }
        }
        if (bukuUntukDihapus != null) {
            bukuList.remove(bukuUntukDihapus);
            System.out.println("Buku berhasil dihapus!");
        } else {
            System.out.println("Buku tidak ditemukan!");
        }
    }

    // Method untuk mencari buku berdasarkan judul atau penulis
    public void cariBuku(String kataKunci) {
        boolean ditemukan = false;
        for (Buku buku : bukuList) {
            if (buku.getJudul().toLowerCase().contains(kataKunci.toLowerCase()) ||
                buku.getPenulis().toLowerCase().contains(kataKunci.toLowerCase())) {
                System.out.println(buku.tampilkanInfo());
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada buku yang ditemukan dengan kata kunci: " + kataKunci);
        }
    }

    // Method untuk menampilkan semua buku yang ada di perpustakaan
    public void tampilkanBuku() {
        if (bukuList.isEmpty()) {
            System.out.println("Tidak ada buku yang tersedia di perpustakaan.");
        } else {
            for (Buku buku : bukuList) {
                System.out.println(buku.tampilkanInfo());
            }
        }
    }
}

// Class utama untuk menjalankan sistem manajemen perpustakaan
public class SistemManajemenPerpustakaan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan();

        while (true) {
            System.out.println("\nSistem Manajemen Perpustakaan");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Tampilkan Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan;

            // Validasi input menu
            if(scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine();  // Mengonsumsi newline
            } else {
                System.out.println("Opsi tidak valid! Silakan masukkan angka yang valid.");
                scanner.nextLine();  // Mengonsumsi input yang tidak valid
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Judul Buku: ");
                    String judul = scanner.nextLine();
                    System.out.print("Masukkan Nama Penulis: ");
                    String penulis = scanner.nextLine();
                    perpustakaan.tambahBuku(judul, penulis);
                    break;

                case 2:
                    System.out.print("Masukkan judul buku yang ingin dihapus: ");
                    String hapusJudul = scanner.nextLine();
                    perpustakaan.hapusBuku(hapusJudul);
                    break;

                case 3:
                    System.out.print("Masukkan judul atau penulis untuk mencari: ");
                    String kataKunci = scanner.nextLine();
                    perpustakaan.cariBuku(kataKunci);
                    break;

                case 4:
                    perpustakaan.tampilkanBuku();
                    break;

                case 5:
                    System.out.println("Keluar...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opsi tidak valid! Silakan coba lagi.");
            }
        }
    }
}

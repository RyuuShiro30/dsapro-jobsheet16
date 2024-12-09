import java.util.Scanner;

public class SistemManajemenKafe {
    static String[][] daftarMenu = {
            {"Kopi Hitam", "15000"},
            {"Latte", "22000"},
            {"Teh Tarik", "15000"},
            {"Mie Goreng", "18000"},
            {"Americano", "22000"}
    };

    static String[][] pesanan = new String[100][5];
    static int jumlahPesanan = 0;
    static final int KAPASITAS_MAX = 100;

    public static void tampilkanDaftarMenu() {
        System.out.println("\n--- Daftar Menu ---");
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.printf("%d. %s - Rp %s\n", i + 1, daftarMenu[i][0], daftarMenu[i][1]);
        }
    }

    public static void tambahPesanan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();
        System.out.print("Masukkan nomor meja: ");
        int nomorMeja = Integer.parseInt(scanner.nextLine());

        tampilkanDaftarMenu();

        double totalHargaTransaksi = 0;

        while (true) {
            System.out.print("Pilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
            int pilihanMenu = scanner.nextInt();
            scanner.nextLine(); 

            if (pilihanMenu == 0) {
                break;
            }

            if (pilihanMenu < 1 || pilihanMenu > daftarMenu.length) {
                System.out.println("Pilihan menu tidak valid. Silakan coba lagi.");
                continue;
            }

            String namaMenu = daftarMenu[pilihanMenu - 1][0];
            double hargaMenu = Double.parseDouble(daftarMenu[pilihanMenu - 1][1]);

            System.out.print("Masukkan jumlah item: ");
            int jumlahItem = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (jumlahItem <= 0) {
                System.out.println("Jumlah item harus lebih besar dari 0.");
                continue;
            }

            double totalHargaItem = hargaMenu * jumlahItem;
            totalHargaTransaksi += totalHargaItem;

            if (jumlahPesanan >= KAPASITAS_MAX) {
                System.out.println("Kapasitas pesanan maksimal telah tercapai.");
                break;
            }

            pesanan[jumlahPesanan][0] = namaPelanggan;
            pesanan[jumlahPesanan][1] = Integer.toString(nomorMeja);
            pesanan[jumlahPesanan][2] = namaMenu;
            pesanan[jumlahPesanan][3] = Integer.toString(jumlahItem);
            pesanan[jumlahPesanan][4] = String.format("%.0f", totalHargaItem);
            jumlahPesanan++;

            System.out.println("Pesanan berhasil ditambahkan.");
        }

        System.out.println("\n===== DAFTAR PESANAN =====");
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Nomor Meja: " + nomorMeja);
        System.out.println("Detail Pesanan:");
        for (int i = 0; i < jumlahPesanan; i++) {
            System.out.printf("- %s x %s = Rp %s\n", pesanan[i][2], pesanan[i][3], pesanan[i][4]);
        }
        System.out.printf("Total Harga Pesanan: Rp %.0f\n", totalHargaTransaksi);
    }

    public static void tampilkanDaftarPesanan() {
        if (jumlahPesanan == 0) {
            System.out.println("Belum ada pesanan yang tercatat.");
            return;
        }

        System.out.println("\n--- Daftar Pesanan ---");
        double totalSemuaPesanan = 0;
        for (int i = 0; i < jumlahPesanan; i++) {
            for (int j = 0; j < pesanan[i].length; j++) {
                System.out.print(pesanan[i][j] + " | ");
            }
            totalSemuaPesanan += Double.parseDouble(pesanan[i][4]);
            System.out.println();
        }
        System.out.printf("Total Harga Semua Pesanan: Rp %.0f\n", totalSemuaPesanan);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Sistem Manajemen Kafe ---");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");

            Scanner scanner = new Scanner(System.in);
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    tambahPesanan();
                    break;
                case "2":
                    tampilkanDaftarPesanan();
                    break;
                case "3":
                    System.out.println("Terima kasih. Sampai jumpa!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
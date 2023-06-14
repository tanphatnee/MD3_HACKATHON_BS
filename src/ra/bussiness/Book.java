    package ra.bussiness;

    import java.util.Scanner;

    public class Book {
        private static int currentId = 0;
        private int bookId;
        private String bookName;
        private String author;
        private String descriptions;
        private double importPrice;
        private double exportPrice;
        private float interest;
        private boolean bookStatus;

        public Book() {
            this.bookId = ++currentId;
            this.bookName = "";
            this.author = "";
            this.descriptions = "";
            this.importPrice = 0.0;
            this.exportPrice = 0.0;
            this.interest = 0.0f;
            this.bookStatus = true;
        }

        public int getBookId() {
            return bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public String getAuthor() {
            return author;
        }

        public String getDescriptions() {
            return descriptions;
        }

        public double getImportPrice() {
            return importPrice;
        }

        public double getExportPrice() {
            return exportPrice;
        }

        public float getInterest() {
            return interest;
        }

        public boolean isBookStatus() {
            return bookStatus;
        }


        public void inputData() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("\033[1;34m Tên sách: \033[0m");
            bookName = scanner.nextLine();

            System.out.print("\033[1;34m Tác giả: \033[0m");
            author = scanner.nextLine();

            System.out.print("\033[1;34m Mô tả về sách (ít nhất 10 ký tự): \033[0m");
            descriptions = scanner.nextLine();
            while (!validateDescriptions(descriptions)) {
                System.out.println("\033[1;31m Mô tả không hợp lệ, vui lòng nhập lại. \033[0m");
                descriptions = scanner.nextLine();
            }

            System.out.print("\033[1;34m Giá nhập: \033[0m");
            importPrice = scanner.nextDouble();
            while (!validateImportPrice(importPrice)) {
                System.out.println("\033[1;31m Giá nhập không hợp lệ, vui lòng nhập lại.\033[0m");
                importPrice = scanner.nextDouble();
            }

            System.out.print("\033[1;34m Giá xuất (phải lớn hơn 1.2 lần giá nhập): \033[0m");
            exportPrice = scanner.nextDouble();
            while (!validateExportPrice(importPrice, exportPrice)) {
                System.out.println("\033[1;31m Giá xuất không hợp lệ, vui lòng nhập lại.\033[0m");
                exportPrice = scanner.nextDouble();
            }

            interest = (float) (exportPrice - importPrice);

            scanner.nextLine(); // Loại bỏ kí tự xuống dòng thừa

            System.out.print("\033[1;34m Trạng thái (true - Còn sách, false - Hết sách): \033[0m");
            bookStatus = scanner.nextBoolean();
        }


        public void displayData() {
            String format = "| %-8s | %-20s | %-20s | %-30s | %-10s | %-10s | %-10s | %-10s |\n";
            String line = "+----------+----------------------+----------------------+--------------------------------+------------+------------+------------+------------+\n";

            System.out.println(line);
            System.out.printf(format, "Mã sách", "Tên sách", "Tác giả", "Mô tả", "Giá nhập", "Giá xuất", "Lợi nhuận", "Trạng thái");
            System.out.println(line);
            System.out.printf(format, bookId, bookName, author, descriptions, importPrice, exportPrice, interest, (bookStatus ? "Còn sách" : "Hết sách"));
            System.out.println(line);
        }



        private static boolean validateDescriptions(String descriptions) {
            return descriptions.length() >= 10;
        }


        private static boolean validateImportPrice(double importPrice) {
            return importPrice > 0;
        }


        private static boolean validateExportPrice(double importPrice, double exportPrice) {
            return exportPrice > importPrice * 1.2;
        }
    }

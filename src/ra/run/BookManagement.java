package ra.run;

import ra.bussiness.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BookManagement {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    sortBooksByInterest();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchBooks();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    System.out.println("\033[1;31m Chương trình đã kết thúc. \033[0m");
                    break;
                default:
                    System.out.println("\033[1;31m Lựa chọn không hợp lệ, vui lòng chọn lại. \033[0m");
            }
        } while (choice != 7);
    }


    private static void displayMenu() {
        System.out.println("\033[1;34m ****************___PHAT-MENU___*************** \033[0m");
        System.out.println("\033[1;34m1. Nhập số lượng Sách thêm mới và nhập thông tin cho từng cuốn Sách. ");
        System.out.println("2. Hiển thị thông tin tất cả Sách trong thư viện.");
        System.out.println("3. Sắp xếp Sách theo lợi nhuận tăng dần. ");
        System.out.println("4. Xóa Sách theo mã Sách. ");
        System.out.println("5. Tìm kiếm tương đối Sách theo tên Sách hoặc mô tả. ");
        System.out.println("6. Thay đổi thông tin Sách theo mã Sách. ");
        System.out.println("7. Thoát !");
        System.out.print("Nhập lựa chọn của bạn : \033[0m");
    }



    private static void addBooks() {
        System.out.print("\033[1;34m Nhập số sách cần thêm: \033[0m");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("\033[1;34m Nhập thông tin sách thứ " + (i + 1) + ":\033[0m");
            Book book = new Book();
            book.inputData();
            books.add(book);
            System.out.println("\033[1;34m Sách đã được thêm thành công.\033[0m");
        }
    }


    private static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("\033[1;31m Danh sách Sách trống. \033[0m");
        } else {
            System.out.println("\033[1;34m Danh sách Sách trong thư viện:\033[0m");
            for (Book book : books) {
                book.displayData();
                System.out.println();
            }
        }
    }


    private static void sortBooksByInterest() {
        if (books.isEmpty()) {
            System.out.println("\033[1;31m Danh Sách sách trống. \033[0m");
        } else {
            Collections.sort(books, Comparator.comparingDouble(Book::getInterest));

            System.out.println("\033[1;34m Danh Sách sách đã được sắp xếp theo lợi nhuận tăng dần:\033[0m");
            for (Book book : books) {
                book.displayData();
                System.out.println();
            }
        }
    }


    private static void deleteBook() {
        if (books.isEmpty()) {
            System.out.println("\033[1;31m Danh sách Sách trống. \033[0m");
        } else {
            System.out.print("\033[1;34m Nhập mã Sách cần xóa: \033[0m");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            boolean isDeleted = false;
            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    books.remove(book);
                    isDeleted = true;
                    System.out.println("\033[1;34m Sách có mã " + bookId + " đã được xóa. \033[0m");
                    break;
                }
            }

            if (!isDeleted) {
                System.out.println("\033[1;31m Không tìm thấy Sách có mã " + bookId + ". \033[0m");
            }
        }
    }


    private static void searchBooks() {
        if (books.isEmpty()) {
            System.out.println("\033[1;31m Danh sách Sách trống. \033[0m");
        } else {
            System.out.print("\033[1;34m Nhập chuỗi tìm kiếm: \033[0m");
            String searchString = scanner.nextLine();

            ArrayList<Book> matchedBooks = new ArrayList<>();
            for (Book book : books) {
                if (validateSearchString(searchString) &&
                        (book.getBookName().contains(searchString) ||
                                book.getDescriptions().contains(searchString))) {
                    matchedBooks.add(book);
                }
            }

            if (matchedBooks.isEmpty()) {
                System.out.println("\033[1;31m Không tìm thấy Sách phù hợp với chuỗi tìm kiếm. \033[0m");
            } else {
                System.out.println("\033[1;34m Danh sách Sách phù hợp với chuỗi tìm kiếm: \033[0m");
                for (Book book : matchedBooks) {
                    book.displayData();
                    System.out.println();
                }
            }
        }
    }


    private static void updateBook() {
        if (books.isEmpty()) {
            System.out.println("\033[1;31m Danh sách Sách trống. \033[0m");
        } else {
            System.out.print("\033[1;34m Nhập mã Sách cần cập nhật: \033[0m");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            boolean isUpdated = false;
            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    System.out.println("\033[1;34m Nhập thông tin mới cho Sách có mã " + bookId + ": \033[0m");
                    book.inputData();
                    isUpdated = true;
                    System.out.println("\033[1;34m Thông tin Sách đã được cập nhật. \033[0m");
                    break;
                }
            }

            if (!isUpdated) {
                System.out.println("\033[1;31m Không tìm thấy Sách có mã " + bookId + ". \033[0m");
            }
        }
    }


    private static boolean validateSearchString(String searchString) {
        return searchString.length() >= 3;
    }
}

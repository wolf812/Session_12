package ra.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Book implements Serializable {
    private String bookId;
    private String bookName;
    private Float price;

    public Book() {
    }

    public Book(String bookId, String bookName, Float price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    /**\
     * Nhập thông tin sách cơ bản
     * @param sc
     * @param bookList
     */
    public void inputData(Scanner sc, List<Book> bookList) {
        System.out.println("Nhập mã sách :");
        this.bookId = sc.nextLine();
        System.out.println("Nhập tên sách :");
        this.bookName = sc.nextLine();
        System.out.println("Nhập vào giá của sách :");
        boolean checkExit = true;
        do {
            try {
                this.price = Float.parseFloat(sc.nextLine());
                checkExit = false;
            } catch (NumberFormatException ex) {
                System.err.println("Giá sách phải là số thực, vui lòng nhập lại.");
            } catch (Exception e) {
                System.err.println("Đã xảy ra lỗi trong quá trình xử lý, vui lòng nhập lại.");
            }
        } while (checkExit);
    }

    public String toString() {
        return "Mã sách:" + this.bookId + "-Tên sách:" + this.bookName + "-Giá sách:" + this.price;
    }
}

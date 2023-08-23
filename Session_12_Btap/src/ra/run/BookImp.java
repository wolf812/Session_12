package ra.run;

import ra.entity.Book;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImp extends Book {
    static List<Book> bookList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("********Menu********");
            System.out.println("1. Nhập thông tin các sách");
            System.out.println("2. In thông tin các sách ra file .txt");
            System.out.println("3. Đọc file .txt và in ra các sách có giá trong khoảng 10000 đến 20000");
            System.out.println("4. Thoát");
            int choice = 0;
            System.out.println("Lựa chọn của bạn :");
            boolean checkChoice = true;
            do {
                try {
                    choice = Integer.parseInt(sc.nextLine());
                    checkChoice = false;
                } catch (NumberFormatException ex) {
                    System.err.println("Dữ liệu  phải là số nguyên, vui lòng nhập lại.");
                } catch (Exception e) {
                    System.err.println("Đã xảy ra lỗi trong quá trình xử lý, vui lòng nhập lại.");
                }
            } while (checkChoice);

            switch (choice) {
                case 1:
                    BookImp.inputDataBook();
                    break;
                case 2:
                    BookImp.writeDataToFile(bookList);
                    break;
                case 3:
                    List<Book> bookListRead = BookImp.readDataFromFile();
                    if (bookListRead != null) {
                        for (Book book : bookListRead) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-4.");
            }
        } while (true);
    }


    public static void inputDataBook() {
        System.out.println("Số lượng sách cần nhập: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sách thứ " + (i + 1) + ":");
            Book bookNew = new Book();
            bookNew.inputData(sc, bookList);
            bookList.add(bookNew);
        }
    }


    public static void writeDataToFile(List<Book> bookList) {
        //1. Khởi tạo đối tượng file để làm việc với file - tương đối
        File file = new File("D:/java_kuo_1/Session_12_Btap/Book.txt");

        //        System.out.println("file book: " + Book.exists());

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //2. Khởi tạo đối tượng FileOutputStream từ file - Checked Excetion
            fos = new FileOutputStream(file);

            //3. Khởi tạo đối tượng ObjectOutputStream từ fos
            oos = new ObjectOutputStream(fos);

            //4. Sử dụng writeObject để ghi object ra file
            oos.writeObject(bookList);

            //5. Đẩy dữ liệu từ Stream xuống file
            oos.flush();

        } catch (FileNotFoundException ex1) {
            System.err.println("File không tồn tại.");
        } catch (Exception ex2) {
            System.err.println("Lỗi khi ghi dữ liệu ra file");
        } finally {
            //6. Đóng các stream
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IIOException ex1) {
                System.err.println("Xảy ra lỗi khi đóng các stream");
            } catch (Exception ex) {
                System.err.println("Xảy ra lỗi trong quá trình đóng các stream");
            }
        }
    }


    public static List<Book> readDataFromFile() {
        //1. Khởi tạo đối tượng File
        File file = new File("D:/java_kuo_1/Session_12_Btap/Book.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Book> bookListRead = null;
        try {

            //2. Khởi tạo đối tượng FileInputStream
            fis = new FileInputStream(file);

            //3. Khởi tạo đối tượng ObjectInputStream
            ois = new ObjectInputStream(fis);
            //4. Đọc dữ liệu object từ file (readObject())
            bookListRead = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex1) {
            System.err.println("Không tồn tại file");
        } catch (IOException ex2) {
            System.err.println("Lỗi khi đọc file");
        } catch (Exception ex) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
        } finally {
            //6. Đóng các stream
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
                System.err.println("Có lỗi khi đóng stream");
            } catch (Exception ex) {
                System.err.println("Có lỗi trong quá trình đóng các stream");
            }
        }
        return bookListRead;
    }
}

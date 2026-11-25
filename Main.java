import java.sql.*;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static class Main {
    static String url = "jdbc:mysql://localhost:3306/db_library_management";
    static String user = "root";
    static String pass = "123456";

    static Scanner sc = new Scanner(System.in);

    }
 void main() {
    while (true) {
        System.out.println("=========== LIBRARY MANAGEMENT ===========");
        System.out.println("1. Danh sách tất cả phiếu mượn");
        System.out.println("2. Thêm mới phiếu mượn");
        System.out.println("3. Cập nhật thông tin phiếu mượn");
        System.out.println("4. Xoá phiếu mượn");
        System.out.println("5. Tìm kiếm phiếu mượn theo tên độc giả");
        System.out.println("6. Tìm kiếm phiếu mượn theo tên sách");
        System.out.println("7. Thoát");
        System.out.print("Sự lựa chọn của bạn là: ");
        String choice = Main.sc.nextLine();
        try {
            switch (choice) {
                case "1":
                    showALL();
                    break;
                case "2":
                    addCards();
                    break;
                case "3":
                    updateCards();
                    break;
             case "4":
                 deleteCard();
                   break;
//               case "5":
//                   findByBorroweName();
//                   break;
//               case "6":
//                  findByBookTitle();
//                  break;
                case "7":
                    System.out.println("Kết thúc");
                    return;
                default:
                    System.out.println("Xin đưa ra lựa chọn hợp lệ!");

            }

        } catch (SQLException e) {
            System.out.println("Lỗi" + e.getMessage());
        }
    }
}
        static void showALL() throws SQLException {
    Connection conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
    CallableStatement cs = conn.prepareCall("{Call get_all_cards()}");
    ResultSet rs = cs.executeQuery();

    boolean found = false;
    while (rs.next()){
        found = true;
        System.out.println(rs.getInt("cards_id") + " | "
                + rs.getString("book_title") + " | "
                + rs.getString("borrower_name") + " | "
                + rs.getTimestamp("borrow_date") + " | "
                + rs.getTimestamp("return_deadline") + " | "
                + rs.getInt("quantity") + " | "
                + rs.getString("status"));
    }
            if (!found) {
                System.out.println("Không có dữ liệu.");
            }
            conn.close();

    }
    // them moi phieu muon
static void addCards() throws SQLException {
    System.out.print("Tên sách: ");
    String title = Main.sc.nextLine();
    System.out.print("Tên độc giả: ");
    String name = Main.sc.nextLine();
    System.out.print("Ngày mượn (yyyy-MM-dd): ");
    String borrow = Main.sc.nextLine();
    System.out.print("Hạn trả (yyyy-MM-dd): ");
    String deadline = Main.sc.nextLine();
    System.out.print("Số lượng: ");
    int qty = Integer.parseInt(Main.sc.nextLine());
    System.out.print("Trạng thái: ");
    String status = Main.sc.nextLine();

    Connection conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
    CallableStatement cs = conn.prepareCall("{call add_cards(?,?,?,?,?,?)}");
    cs.setString(1, title);
    cs.setString(2, name);
    cs.setTimestamp(3, Timestamp.valueOf(borrow + " 00:00:00"));
    cs.setTimestamp(4, Timestamp.valueOf(deadline + " 00:00:00"));
    cs.setInt(5, qty);
    cs.setString(6, status);
    cs.execute();
    System.out.println("Thêm thành công!");
    conn.close();
}

// cap nhap lai phieu muon
        static void updateCards() throws SQLException {
            System.out.print("Nhập card_id cần sửa: ");
            int id = Integer.parseInt(Main.sc.nextLine());
            System.out.print("Tên sách mới: ");
            String title = Main.sc.nextLine();
            System.out.print("Tên độc giả mới: ");
            String name = Main.sc.nextLine();
            System.out.print("Ngày mượn (yyyy-MM-dd): ");
            String borrow = Main.sc.nextLine();
            System.out.print("Hạn trả (yyyy-MM-dd): ");
            String deadline = Main.sc.nextLine();
            System.out.print("Số lượng: ");
            int qty = Integer.parseInt(Main.sc.nextLine());
            System.out.print("Trạng thái: ");
            String status = Main.sc.nextLine();

            Connection conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            CallableStatement cs = conn.prepareCall("{call update_card_id(?,?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, title);
            cs.setString(3, name);
            cs.setTimestamp(4, Timestamp.valueOf(borrow + " 00:00:00"));
            cs.setTimestamp(5, Timestamp.valueOf(deadline + " 00:00:00"));
            cs.setInt(6, qty);
            cs.setString(7, status);
            cs.execute();
            System.out.println("Cập nhật thành công!");
            conn.close();
        }
    // xoa phieu
        static void deleteCard() throws SQLException {
            System.out.print("Nhập mã phiếu mượn (cards_id) cần xóa: ");
            int id = Integer.parseInt(Main.sc.nextLine());

            Connection conn = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            CallableStatement cs = conn.prepareCall("{call delete_card_by_id(?)}");
            cs.setInt(1, id);

            int rows = cs.executeUpdate();
            if (rows > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Không tìm thấy phiếu mượn có mã: " + id);
            }

            conn.close();
        }

// các mục url, user và pass dù đã đặt trong class MAin nhưng vẫn bị lỗi, gợi ý sửa của java là Main.url, Main.user, Main.pass
// hôm sau học sẽ hỏi thầy vấn đề này

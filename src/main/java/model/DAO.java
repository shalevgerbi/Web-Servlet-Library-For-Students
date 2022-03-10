package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
    public final static String connString = "jdbc:derby:C:\\Users\\shalev\\Desktop\\java_temp\\HW4\\my_db";

    public static Student getStudentByMail(String email) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM APP.\"Students\" where S_EMAIL= '" + email + "'");
            if (rs.next()) {
                Student s = new Student();

                s.setFirstName(rs.getString("S_FIRSTNAME"));
                s.setLastName(rs.getString("S_LASTNAME"));
                s.setEmail(email);
                s.setID(rs.getInt("S_ID"));
                s.setPassword(rs.getString("S_PWD"));
                s.setBookCounter(rs.getInt("S_BOOKCOUNTER"));
                s.setDelayDays(rs.getInt("S_DELAYDAYS"));
                st.close();
                return s;
            }
            return null;
        } catch (Throwable t) {
            return null;
        }
    }

    public static ArrayList<Book> getBooks() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM APP.\"Books\"");
            ArrayList<Book> bookList = new ArrayList<>();
            while (rs.next()) {
                if (rs.getInt("B_C_COUNT") <= 0)
                    continue;
                Book b = new Book();

                b.setIsbn(rs.getInt("ISBN"));
                b.setBookName(rs.getString("B_NAME"));
                b.setAuthorName(rs.getString("B_AUTNAME"));
                b.setCategory(rs.getString("B_CAT"));
                b.setReleaseYear(rs.getInt("B_RELYEAR"));
                b.setCopyCounter(rs.getInt("B_C_COUNT"));
                bookList.add(b);
            }
            st.close();
            return bookList;
        } catch (Throwable t) {
            return null;
        }
    }

    public static Book getBookByIsbn(int isbn) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM APP.\"Books\" WHERE ISBN=" + isbn);
            if (rs.next()) {
                Book b = new Book();

                b.setIsbn(isbn);
                b.setBookName(rs.getString("B_NAME"));
                b.setAuthorName(rs.getString("B_AUTNAME"));
                b.setCategory(rs.getString("B_CAT"));
                b.setReleaseYear(rs.getInt("B_RELYEAR"));
                b.setCopyCounter(rs.getInt("B_C_COUNT"));
                st.close();
                return b;
            }
            st.close();
            return null;
        } catch (Throwable t) {
            return null;
        }
    }

    public static void borrowBook(Book b) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            st.executeUpdate("UPDATE APP.\"Books\" SET B_C_COUNT=" + (b.getCopyCounter() - 1) + " WHERE ISBN=" + b.getIsbn());
            st.close();
            return;
        } catch (Throwable t) {
            return;

        }
    }

    public static void updateSCounter(Student s) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            st.executeUpdate("UPDATE APP.\"Students\" SET S_BOOKCOUNTER=" + (s.getBookCounter() + 1) + " WHERE S_ID=" + s.getID());
            st.close();
            return;
        } catch (Throwable t) {
            return;

        }
    }

    public static ArrayList<String> getCategories() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM APP.\"Books\" ");
            ArrayList<String> categories = new ArrayList<>();
            while (rs.next()) {
                if(categories.contains(rs.getString("B_CAT")))
                    continue;
                categories.add(rs.getString("B_CAT"));
            }
            st.close();
            return categories;
        } catch (Throwable t) {
            return null;
        }
    }

    public static ArrayList<Book> getBooksByCategory(String category) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection cn = DriverManager.getConnection(connString, "root", "root");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM APP.\"Books\" WHERE B_CAT='" + category+"'");
            ArrayList<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book();
                b.setIsbn(rs.getInt("ISBN"));
                b.setBookName(rs.getString("B_NAME"));
                b.setAuthorName(rs.getString("B_AUTNAME"));
                b.setCategory(rs.getString("B_CAT"));
                b.setReleaseYear(rs.getInt("B_RELYEAR"));
                b.setCopyCounter(rs.getInt("B_C_COUNT"));
                books.add(b);
            }
                st.close();
                return books;
        } catch (Throwable t) {
            return null;
        }
    }
}

package control;

import model.Book;
import model.DAO;
import model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShowBooksCatServlet", value = "/ShowBooksCatServlet")
public class ShowBooksCatServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student s = new Student();
        try {
            s = (Student) request.getSession().getAttribute("loggedInUser");
        } catch (Throwable t) {
            request.setAttribute("error", "you must log in");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        String category;
        //boolean isGuest = false;
        try {
            category = request.getParameter("category");
        } catch (Throwable t) {
            request.setAttribute("error", "wrong category");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        ArrayList<Book> b = new ArrayList<>();
        //String pass = request.getParameter("passTB");
        b = DAO.getBooksByCategory(category);
        if (b == null) {
            request.setAttribute("error", "No Books in this category");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }


        HttpSession session = request.getSession();
        session.setAttribute("bookList", b);
        request.getRequestDispatcher("showBooks.jsp").forward(request, response);
        return;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}

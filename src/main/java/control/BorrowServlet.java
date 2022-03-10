package control;

import model.Book;
import model.DAO;
import model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BorrowServlet", value = "/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student s = new Student();
        try {
            s = (Student) request.getSession().getAttribute("loggedInUser");
        }
        catch(Throwable t){
        request.setAttribute("error", "you must log in");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }

        int isbn;
        //boolean isGuest = false;
        try{
            isbn = Integer.parseInt(request.getParameter("isbn"));
        }
        catch(Throwable t){
            request.setAttribute("error", "wrong isbn");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        Book b =new Book();
        //String pass = request.getParameter("passTB");
        b = DAO.getBookByIsbn(isbn);
        if (b == null)
        {
            request.setAttribute("error", "No Books with this ISBN");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        if(s.getBookCounter() >= 3){
            request.setAttribute("error", "you already have 3 books");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        DAO.borrowBook(b);
        DAO.updateSCounter(s);
        String message = "book borrowed";
        request.getSession().setAttribute("message",message);
        request.getRequestDispatcher("Student.jsp").forward(request, response);
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

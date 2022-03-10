package control;

import model.DAO;
import model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email;
        boolean isGuest = false;
        try{
            email = request.getParameter("emailTB");
        }
        catch(Throwable t){
            request.setAttribute("error", "wrong user name formats");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        Student s = new Student();
        String pass = request.getParameter("passTB");
        s = DAO.getStudentByMail(email);
        if (s == null)
        {
            request.setAttribute("error", "No Student with this Name");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        if (!s.getPassword().equals(pass)){
            request.setAttribute("error", "Wrong password for user: "+s.getFirstName());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        session.setAttribute("loggedInUser", s);
//        Cookie[] cs = request.getCookies();
//
//        if(cs!= null) {
//            Cookie c = new Cookie("userName", userName);
//            c.setMaxAge(60*60*365);
//            response.addCookie(c);
//        }

        session.setAttribute("loggedInUser",s);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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

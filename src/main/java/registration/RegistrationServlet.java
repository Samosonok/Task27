package registration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/form"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/form.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String consent = request.getParameter("consent");

        String page = "";
        if(firstName.isEmpty()||firstName.matches(".*\\d+.*")){
            page = "/errorPage.jsp";
            request.setAttribute("error","First name is empty or contains numbers!");
        } else if(lastName.isEmpty()||lastName.matches(".*\\d+.*")) {
            page = "/errorPage.jsp";
            request.setAttribute("error","Last name is empty or contains numbers!");
        } else if(gender==null) {
            page = "/errorPage.jsp";
            request.setAttribute("error","Gender not selected!");
        } else if(password.isEmpty()||password.length()<6) {
            page = "/errorPage.jsp";
            request.setAttribute("error","Password is empty or less than 6 characters!");
        } else if(consent==null) {
            page = "/errorPage.jsp";
            request.setAttribute("error","No consent to data processing!");
        } else {
            page = "/welcomePage.jsp";
            request.setAttribute("name", firstName);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}

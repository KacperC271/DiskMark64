package SERVLET;

import java.io.IOException;
import EJB_JPA.EJB_JPA;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DATA_BASE")
public class Data_Base_Servlet extends HttpServlet {
    String InputText;
    String MD5Hash;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action.equals("SAVE")) {
            InputText = request.getParameter("InputText");
            MD5Hash = request.getParameter("MD5Hash");
            EJB_JPA Save = new EJB_JPA();
            Save.SAVE(InputText, MD5Hash);
            response.sendRedirect("index.jsp");
        }
        else if (action.equals("DELETE")) {
            InputText = request.getParameter("ID");
            EJB_JPA Delete = new EJB_JPA();
            Delete.DELETE(InputText);
            response.sendRedirect("index.jsp");
        }
    }
}

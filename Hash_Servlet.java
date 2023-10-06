package SERVLET;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Hash_Servlet")
public class Hash_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String InputText = request.getParameter("TEXT_TO_HASH");

        if (InputText != null && !InputText.isEmpty()) {
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(InputText.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : array) {
                    sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
                }
                String MD5Hash = sb.toString();

                request.getSession().setAttribute("MD5_HASH", MD5Hash);
                request.getSession().setAttribute("TEXT_TO_HASH", InputText);

                response.sendRedirect("index.jsp");

            } catch (java.security.NoSuchAlgorithmException e) {
                response.getWriter().println("Błąd: Nie można znaleźć algorytmu MD5.");
                response.sendRedirect("index.jsp");
            }
        }
    }
}

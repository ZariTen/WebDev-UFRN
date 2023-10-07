package controle;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/telaLoginServlet")
public class telaLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession sessao = request.getSession();
    	sessao.setMaxInactiveInterval(60);
    	
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        
        if (usuario.equals("admin") && senha.equals("admin")){
        	sessao.setAttribute("usuario", usuario);
        	response.sendRedirect("telaMenu.jsp");
        } else {
        	response.sendRedirect("index.jsp");
        }
    }
}

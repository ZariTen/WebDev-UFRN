package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.BancoContatos;
import modelo.Contato;

@WebServlet("/listarContatosServlet")
public class listarContatosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	    HttpSession session = request.getSession();
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        response.setContentType("text/html");
        
        BancoContatos bancoContatos = BancoContatos.getInstancia();
        
        List<Contato> contatos = bancoContatos.obterTodosContatos();

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Lista de Contatos</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/listar.css\"></head><body>");
        out.println("<h1>Lista de Contatos</h1>");
        out.println("<ul>");

        for (Contato contato : contatos) {
            out.println("<li>Nome: " + contato.getNome() + "</li>");
            out.println("<li>Telefone Celular: " + contato.getTelefoneCelular() + "</li>");
            out.println("<li>Telefone Residencial: " + contato.getTelefoneResidencial() + "</li>");
            out.println("<li>E-mail: " + contato.getEmail() + "</li>");
            out.println("<li>Data de Nascimento: " + contato.getDataNascimento() + "</li>");
            out.println("<br>");
        }

        out.println("</ul>");
        out.println("</body></html>");
    }
}


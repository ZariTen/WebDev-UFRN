package controle;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.BancoContatos;
import modelo.Contato;

@WebServlet("/excluirUsuarioServlet")
public class excluirUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	    HttpSession session = request.getSession();
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        String nome = request.getParameter("nome");

        BancoContatos bancoContatos = BancoContatos.getInstancia();

        Contato contato = bancoContatos.buscarContatoPorNome(nome);

        if (contato != null) {
            bancoContatos.removerContato(contato);
            
            response.setContentType("text/html");
            
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>Confirmação de Exclusão</title></head><body>");
            out.println("<h1>Contato Excluído</h1>");
            out.println("<p>O contato com o nome " + nome + " foi excluído com sucesso.</p>");
            out.println("<p><a href='listaContatos.jsp'>Voltar para a lista de contatos</a></p>");
            out.println("</body></html>");
        }
        
    }
}


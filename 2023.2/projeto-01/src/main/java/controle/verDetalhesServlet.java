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

@WebServlet("/verDetalhesServlet")
public class verDetalhesServlet extends HttpServlet {
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

        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();

        if (contato != null) {
            out.println("<html><head><title>Detalhes do Contato</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/listar.css\"></head><body>");
            out.println("<h1>Detalhes do Contato</h1>");
            out.println("<p>Nome: " + contato.getNome() + "</p>");
            out.println("<p>Telefone Celular: " + contato.getTelefoneCelular() + "</p>");
            out.println("<p>Telefone Residencial: " + contato.getTelefoneResidencial() + "</p>");
            out.println("<p>E-mail: " + contato.getEmail() + "</p>");
            out.println("<p>Data de Nascimento: " + contato.getDataNascimento() + "</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><head><title>Contato não encontrado</title></head><body>");
            out.println("<h1>Contato não encontrado</h1>");
            out.println("<p>O contato com o nome " + nome + " não foi encontrado.</p>");
            out.println("</body></html>");
        }
    }
}


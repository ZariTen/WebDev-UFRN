package controle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.BancoContatos;
import modelo.Contato;


@WebServlet("/cadastroContatoServlet")
public class cadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String telefoneCelular = request.getParameter("telefoneCelular");
        String telefoneResidencial = request.getParameter("telefoneResidencial");
        String email = request.getParameter("email");
        String dataNascimentoStr = request.getParameter("dataNascimento");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = null;
        try {
            dataNascimento = dateFormat.parse(dataNascimentoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setTelefoneCelular(telefoneCelular);
        contato.setTelefoneResidencial(telefoneResidencial);
        contato.setEmail(email);
        contato.setDataNascimento(dataNascimento);

        BancoContatos bancoContatos = BancoContatos.getInstancia();
        bancoContatos.adicionarContato(contato);

        response.sendRedirect("telaMenu.jsp");
    }
}


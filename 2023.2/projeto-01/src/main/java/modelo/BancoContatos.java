package modelo;

import java.util.ArrayList;
import java.util.List;

public class BancoContatos {
    private static BancoContatos instancia = null;
    private List<Contato> listaDeContatos;

    private BancoContatos() {
        listaDeContatos = new ArrayList<>();
    }

    public static BancoContatos getInstancia() {
        if (instancia == null) {
            instancia = new BancoContatos();
        }
        return instancia;
    }

    public void adicionarContato(Contato contato) {
        if (!listaDeContatos.contains(contato)) {
            listaDeContatos.add(contato);
        }
    }

    public List<Contato> obterTodosContatos() {
        return listaDeContatos;
    }

    public void removerContato(Contato contato) {
        listaDeContatos.remove(contato);
    }

	public Contato buscarContatoPorNome(String nome) {
		for (Contato contato : listaDeContatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                return contato;
            }
        }
        return null;
	}

}


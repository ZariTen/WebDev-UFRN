package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class BancoContatos {
    private List<Contato> listaDeContatos;

    public BancoContatos() {
        listaDeContatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        listaDeContatos.add(contato);
    }

    public List<Contato> obterTodosContatos() {
        return listaDeContatos;
    }

    public void removerContato(Contato contato) {
        Iterator<Contato> iterator = listaDeContatos.iterator();
        while (iterator.hasNext()) {
            Contato c = iterator.next();
            if (c.equals(contato)) {
                iterator.remove();
            }
        }
    }

}


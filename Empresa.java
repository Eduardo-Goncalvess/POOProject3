package TrabalhoAeD.controle;

import java.util.List;
import java.util.ArrayList;

public class Empresa extends Usuario {
    private List<Produto> produtos;

    private Empresa(int codigo, String nome, int telefone, String cidade) {
        super(codigo, nome, telefone, cidade);
        this.produtos = new ArrayList<Produto>();
    }

    public Empresa(Empresa outra) {
        super(outra);
        this.produtos = (outra.produtos != null) ? new ArrayList<>(outra.produtos) : new ArrayList<Produto>();
    }

    static public Empresa getInstance(int codigo, String nome, int telefone, String cidade) {
        if (codigo < 0 || nome == null || telefone < 0 || cidade == null) {
            return null;
        } else {
            return new Empresa(codigo, nome, telefone, cidade);
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public boolean adicionarProduto(Produto produto) {
        if (produto != null && !produtos.contains(produto)) {
            produtos.add(produto);
            return true;
        }
        return false;
    }

    public boolean removerProduto(Produto produto) {
        if (produto != null && produtos.contains(produto)) {
            produtos.remove(produto);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
package TrabalhoAeD.controle;

import java.util.List;
import java.util.ArrayList;

public class Empresa {
    private int codigo;
    private String nome;
    private int telefone;
    private String cidade;
    private List<Produto> produtos;

    private Empresa(int codigo, String nome, int telefone, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.cidade = cidade;
        this.produtos = new ArrayList<Produto>();
    }

    public Empresa(Empresa outra) {
        this.codigo = outra.codigo;
        this.nome = outra.nome;
        this.telefone = outra.telefone;
        this.cidade = outra.cidade;
        this.produtos = (outra.produtos != null) ? new ArrayList<>(outra.produtos) : new ArrayList<Produto>();
    }

    static public Empresa getInstance(int codigo, String nome, int telefone, String cidade) {
        if (codigo < 0 || nome == null || telefone < 0 || cidade == null) {
            return null;
        } else {
            return new Empresa(codigo, nome, telefone, cidade);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
        return String.format("%-6d | %-15s | %-8d | %-12s",
                codigo,
                nome.length() > 15 ? nome.substring(0, 12) + "..." : nome,
                telefone,
                cidade.length() > 12 ? cidade.substring(0, 9) + "..." : cidade);
    }
}

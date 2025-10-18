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
        this.produtos = new ArrayList<>(outra.produtos);
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

    public int getTelefone() {
        return telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void adicionarProduto(Produto produto) {
        if (produto != null && !produtos.contains(produto)) {
            produtos.add(produto);
        }
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }
}

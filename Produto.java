package TrabalhoAeD.controle;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private Categoria categoria;
    private int quantidade;
    private Empresa fornecedor;

    public Produto (int codigo, String nome, double preco, Categoria categoria, int quantidade, Empresa fornecedor) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public Produto(String nome, double preco, Categoria categoria, int quantidade, Empresa fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
//to comendo na fente do pc morrendo de foem kak

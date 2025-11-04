package TrabalhoAeD.controle;

public class Produto {

    private int codigo;
    private String nome;
    private double preco;
    private Categoria categoria;
    private int quantidade;
    private Empresa fornecedor;

    private Produto(int codigo, String nome, double preco, Categoria categoria, int quantidade, Empresa fornecedor) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public Produto(Produto outro) {
        this.codigo = outro.codigo;
        this.nome = outro.nome;
        this.preco = outro.preco;
        this.categoria = (outro.categoria != null) ? new Categoria(outro.categoria) : null;
        this.quantidade = outro.quantidade;
        this.fornecedor = (outro.fornecedor != null) ? new Empresa(outro.fornecedor) : null;
    }

    static public Produto getInstance(int codigo, String nome, double preco, Categoria categoria, int quantidade,
            Empresa fornecedor) {
        if (codigo < 0 || nome == null || preco < 0 || categoria == null || quantidade < 0 || fornecedor == null) {
            return null;
        } else {
            return new Produto(codigo, nome, preco, categoria, quantidade, fornecedor);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Empresa getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Empresa fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        String nomeCategoria = (categoria != null) ? categoria.getNome() : "Nenhuma";
        String nomeFornecedor = (fornecedor != null) ? fornecedor.getNome() : "Nenhum";

        return String.format("%-6d | %-15s | R$ %-8.2f | %-8d | %-12s | %-15s",
                codigo,
                nome.length() > 15 ? nome.substring(0, 12) + "..." : nome,
                preco,
                quantidade,
                nomeCategoria.length() > 12 ? nomeCategoria.substring(0, 9) + "..." : nomeCategoria,
                nomeFornecedor.length() > 15 ? nomeFornecedor.substring(0, 12) + "..." : nomeFornecedor);
    }
}

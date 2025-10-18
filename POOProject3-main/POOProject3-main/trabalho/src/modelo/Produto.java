package TrabalhoAeD.controle;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private Categoria categoria;
    private int quantidade;
    private Empresa fornecedor;

    public Produto(int codigo, String nome, double preco, Categoria categoria, int quantidade, Empresa fornecedor) {
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

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade;
    }

    public Empresa getFornecedor() { return fornecedor; }
    public void setFornecedor(Empresa fornecedor) { 
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        String nomeFornecedor = (fornecedor != null) ? fornecedor.getNome() : "Nenhum";
        String nomeCategoria = (categoria != null) ? categoria.getNome() : "Nenhuma";
        
        return "Produto [Código: " + codigo + ", Nome: " + nome + 
               ", Preço: R$ " + String.format("%.2f", preco) + 
               ", Categoria: " + nomeCategoria + 
               ", Quantidade: " + quantidade + 
               ", Fornecedor: " + nomeFornecedor + "]";
    }
}
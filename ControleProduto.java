package TrabalhoAeD.controle;

import TrabalhoAeD.dados.RepositorioProduto;
import java.util.ArrayList;
import java.util.List;

public class ControleProduto {
    private RepositorioProduto repoProduto;

    public ControleProduto() {
        this.repoProduto = RepositorioProduto.getInstance();
    }

    public Produto getInstance(int codigo, String nome, double preco, Categoria categoria, int quantidade,
            Empresa fornecedor) {
        return Produto.getInstance(codigo, nome, preco, categoria, quantidade, fornecedor);
    }

    public boolean add(Produto produto) {
        if (repoProduto.buscarPorNome(produto)) {
            return false;
        }
        return repoProduto.add(produto);
    }

    public boolean remove(int codigo) {
        return repoProduto.remove(codigo);
    }

    public Produto getProduto(int codigo) {
        return repoProduto.getProduto(codigo);
    }

    public List<Produto> getProdutosPorEmpresa(int codigoEmpresa) {
        return repoProduto.getProdutosPorEmpresa(codigoEmpresa);
    }

    public boolean removerFornecedorDosProdutos(int codigoFornecedor) {
        return repoProduto.removerFornecedorDosProdutos(codigoFornecedor);
    }

    public boolean removerCategoriaDosProdutos(int codigoCategoria) {
        return repoProduto.removerCategoriaDosProdutos(codigoCategoria);
    }

    public boolean excluirProduto(int codigo) {
        return repoProduto.remover(codigo);
    }

    public boolean comprarProduto(int codigo, int quantidade) {
        return repoProduto.comprarProduto(codigo, quantidade);
    }

    public boolean verificarDisponibilidade(int codigo, int quantidade) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            return produto.getQuantidade() >= quantidade;
        }
        return false;
    }

    public double calcularValorCompra(int codigo, int quantidade) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            return produto.getPreco() * quantidade;
        }
        return 0.0;
    }

    public boolean atualizarNome(int codigo, String novoNome) {
        return repoProduto.atualizarNome(codigo, novoNome);
    }

    public boolean atualizarPreco(int codigo, double novoPreco) {
        return repoProduto.atualizarPreco(codigo, novoPreco);
    }

    public boolean atualizarQuantidade(int codigo, int novaQuantidade) {
        return repoProduto.atualizarQuantidade(codigo, novaQuantidade);
    }

    public boolean atualizarCategoria(int codigo, Categoria novaCategoria) {
        return repoProduto.atualizarCategoria(codigo, novaCategoria);
    }

    public boolean atualizarFornecedor(int codigo, Empresa novoFornecedor) {
        return repoProduto.atualizarFornecedor(codigo, novoFornecedor);
    }

    public boolean verificarNome(String nome) {
        return repoProduto.verificarNome(nome);
    }

    public int getProxCodigo() {
        return repoProduto.getProxCodigo();
    }

    public List<Produto> listarProdutos() {
        List<Produto> original = repoProduto.listarProdutos();
        List<Produto> copia = new ArrayList<>();
        for (Produto produto : original) {
            copia.add(new Produto(produto));
        }
        return copia;
    }
}
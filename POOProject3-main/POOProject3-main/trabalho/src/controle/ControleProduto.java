package TrabalhoAeD.controle;

import dados.RepositorioProduto;
import modelo.Produto;
import modelo.Categoria;
import modelo.Empresa;
import java.util.ArrayList;
import java.util.List;

public class ControleProduto {
    private RepositorioProduto repoProduto;

    public ControleProduto() {
        this.repoProduto = RepositorioProduto.getInstancia();
    }

    public boolean cadastrarProduto(Produto produto) {
        return repoProduto.inserir(produto);
    }

    public Produto buscarPorNome(String nome) {
        return repoProduto.buscarPorNome(nome);
    }

    public Produto buscarPorCodigo(int codigo) {
        return repoProduto.buscar(codigo);
    }

    public boolean excluirProduto(int codigo) {
        return repoProduto.remover(codigo);
    }

    public boolean atualizarProduto(Produto produto) {
        if (produto == null) return false;
        return repoProduto.atualizar(produto);
    }

    public List<Produto> listarProdutos() {
        Produto[] produtosArray = repoProduto.listarTodos();
        List<Produto> produtosList = new ArrayList<>();
        for (Produto produto : produtosArray) {
            if (produto != null) {
                produtosList.add(produto);
            }
        }
        return produtosList;
    }

    public List<Produto> buscarPorCategoria(int codigoCategoria) {
        Produto[] produtosArray = repoProduto.buscarPorCategoria(codigoCategoria);
        List<Produto> produtosList = new ArrayList<>();
        for (Produto produto : produtosArray) {
            if (produto != null) {
                produtosList.add(produto);
            }
        }
        return produtosList;
    }

    public List<Produto> buscarPorFornecedor(int codigoFornecedor) {
        Produto[] produtosArray = repoProduto.buscarPorFornecedor(codigoFornecedor);
        List<Produto> produtosList = new ArrayList<>();
        for (Produto produto : produtosArray) {
            if (produto != null) {
                produtosList.add(produto);
            }
        }
        return produtosList;
    }

    public boolean verificarCodigo(int codigo) {
        return repoProduto.buscar(codigo) != null;
    }

    public boolean verificarNome(String nome) {
        return repoProduto.buscarPorNome(nome) != null;
    }

    public int getProxCodigo() {
        return repoProduto.getProxCodigo();
    }

    public boolean atualizarEstoque(int codigoProduto, int novaQuantidade) {
        Produto produto = buscarPorCodigo(codigoProduto);
        if (produto != null && novaQuantidade >= 0) {
            produto.setQuantidade(novaQuantidade);
            return atualizarProduto(produto);
        }
        return false;
    }

    public boolean ajustarPreco(int codigoProduto, double novoPreco) {
        Produto produto = buscarPorCodigo(codigoProduto);
        if (produto != null && novoPreco >= 0) {
            produto.setPreco(novoPreco);
            return atualizarProduto(produto);
        }
        return false;
    }
}
package TrabalhoAeD.dados;

import TrabalhoAeD.controle.Categoria;
import TrabalhoAeD.controle.Empresa;
import TrabalhoAeD.controle.Produto;

import java.util.List;
import java.util.ArrayList;

public class RepositorioProduto {
    private static RepositorioProduto instancia;
    private List<Produto> produtos;
    private int proximoCodigo;

    private RepositorioProduto() {
        this.produtos = new ArrayList<Produto>();
        this.proximoCodigo = 1;
    }

    public static RepositorioProduto getInstance() {
        if (instancia == null) {
            instancia = new RepositorioProduto();
        }
        return instancia;
    }

    public boolean add(Produto p) {
        if (p != null) {
            produtos.add(p);
            proximoCodigo++;
            return true;
        }
        return false;
    }

    public boolean remove(int codigo) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getCodigo() == codigo) {
                produtos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean verificarNome(String nome) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscarPorNome(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p != null && p.getNome().equalsIgnoreCase(produto.getNome())) {
                return true;
            }
        }
        return false;
    }

    public Produto getProduto(int codigo) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public List<Produto> getProdutosPorEmpresa(int codigoEmpresa) {
        List<Produto> produtosDaEmpresa = new ArrayList<>();
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getFornecedor() != null && p.getFornecedor().getCodigo() == codigoEmpresa) {
                produtosDaEmpresa.add(p);
            }
        }
        return produtosDaEmpresa;
    }

    public boolean atualizarNome(int codigo, String novoNome) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            produto.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarPreco(int codigo, double novoPreco) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            produto.setPreco(novoPreco);
            return true;
        }
        return false;
    }

    public boolean atualizarQuantidade(int codigo, int novaQuantidade) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            produto.setQuantidade(novaQuantidade);
            return true;
        }
        return false;
    }

    public boolean atualizarCategoria(int codigo, Categoria novaCategoria) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            produto.setCategoria(novaCategoria);
            return true;
        }
        return false;
    }

    public boolean atualizarFornecedor(int codigo, Empresa novoFornecedor) {
        Produto produto = getProduto(codigo);
        if (produto != null) {
            produto.setFornecedor(novoFornecedor);
            return true;
        }
        return false;
    }

    public boolean removerFornecedorDosProdutos(int codigoFornecedor) {
        boolean removido = false;
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getFornecedor() != null && p.getFornecedor().getCodigo() == codigoFornecedor) {
                p.setFornecedor(null);
                removido = true;
            }
        }
        return removido;
    }

    public boolean removerCategoriaDosProdutos(int codigoCategoria) {
        boolean removido = false;
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getCategoria() != null && p.getCategoria().getCodigo() == codigoCategoria) {
                p.setCategoria(null);
                removido = true;
            }
        }
        return removido;
    }

    public boolean comprarProduto(int codigo, int quantidade) {
        Produto produto = getProduto(codigo);
        if (produto != null && produto.getQuantidade() >= quantidade) {
            int novaQuantidade = produto.getQuantidade() - quantidade;
            produto.setQuantidade(novaQuantidade);
            return true;
        }
        return false;
    }

    public boolean reporEstoque(int codigo, int quantidade) {
        Produto produto = getProduto(codigo);
        if (produto != null && quantidade > 0) {
            int novaQuantidade = produto.getQuantidade() + quantidade;
            produto.setQuantidade(novaQuantidade);
            return true;
        }
        return false;
    }

    public int getProxCodigo() {
        return proximoCodigo;
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

}
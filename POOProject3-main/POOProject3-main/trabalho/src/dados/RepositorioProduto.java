package TrabalhoAeD.dados;

import modelo.Produto;
import modelo.Categoria;
import modelo.Empresa;
import java.util.HashMap;

public class RepositorioProduto {
    private static RepositorioProduto instancia;
    private HashMap<Integer, Produto> produtos;
    private int proximoCodigo;

    private RepositorioProduto() {
        this.produtos = new HashMap<>();
        this.proximoCodigo = 1;
    }

    public static synchronized RepositorioProduto getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioProduto();
        }
        return instancia;
    }

    public boolean inserir(Produto produto) {
        if (produto == null || produto.getNome() == null || 
            produto.getNome().trim().isEmpty() || 
            produto.getPreco() < 0 || produto.getQuantidade() < 0 ||
            buscarPorNome(produto.getNome()) != null) {
            return false;
        }
        
        produto.setCodigo(proximoCodigo++);
        produtos.put(produto.getCodigo(), produto);
        return true;
    }

    public Produto buscar(int codigo) {
        return produtos.get(codigo);
    }

    public Produto buscarPorNome(String nome) {
        for (Produto produto : produtos.values()) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    public boolean remover(int codigo) {
        return produtos.remove(codigo) != null;
    }

    public boolean atualizar(Produto produto) {
        if (produto == null || !produtos.containsKey(produto.getCodigo())) {
            return false;
        }
        produtos.put(produto.getCodigo(), produto);
        return true;
    }

    public Produto[] listarTodos() {
        return produtos.values().toArray(new Produto[0]);
    }

    public Produto[] buscarPorCategoria(int codigoCategoria) {
        return produtos.values().stream()
                .filter(p -> p.getCategoria() != null && p.getCategoria().getCodigo() == codigoCategoria)
                .toArray(Produto[]::new);
    }

    public Produto[] buscarPorFornecedor(int codigoFornecedor) {
        return produtos.values().stream()
                .filter(p -> p.getFornecedor() != null && p.getFornecedor().getCodigo() == codigoFornecedor)
                .toArray(Produto[]::new);
    }

    public int getProxCodigo() {
        return proximoCodigo;
    }
}
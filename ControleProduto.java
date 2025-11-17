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
            Empresa fornecedor) throws ValidacaoException {
        Produto produto = Produto.getInstance(codigo, nome, preco, categoria, quantidade, fornecedor);
        if (produto == null) {
            throw new ValidacaoException("Dados inválidos para criar produto");
        }
        return produto;
    }

    public boolean add(Produto produto) throws CadastroException, ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser nulo");
        }
        if (repoProduto.buscarPorNome(produto)) {
            throw new CadastroException("Já existe um produto com este nome: " + produto.getNome());
        }
        boolean resultado = repoProduto.add(produto);
        if (!resultado) {
            throw new CadastroException("Falha ao cadastrar produto");
        }
        return resultado;
    }

    public boolean remove(int codigo) throws NegocioException {
        boolean resultado = repoProduto.remove(codigo);
        if (!resultado) {
            throw new NegocioException("Produto não encontrado ou não pode ser removido. Código: " + codigo);
        }
        return resultado;
    }

    public Produto getProduto(int codigo) throws NegocioException {
        Produto produto = repoProduto.getProduto(codigo);
        if (produto == null) {
            throw new NegocioException("Produto não encontrado. Código: " + codigo);
        }
        return produto;
    }

    public List<Produto> getProdutosPorEmpresa(int codigoEmpresa) throws NegocioException {
        List<Produto> produtos = repoProduto.getProdutosPorEmpresa(codigoEmpresa);
        if (produtos == null) {
            throw new NegocioException("Empresa não encontrada ou sem produtos. Código: " + codigoEmpresa);
        }
        return produtos;
    }

    public boolean removerFornecedorDosProdutos(int codigoFornecedor) throws NegocioException {
        boolean resultado = repoProduto.removerFornecedorDosProdutos(codigoFornecedor);
        if (!resultado) {
            throw new NegocioException("Falha ao remover fornecedor dos produtos");
        }
        return resultado;
    }

    public boolean removerCategoriaDosProdutos(int codigoCategoria) throws NegocioException {
        boolean resultado = repoProduto.removerCategoriaDosProdutos(codigoCategoria);
        if (!resultado) {
            throw new NegocioException("Falha ao remover categoria dos produtos");
        }
        return resultado;
    }

    public boolean comprarProduto(int codigo, int quantidade) throws NegocioException, ValidacaoException {
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero");
        }
        boolean resultado = repoProduto.comprarProduto(codigo, quantidade);
        if (!resultado) {
            throw new NegocioException("Falha ao realizar compra do produto");
        }
        return resultado;
    }

    public boolean reporEstoque(int codigo, int quantidade) throws NegocioException, ValidacaoException {
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero");
        }
        boolean resultado = repoProduto.reporEstoque(codigo, quantidade);
        if (!resultado) {
            throw new NegocioException("Falha ao repor estoque do produto");
        }
        return resultado;
    }

    public boolean verificarDisponibilidade(int codigo, int quantidade) throws NegocioException, ValidacaoException {
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero");
        }
        Produto produto = getProduto(codigo);
        return produto.getQuantidade() >= quantidade;
    }

    public double calcularValorCompra(int codigo, int quantidade) throws NegocioException, ValidacaoException {
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade deve ser maior que zero");
        }
        Produto produto = getProduto(codigo);
        return produto.getPreco() * quantidade;
    }

    public boolean atualizarNome(int codigo, String novoNome) throws NegocioException, ValidacaoException {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio");
        }
        boolean resultado = repoProduto.atualizarNome(codigo, novoNome);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar nome do produto");
        }
        return resultado;
    }

    public boolean atualizarPreco(int codigo, double novoPreco) throws NegocioException, ValidacaoException {
        if (novoPreco < 0) {
            throw new ValidacaoException("Preço não pode ser negativo");
        }
        boolean resultado = repoProduto.atualizarPreco(codigo, novoPreco);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar preço do produto");
        }
        return resultado;
    }

    public boolean atualizarQuantidade(int codigo, int novaQuantidade) throws NegocioException, ValidacaoException {
        if (novaQuantidade < 0) {
            throw new ValidacaoException("Quantidade não pode ser negativa");
        }
        boolean resultado = repoProduto.atualizarQuantidade(codigo, novaQuantidade);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar quantidade do produto");
        }
        return resultado;
    }

    public boolean atualizarCategoria(int codigo, Categoria novaCategoria) throws NegocioException, ValidacaoException {
        if (novaCategoria == null) {
            throw new ValidacaoException("Categoria não pode ser nula");
        }
        boolean resultado = repoProduto.atualizarCategoria(codigo, novaCategoria);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar categoria do produto");
        }
        return resultado;
    }

    public boolean atualizarFornecedor(int codigo, Empresa novoFornecedor) throws NegocioException, ValidacaoException {
        if (novoFornecedor == null) {
            throw new ValidacaoException("Fornecedor não pode ser nulo");
        }
        boolean resultado = repoProduto.atualizarFornecedor(codigo, novoFornecedor);
        if (!resultado) {
            throw new NegocioException("Falha ao atualizar fornecedor do produto");
        }
        return resultado;
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
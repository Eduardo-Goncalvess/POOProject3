package TrabalhoAeD.controle;

import java.util.List;

public class Sistema {
	private ControleProduto cProduto;
	private ControleEmpresa cEmpresa;
	private ControleCategoria cCategoria;

	private static Sistema instance;

	private Sistema() {
		cProduto = new ControleProduto();
		cEmpresa = new ControleEmpresa();
		cCategoria = new ControleCategoria();
		init();

	}

	public static Sistema getInstance() {
		if (instance == null)
			instance = new Sistema();

		return instance;
	}

	public boolean cadastrarEmpresa(Empresa o) {
		return cEmpresa.add(o);
	}

	public boolean cadastrarCategoria(Categoria o) {
		return cCategoria.add(o);
	}

	public boolean cadastrarProduto(Produto o) {
		return cProduto.add(o);
	}

	public Empresa getInstanceEmpresa(int codigo, String nome, int telefone, String cidade) {
		return cEmpresa.getInstance(codigo, nome, telefone, cidade);
	}

	public Produto getInstanceProduto(int codigo, String nome, double preco, Categoria categoria, int quantidade,
			Empresa fornecedor) {
		return cProduto.getInstance(codigo, nome, preco, categoria, quantidade, fornecedor);
	}

	public Categoria getInstanceCategoria(int codigo, String nome, String descricao) {
		return cCategoria.getInstance(codigo, nome, descricao);
	}

	public int getProxCodigoEmpresa() {
		return cEmpresa.getProxCodigo();
	}

	public int getProxCodigoProduto() {
		return cProduto.getProxCodigo();
	}

	public int getProxCodigoCategoria() {
		return cCategoria.getProxCodigo();
	}

	public boolean verificarNomeEmpresa(String nome) {
		return cEmpresa.verificarNome(nome);
	}

	public boolean verificarNomeCategoria(String nome) {
		return cProduto.verificarNome(nome);
	}

	public boolean verificarNomeProduto(String nome) {
		return cCategoria.verificarNome(nome);
	}

	public boolean removerCategoria(int codigo) {
		return cCategoria.remove(codigo);
	}

	public boolean removerEmpresa(int codigo) {
		return cEmpresa.remove(codigo);
	}

	public boolean removerProduto(int codigo) {
		return cProduto.remove(codigo);
	}

	public Empresa getEmpresa(int codigo) {
		return cEmpresa.getEmpresa(codigo);
	}

	public Categoria getCategoria(int codigo) {
		return cCategoria.getCategoria(codigo);
	}

	public Produto getProduto(int codigo) {
		return cProduto.getProduto(codigo);
	}

	public List<Produto> getProdutosPorEmpresa(int codigoEmpresa) {
		return cProduto.getProdutosPorEmpresa(codigoEmpresa);
	}

	public boolean verificarProdutoNaEmpresa(int codigoEmpresa, String nomeProduto) {
		return cEmpresa.verificarProdutoNaEmpresa(codigoEmpresa, nomeProduto);
	}

	public boolean adicionarProdutoFornecedor(int codigoEmpresa, Produto produto) {
		return cEmpresa.adicionarProdutoFornecedor(codigoEmpresa, produto);
	}

	public boolean removerProdutoFornecedor(Produto produto, int codF) {
		return cEmpresa.removerProdutoFornecedor(produto, codF);
	}

	public boolean removerCategoriaDosProdutos(int codigoCategoria) {
		return cProduto.removerCategoriaDosProdutos(codigoCategoria);
	}

	public boolean removerFornecedorDosProdutos(int codigoFornecedor) {
		return cProduto.removerFornecedorDosProdutos(codigoFornecedor);
	}

	public boolean comprarProduto(int codigo, int quantidade) {
		return cProduto.comprarProduto(codigo, quantidade);
	}

	public boolean verificarDisponibilidadeProduto(int codigo, int quantidade) {
		return cProduto.verificarDisponibilidade(codigo, quantidade);
	}

	public double calcularValorCompra(int codigo, int quantidade) {
		return cProduto.calcularValorCompra(codigo, quantidade);
	}

	public boolean atualizarNomeEmpresa(int codigo, String novoNome) {
		return cEmpresa.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarTelefoneEmpresa(int codigo, int novoTelefone) {
		return cEmpresa.atualizarTelefone(codigo, novoTelefone);
	}

	public boolean atualizarCidadeEmpresa(int codigo, String novaCidade) {
		return cEmpresa.atualizarCidade(codigo, novaCidade);
	}

	public boolean atualizarNomeCategoria(int codigo, String novoNome) {
		return cCategoria.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarDescricaoCategoria(int codigo, String novaDescricao) {
		return cCategoria.atualizarDescricao(codigo, novaDescricao);
	}

	public boolean atualizarNomeProduto(int codigo, String novoNome) {
		return cProduto.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarPrecoProduto(int codigo, double novoPreco) {
		return cProduto.atualizarPreco(codigo, novoPreco);
	}

	public boolean atualizarQuantidadeProduto(int codigo, int novaQuantidade) {
		return cProduto.atualizarQuantidade(codigo, novaQuantidade);
	}

	public boolean atualizarCategoriaProduto(int codigo, Categoria novaCategoria) {
		return cProduto.atualizarCategoria(codigo, novaCategoria);
	}

	public boolean atualizarFornecedorProduto(int codigo, Empresa novoFornecedor) {
		return cProduto.atualizarFornecedor(codigo, novoFornecedor);
	}

	public List<Empresa> listarEmpresas() {
		return cEmpresa.listarEmpresas();
	}

	public List<Produto> listarProdutos() {
		return cProduto.listarProdutos();
	}

	public List<Categoria> listarCategorias() {
		return cCategoria.listarCategorias();
	}

	private void init() {
		Categoria catEletronicos = getInstanceCategoria(getProxCodigoCategoria(), "Eletrônicos",
				"Produtos eletrônicos em geral");
		cadastrarCategoria(catEletronicos);

		Categoria catMoveis = getInstanceCategoria(getProxCodigoCategoria(), "Móveis", "Móveis para casa e escritório");
		cadastrarCategoria(catMoveis);

		Categoria catRoupas = getInstanceCategoria(getProxCodigoCategoria(), "Roupas",
				"Vestuário masculino e feminino");
		cadastrarCategoria(catRoupas);

		Empresa empTech = getInstanceEmpresa(getProxCodigoEmpresa(), "Tech Solutions", 1234, "São Paulo");
		cadastrarEmpresa(empTech);

		Empresa empCasa = getInstanceEmpresa(getProxCodigoEmpresa(), "Casa & Conforto", 5678, "Rio de Janeiro");
		cadastrarEmpresa(empCasa);

		Empresa empModa = getInstanceEmpresa(getProxCodigoEmpresa(), "Moda Elegante", 9012, "Belo Horizonte");
		cadastrarEmpresa(empModa);

		Produto prod1 = getInstanceProduto(getProxCodigoProduto(), "Smartphone", 899.99, catEletronicos, 50, empTech);
		cadastrarProduto(prod1);

		Produto prod2 = getInstanceProduto(getProxCodigoProduto(), "Notebook", 2499.99, catEletronicos, 20, empTech);
		cadastrarProduto(prod2);

		Produto prod3 = getInstanceProduto(getProxCodigoProduto(), "Sofá", 1299.99, catMoveis, 15, empCasa);
		cadastrarProduto(prod3);

		Produto prod4 = getInstanceProduto(getProxCodigoProduto(), "Mesa", 599.99, catMoveis, 30, empCasa);
		cadastrarProduto(prod4);

		Produto prod5 = getInstanceProduto(getProxCodigoProduto(), "Camiseta", 49.99, catRoupas, 100, empModa);
		cadastrarProduto(prod5);

		Produto prod6 = getInstanceProduto(getProxCodigoProduto(), "Calça Jeans", 89.99, catRoupas, 80, empModa);
		cadastrarProduto(prod6);

		adicionarProdutoFornecedor(empTech.getCodigo(), prod1);
		adicionarProdutoFornecedor(empTech.getCodigo(), prod2);
		adicionarProdutoFornecedor(empCasa.getCodigo(), prod3);
		adicionarProdutoFornecedor(empCasa.getCodigo(), prod4);
		adicionarProdutoFornecedor(empModa.getCodigo(), prod5);
		adicionarProdutoFornecedor(empModa.getCodigo(), prod6);
	}
}

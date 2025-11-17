package TrabalhoAeD.controle;

import java.util.ArrayList;
import java.util.List;

import TrabalhoAeD.dados.RepositorioEmpresa;
import TrabalhoAeD.dados.RepositorioUsuario;

public class Sistema {
	private ControleProduto cProduto;
	private ControleEmpresa cEmpresa;
	private ControleCategoria cCategoria;
	private ControleUsuario cUsuario;
	private ControleCompra cCompra;

	private static Sistema instance;

	private Sistema() throws ValidacaoException, CadastroException, NegocioException {
		cProduto = new ControleProduto();
		cEmpresa = new ControleEmpresa();
		cCategoria = new ControleCategoria();
		cUsuario = new ControleUsuario();
		cCompra = new ControleCompra();
		init();
	}

	public static Sistema getInstance() throws ValidacaoException, CadastroException, NegocioException {
		if (instance == null)
			instance = new Sistema();
		return instance;
	}

	public boolean cadastrarUsuario(Usuario o) throws CadastroException, ValidacaoException {
		return cUsuario.add(o);
	}

	public Usuario getInstanceUsuario(int codigo, String nome, int telefone, String cidade) throws ValidacaoException {
		return cUsuario.getInstance(codigo, nome, telefone, cidade);
	}

	public int getProxCodigoUsuario() {
		return cUsuario.getProxCodigo();
	}

	public boolean verificarNomeUsuario(String nome) {
		return cUsuario.verificarNome(nome);
	}

	public boolean removerUsuario(int codigo) throws NegocioException {
		return cUsuario.remove(codigo);
	}

	public Usuario getUsuario(int codigo) throws NegocioException {
		return cUsuario.getUsuario(codigo);
	}

	public boolean atualizarNomeUsuario(int codigo, String novoNome) throws NegocioException, ValidacaoException {
		return cUsuario.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarTelefoneUsuario(int codigo, int novoTelefone) throws NegocioException, ValidacaoException {
		return cUsuario.atualizarTelefone(codigo, novoTelefone);
	}

	public boolean atualizarCidadeUsuario(int codigo, String novaCidade) throws NegocioException, ValidacaoException {
		return cUsuario.atualizarCidade(codigo, novaCidade);
	}

	public List<Usuario> listarUsuarios() {
		return cUsuario.listarUsuarios();
	}

	public boolean cadastrarEmpresa(Empresa o) throws CadastroException, ValidacaoException {
		return cEmpresa.add(o);
	}

	public boolean cadastrarCategoria(Categoria o) throws CadastroException, ValidacaoException {
		return cCategoria.add(o);
	}

	public boolean cadastrarProduto(Produto o) throws CadastroException, ValidacaoException {
		return cProduto.add(o);
	}

	public Empresa getInstanceEmpresa(int codigo, String nome, int telefone, String cidade) throws ValidacaoException {
		return cEmpresa.getInstance(codigo, nome, telefone, cidade);
	}

	public Produto getInstanceProduto(int codigo, String nome, double preco, Categoria categoria, int quantidade,
			Empresa fornecedor) throws ValidacaoException {
		return cProduto.getInstance(codigo, nome, preco, categoria, quantidade, fornecedor);
	}

	public Categoria getInstanceCategoria(int codigo, String nome, String descricao) throws ValidacaoException {
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
		return cCategoria.verificarNome(nome);
	}

	public boolean verificarNomeProduto(String nome) {
		return cProduto.verificarNome(nome);
	}

	public boolean removerCategoria(int codigo) throws NegocioException {
		return cCategoria.remove(codigo);
	}

	public boolean removerEmpresa(int codigo) throws NegocioException {
		return cEmpresa.remove(codigo);
	}

	public boolean removerProduto(int codigo) throws NegocioException {
		return cProduto.remove(codigo);
	}

	public Empresa getEmpresa(int codigo) throws NegocioException {
		return cEmpresa.getEmpresa(codigo);
	}

	public Categoria getCategoria(int codigo) throws NegocioException {
		return cCategoria.getCategoria(codigo);
	}

	public Produto getProduto(int codigo) throws NegocioException {
		return cProduto.getProduto(codigo);
	}

	public List<Produto> getProdutosPorEmpresa(int codigoEmpresa) throws NegocioException {
		return cProduto.getProdutosPorEmpresa(codigoEmpresa);
	}

	public boolean verificarProdutoNaEmpresa(int codigoEmpresa, String nomeProduto) throws NegocioException {
		return cEmpresa.verificarProdutoNaEmpresa(codigoEmpresa, nomeProduto);
	}

	public boolean adicionarProdutoFornecedor(int codigoEmpresa, Produto produto)
			throws NegocioException, ValidacaoException {
		return cEmpresa.adicionarProdutoFornecedor(codigoEmpresa, produto);
	}

	public boolean removerProdutoFornecedor(Produto produto, int codF) throws NegocioException, ValidacaoException {
		return cEmpresa.removerProdutoFornecedor(produto, codF);
	}

	public boolean removerCategoriaDosProdutos(int codigoCategoria) throws NegocioException {
		return cProduto.removerCategoriaDosProdutos(codigoCategoria);
	}

	public boolean removerFornecedorDosProdutos(int codigoFornecedor) throws NegocioException {
		return cProduto.removerFornecedorDosProdutos(codigoFornecedor);
	}

	public boolean comprarProduto(int codigo, int quantidade) throws NegocioException, ValidacaoException {
		return cProduto.comprarProduto(codigo, quantidade);
	}

	public boolean reporEstoque(int codigoProduto, int quantidade) throws NegocioException, ValidacaoException {
		return cProduto.reporEstoque(codigoProduto, quantidade);
	}

	public boolean verificarDisponibilidadeProduto(int codigo, int quantidade)
			throws NegocioException, ValidacaoException {
		return cProduto.verificarDisponibilidade(codigo, quantidade);
	}

	public double calcularValorCompra(int codigo, int quantidade) throws NegocioException, ValidacaoException {
		return cProduto.calcularValorCompra(codigo, quantidade);
	}

	public boolean atualizarNomeEmpresa(int codigo, String novoNome) throws NegocioException, ValidacaoException {
		return cEmpresa.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarTelefoneEmpresa(int codigo, int novoTelefone) throws NegocioException, ValidacaoException {
		return cEmpresa.atualizarTelefone(codigo, novoTelefone);
	}

	public boolean atualizarCidadeEmpresa(int codigo, String novaCidade) throws NegocioException, ValidacaoException {
		return cEmpresa.atualizarCidade(codigo, novaCidade);
	}

	public boolean atualizarNomeCategoria(int codigo, String novoNome) throws NegocioException, ValidacaoException {
		return cCategoria.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarDescricaoCategoria(int codigo, String novaDescricao)
			throws NegocioException, ValidacaoException {
		return cCategoria.atualizarDescricao(codigo, novaDescricao);
	}

	public boolean atualizarNomeProduto(int codigo, String novoNome) throws NegocioException, ValidacaoException {
		return cProduto.atualizarNome(codigo, novoNome);
	}

	public boolean atualizarPrecoProduto(int codigo, double novoPreco) throws NegocioException, ValidacaoException {
		return cProduto.atualizarPreco(codigo, novoPreco);
	}

	public boolean atualizarQuantidadeProduto(int codigo, int novaQuantidade)
			throws NegocioException, ValidacaoException {
		return cProduto.atualizarQuantidade(codigo, novaQuantidade);
	}

	public boolean atualizarCategoriaProduto(int codigo, Categoria novaCategoria)
			throws NegocioException, ValidacaoException {
		return cProduto.atualizarCategoria(codigo, novaCategoria);
	}

	public boolean atualizarFornecedorProduto(int codigo, Empresa novoFornecedor)
			throws NegocioException, ValidacaoException {
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

	public List<Compra> getComprasUsuario(int codigoUsuario) throws NegocioException {
		return cUsuario.getCompras(codigoUsuario);
	}

	public boolean pagarTodasPendencias(int codigoUsuario) throws NegocioException {
		return cUsuario.pagarTodasPendencias(codigoUsuario);
	}

	public boolean realizarCompra(int codigoProduto, int quantidade, double valorTotal, int codigoComprador,
			boolean pago) throws NegocioException, ValidacaoException {

		Produto produto = getProduto(codigoProduto);
		if (produto == null) {
			throw new NegocioException("Produto não encontrado");
		}

		comprarProduto(codigoProduto, quantidade);

		Usuario comprador = getComprador(codigoComprador);

		return cCompra.registrarCompra(valorTotal, quantidade, produto, pago, comprador);
	}

	public List<Usuario> listarTodosUsuariosEEmpresas() {
		List<Usuario> todos = new ArrayList<>();

		List<Usuario> usuarios = listarUsuarios();
		for (Usuario usuario : usuarios) {
			int cont = 0;
			for (Empresa emp : listarEmpresas()) {
				if (emp.getCodigo() == usuario.getCodigo()) {
					cont = 1;
					break;
				}
			}
			if (cont == 0) {
				todos.add(usuario);
			}
		}

		todos.addAll(listarEmpresas());

		return todos;
	}

	private Usuario getComprador(int codigo) throws NegocioException {
		List<Usuario> todos = listarTodosUsuariosEEmpresas();
		for (Usuario usuario : todos) {
			if (usuario.getCodigo() == codigo) {
				return usuario;
			}
		}
		throw new NegocioException("Comprador não encontrado. Código: " + codigo);
	}

	private void init() throws ValidacaoException, CadastroException, NegocioException {
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

		Usuario user1 = getInstanceUsuario(getProxCodigoUsuario(), "João Silva", 1111, "São Paulo");
		cadastrarUsuario(user1);

		Usuario user2 = getInstanceUsuario(getProxCodigoUsuario(), "Maria Santos", 2222, "Rio de Janeiro");
		cadastrarUsuario(user2);

		Usuario user3 = getInstanceUsuario(getProxCodigoUsuario(), "Pedro Oliveira", 3333, "Belo Horizonte");
		cadastrarUsuario(user3);

		Usuario user4 = getInstanceUsuario(getProxCodigoUsuario(), "Ana Costa", 4444, "Curitiba");
		cadastrarUsuario(user4);

		realizarCompra(prod1.getCodigo(), 1, calcularValorCompra(prod1.getCodigo(), 1), user1.getCodigo(), true);

		realizarCompra(prod5.getCodigo(), 2, calcularValorCompra(prod5.getCodigo(), 2), user2.getCodigo(), false);

		realizarCompra(prod2.getCodigo(), 1, calcularValorCompra(prod2.getCodigo(), 1), user3.getCodigo(), false);
		realizarCompra(prod4.getCodigo(), 1, calcularValorCompra(prod4.getCodigo(), 1), user3.getCodigo(), true);

		realizarCompra(prod3.getCodigo(), 1, calcularValorCompra(prod3.getCodigo(), 1), empTech.getCodigo(), false);
	}
}
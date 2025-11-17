package TrabalhoAeD.ui;

import TrabalhoAeD.controle.Categoria;
import TrabalhoAeD.controle.Empresa;
import TrabalhoAeD.controle.Produto;
import TrabalhoAeD.controle.Sistema;
import TrabalhoAeD.controle.Usuario;
import TrabalhoAeD.controle.CadastroException;
import TrabalhoAeD.controle.ValidacaoException;
import TrabalhoAeD.controle.NegocioException;

import java.util.List;
import java.util.Scanner;

public class UIProduto {
    private Sistema sis;
    private Scanner scn;
    private UIEmpresa uiEmpresa;
    private UICategoria uiCategoria;

    private static UIProduto instance;

    private UIProduto() throws ValidacaoException, CadastroException, NegocioException {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
        uiEmpresa = UIEmpresa.getInstance();
        uiCategoria = UICategoria.getInstance();
    }

    public static UIProduto getInstance() throws ValidacaoException, CadastroException, NegocioException {
        if (instance == null) {
            instance = new UIProduto();
        }
        return instance;
    }

    public void cadastrarProduto() throws CadastroException, ValidacaoException, NegocioException {
        System.out.println("\n=== CADASTRAR PRODUTO ===");
        try {
            int codigo = sis.getProxCodigoProduto();
            System.out.println("Código: " + codigo);
            System.out.println();

            System.out.println("Se não aparecer Nome:, pressione Enter");
            scn.nextLine();

            System.out.print("Nome: ");
            String nome = scn.nextLine();
            if (nome.isEmpty()) {
                throw new ValidacaoException("Nome vazio.");
            }

            System.out.print("Preço: R$ ");
            double preco = scn.nextDouble();
            if (preco < 0) {
                throw new ValidacaoException("Preço inválido.");
            }

            System.out.print("Quantidade: ");
            int quantidade = scn.nextInt();
            if (quantidade < 0) {
                throw new ValidacaoException("Quantidade inválida.");
            }

            System.out.println("Digite o código da categoria:");
            int cdCat = scn.nextInt();
            Categoria categoria = sis.getCategoria(cdCat);
            while (categoria == null) {
                System.out.println("Código inválido. Tente novamente:");
                cdCat = scn.nextInt();
                categoria = sis.getCategoria(cdCat);
            }

            System.out.println("Digite o código do fornecedor:");
            int cdEmp = scn.nextInt();
            Empresa fornecedor = sis.getEmpresa(cdEmp);
            while (fornecedor == null) {
                System.out.println("Código inválido. Tente novamente:");
                cdEmp = scn.nextInt();
                fornecedor = sis.getEmpresa(cdEmp);
            }

            if (!sis.verificarProdutoNaEmpresa(fornecedor.getCodigo(), nome)) {
                throw new CadastroException("Esta empresa já possui um produto com esse nome!");
            }

            Produto produto = sis.getInstanceProduto(codigo, nome, preco, categoria, quantidade, fornecedor);
            sis.cadastrarProduto(produto);

            if (sis.adicionarProdutoFornecedor(fornecedor.getCodigo(), produto)) {
                System.out.println("Produto cadastrado e vinculado ao fornecedor com sucesso.");
            } else {
                throw new NegocioException("Produto cadastrado, mas falha ao vincular ao fornecedor.");
            }
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public void removerProduto() throws NegocioException, ValidacaoException {
        System.out.println("\n=== REMOVER PRODUTO ===");
        try {
            listarProdutos();
            System.out.println("");
            System.out.print("Informe o código do produto que deseja remover: ");
            int cd = scn.nextInt();
            if (cd < 0) {
                throw new ValidacaoException("Código inválido.");
            }

            Produto produto = sis.getProduto(cd);
            if (produto != null && produto.getFornecedor() != null) {
                if (sis.removerProdutoFornecedor(produto, produto.getFornecedor().getCodigo())) {
                    System.out.println("Produto desvinculado do fornecedor com sucesso.");
                } else {
                    throw new NegocioException("Falha ao desvincular o produto do fornecedor.");
                }
            }

            sis.removerProduto(cd);
            System.out.println("Produto removido com sucesso.");
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public void removerFornecedorDosProdutos(int codigoFornecedor) throws NegocioException {
        sis.removerFornecedorDosProdutos(codigoFornecedor);
    }

    public void comprarProduto() throws ValidacaoException, NegocioException, CadastroException {
        System.out.println("\n=== COMPRAR PRODUTO ===");
        listarProdutos();

        if (sis.listarProdutos().isEmpty()) {
            throw new NegocioException("Nenhum produto disponível.");
        }

        try {
            System.out.print("Código do produto: ");
            int codigoProduto = scn.nextInt();

            Produto produto = sis.getProduto(codigoProduto);
            if (produto == null) {
                throw new NegocioException("Produto não encontrado.");
            }

            System.out.println("Produto: " + produto.getNome());
            System.out.println("Estoque: " + produto.getQuantidade());
            System.out.println("Preço unitário: R$ " + produto.getPreco());

            System.out.print("Quantidade: ");
            int quantidade = scn.nextInt();
            scn.nextLine();

            if (quantidade <= 0) {
                throw new ValidacaoException("Quantidade inválida.");
            }

            if (!sis.verificarDisponibilidadeProduto(codigoProduto, quantidade)) {
                throw new NegocioException("Estoque insuficiente.");
            }

            double valorTotal = sis.calcularValorCompra(codigoProduto, quantidade);
            System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));

            System.out.println("\n=== SELECIONAR USUÁRIO/EMPRESA ===");
            List<Usuario> todos = sis.listarTodosUsuariosEEmpresas();

            if (todos.isEmpty()) {
                throw new NegocioException("Nenhum usuário/empresa cadastrado!");
            }

            System.out.println("CÓDIGO | NOME            | TELEFONE | CIDADE");
            System.out.println("---------------------------------------------");
            for (Usuario usuario : todos) {
                System.out.println(usuario.toString());
            }

            System.out.print("\nDigite o código do usuário/empresa que está comprando: ");
            int codigoComprador = scn.nextInt();
            scn.nextLine();

            Usuario comprador = null;
            for (Usuario usuario : todos) {
                if (usuario.getCodigo() == codigoComprador) {
                    comprador = usuario;
                    break;
                }
            }

            if (comprador == null) {
                throw new NegocioException("Usuário/Empresa não encontrado!");
            }

            System.out.println("\n=== FORMA DE PAGAMENTO ===");
            System.out.println("1 - Pagar agora");
            System.out.println("2 - Pagar depois");
            System.out.print("Opção: ");
            int opcaoPagamento = scn.nextInt();
            scn.nextLine();

            boolean pago = (opcaoPagamento == 1);

            System.out.println("\n=== RESUMO DA COMPRA ===");
            System.out.println("Produto: " + produto.getNome());
            System.out.println("Quantidade: " + quantidade);
            System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));
            System.out.println("Comprador: " + comprador.getNome());
            System.out.println("Status: " + (pago ? "PAGO" : "PENDENTE"));

            System.out.println("\n1 - Confirmar compra");
            System.out.println("2 - Cancelar");
            System.out.print("Opção: ");
            int opcao = scn.nextInt();
            scn.nextLine();

            if (opcao == 1) {
                if (sis.realizarCompra(codigoProduto, quantidade, valorTotal, codigoComprador, pago)) {
                    System.out.println("Compra realizada com sucesso!");
                    System.out.println("Status: " + (pago ? "PAGO" : "PENDENTE"));
                } else {
                    throw new NegocioException("Erro na compra.");
                }
            } else {
                System.out.println("Compra cancelada.");
            }
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public void reporEstoque() throws ValidacaoException, NegocioException {
        System.out.println("\n=== REPOR ESTOQUE ===");
        listarProdutos();

        if (sis.listarProdutos().isEmpty()) {
            throw new NegocioException("Nenhum produto cadastrado.");
        }
        try {
            System.out.print("Código do produto: ");
            int codigoProduto = scn.nextInt();
            scn.nextLine();

            Produto produto = sis.getProduto(codigoProduto);
            if (produto == null) {
                throw new NegocioException("Produto não encontrado.");
            }

            System.out.println("Produto: " + produto.getNome());
            System.out.println("Estoque atual: " + produto.getQuantidade());

            System.out.print("Quantidade a adicionar: ");
            int quantidade = scn.nextInt();
            scn.nextLine();

            if (quantidade <= 0) {
                throw new ValidacaoException("Quantidade deve ser maior que zero.");
            }

            sis.reporEstoque(codigoProduto, quantidade);
            produto = sis.getProduto(codigoProduto);
            System.out.println("Estoque reposto com sucesso!");
            System.out.println("Novo estoque: " + produto.getQuantidade());
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public void alterarProduto() throws ValidacaoException, NegocioException, CadastroException {
        System.out.println("\n=== ALTERAR PRODUTO ===");
        listarProdutos();

        try {
            System.out.print("Informe o código do produto que deseja alterar: ");
            int codigo = scn.nextInt();

            Produto produto = sis.getProduto(codigo);
            if (produto == null) {
                throw new NegocioException("Produto não encontrado!");
            }

            int opcao;
            do {
                opcao = menuAlterarProduto();
                while (opcao <= 0 || opcao > 6) {
                    opcao = menuAlterarProduto();
                }
                switch (opcao) {
                    case 1:
                        alterarNome(codigo, produto.getFornecedor().getCodigo());
                        break;
                    case 2:
                        alterarPreco(codigo);
                        break;
                    case 3:
                        alterarQuantidade(codigo);
                        break;
                    case 4:
                        alterarCategoria(codigo);
                        break;
                    case 5:
                        alterarFornecedor(codigo);
                        break;
                    case 6:
                        System.out.println("Alterações concluídas.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (opcao != 6);
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    private void alterarNome(int codigo, int codigoFornecedor)
            throws ValidacaoException, NegocioException, CadastroException, java.util.InputMismatchException {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio.");
        }

        if (!sis.verificarProdutoNaEmpresa(codigoFornecedor, novoNome)) {
            throw new CadastroException("Esta empresa já possui um produto com esse nome!");
        }

        boolean sucesso = sis.atualizarNomeProduto(codigo, novoNome);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar nome.");
        }
        System.out.println("Nome alterado com sucesso.");
    }

    private void alterarPreco(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.print("Novo preço: R$ ");
        double novoPreco = scn.nextDouble();

        if (novoPreco < 0) {
            throw new ValidacaoException("Preço não pode ser negativo.");
        }

        boolean sucesso = sis.atualizarPrecoProduto(codigo, novoPreco);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar preço.");
        }
        System.out.println("Preço alterado com sucesso.");
    }

    private void alterarQuantidade(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.print("Nova quantidade: ");
        int novaQuantidade = scn.nextInt();

        if (novaQuantidade < 0) {
            throw new ValidacaoException("Quantidade não pode ser negativa.");
        }

        boolean sucesso = sis.atualizarQuantidadeProduto(codigo, novaQuantidade);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar quantidade.");
        }
        System.out.println("Quantidade alterada com sucesso.");
    }

    private void alterarCategoria(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.println("Categorias disponíveis:");
        uiCategoria.listarCategorias();

        System.out.print("Digite o código da nova categoria: ");
        int codigoCategoria = scn.nextInt();

        Categoria novaCategoria = sis.getCategoria(codigoCategoria);
        if (novaCategoria == null) {
            throw new NegocioException("Categoria não encontrada!");
        }

        boolean sucesso = sis.atualizarCategoriaProduto(codigo, novaCategoria);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar categoria.");
        }
        System.out.println("Categoria alterada com sucesso.");
    }

    private void alterarFornecedor(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.println("Empresas disponíveis:");
        uiEmpresa.listarEmpresas();

        System.out.print("Digite o código da nova empresa: ");
        int codigoEmpresa = scn.nextInt();
        scn.nextLine();

        Empresa novaEmpresa = sis.getEmpresa(codigoEmpresa);
        if (novaEmpresa == null) {
            throw new NegocioException("Empresa não encontrada!");
        }
        Produto produto = sis.getProduto(codigo);
        if (produto == null) {
            throw new NegocioException("Produto não encontrado!");
        }

        Empresa empresaAntiga = produto.getFornecedor();

        boolean sucesso = sis.atualizarFornecedorProduto(codigo, novaEmpresa);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar fornecedor.");
        }

        if (empresaAntiga != null) {
            sis.removerProdutoFornecedor(produto, empresaAntiga.getCodigo());
        }

        if (sis.adicionarProdutoFornecedor(codigoEmpresa, produto)) {
            System.out.println("Fornecedor alterado com sucesso.");
        } else {
            throw new NegocioException("Fornecedor alterado, mas falha ao vincular à nova empresa.");
        }
    }

    public void listarProdutos() {
        System.out.println("\n=== LISTA DE TODOS OS PRODUTOS ===");
        List<Produto> produtos = sis.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("CÓDIGO | NOME | PREÇO | QUANTIDADE | CATEGORIA | FORNECEDOR");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            System.out.println(produto.toString());
        }
        System.out.println("Total: " + produtos.size() + " produtos");
    }

    public void listarProdutosPorEmpresa() throws NegocioException {
        System.out.println("\n=== LISTAR PRODUTOS POR EMPRESA ===");
        List<Empresa> empresas = sis.listarEmpresas();

        for (int i = 0; i < empresas.size(); i++) {
            Empresa emp = empresas.get(i);
            System.out.println(emp.getCodigo() + " - " + emp.getNome());
        }

        System.out.print("Digite o código da empresa: ");
        int codigoEmpresa = scn.nextInt();

        List<Produto> produtosEmpresa = sis.getProdutosPorEmpresa(codigoEmpresa);

        if (produtosEmpresa.isEmpty()) {
            System.out.println("Nenhum produto cadastrado para esta empresa.");
        } else {
            for (int i = 0; i < produtosEmpresa.size(); i++) {
                Produto produto = produtosEmpresa.get(i);
                System.out.println(produto.toString());
            }
            System.out.println("Total: " + produtosEmpresa.size() + " produtos");
        }
    }

    public int menuAlterarProduto() throws java.util.InputMismatchException {
        System.out.println("\nO que deseja alterar?");
        System.out.println("1: Nome");
        System.out.println("2: Preço");
        System.out.println("3: Quantidade");
        System.out.println("4: Categoria");
        System.out.println("5: Fornecedor");
        System.out.println("6: Concluir");
        System.out.print("Escolha: ");
        int num = scn.nextInt();
        scn.nextLine();
        return num;
    }
}
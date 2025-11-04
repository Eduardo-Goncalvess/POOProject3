package TrabalhoAeD.ui;

import TrabalhoAeD.controle.Categoria;
import TrabalhoAeD.controle.Empresa;
import TrabalhoAeD.controle.Produto;
import TrabalhoAeD.controle.Sistema;
import java.util.List;
import java.util.Scanner;

public class UIProduto {
    private Sistema sis;
    private Scanner scn;
    private UIEmpresa uiEmpresa;
    private UICategoria uiCategoria;

    private static UIProduto instance;

    private UIProduto() {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
        uiEmpresa = UIEmpresa.getInstance();
        uiCategoria = UICategoria.getInstance();
    }

    public static UIProduto getInstance() {
        if (instance == null) {
            instance = new UIProduto();
        }
        return instance;
    }

    public void cadastrarProduto() {
        System.out.println("\n=== CADASTRAR PRODUTO ===");
        int codigo = sis.getProxCodigoProduto();
        System.out.println("Código: " + codigo);
        System.out.println();

        System.out.println("Se não aparecer Nome:, pressione Enter");
        scn.nextLine();

        System.out.print("Nome: ");
        String nome = scn.nextLine();
        if (nome.isEmpty()) {
            System.out.println("Nome vazio.");
            return;
        }

        System.out.print("Preço: R$ ");
        double preco = scn.nextDouble();
        if (preco < 0) {
            System.out.println("Preço inválido.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scn.nextInt();
        if (quantidade < 0) {
            System.out.println("Quantidade inválida.");
            return;
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
            System.out.println("Esta empresa já possui um produto com esse nome!");
            return;
        }

        Produto produto = sis.getInstanceProduto(codigo, nome, preco, categoria, quantidade, fornecedor);

        if (sis.cadastrarProduto(produto)) {

            if (sis.adicionarProdutoFornecedor(fornecedor.getCodigo(), produto)) {
                System.out.println("Produto cadastrado e vinculado ao fornecedor com sucesso.");
            } else {
                System.out.println("Produto cadastrado, mas falha ao vincular ao fornecedor.");
                sis.removerProduto(codigo);
            }
        } else {
            System.out.println("Falha no cadastro do produto.");
        }
    }

    public void removerProduto() {
        System.out.println("\n=== REMOVER PRODUTO ===");
        listarProdutos();
        System.out.println("");
        System.out.print("Informe o código do produto que deseja remover: ");
        int cd = scn.nextInt();
        if (cd < 0) {
            System.out.println("Código inválido.");
            return;
        }

        Produto produto = sis.getProduto(cd);
        if (produto != null && produto.getFornecedor() != null) {
            if (sis.removerProdutoFornecedor(produto, produto.getFornecedor().getCodigo()))
                System.out.println("Produto desvinculado do fornecedor com sucesso.");
            else
                System.out.println("Falha ao desvincular o produto do fornecedor.");
        }

        if (sis.removerProduto(cd)) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Falha ao remover produto.");
        }
    }

    public void removerFornecedorDosProdutos(int codigoFornecedor) {
        if (sis.removerFornecedorDosProdutos(codigoFornecedor)) {
            System.out.println("Fornecedor removido de todos os produtos.");
        } else {
            System.out.println("Nenhum produto vinculado a este fornecedor.");
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

    public void listarProdutosPorEmpresa() {
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

    public void comprarProduto() {
        System.out.println("\nCOMPRAR PRODUTO");
        listarProdutos();

        if (sis.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto disponível.");
            return;
        }

        System.out.print("Código do produto: ");
        int codigo = scn.nextInt();
        scn.nextLine();

        Produto produto = sis.getProduto(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Produto: " + produto.getNome());
        System.out.println("Estoque: " + produto.getQuantidade());

        System.out.print("Quantidade: ");
        int quantidade = scn.nextInt();
        scn.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        if (!sis.verificarDisponibilidadeProduto(codigo, quantidade)) {
            System.out.println("Estoque insuficiente.");
            return;
        }

        double valorTotal = sis.calcularValorCompra(codigo, quantidade);
        System.out.println("Valor total: R$ " + String.format("%.2f", valorTotal));

        System.out.println("1 - Confirmar");
        System.out.println("2 - Cancelar");
        System.out.print("Opção: ");
        int opcao = scn.nextInt();
        scn.nextLine();

        if (opcao == 1) {
            if (sis.comprarProduto(codigo, quantidade)) {
                System.out.println("Compra realizada.");
            } else {
                System.out.println("Erro na compra.");
            }
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    public void alterarProduto() {
        System.out.println("\n=== ALTERAR PRODUTO ===");
        listarProdutos();

        System.out.print("Informe o código do produto que deseja alterar: ");
        int codigo = scn.nextInt();

        Produto produto = sis.getProduto(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
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
    }

    public int menuAlterarProduto() {
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

    private void alterarNome(int codigo, int codigoFornecedor) {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        if (!sis.verificarProdutoNaEmpresa(codigoFornecedor, novoNome)) {
            System.out.println("Esta empresa já possui um produto com esse nome!");
            return;
        }

        if (sis.atualizarNomeProduto(codigo, novoNome)) {
            System.out.println("Nome alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar nome.");
        }
    }

    private void alterarPreco(int codigo) {
        System.out.print("Novo preço: R$ ");
        double novoPreco = scn.nextDouble();

        if (novoPreco < 0) {
            System.out.println("Preço não pode ser negativo.");
            return;
        }

        if (sis.atualizarPrecoProduto(codigo, novoPreco)) {
            System.out.println("Preço alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar preço.");
        }
    }

    private void alterarQuantidade(int codigo) {
        System.out.print("Nova quantidade: ");
        int novaQuantidade = scn.nextInt();

        if (novaQuantidade < 0) {
            System.out.println("Quantidade não pode ser negativa.");
            return;
        }

        if (sis.atualizarQuantidadeProduto(codigo, novaQuantidade)) {
            System.out.println("Quantidade alterada com sucesso.");
        } else {
            System.out.println("Falha ao alterar quantidade.");
        }
    }

    private void alterarCategoria(int codigo) {
        System.out.println("Categorias disponíveis:");
        uiCategoria.listarCategorias();

        System.out.print("Digite o código da nova categoria: ");
        int codigoCategoria = scn.nextInt();

        Categoria novaCategoria = sis.getCategoria(codigoCategoria);
        if (novaCategoria == null) {
            System.out.println("Categoria não encontrada!");
            return;
        }

        if (sis.atualizarCategoriaProduto(codigo, novaCategoria)) {
            System.out.println("Categoria alterada com sucesso.");
        } else {
            System.out.println("Falha ao alterar categoria.");
        }
    }

    private void alterarFornecedor(int codigo) {
        System.out.println("Empresas disponíveis:");
        uiEmpresa.listarEmpresas();

        System.out.print("Digite o código da nova empresa: ");
        int codigoEmpresa = scn.nextInt();
        scn.nextLine();

        Empresa novaEmpresa = sis.getEmpresa(codigoEmpresa);
        if (novaEmpresa == null) {
            System.out.println("Empresa não encontrada!");
            return;
        }
        Produto produto = sis.getProduto(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        Empresa empresaAntiga = produto.getFornecedor();

        if (sis.atualizarFornecedorProduto(codigo, novaEmpresa)) {
            if (empresaAntiga != null) {
                sis.removerProdutoFornecedor(produto, empresaAntiga.getCodigo());
            }

            if (sis.adicionarProdutoFornecedor(codigoEmpresa, produto)) {
                System.out.println("Fornecedor alterado com sucesso.");
            } else {
                System.out.println("Fornecedor alterado, mas falha ao vincular à nova empresa.");
            }
        } else {
            System.out.println("Falha ao alterar fornecedor.");
        }
    }
}

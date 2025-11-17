package TrabalhoAeD.ui;

import TrabalhoAeD.controle.Empresa;
import TrabalhoAeD.controle.Sistema;
import TrabalhoAeD.controle.CadastroException;
import TrabalhoAeD.controle.ValidacaoException;
import TrabalhoAeD.controle.NegocioException;
import java.util.List;
import java.util.Scanner;

public class UIEmpresa {
    private Sistema sis;
    private Scanner scn;
    private static UIEmpresa instance;

    private UIEmpresa() throws ValidacaoException, CadastroException, NegocioException {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    public static UIEmpresa getInstance() throws ValidacaoException, CadastroException, NegocioException {
        if (instance == null) {
            instance = new UIEmpresa();
        }
        return instance;
    }

    public void cadastrarEmpresa() throws CadastroException, ValidacaoException {
        System.out.println("\n=== CADASTRAR EMPRESA ===");
        int codigo = sis.getProxCodigoEmpresa();
        System.out.print("Código: " + codigo);
        System.out.println();

        System.out.print("Nome: ");
        String nome = scn.next();
        if (nome.isEmpty()) {
            throw new ValidacaoException("Nome vazio.");
        }
        if (sis.verificarNomeEmpresa(nome)) {
            throw new CadastroException("Nome já existente!");
        }

        System.out.print("Telefone (apenas números, 4 dígitos): ");
        int telefone = scn.nextInt();
        while (telefone < 1000 || telefone > 9999) {
            System.out.print("Tente novamente: ");
            telefone = scn.nextInt();
        }

        System.out.print("Cidade: ");
        String cidade = scn.nextLine();
        cidade = scn.nextLine();

        Empresa novaEmpresa = sis.getInstanceEmpresa(codigo, nome, telefone, cidade);
        sis.cadastrarEmpresa(novaEmpresa);
        System.out.println("Empresa cadastrada com sucesso.");
    }

    public void removerEmpresa() throws NegocioException, ValidacaoException, CadastroException {
        System.out.println("\n=== REMOVER EMPRESA ===");
        System.out.print("Lista de Empresas: ");
        listarEmpresas();

        System.out.print("Informe o código da empresa que deseja remover: ");
        int cd = scn.nextInt();
        if (cd < 0) {
            throw new ValidacaoException("Código inválido.");
        }

        UIProduto uiProduto = UIProduto.getInstance();
        uiProduto.removerFornecedorDosProdutos(cd);

        sis.removerEmpresa(cd);
        System.out.println("Empresa removida com sucesso.");
    }

    public void alterarEmpresa() throws ValidacaoException, NegocioException, CadastroException {
        System.out.println("\n=== ALTERAR EMPRESA ===");
        listarEmpresas();

        System.out.print("Informe o código da empresa que deseja alterar: ");
        int codigo = scn.nextInt();

        Empresa empresa = sis.getEmpresa(codigo);
        if (empresa == null) {
            throw new NegocioException("Empresa não encontrada!");
        }

        int opcao;
        do {
            opcao = menuAlterarEmpresa();
            while (opcao <= 0 || opcao > 4) {
                opcao = menuAlterarEmpresa();
            }

            switch (opcao) {
                case 1:
                    alterarNome(codigo);
                    break;
                case 2:
                    alterarTelefone(codigo);
                    break;
                case 3:
                    alterarCidade(codigo);
                    break;
                case 4:
                    System.out.println("Alterações concluídas.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 4);
    }

    private void alterarNome(int codigo) throws ValidacaoException, NegocioException, CadastroException {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio.");
        }

        Empresa empresaAtual = sis.getEmpresa(codigo);
        if (!novoNome.equalsIgnoreCase(empresaAtual.getNome()) && sis.verificarNomeEmpresa(novoNome)) {
            throw new CadastroException("Já existe uma empresa com este nome!");
        }

        boolean sucesso = sis.atualizarNomeEmpresa(codigo, novoNome);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar nome.");
        }
        System.out.println("Nome alterado com sucesso.");
    }

    private void alterarTelefone(int codigo) throws ValidacaoException, NegocioException {
        System.out.print("Novo telefone (4 dígitos): ");
        int novoTelefone = scn.nextInt();

        if (novoTelefone < 1000 || novoTelefone > 9999) {
            throw new ValidacaoException("Telefone deve ter 4 dígitos");
        }

        boolean sucesso = sis.atualizarTelefoneEmpresa(codigo, novoTelefone);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar telefone.");
        }
        System.out.println("Telefone alterado com sucesso.");
    }

    private void alterarCidade(int codigo) throws ValidacaoException, NegocioException {
        System.out.print("Nova cidade: ");
        scn.nextLine();
        String novaCidade = scn.nextLine();

        if (novaCidade.isEmpty()) {
            throw new ValidacaoException("Cidade não pode ser vazia.");
        }

        boolean sucesso = sis.atualizarCidadeEmpresa(codigo, novaCidade);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar cidade.");
        }
        System.out.println("Cidade alterada com sucesso.");
    }

    public void listarEmpresas() {
        System.out.println("\n=== LISTA DE EMPRESAS ===");
        List<Empresa> empresas = sis.listarEmpresas();

        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
            return;
        }

        System.out.println("CÓDIGO | NOME | TELEFONE | CIDADE");
        System.out.println("----------------------------------");
        for (int i = 0; i < empresas.size(); i++) {
            Empresa empresa = empresas.get(i);
            System.out.println(empresa.toString());
        }
        System.out.println("Total: " + empresas.size() + " empresas");
    }

    public int menuAlterarEmpresa() {
        System.out.println("\nO que você deseja alterar?");
        System.out.println("1: Nome");
        System.out.println("2: Telefone");
        System.out.println("3: Cidade");
        System.out.println("4: Concluir");
        System.out.print("Escolha uma opção: ");
        int num = scn.nextInt();
        scn.nextLine();
        return num;
    }
}
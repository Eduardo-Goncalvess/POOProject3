package TrabalhoAeD.ui;

import TrabalhoAeD.controle.Empresa;
import TrabalhoAeD.controle.Sistema;
import java.util.List;
import java.util.Scanner;

public class UIEmpresa {
    private Sistema sis;
    private Scanner scn;
    private static UIEmpresa instance;

    private UIEmpresa() {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    public static UIEmpresa getInstance() {
        if (instance == null) {
            instance = new UIEmpresa();
        }
        return instance;
    }

    public void cadastrarEmpresa() {
        System.out.println("\n=== CADASTRAR EMPRESA ===");
        int codigo = sis.getProxCodigoEmpresa();
        System.out.print("Código: " + codigo);
        System.out.println();

        System.out.print("Nome: ");
        String nome = scn.next();
        if (nome.isEmpty()) {
            System.out.println("Nome vazio.");
            return;
        }
        if (sis.verificarNomeEmpresa(nome)) {
            System.out.println("Nome já existente!");
            return;
        }

        System.out.print("Telefone (apenas números, 4 dígitos): ");
        int telefone = scn.nextInt();
        while (telefone < 1000 || telefone > 9999) {
            System.out.print("Tente novamente: ");
            telefone = scn.nextInt();
        }

        System.out.print("Cidade: ");
        String cidade = scn.nextLine();
        scn.next();

        if (sis.cadastrarEmpresa(sis.getInstanceEmpresa(codigo, nome, telefone, cidade))) {
            System.out.println("Empresa cadastrada com sucesso.");
        } else {
            System.out.println("Falha no cadastro da empresa.");
        }
    }

    public void removerEmpresa() {
        System.out.println("\n=== REMOVER EMPRESA ===");
        System.out.print("Lista de Empresas: ");
        listarEmpresas();

        System.out.print("Informe o código da empresa que deseja remover: ");
        int cd = scn.nextInt();
        if (cd < 0) {
            System.out.println("Código inválido.");
            return;
        }

        UIProduto uiProduto = UIProduto.getInstance();
        uiProduto.removerFornecedorDosProdutos(cd);

        if (sis.removerEmpresa(cd)) {
            System.out.println("Empresa removida com sucesso.");
        } else {
            System.out.println("Falha ao remover empresa.");
        }
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

    public void alterarEmpresa() {
        System.out.println("\n=== ALTERAR EMPRESA ===");
        listarEmpresas();

        System.out.print("Informe o código da empresa que deseja alterar: ");
        int codigo = scn.nextInt();

        Empresa empresa = sis.getEmpresa(codigo);
        if (empresa == null) {
            System.out.println("Empresa não encontrada!");
            return;
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

    private void alterarNome(int codigo) {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        Empresa empresaAtual = sis.getEmpresa(codigo);
        if (!novoNome.equalsIgnoreCase(empresaAtual.getNome()) && sis.verificarNomeEmpresa(novoNome)) {
            System.out.println("Já existe uma empresa com este nome!");
            return;
        }

        if (sis.atualizarNomeEmpresa(codigo, novoNome)) {
            System.out.println("Nome alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar nome.");
        }
    }

    private void alterarTelefone(int codigo) {
        System.out.print("Novo telefone (4 dígitos): ");
        int novoTelefone = scn.nextInt();

        while (novoTelefone < 1000 || novoTelefone > 9999) {
            System.out.print("Telefone inválido. Tente novamente (4 dígitos): ");
            novoTelefone = scn.nextInt();
        }

        if (sis.atualizarTelefoneEmpresa(codigo, novoTelefone)) {
            System.out.println("Telefone alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar telefone.");
        }
    }

    private void alterarCidade(int codigo) {
        System.out.print("Nova cidade: ");
        scn.nextLine();
        String novaCidade = scn.nextLine();

        if (novaCidade.isEmpty()) {
            System.out.println("Cidade não pode ser vazia.");
            return;
        }

        if (sis.atualizarCidadeEmpresa(codigo, novaCidade)) {
            System.out.println("Cidade alterada com sucesso.");
        } else {
            System.out.println("Falha ao alterar cidade.");
        }
    }
}

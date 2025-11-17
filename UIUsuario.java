package TrabalhoAeD.ui;

import TrabalhoAeD.controle.CadastroException;
import TrabalhoAeD.controle.Compra;
import TrabalhoAeD.controle.NegocioException;
import TrabalhoAeD.controle.Sistema;
import TrabalhoAeD.controle.Usuario;
import TrabalhoAeD.controle.ValidacaoException;
import java.util.List;
import java.util.Scanner;

public class UIUsuario {

    private Sistema sis;
    private Scanner scn;
    private static UIUsuario instance;

    private UIUsuario() throws ValidacaoException, CadastroException, NegocioException {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    public static UIUsuario getInstance() throws ValidacaoException, CadastroException, NegocioException {
        if (instance == null) {
            instance = new UIUsuario();
        }
        return instance;
    }

    public void cadastrarUsuario() throws ValidacaoException, CadastroException {
        System.out.println("\n=== CADASTRAR USUÁRIO ===");
        try {
            int codigo = sis.getProxCodigoUsuario();
            System.out.print("Código: " + codigo);
            System.out.println();

            System.out.print("Nome: ");
            String nome = scn.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Nome vazio.");
                return;
            }
            if (sis.verificarNomeUsuario(nome)) {
                System.out.println("Nome já existente!");
                return;
            }

            System.out.print("Telefone (apenas números, 4 dígitos): ");
            int telefone = scn.nextInt();
            while (telefone < 1000 || telefone > 9999) {
                System.out.print("Tente novamente: ");
                telefone = scn.nextInt();
            }

            scn.nextLine();
            System.out.print("Cidade: ");
            String cidade = scn.nextLine();

            if (sis.cadastrarUsuario(sis.getInstanceUsuario(codigo, nome, telefone, cidade))) {
                System.out.println("Usuário cadastrado com sucesso.");
            } else {
                System.out.println("Falha no cadastro do usuário.");
            }
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public void removerUsuario() throws NegocioException, ValidacaoException {
        System.out.println("\n=== REMOVER USUÁRIO ===");
        try {
            listarUsuarios();
            System.out.println("");

            System.out.print("Informe o código do usuário que deseja remover: ");
            int cd = scn.nextInt();
            if (cd < 0) {
                System.out.println("Código inválido.");
                return;
            }

            if (sis.removerUsuario(cd)) {
                System.out.println("Usuário removido com sucesso.");
            } else {
                System.out.println("Falha ao remover usuário.");
            }
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");

        List<Usuario> usuarios = sis.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("CÓDIGO | NOME | TELEFONE | CIDADE");
        System.out.println("----------------------------------");

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println(usuario.toString());
        }

        System.out.println("Total: " + usuarios.size() + " usuários");
    }

    public void alterarUsuario() throws ValidacaoException, NegocioException {
        System.out.println("\n=== ALTERAR USUÁRIO ===");
        listarUsuarios();

        try {
            System.out.print("Informe o código do usuário que deseja alterar: ");
            int codigo = scn.nextInt();

            Usuario usuario = sis.getUsuario(codigo);
            if (usuario == null) {
                System.out.println("Usuário não encontrado!");
                return;
            }

            int opcao;
            do {
                opcao = menuAlterarUsuario();
                while (opcao <= 0 || opcao > 4) {
                    opcao = menuAlterarUsuario();
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
        } catch (java.util.InputMismatchException e) {
            scn.nextLine();
            throw new ValidacaoException("Entrada inválida. Digite um número.");
        }
    }

    public int menuAlterarUsuario() throws java.util.InputMismatchException{
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

    private void alterarNome(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        Usuario usuarioAtual = sis.getUsuario(codigo);
        if (usuarioAtual == null) {
            throw new NegocioException("Usuário não encontrado!");
        }
        if (!novoNome.equalsIgnoreCase(usuarioAtual.getNome()) && sis.verificarNomeUsuario(novoNome)) {
            System.out.println("Já existe um usuário com este nome!");
            return;
        }

        boolean sucesso = sis.atualizarNomeUsuario(codigo, novoNome);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar nome.");
        }
        System.out.println("Nome alterado com sucesso.");
    }

    private void alterarTelefone(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.print("Novo telefone (4 dígitos): ");
        int novoTelefone = scn.nextInt();

        while (novoTelefone < 1000 || novoTelefone > 9999) {
            System.out.print("Telefone inválido. Tente novamente (4 dígitos): ");
            novoTelefone = scn.nextInt();
        }

        if (sis.atualizarTelefoneUsuario(codigo, novoTelefone)) {
            System.out.println("Telefone alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar telefone.");
        }
    }

    private void alterarCidade(int codigo) throws ValidacaoException, NegocioException, java.util.InputMismatchException {
        System.out.print("Nova cidade: ");
        scn.nextLine();
        String novaCidade = scn.nextLine();

        if (novaCidade.isEmpty()) {
            System.out.println("Cidade não pode ser vazia.");
            return;
        }

        if (sis.atualizarCidadeUsuario(codigo, novaCidade)) {
            System.out.println("Cidade alterada com sucesso.");
        } else {
            System.out.println("Falha ao alterar cidade.");
        }
    }

    public void pagarPendencias() throws NegocioException {
        System.out.println("\n=== PAGAR PENDÊNCIAS ===");

        List<Usuario> todos = sis.listarTodosUsuariosEEmpresas();

        if (todos.isEmpty()) {
            System.out.println("Nenhum usuário/empresa cadastrado!");
            return;
        }

        System.out.println("CÓDIGO | NOME            | TELEFONE | CIDADE");
        System.out.println("---------------------------------------------");

        for (int i = 0; i < todos.size(); i++) {
            Usuario usuario = todos.get(i);
            System.out.println(usuario.toString());
        }

        System.out.print("\nDigite o código do usuário/empresa: ");
        int codigoUsuario = scn.nextInt();
        scn.nextLine();

        Usuario usuario = null;
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getCodigo() == codigoUsuario) {
                usuario = todos.get(i);
                break;
            }
        }

        if (usuario == null) {
            System.out.println("Usuário/Empresa não encontrado!");
            return;
        }

        List<Compra> compras = sis.getComprasUsuario(codigoUsuario);

        if (compras.isEmpty()) {
            System.out.println("\n" + usuario.getNome() + " não tem nenhuma compra!");
            return;
        }

        System.out.println("\n=== COMPRAS DE: " + usuario.getNome() + " ===");
        System.out.println("VALOR TOTAL | QUANTIDADE | PRODUTO       | STATUS");
        System.out.println("--------------------------------------------------");

        double totalPendente = 0;
        for (int i = 0; i < compras.size(); i++) {
            Compra compra = compras.get(i);
            System.out.println((i + 1) + ". " + compra.toString());

            if (!compra.isPaga()) {
                totalPendente += compra.getValorTotal();
            }
        }

        System.out.println("\nVALOR TOTAL PENDENTE: R$ " + String.format("%.2f", totalPendente));

        if (totalPendente > 0) {
            System.out.print("\nPagar todas as pendências? (1-Sim / 2-Não): ");
            int opcao = scn.nextInt();
            scn.nextLine();

            if (opcao == 1) {
                if (sis.pagarTodasPendencias(codigoUsuario)) {
                    System.out.println("Todas as pendências pagas! Valor: R$ " + String.format("%.2f", totalPendente));
                } else {
                    System.out.println("Erro ao processar pagamento.");
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Não há pendências para pagar.");
        }
    }
}

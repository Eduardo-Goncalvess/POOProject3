package TrabalhoAeD.ui;

import TrabalhoAeD.controle.Categoria;
import TrabalhoAeD.controle.Sistema;
import TrabalhoAeD.controle.CadastroException;
import TrabalhoAeD.controle.ValidacaoException;
import TrabalhoAeD.controle.NegocioException;
import java.util.List;
import java.util.Scanner;

public class UICategoria {
    private Sistema sis;
    private Scanner scn;
    private static UICategoria instance;

    private UICategoria() throws ValidacaoException, CadastroException, NegocioException {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    public static UICategoria getInstance() throws ValidacaoException, CadastroException, NegocioException {
        if (instance == null) {
            instance = new UICategoria();
        }
        return instance;
    }

    public void cadastrarCategoria() throws CadastroException, ValidacaoException {
        System.out.println("\n=== CADASTRAR CATEGORIA ===");
        int codigo = sis.getProxCodigoCategoria();
        System.out.println("Código: " + codigo);
        System.out.println();

        System.out.print("Nome: ");
        String nome = scn.nextLine();
        if (nome.isEmpty()) {
            throw new ValidacaoException("Nome vazio.");
        }
        if (sis.verificarNomeCategoria(nome)) {
            throw new CadastroException("Nome já existente!");
        }

        System.out.print("Descrição: ");
        String descricao = scn.nextLine();

        Categoria novaCategoria = sis.getInstanceCategoria(codigo, nome, descricao);
        sis.cadastrarCategoria(novaCategoria);
        System.out.println("Categoria cadastrada com sucesso.");
    }

    public void removerCategoria() throws NegocioException, ValidacaoException{
        System.out.println("\n=== REMOVER CATEGORIA ===");
        listarCategorias();
        System.out.println("");
        System.out.print("Informe o código da categoria que deseja remover: ");
        int cd = scn.nextInt();
        if (cd < 0) {
            throw new ValidacaoException("Código inválido.");
        }
        sis.removerCategoriaDosProdutos(cd);
        sis.removerCategoria(cd);
        System.out.println("Categoria removida com sucesso.");
        System.out.println("Todos os produtos que tinham esta categoria agora estão sem categoria.");
    }

    public void alterarCategoria() throws ValidacaoException, NegocioException, CadastroException {
        System.out.println("\n=== ALTERAR CATEGORIA ===");
        listarCategorias();

        System.out.print("Informe o código da categoria que deseja alterar: ");
        int codigo = scn.nextInt();

        Categoria categoria = sis.getCategoria(codigo);
        if (categoria == null) {
            throw new NegocioException("Categoria não encontrada!");
        }

        int opcao;
        do {
            opcao = menuAlterarCategoria();
            while (opcao <= 0 || opcao > 3) {
                opcao = menuAlterarCategoria();
            }

            switch (opcao) {
                case 1:
                    alterarNome(codigo);
                    break;
                case 2:
                    alterarDescricao(codigo);
                    break;
                case 3:
                    System.out.println("Alterações concluídas.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 3);
    }

    private void alterarNome(int codigo) throws ValidacaoException, NegocioException, CadastroException {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio.");
        }

        Categoria categoriaAtual = sis.getCategoria(codigo);
        if (!novoNome.equalsIgnoreCase(categoriaAtual.getNome()) && sis.verificarNomeCategoria(novoNome)) {
            throw new CadastroException("Já existe uma categoria com este nome!");
        }

        boolean sucesso = sis.atualizarNomeCategoria(codigo, novoNome);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar nome.");
        }
        System.out.println("Nome alterado com sucesso.");
    }

    private void alterarDescricao(int codigo) throws ValidacaoException, NegocioException {
        System.out.print("Nova descrição: ");
        String novaDescricao = scn.nextLine();

        if (novaDescricao.isEmpty()) {
            throw new ValidacaoException("Descrição não pode ser vazia.");
        }

        boolean sucesso = sis.atualizarDescricaoCategoria(codigo, novaDescricao);
        if (!sucesso) {
            throw new NegocioException("Falha ao alterar descrição.");
        }
        System.out.println("Descrição alterada com sucesso.");
    }

    public void listarCategorias() {
        System.out.println("\n=== LISTA DE CATEGORIAS ===");
        List<Categoria> categorias = sis.listarCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }

        System.out.println("CÓDIGO | NOME | DESCRIÇÃO");
        System.out.println("--------------------------");
        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = categorias.get(i);
            System.out.println(categoria.toString());
        }
        System.out.println("Total: " + categorias.size() + " categorias");
    }

    public int menuAlterarCategoria() {
        System.out.println("\nO que você deseja alterar?");
        System.out.println("1: Nome");
        System.out.println("2: Descrição");
        System.out.println("3: Concluir");
        System.out.print("Escolha uma opção: ");
        int num = scn.nextInt();
        scn.nextLine();
        return num;
    }
}
package TrabalhoAeD.ui;

import TrabalhoAeD.controle.Categoria;
import TrabalhoAeD.controle.Sistema;
import java.util.List;
import java.util.Scanner;

public class UICategoria {
    private Sistema sis;
    private Scanner scn;
    private static UICategoria instance;

    private UICategoria() {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }

    public static UICategoria getInstance() {
        if (instance == null) {
            instance = new UICategoria();
        }
        return instance;
    }

    public void cadastrarCategoria() {
        System.out.println("\n=== CADASTRAR CATEGORIA ===");
        int codigo = sis.getProxCodigoCategoria();
        System.out.println("Código: " + codigo);
        System.out.println();

        System.out.print("Nome: ");
        String nome = scn.nextLine();
        if (nome.isEmpty()) {
            System.out.println("Nome vazio.");
            return;
        }
        if (sis.verificarNomeCategoria(nome)) {
            System.out.println("Nome já existente!");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = scn.nextLine();

        if (sis.cadastrarCategoria(sis.getInstanceCategoria(codigo, nome, descricao))) {
            System.out.println("Categoria cadastrada com sucesso.");
        } else {
            System.out.println("Falha no cadastro da categoria.");
        }
    }

    public void removerCategoria() {
        System.out.println("\n=== REMOVER CATEGORIA ===");
        listarCategorias();
        System.out.println("");
        System.out.print("Informe o código da categoria que deseja remover: ");
        int cd = scn.nextInt();
        if (cd < 0) {
            System.out.println("Código inválido.");
            return;
        }
        sis.removerCategoriaDosProdutos(cd);
    
        if (sis.removerCategoria(cd)) {
            System.out.println("Categoria removida com sucesso.");
            System.out.println("Todos os produtos que tinham esta categoria agora estão sem categoria.");
        } else {
            System.out.println("Falha ao remover categoria.");
        }
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

    public void alterarCategoria() {
        System.out.println("\n=== ALTERAR CATEGORIA ===");
        listarCategorias();

        System.out.print("Informe o código da categoria que deseja alterar: ");
        int codigo = scn.nextInt();

        Categoria categoria = sis.getCategoria(codigo);
        if (categoria == null) {
            System.out.println("Categoria não encontrada!");
            return;
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

    private void alterarNome(int codigo) {
        System.out.print("Novo nome: ");
        String novoNome = scn.nextLine();

        if (novoNome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }

        Categoria categoriaAtual = sis.getCategoria(codigo);
        if (!novoNome.equalsIgnoreCase(categoriaAtual.getNome()) && sis.verificarNomeCategoria(novoNome)) {
            System.out.println("Já existe uma categoria com este nome!");
            return;
        }

        if (sis.atualizarNomeCategoria(codigo, novoNome)) {
            System.out.println("Nome alterado com sucesso.");
        } else {
            System.out.println("Falha ao alterar nome.");
        }
    }

    private void alterarDescricao(int codigo) {
        System.out.print("Nova descrição: ");
        String novaDescricao = scn.nextLine();

        if (novaDescricao.isEmpty()) {
            System.out.println("Descrição não pode ser vazia.");
            return;
        }

        if (sis.atualizarDescricaoCategoria(codigo, novaDescricao)) {
            System.out.println("Descrição alterada com sucesso.");
        } else {
            System.out.println("Falha ao alterar descrição.");
        }
    }
}

package TrabalhoAeD.ui;

import java.util.Scanner;

public class UIPrincipal {
    private Scanner scn;
    private UICategoria uiCategoria;
    private UIEmpresa uiEmpresa;
    private UIProduto uiProduto;

    public UIPrincipal() {
        scn = new Scanner(System.in);
        uiEmpresa = UIEmpresa.getInstance();
        uiCategoria = UICategoria.getInstance();
        uiProduto = UIProduto.getInstance();
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = menuPrincipal();

            while (opcao < 0 || opcao > 14) {
                System.out.println("Opção inválida. Tente novamente.");
                opcao = menuPrincipal();
            }

            switch (opcao) {
                // -- CATEGORIA --
                case 1:
                    uiCategoria.cadastrarCategoria();
                    break;
                case 2:
                    uiCategoria.listarCategorias();
                    break;
                case 3:
                    uiCategoria.alterarCategoria();
                    break;
                case 4:
                    uiCategoria.removerCategoria();
                    break;

                // -- EMPRESA --
                case 5:
                    uiEmpresa.cadastrarEmpresa();
                    break;
                case 6:
                    uiEmpresa.listarEmpresas();
                    break;
                case 7:
                    uiEmpresa.alterarEmpresa();
                    break;
                case 8:
                    uiEmpresa.removerEmpresa();
                    break;

                // -- PRODUTO --
                case 9:
                    uiProduto.cadastrarProduto();
                    break;
                case 10:
                    uiProduto.listarProdutos();
                    break;
                case 11:
                    uiProduto.alterarProduto();
                    break;
                case 12:
                    uiProduto.removerProduto();
                    break;
                case 13:
                    uiProduto.listarProdutosPorEmpresa();
                    break;
                case 14:
                uiProduto.comprarProduto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        System.out.println("Você saiu do programa");
    }

    public int menuPrincipal() {
        System.out.println("\n=== SISTEMA DE CADASTRO ===");

        System.out.println("\n=== CATEGORIA ===");
        System.out.println("1. Cadastrar categoria");
        System.out.println("2. Listar categorias");
        System.out.println("3. Alterar categoria");
        System.out.println("4. Remover categoria");

        System.out.println("\n=== EMPRESA ===");
        System.out.println("5. Cadastrar empresa");
        System.out.println("6. Listar empresas");
        System.out.println("7. Alterar empresa");
        System.out.println("8. Remover empresa");

        System.out.println("\n=== PRODUTO ===");
        System.out.println("9. Cadastrar produto");
        System.out.println("10. Listar produtos");
        System.out.println("11. Alterar produto");
        System.out.println("12. Remover produto");
        System.out.println("13. Listar produtos por empresa");
        System.out.println("14. Comprar produto");

        System.out.println("\n=== OUTROS ===");
        System.out.println("0. Sair");

        System.out.print("\nEscolha uma opção: ");

        try {
            int opcao = scn.nextInt();
            return opcao;
        } catch (Exception e) {
            System.out.println("Entrada inválida. Digite um número.");
            scn.nextLine();
            return -1;
        }
    }
}

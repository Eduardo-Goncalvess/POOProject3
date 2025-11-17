package TrabalhoAeD.ui;

import java.util.Scanner;

import TrabalhoAeD.controle.CadastroException;
import TrabalhoAeD.controle.NegocioException;
import TrabalhoAeD.controle.ValidacaoException;

public class UIPrincipal {
    private Scanner scn;
    private UICategoria uiCategoria;
    private UIEmpresa uiEmpresa;
    private UIProduto uiProduto;
    private UIUsuario uiUsuario;

    public UIPrincipal() throws ValidacaoException, CadastroException, NegocioException {
        scn = new Scanner(System.in);
        uiEmpresa = UIEmpresa.getInstance();
        uiCategoria = UICategoria.getInstance();
        uiProduto = UIProduto.getInstance();
        uiUsuario = UIUsuario.getInstance();
    }

    public void iniciar() {
        int opcao;
        do {
            opcao = menuPrincipal();

            while (opcao < 0 || opcao > 20) {
                System.out.println("Opção inválida. Tente novamente.");
                opcao = menuPrincipal();
            }

            try {
                switch (opcao) {
                    // -- USUÁRIO --
                    case 1:
                        uiUsuario.cadastrarUsuario();
                        break;
                    case 2:
                        uiUsuario.listarUsuarios();
                        break;
                    case 3:
                        uiUsuario.alterarUsuario();
                        break;
                    case 4:
                        uiUsuario.removerUsuario();
                        break;

                    // -- CATEGORIA --
                    case 5:
                        uiCategoria.cadastrarCategoria();
                        break;
                    case 6:
                        uiCategoria.listarCategorias();
                        break;
                    case 7:
                        uiCategoria.alterarCategoria();
                        break;
                    case 8:
                        uiCategoria.removerCategoria();
                        break;

                    // -- EMPRESA --
                    case 9:
                        uiEmpresa.cadastrarEmpresa();
                        break;
                    case 10:
                        uiEmpresa.listarEmpresas();
                        break;
                    case 11:
                        uiEmpresa.alterarEmpresa();
                        break;
                    case 12:
                        uiEmpresa.removerEmpresa();
                        break;

                    // -- PRODUTO --
                    case 13:
                        uiProduto.cadastrarProduto();
                        break;
                    case 14:
                        uiProduto.listarProdutos();
                        break;
                    case 15:
                        uiProduto.alterarProduto();
                        break;
                    case 16:
                        uiProduto.removerProduto();
                        break;
                    case 17:
                        uiProduto.listarProdutosPorEmpresa();
                        break;
                    case 18:
                        uiProduto.comprarProduto();
                        break;
                    case 19:
                        uiProduto.reporEstoque();
                        break;
                    case 20:
                        uiUsuario.pagarPendencias();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (ValidacaoException e) {
                System.out.println("❌ Erro de validação: " + e.getMessage());
            } catch (CadastroException e) {
                System.out.println("❌ Erro de cadastro: " + e.getMessage());
            } catch (NegocioException e) {
                System.out.println("❌ Erro de negócio: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        } while (opcao != 0);

        System.out.println("Você saiu do programa");
    }

    public int menuPrincipal() {
        System.out.println("\n=== SISTEMA DE CADASTRO ===");

        System.out.println("\n=== USUÁRIO ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Listar usuários");
        System.out.println("3. Alterar usuário");
        System.out.println("4. Remover usuário");

        System.out.println("\n=== CATEGORIA ===");
        System.out.println("5. Cadastrar categoria");
        System.out.println("6. Listar categorias");
        System.out.println("7. Alterar categoria");
        System.out.println("8. Remover categoria");

        System.out.println("\n=== EMPRESA ===");
        System.out.println("9. Cadastrar empresa");
        System.out.println("10. Listar empresas");
        System.out.println("11. Alterar empresa");
        System.out.println("12. Remover empresa");

        System.out.println("\n=== PRODUTO ===");
        System.out.println("13. Cadastrar produto");
        System.out.println("14. Listar produtos");
        System.out.println("15. Alterar produto");
        System.out.println("16. Remover produto");
        System.out.println("17. Listar produtos por empresa");
        System.out.println("18. Comprar produto");
        System.out.println("19. Repor estoque");

        System.out.println("\n=== FINANCEIRO ===");
        System.out.println("20. Pagar pendências");

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
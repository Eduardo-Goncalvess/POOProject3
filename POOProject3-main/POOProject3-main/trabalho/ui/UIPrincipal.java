package TrabalhoAeD.ui;

import java.util.Scanner;
import TrabalhoAeD.ui.UICategoria;
import TrabalhoAeD.ui.UIEmpresa;
import TrabalhoAeD.ui.UIProduto;

public class UIPrincipal {
    private Scanner scn;
    private UICategoria uiCategoria;
    private UIEmpresa uiEmpresa;
    private UIProduto uiProduto;

    public UIPrincipal() {
        scn = new Scanner(System.in);
        uiEmpresa = new UICategoria();
        uiCategoria = new UIEmpresa();
        uiProduto= new UIProduto();
    }

    public void iniciar() {
        int opcao;
        int tmndespa = 10;
        do {
            opcao = menuPrincipal();
            while (opcao <= 0 || opcao > 19) {
                opcao = menuPrincipal();
            }

            switch (opcao) {
            case 1:
                //uiDisc.cadastrarDisciplina();
                break;
            case 2:
                //uiDisc.removerDisciplina(tmndespa);
                break;
            case 3:
                //uiDisc.listarDisciplinas(tmndespa);
                break;
            case 4:
                //uiAluno.cadastrarAluno();
                break;
            case 5:
                //uiAluno.alterarAluno();
                break;
            case 6:
                //uiAluno.listarAlunos();
                break;
            case 7:
                //uiTurma.matricularAluno();
                break;
            case 8:
               //uiTurma.listarMatriculas(tmndespa);
                break;
            case 9:
                //tmndespa = tamnahodespa(tmndespa);
                break;
            case 10:
                //uiTurma.cadastrarTurma();
                break;
            case 11:
                //uiTurma.removerTurma();
                break;
            case 12:
                //uiTurma.listarTurmas(tmndespa);
                break;
            case 13:
                //uiTurma.alterarTurma();
                break;
            case 14:
                //uiTurma.adicionarDisciplina();
                break;
            case 15:
                //uiTurma.removerDisciplina(tmndespa);
                break;
            case 16:
                //uiTurma.listarDisciplinasDeUmaTurma(tmndespa);
                break;
            case 17:
                //uiDisc.alterarDisciplina(tmndespa);
                break;
            case 18:
                //uiTurma.removerMatricula(tmndespa);
                break;
            case 19:
                //uiTurma.alterarNotasAluno(tmndespa);
                break;
            default:
                System.out.println("Você saiu do programa");
                break;
            }
        } while (opcao != 0);
        System.out.println("Você saiu do programa");
    }

    public int menuPrincipal() {
            System.out.println("\n=== SISTEMA DE MATRÍCULAS ===");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Remover disciplina");
            System.out.println("3. Listar disciplinas");
            System.out.println("4. Cadastrar aluno");
            System.out.println("5. Alterar dados de aluno");
            System.out.println("6. Listar alunos");
            System.out.println("7. Matricular aluno em disciplina");
            System.out.println("8. Listar matrículas");
            System.out.println("9. Configurar largura das colunas");
            System.out.println("10. Cadastrar Turma");
            System.out.println("11. Remover Turma");
            System.out.println("12. Listar Turma");
            System.out.println("13. Alterar Turma");
            System.out.println("14. Adicionar Disciplina Turma");
            System.out.println("15. Remover Disciplina Turma");
            System.out.println("16. Listar Disciplinas De uma Turma");
            System.out.println("17. Alterar Disciplina");
            System.out.println("18. Remover Matrícula");
            System.out.println("19. Alterar Notas Aluno");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

        return scn.nextInt();
    }

    private int tamnahodespa(int tmndespa) {
        System.out.println("Novo tamanho: ");
        int tamanho = scn.nextInt();
        while (tamanho <= 5) {
            System.out.println("Insira um valor maior que 5.");
            tamanho = scn.nextInt();
        }
        return tamanho;
    }
}

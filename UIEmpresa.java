package TrabalhoAeD.ui;

import java.util.Scanner;

import trabalhoAeH.controle.Sistema;

public class UIEmpresa {
    private Sistema sis;
    private Scanner scn;

    public UIEmpresa() {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }
}

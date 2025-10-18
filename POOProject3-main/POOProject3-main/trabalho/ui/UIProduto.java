package TrabalhoAeD.ui;

import java.util.Scanner;

import trabalhoAeH.controle.Sistema;

public class UIProduto {
    private Sistema sis;
    private Scanner scn;

    public UIProduto() {
        sis = Sistema.getInstance();
        scn = new Scanner(System.in);
    }
    
}

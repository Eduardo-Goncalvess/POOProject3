package TrabalhoAeD.controle;

import trabalhoAeH.controle.ControleAluno;
import trabalhoAeH.controle.ControleDisciplina;
import trabalhoAeH.controle.ControleMatricula;
import trabalhoAeH.controle.ControleNotas;
import trabalhoAeH.controle.ControleTurma;

public class Sistema {
    private ControleNotas cNota;
	private ControleDisciplina cDisciplina;
	private ControleAluno cAluno;
	private ControleTurma cTurma;
	private ControleMatricula cMatricula;

	private static Sistema instance; 

	private Sistema() {
		cAluno = new ControleAluno();
		cDisciplina = new ControleDisciplina();
		cTurma = new ControleTurma();
		cMatricula = new ControleMatricula();
		cNota = new ControleNotas();
		init();

	}

	public static Sistema getInstance() {
		if (instance == null)
			instance = new Sistema();

		return instance;
	}
}

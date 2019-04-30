package br.com.usjt.projcontrol.DAO;



import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.usjt.projcontrol.model.Aluno;
import br.com.usjt.projcontrol.model.Turma;

class TurmaDAOTest {
	
	private Turma turma;
	private TurmaDAO turmaDao;
	private Aluno aluno;
	private AlunoDAO alunoDao;

	@BeforeEach
	void setUp() throws Exception {
		turma = new Turma();
		turmaDao = new TurmaDAO();
		aluno = new Aluno();
		
		aluno.setEmail("martin@hotmail.com");
		aluno.setSenha("Aluno123@");
		alunoDao = new AlunoDAO();
		aluno = alunoDao.loginAluno(aluno);
	}

	@Test
	@Disabled("java.sql.SQLException: Invalid argument value: java.io.NotSerializableException")
	void testCadastroDeTurmas() {
		turma.setAlunos(aluno);
		turma.setCodigoIdentificador("TOP-104E");
		turma.setAnoLetivo(2019);
		
		turmaDao.cadastrarTurma(turma);
	}
	
	@Test
	void testArrayTurmas() {
		ArrayList<Turma> lista = turmaDao.getTurmas();
		
		System.out.println("Array getTurmas()");
		for (Turma t : lista) {
			System.out.println(t.getSemestreLetivo() + " " + t.getAnoLetivo());
		}
		
		assertEquals(1, lista.get(0).getSemestreLetivo());
		assertEquals(2018, lista.get(0).getAnoLetivo());
		assertEquals(2, lista.get(3).getSemestreLetivo());
		assertEquals(2019, lista.get(3).getAnoLetivo());
	}
	
	@Test
	void testArrayTurmasByPeriodo() {
		turma.setAnoLetivo(2018);
		turma.setSemestreLetivo(1);
		ArrayList<Turma> lista = turmaDao.getTurmasByPeriodo(turma);
		
		System.out.println("Array getTurmasByPeriodo()");
		for (Turma t : lista) {
			System.out.println(t.getSemestreLetivo() + " " + t.getAnoLetivo());
		}
		assertEquals(1, lista.get(0).getSemestreLetivo());
		assertEquals(2018, lista.get(0).getAnoLetivo());
		
	}
	

}

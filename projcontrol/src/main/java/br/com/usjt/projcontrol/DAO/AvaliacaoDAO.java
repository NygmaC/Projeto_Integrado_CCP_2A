package br.com.usjt.projcontrol.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.usjt.projcontrol.Conexao.Conexao;
import br.com.usjt.projcontrol.model.Avaliacao;

public class AvaliacaoDAO {

	private Conexao conexao = null;
	
	public ArrayList<Avaliacao> getAvaliacoes() {
		ArrayList<Avaliacao> arrayAvaliacoes = new ArrayList<Avaliacao>();
		Avaliacao ava = null;
		conexao = new Conexao();
		
		String sql ="select  Av.id AS avaliacao_id ,T.sigla AS sigla, "
				+ "U.nome AS nome, " + 
				"T.semestre_letivo AS semestre, "
				+ "T.ano_letivo AS ano, Av.nota AS nota, "
				+ "G.nome AS nome_grupo " + 
				"from avaliacao Av " + 
				"INNER JOIN turma_aluno Ta ON Av.id = Ta.id " + 
				"INNER JOIN turma T ON T.id = Ta.turma_id " + 
				"INNER JOIN grupo G ON Ta.grupo_id = G.id " + 
				"INNER JOIN professor P ON P.professor_id = G.orientador_id " + 
				"INNER JOIN usuario U ON P.professor_id = U.id GROUP BY Av.id;";
		

		try (Connection conn = conexao.getConexaoMYSQL()) {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				ava = new Avaliacao();
				ava.setAvaliacaoId(rs.getInt(1));
				ava.setNota(rs.getDouble("nota"));
				arrayAvaliacoes.add(ava);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexao.closeConexaoMYSQL();
		}
	
		return arrayAvaliacoes;
	}
	
	
	public void getAvaliacaoId() {

		String sqlAvaliacaoId = "SELECT id FROM grupo ORDER BY id DESC LIMIT 0,1";

		try (Connection conn = Conexao.getConexaoMYSQL()) {
			PreparedStatement stmt = conn.prepareStatement(sqlAvaliacaoId);

			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			conexao.closeConexaoMYSQL();
		}
	}
}
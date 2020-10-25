package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import modelo.Funcionario;

//classe Dao está relacionada com questoes de persistencia no banco
//Create, Read, Update and Delete- são as quatro operações básicas (criação, consulta, atualização e exclusao de dados) 
public class FuncionarioDao {

	// passa o objeto da Funcionario - Adiciona informações no banco
	public void adiciona(Funcionario f) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO funcionario (nomeFuncionario, cpfFuncionario, telefoneFuncionario, "
				+ "enderecoFuncionario, cargoFuncionario, salarioFuncionario) VALUES (?, ?, ?, ?, ?, ?)";

		// objeto associado á conexao, utilizado para executar SQL no banco
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		// setar cada interrogação
		comandoSql.setString(1, f.getNome());
		comandoSql.setString(2, f.getCpf());
		comandoSql.setString(3, f.getTelefone());
		comandoSql.setString(4, f.getEndereco());
		comandoSql.setString(5, f.getCargo());
		comandoSql.setDouble(6, f.getSalario());

		// executa o 'comandoSql
		comandoSql.execute();
		// grava as informacoes no banco
		Conexao.getInstance().commit();
	}

	// Atualiza informacoes no banco passando como parametros as colunas da tabela
	public void atualiza(Funcionario f) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE funcionario SET nomeFuncionario=?, cpfFuncionario=?, telefoneFuncionario=?, "
				+ "enderecoFuncionario=?, cargoFuncionario=?, salarioFuncionario=?  WHERE idFuncionario=?";

		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setString(1, f.getNome());
		comandoSql.setString(2, f.getCpf());
		comandoSql.setString(3, f.getTelefone());
		comandoSql.setString(4, f.getEndereco());
		comandoSql.setString(5, f.getCargo());
		comandoSql.setDouble(6, f.getSalario());

		comandoSql.execute();
		// grava as informacoes no banco
		Conexao.getInstance().commit();
	}

	// deleta referente ao id no BD na tabela Funcionario
	public void remove(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM funcionario WHERE idFuncionario=?";

		// o objeto 'comandoSql' associado a conexao, é utilizado para executar a sql
		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);
		comandoSql.setInt(1, id);

		comandoSql.execute();
		// grava as informacoes no banco
		Conexao.getInstance().commit();
	}

	public List<Funcionario> listaTodos() throws ClassNotFoundException, SQLException {
		List<Funcionario> lista = new LinkedList<Funcionario>();

		String sql = "SELECT * FROM funcionario";

		PreparedStatement comandoSql = Conexao.getInstance().prepareStatement(sql);

		ResultSet rs = comandoSql.executeQuery();

		while (rs.next()) {
			Funcionario f = new Funcionario();
			f.setId(rs.getInt("idFuncionario"));
			f.setNome(rs.getString("nomeFuncionario"));
			f.setCpf(rs.getString("cpfFuncionario"));
			f.setTelefone(rs.getString("telefoneFuncionario"));
			f.setEndereco(rs.getString("enderecoFuncionario"));
			f.setCargo(rs.getString("cargoFuncionario"));
			f.setSalario(rs.getDouble("salarioFuncionario"));

			lista.add(f);
		}

		return lista;
	}

}

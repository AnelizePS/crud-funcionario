package dao;

//classe que realiza a conexao com o BD criado no MySql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection conexao;
	private String banco = "bd_aplicacao"; // nome do BD
	private String login = "root"; // login do mysql
	private String senha = ""; // senha do mysql

	// trata exce�oes
	public Conexao() throws ClassNotFoundException, SQLException {
		// carrega o drive
		Class.forName("com.mysql.cj.jdbc.Driver");

		// estabelece conex�o: drive://servidor:porta/banco, login, senha
		conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.banco, this.login, this.senha);

		// as modificac�es so s�o salvas no banco com o commit (explicito no c�digo)
		conexao.setAutoCommit(false);
	}

	// verifica se n�o existe conexao, sen�o houver cria uma nova conex�o
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if (conexao == null)
			// seta o atriuto de classe 'conexao'
			new Conexao();

		return conexao;
	}
}
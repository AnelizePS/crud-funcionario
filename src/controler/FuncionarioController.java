package controler;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.FuncionarioDao;
import modelo.Funcionario;

@ManagedBean // indica que a classe administra o CRUD de funcionario
@ViewScoped // para nao perder o ID na atualizacao, mantém ID

// classe que gerencia as informacoes da aplicacao - do banco para a interface ou o contrario 
public class FuncionarioController {

	private Funcionario funcionario = new Funcionario();


	public void gravar() {

		FuncionarioDao dao = new FuncionarioDao();

		try {
			if (funcionario.getId() == null) {
				dao.adiciona(funcionario);
			} else {
				dao.atualiza(funcionario);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Limpa as inf do formulario
		funcionario = new Funcionario();
	}

	public List<Funcionario> getTodosFuncionarios() {
		List<Funcionario> lista = null;
		try {
			lista = new FuncionarioDao().listaTodos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void remover(Funcionario f) {
		try {
			new FuncionarioDao().remove(f.getId());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void carregar(Funcionario f) {
		funcionario = f;
	}

}

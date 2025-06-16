package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexao {
	public static Connection conectar() {
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/crudfuncionario?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
					"appuser",
					"senha");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e.getMessage());
		}
		return conexao;
	}

	public static void main(String[] args) {
		try (Connection conn = Conexao.conectar()) {
			if (conn != null) {
				System.out.println("Conexão bem-sucedida!");
			} else {
				System.out.println("Falha ao conectar.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
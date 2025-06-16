package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Funcionario;

public class ControllerFuncionario {

  public static void adicionarFuncionario(Funcionario funcionario) throws SQLException {
    String sql = "INSERT INTO funcionario (codigo, nome, contacto, departamento, genero, estado_civil, dias_trabalhados, salario_diario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setInt(1, funcionario.getCodigo());
      stmt.setString(2, funcionario.getNome());
      stmt.setString(3, funcionario.getContacto());
      stmt.setString(4, funcionario.getDepartamento());
      stmt.setString(5, funcionario.getGenero());
      stmt.setString(6, funcionario.getEstadoCivil());
      stmt.setInt(7, funcionario.getDiasTrabalhados());
      stmt.setDouble(8, funcionario.getSalarioDiario());
      stmt.executeUpdate();
    }
  }
  
  
  
  
  public static ArrayList<Funcionario> listarFuncionariosFiltrados() throws SQLException {
	    ArrayList<Funcionario> lista = new ArrayList<>();
	    String sql = "SELECT * FROM funcionario WHERE (departamento = 'Operacoes' OR departamento = 'RH') AND (dias_trabalhados * salario_diario) > 50000 AND dias_trabalhados >= 30 AND genero = 'Masculino' AND estado_civil IN ('Casado', 'Solteiro', 'Viúvo')";
	    try (Connection con = Conexao.conectar();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            lista.add(new Funcionario(
	                rs.getInt("codigo"),
	                rs.getString("nome"),
	                rs.getString("contacto"),
	                rs.getString("departamento"),
	                rs.getString("genero"),
	                rs.getString("estado_civil"),
	                rs.getInt("dias_trabalhados"),
	                rs.getDouble("salario_diario")
	            ));
	        }
	    }
	    return lista;
	}



  public static ArrayList<Funcionario> listarFuncionarios() throws SQLException {
    ArrayList<Funcionario> lista = new ArrayList<>();
    String sql = "SELECT * FROM funcionario";
    try (Connection con = Conexao.conectar();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        lista.add(new Funcionario(
            rs.getInt("codigo"),
            rs.getString("nome"),
            rs.getString("contacto"),
            rs.getString("departamento"),
            rs.getString("genero"),
            rs.getString("estado_civil"),
            rs.getInt("dias_trabalhados"),
            rs.getDouble("salario_diario")));
      }
    }
    return lista;
  }

  public static void actualizarFuncionario(Funcionario funcionario) throws SQLException {
    String sql = "UPDATE funcionario SET nome=?, contacto=?, departamento=?, genero=?, estado_civil=?, dias_trabalhados=?, salario_diario=? WHERE codigo=?";
    try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setString(1, funcionario.getNome());
      stmt.setString(2, funcionario.getContacto());
      stmt.setString(3, funcionario.getDepartamento());
      stmt.setString(4, funcionario.getGenero());
      stmt.setString(5, funcionario.getEstadoCivil());
      stmt.setInt(6, funcionario.getDiasTrabalhados());
      stmt.setDouble(7, funcionario.getSalarioDiario());
      stmt.setInt(8, funcionario.getCodigo());
      stmt.executeUpdate();
    }
  }

  public static void removerFuncionario(int codigo) throws SQLException {
    String sql = "DELETE FROM funcionario WHERE codigo=?";
    try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
      stmt.setInt(1, codigo);
      stmt.executeUpdate();
    }
  }
}
package model;

import java.io.Serializable;

public class Funcionario implements Comparable<Funcionario>, Serializable {
  private int codigo;
  private String nome;
  private String contacto;
  private String departamento;
  private String genero;
  private String estadoCivil;
  private int diasTrabalhados;
  private double salarioDiario;

  public Funcionario(int codigo, String nome, String contacto, String departamento,
      String genero, String estadoCivil, int diasTrabalhados, double salarioDiario) {
    this.codigo = codigo;
    this.nome = nome;
    this.contacto = contacto;
    this.departamento = departamento;
    this.genero = genero;
    this.estadoCivil = estadoCivil;
    this.diasTrabalhados = diasTrabalhados;
    this.salarioDiario = salarioDiario;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public int getDiasTrabalhados() {
    return diasTrabalhados;
  }

  public void setDiasTrabalhados(int diasTrabalhados) {
    this.diasTrabalhados = diasTrabalhados;
  }

  public double getSalarioDiario() {
    return salarioDiario;
  }

  public void setSalarioDiario(double salarioDiario) {
    this.salarioDiario = salarioDiario;
  }

  public double calcularSalario() {
    return diasTrabalhados * salarioDiario;
  }

  public int compareTo(Funcionario outro) {
    return Integer.compare(this.codigo, outro.codigo);
  }

  public String toString() {
    return codigo + " - " + nome + " - " + contacto + " - " + departamento + " - " +
        genero + " - " + estadoCivil + " - " + diasTrabalhados + " dias - Salário: " + calcularSalario();
  }
}

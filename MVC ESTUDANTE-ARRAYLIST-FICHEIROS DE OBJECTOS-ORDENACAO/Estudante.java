import java.io.Serializable;

public class Estudante implements Comparable<Estudante>, Serializable {
  private int codigo;
  private String nome;
  private double test1;
  private double test2;

  public Estudante(int codigo, String nome, double test1, double test2) {
    this.codigo = codigo;
    this.nome = nome;
    this.test1 = test1;
    this.test2 = test2;
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

  public double getTest1() {
    return test1;
  }

  public void setTest1(double test1) {
    this.test1 = test1;
  }

  public double getTest2() {
    return test2;
  }

  public void setTest2(double test2) {
    this.test2 = test2;
  }

  public double calculaMedia() {
    return (test1 + test2) / 2;
  }

  public int compareTo(Estudante estudante) {
    if (this.codigo > estudante.codigo)
      return 1;
    if (this.codigo < estudante.codigo)
      return -1;
    return 0;
  }

  public String toString() {
    return codigo + "-" + nome + "-" + test1 + "-" + test2 + "-" + calculaMedia();
  }
}
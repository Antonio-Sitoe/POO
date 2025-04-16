public class Estudante implements Comparable<Estudante> {
  private int codigo;
  private String nome;
  private double nota1;
  private double nota2;

  public Estudante(int codigo2, String nome, double nota1, double nota2) {
    this.nome = nome;
    this.nota1 = nota1;
    this.nota2 = nota2;
    this.codigo = codigo2;
  }

  public Estudante() {
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public int getCodigo() {
    return this.codigo;
  }

  public double getNota1() {
    return this.nota1;
  }

  public void setNota1(double nota1) {
    this.nota1 = nota1;
  }

  public double getNota2() {
    return this.nota2;
  }

  public void setNota2(double nota2) {
    this.nota2 = nota2;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double calculaMedia() {
    return (nota1 + nota2) / 2;
  }

  @Override
  public String toString() {
    return codigo + "-" + nome + "-" + nota1 + "-" + nota2 + "-" + calculaMedia();
  }

  @Override
  public int compareTo(Estudante estudante) {
    if (this.codigo > estudante.codigo)
      return 1;
    if (this.codigo < estudante.codigo)
      return -1;
    return 0;
  }

}
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ControllerFuncionario {
  static Scanner k = new Scanner(System.in);

  public static void adicionarFuncionario() throws IOException {
    FileWriter fw = new FileWriter("funcionarios.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);

    System.out.println("Codigo: ");
    int codigo = Integer.parseInt(k.next());

    System.out.println("Nome: ");
    String nome = k.next();

    System.out.println("Contacto: ");
    String contacto = k.next();

    System.out.println("Departamento: ");
    String departamento = k.next();

    System.out.println("Gênero: ");
    String genero = k.next();

    System.out.println("Estado Civil: ");
    String estadoCivil = k.next();

    System.out.println("Dias Trabalhados: ");
    int dias = k.nextInt();

    System.out.println("Salário Diário: ");
    double salarioDiario = k.nextDouble();

    Funcionario funcionario = new Funcionario(codigo, nome, contacto, departamento, genero, estadoCivil, dias,
        salarioDiario);

    bw.write(funcionario.toString());
    bw.newLine();
    bw.close();
    fw.close();
    System.out.println("FUNCIONÁRIO ADICIONADO COM SUCESSO!");
  }

  public static ArrayList<Funcionario> listaFuncionarios() throws IOException {
    ArrayList<Funcionario> funcionarios = new ArrayList<>();
    FileReader fr = new FileReader("funcionarios.txt");
    BufferedReader br = new BufferedReader(fr);
    String linha;

    while ((linha = br.readLine()) != null && !linha.isEmpty()) {
      String[] dados = linha.split("-");
      if (dados.length >= 8) {
        int codigo = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String contacto = dados[2];
        String departamento = dados[3];
        String genero = dados[4];
        String estadoCivil = dados[5];
        int dias = Integer.parseInt(dados[6]);
        double salarioDiario = Double.parseDouble(dados[7]);

        funcionarios
            .add(new Funcionario(codigo, nome, contacto, departamento, genero, estadoCivil, dias, salarioDiario));
      }
    }

    br.close();
    fr.close();
    return funcionarios;
  }

  public static void listarFuncionarios() throws IOException {
    ArrayList<Funcionario> funcionarios = listaFuncionarios();
    for (Funcionario f : funcionarios) {
      System.out.println(f.toString());
    }
  }

  public static void actualizarFuncionario(int codigo) throws IOException {
    ArrayList<Funcionario> funcionarios = listaFuncionarios();
    boolean encontrado = false;

    for (Funcionario f : funcionarios) {
      if (f.getCodigo() == codigo) {
        encontrado = true;
        char opcao;
        do {
          System.out.println("MENU DE ACTUALIZAÇÃO:");
          System.out.println("1 - Nome");
          System.out.println("2 - Contacto");
          System.out.println("3 - Departamento");
          System.out.println("4 - Gênero");
          System.out.println("5 - Estado Civil");
          System.out.println("6 - Dias Trabalhados");
          System.out.println("7 - Salário Diário");
          System.out.println("0 - Sair");
          opcao = k.next().charAt(0);

          switch (opcao) {
            case '1':
              System.out.print("Novo Nome: ");
              f.setNome(k.next());
              break;
            case '2':
              System.out.print("Novo Contacto: ");
              f.setContacto(k.next());
              break;
            case '3':
              System.out.print("Novo Departamento: ");
              f.setDepartamento(k.next());
              break;
            case '4':
              System.out.print("Novo Gênero: ");
              f.setGenero(k.next());
              break;
            case '5':
              System.out.print("Novo Estado Civil: ");
              f.setEstadoCivil(k.next());
              break;
            case '6':
              System.out.print("Novos Dias Trabalhados: ");
              f.setDiasTrabalhados(k.nextInt());
              break;
            case '7':
              System.out.print("Novo Salário Diário: ");
              f.setSalarioDiario(k.nextDouble());
              break;
            case '0':
              break;
            default:
              System.out.println("Opção inválida!");
          }
        } while (opcao != '0');
      }
    }

    FileWriter fw = new FileWriter("funcionarios.txt");
    BufferedWriter bw = new BufferedWriter(fw);
    for (Funcionario f : funcionarios) {
      bw.write(f.toString());
      bw.newLine();
    }
    bw.close();
    fw.close();

    if (encontrado) {
      System.out.println("FUNCIONÁRIO ACTUALIZADO COM SUCESSO!");
    } else {
      System.out.println("FUNCIONÁRIO NÃO ENCONTRADO!");
    }
  }

  public static void removerFuncionario(int codigo) throws IOException {
    ArrayList<Funcionario> funcionarios = listaFuncionarios();
    boolean removido = funcionarios.removeIf(f -> f.getCodigo() == codigo);

    FileWriter fw = new FileWriter("funcionarios.txt");
    BufferedWriter bw = new BufferedWriter(fw);
    for (Funcionario f : funcionarios) {
      bw.write(f.toString());
      bw.newLine();
    }
    bw.close();
    fw.close();

    if (removido) {
      System.out.println("FUNCIONÁRIO REMOVIDO COM SUCESSO!");
    } else {
      System.out.println("FUNCIONÁRIO NÃO ENCONTRADO!");
    }
  }

  public static void ordenarFuncionarios() throws IOException {
    ArrayList<Funcionario> funcionarios = listaFuncionarios();
    Collections.sort(funcionarios, (f1, f2) -> Integer.compare(f1.getCodigo(), f2.getCodigo()));

    FileWriter fw = new FileWriter("funcionarios.txt");
    BufferedWriter bw = new BufferedWriter(fw);
    for (Funcionario f : funcionarios) {
      bw.write(f.toString());
      bw.newLine();
    }
    bw.close();
    fw.close();
    System.out.println("FUNCIONÁRIOS ORDENADOS COM SUCESSO!");
  }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ControllerFuncionario {
  static Scanner k = new Scanner(System.in);

  public static ArrayList<Funcionario> lerFuncionarios() {
    ArrayList<Funcionario> lista = new ArrayList<>();

    try (ObjectInputStream ler = new ObjectInputStream(new FileInputStream("funcionarios.bin"))) {
      while (true) {
        lista.add((Funcionario) ler.readObject());
      }
    } catch (EOFException e) {
      // fim do ficheiro
    } catch (Exception e) {
      System.out.println("Erro ao ler: " + e.getMessage());
    }

    return lista;
  }

  public static void salvarFuncionarios(ArrayList<Funcionario> funcionarios) throws IOException {
    try (ObjectOutputStream escrever = new ObjectOutputStream(new FileOutputStream("funcionarios.bin"))) {
      for (Funcionario f : funcionarios) {
        if (f != null) {
          escrever.writeObject(f);
        }
      }
    }
  }

  public static void adicionarFuncionario() throws IOException {
    ArrayList<Funcionario> lista = lerFuncionarios();

    System.out.print("Código: ");
    int codigo = k.nextInt();

    System.out.print("Nome: ");
    String nome = k.next();

    System.out.print("Contacto: ");
    String contacto = k.next();

    System.out.print("Departamento: ");
    String departamento = k.next();

    System.out.print("Gênero: ");
    String genero = k.next();

    System.out.print("Estado Civil: ");
    String estadoCivil = k.next();

    System.out.print("Dias Trabalhados: ");
    int dias = k.nextInt();

    System.out.print("Salário Diário: ");
    double salario = k.nextDouble();

    Funcionario novo = new Funcionario(codigo, nome, contacto, departamento, genero, estadoCivil, dias, salario);
    lista.add(novo);

    salvarFuncionarios(lista);
    System.out.println("FUNCIONÁRIO ADICIONADO!");
  }

  public static void listarFuncionarios() {
    ArrayList<Funcionario> lista = lerFuncionarios();

    if (lista.isEmpty()) {
      System.out.println("Nenhum funcionário encontrado.");
    } else {
      for (Funcionario f : lista) {
        System.out.println(f);
      }
    }
  }

  public static void actualizarFuncionario(int codigo) throws IOException {
    ArrayList<Funcionario> lista = lerFuncionarios();

    for (Funcionario f : lista) {
      if (f != null && f.getCodigo() == codigo) {
        System.out.println("1-Nome\n2-Contacto\n3-Departamento\n4-Gênero\n5-Estado Civil\n6-Dias\n7-Salário\n0-Sair");
        char opcao;
        do {
          System.out.print("Opção: ");
          opcao = k.next().charAt(0);
          switch (opcao) {
            case '1':
              System.out.print("Nome: ");
              f.setNome(k.next());
              break;
            case '2':
              System.out.print("Contacto: ");
              f.setContacto(k.next());
              break;
            case '3':
              System.out.print("Departamento: ");
              f.setDepartamento(k.next());
              break;
            case '4':
              System.out.print("Gênero: ");
              f.setGenero(k.next());
              break;
            case '5':
              System.out.print("Estado Civil: ");
              f.setEstadoCivil(k.next());
              break;
            case '6':
              System.out.print("Dias: ");
              f.setDiasTrabalhados(k.nextInt());
              break;
            case '7':
              System.out.print("Salário: ");
              f.setSalarioDiario(k.nextDouble());
              break;
          }
        } while (opcao != '0');
      }
    }

    salvarFuncionarios(lista);
    System.out.println("FUNCIONÁRIO ACTUALIZADO.");
  }

  public static void removerFuncionario(int codigo) throws IOException {
    ArrayList<Funcionario> lista = lerFuncionarios();

    lista.removeIf(f -> f != null && f.getCodigo() == codigo);

    salvarFuncionarios(lista);
    System.out.println("FUNCIONÁRIO REMOVIDO.");
  }

  public static void ordenarFuncionarios() throws IOException {
    ArrayList<Funcionario> lista = lerFuncionarios();
    Collections.sort(lista);
    salvarFuncionarios(lista);
    System.out.println("FUNCIONÁRIOS ORDENADOS.");
  }
}

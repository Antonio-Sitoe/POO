
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.ObjectOutputStream;

public class ControllerEstudante {
  static Scanner k = new Scanner(System.in);

  public static ArrayList<Estudante> listaDeEstudantes() {
    ArrayList<Estudante> estudantes = new ArrayList<Estudante>();

    try {
      FileInputStream abrir = new FileInputStream("estudantes.bin");
      ObjectInputStream ler = new ObjectInputStream(abrir);

      for (int i = 0; i < 100; i++) {
        estudantes.add((Estudante) ler.readObject());
      }
      ler.close();
      abrir.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return estudantes;
  }

  public static void adicionarEStudantes() throws Exception {
    ArrayList<Estudante> estudantes = listaDeEstudantes();

    FileOutputStream criar = new FileOutputStream("estudantes.bin");
    ObjectOutputStream escrever = new ObjectOutputStream(criar);
    int i = 0;

    while (i < estudantes.size()) {
      if (estudantes.get(i) != null) {
        escrever.writeObject(estudantes.get(i));
        i++;
      }
    }

    System.out.print("Codigo:");
    int Codigo = k.nextInt();
    System.out.print("Nome:");
    String nome = k.next();
    System.out.print("Teste1:");
    double Teste1 = k.nextDouble();
    System.out.print("Teste2:");
    double Teste2 = k.nextDouble();

    escrever.writeObject(new Estudante(Codigo, nome, Teste1, Teste2));

    escrever.close();
    criar.close();
    System.out.println("Estudante ADICIONADO COM SUCESSO");
  }

  public static void listarEstudantes() throws Exception {
    ArrayList<Estudante> estudantes = listaDeEstudantes();

    int i = 0;

    while (i < estudantes.size()) {
      if (estudantes.get(i) != null) {
        System.out.println(estudantes.get(i).toString());
        i++;
      }
    }
  }

  public static void actualizarEstudante(int codigo) throws Exception {
    ArrayList<Estudante> estudantes = listaDeEstudantes();
    char opcao;

    for (Estudante estudante : estudantes) {
      if ((estudante != null) && (estudante.getCodigo() == codigo)) {

        do {
          System.out.println("MENU DE ACTUALIZACAO DO ESTUDANTE");
          System.out.println("1-Nome: ");
          System.out.println("2-Teste 1: ");
          System.out.println("3-Teste 2: ");
          System.out.println("0-Sair: ");
          System.out.println("Digite uma opcao: ");
          opcao = k.next().charAt(0);

          switch (opcao) {
            case '1':
              System.out.println("Nome:");
              String nome = k.next();
              estudante.setNome(nome);
              estudante.setTest1(estudante.getTest1());
              estudante.setTest2(estudante.getTest2());
              break;

            case '2':
              System.out.println("Teste 1:");
              double teste1 = k.nextDouble();
              estudante.setTest1(teste1);
              estudante.setNome(estudante.getNome());
              estudante.setTest2(estudante.getTest2());
              break;
            case '3':
              System.out.println("Teste 2:");
              double teste2 = k.nextDouble();
              estudante.setTest2(teste2);
              estudante.setNome(estudante.getNome());
              estudante.setTest1(estudante.getTest1());
              break;

            case '0':
              System.out.println("Saiu do menu de actualizacao");
              break;

            default:
              System.out.println("Opcao invalida:");
              break;
          }
        } while (opcao != '0');
      }
    }

    FileOutputStream criar = new FileOutputStream("estudantes.bin");
    ObjectOutputStream escrever = new ObjectOutputStream(criar);

    int i = 0;
    while (i < estudantes.size()) {
      if (estudantes.get(i) != null) {
        escrever.writeObject(estudantes.get(i));
      }
      i++;
    }

    escrever.close();
    criar.close();
    System.out.println("ESTUDANTE ACTUALIZADO COM SUCESSO.");
  }

  public static void removerEstudante(int codigo) throws Exception {
    ArrayList<Estudante> estudantes = listaDeEstudantes();

    for (int i = 0; i < estudantes.size(); i++) {
      if ((estudantes.get(i) != null) &&
          (estudantes.get(i).getCodigo() == codigo)) {
        estudantes.remove(estudantes.get(i));
      }
    }

    FileOutputStream criar = new FileOutputStream("estudantes.bin");
    ObjectOutputStream escrever = new ObjectOutputStream(criar);

    int i = 0;

    while (i < estudantes.size()) {
      if (estudantes.get(i) != null) {
        escrever.writeObject(estudantes.get(i));
        i++;
      }
    }
    escrever.close();
    criar.close();
    System.out.println("ESTUDANTE REMOVIDO COM SUCESSO");
  }

  public static void ordenarEstudante() throws Exception {
    ArrayList<Estudante> estudantes = listaDeEstudantes();

    Collections.sort(estudantes);

    FileOutputStream criar = new FileOutputStream("estudantes.bin");
    ObjectOutputStream escrever = new ObjectOutputStream(criar);

    int i = 0;
    while (i < estudantes.size()) {
      if (estudantes.get(i) != null) {
        escrever.writeObject(estudantes.get(i));
        i++;
      }
    }
    escrever.close();
    criar.close();
    System.out.println("ESTUDANTES ORDENADOS COM SUCESSO");
  }
}

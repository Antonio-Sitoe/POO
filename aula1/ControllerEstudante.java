import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ControllerEstudante {
  static Scanner k = new Scanner(System.in);

  public static void adicionarEstudante() throws IOException {
    FileWriter criar = new FileWriter("notas.txt", true);
    BufferedWriter adicionar = new BufferedWriter(criar);

    System.out.println("Codigo ");
    int codigo = Integer.parseInt(k.next());

    System.out.println("Nome ");
    String nome = k.next();

    System.out.println("TESTE 1 ");
    double test1 = k.nextDouble();

    System.out.println("TESTE 2 ");
    double test2 = k.nextDouble();

    Estudante estudante = new Estudante(codigo, nome, test1, test2);

    adicionar.write(estudante.toString());
    adicionar.newLine();

    adicionar.close();
    criar.close();
    System.out.println("ADICIONDO COM SUCESSO: ");

  }

  public static ArrayList<Estudante> listaDeEstudantes() throws IOException {
    ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
    FileReader abrir = new FileReader("notas.txt");
    BufferedReader ler = new BufferedReader(abrir);
    String linha = ler.readLine();

    String[] elementosDaLinha;

    while ((linha != null) && !linha.isEmpty()) {
      elementosDaLinha = linha.split("-");
      if (elementosDaLinha[0] != null && !elementosDaLinha[0].isEmpty() &&
          elementosDaLinha[1] != null && !elementosDaLinha[1].isEmpty() &&
          elementosDaLinha[2] != null && !elementosDaLinha[2].isEmpty() &&
          elementosDaLinha[3] != null && !elementosDaLinha[3].isEmpty()) {
        int codigo = Integer.parseInt(elementosDaLinha[0]);
        String nome = elementosDaLinha[1];
        double nota1 = Double.parseDouble(elementosDaLinha[2]);
        double nota2 = Double.parseDouble(elementosDaLinha[3]);
        estudantes.add(new Estudante(codigo, nome, nota1, nota2));
      }

      linha = ler.readLine();
    }

    ler.close();
    abrir.close();
    return estudantes;
  }

  public static void listarEstudantes() throws IOException {
    ArrayList<Estudante> estudantes = listaDeEstudantes();
    for (int i = 0; i < estudantes.size(); i++) {
      System.out.println(estudantes.get(i).toString());
    }
  }

  static void actualizarEstudante(int codigo) throws IOException {
    ArrayList<Estudante> estudantes = listaDeEstudantes();

    char opcao;
    boolean exist = false;

    for (Estudante estudante : estudantes) {
      if ((estudante.getCodigo()) == codigo) {
        exist = true;
        do {
          System.out.println("MENU DE ACTUALIZACAO DO ESTUDANTE");
          System.out.println("1-Nome:");
          System.out.println("2-Teste1:");
          System.out.println("3-Teste2:");
          System.out.println("0-Sair:");
          opcao = k.next().charAt(0);
          switch (opcao) {
            case '1':
              System.out.println("Nome:");
              String nome = k.next();
              estudante.setNome(nome);
              estudante.setNota1(estudante.getNota1());
              estudante.setNota2(estudante.getNota2());
              break;
            case '2':
              System.out.println("Teste 1:");
              double teste1 = k.nextDouble();
              estudante.setNota1(teste1);
              estudante.setNome(estudante.getNome());
              estudante.setNota2(estudante.getNota2());
              break;

            case '3':
              System.out.println("Teste 2:");
              double teste2 = k.nextDouble();
              estudante.setNota2(teste2);
              estudante.setNome(estudante.getNome());
              estudante.setNota1(estudante.getNota1());
              break;
            case '@':
              System.out.println("Saiu do menu de actualizacao");
              break;
            default:
              System.out.println("Opcao invalida:");
              break;
          }

        } while (opcao != '0');
      }
    }

    FileWriter crian = new FileWriter("notas.txt");
    BufferedWriter adicionar = new BufferedWriter(crian);
    for (int i = 0; i < estudantes.size(); i++) {
      adicionar.write(estudantes.get(i).toString());
      adicionar.newLine();
    }
    adicionar.close();
    crian.close();
    if (exist) {
      System.out.println("ACTUALIZADO COM SUCESSO:");
    } else {
      System.out.println("NENHUM ESTUDANTE ENCONTRADO:");
    }
  }

  public static void removerEstudante(int codigo) throws IOException {
    ArrayList<Estudante> estudantes = listaDeEstudantes();
    boolean exist = false;
    for (int i = 0; i < estudantes.size(); i++) {
      if (estudantes.get(i).getCodigo() == codigo) {
        exist = true;
        estudantes.remove(estudantes.get(i));
      }
    }

    FileWriter criar = new FileWriter("notas.txt");
    BufferedWriter adicionar = new BufferedWriter(criar);
    for (int i = 0; i < estudantes.size(); i++) {
      adicionar.write(estudantes.get(i).toString());
      adicionar.newLine();
    }
    adicionar.close();
    criar.close();
    if (exist) {
      System.out.println("REMOVIDO COM SUCESSO:");
    } else {
      System.out.println("NENHUM ESTUDANTE ENCONTRADO:");
    }
  }

  public static void ordenarEstudantes() throws IOException {
    ArrayList<Estudante> estudantes = listaDeEstudantes();
    Collections.sort(estudantes);

    FileWriter fileWriter = new FileWriter("notas.txt");
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    for (int i = 0; i < estudantes.size(); i++) {
      bufferedWriter.write(estudantes.get(i).toString());
      bufferedWriter.newLine();
    }

    bufferedWriter.close();
    fileWriter.close();
    System.out.println("ESTUDANTES ORDENADOS COM SUCESSO:");
  }
}

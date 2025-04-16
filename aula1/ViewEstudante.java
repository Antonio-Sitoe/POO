import java.io.IOException;
import java.util.Scanner;

public class ViewEstudante {
  public static void main(String[] args) throws IOException {
    Scanner k = new Scanner(System.in);
    int opcao;
    do {
      System.out.println("MENU PRINCIPAL DO SISTEMA");
      System.out.println("1 - Adicionar Estudante: ");
      System.out.println("2 - Listar Estudantes: ");
      System.out.println("3 - Actualizar Estudante: ");
      System.out.println("4 - Remover Estudante: ");
      System.out.println("5 - Ordenar Estudantes: ");
      System.out.println("8 - Sair: ");
      System.out.print("Opcao: ");
      opcao = k.nextInt();

      switch (opcao) {
        case 1:
          ControllerEstudante.adicionarEstudante();
          break;
        case 2:
          ControllerEstudante.listarEstudantes();
          break;
        case 3:
          System.out.print("Digite um codigo de estudante que existe na lista: ");
          int codigoActualizacao = k.nextInt();
          ControllerEstudante.actualizarEstudante(codigoActualizacao);
          break;
        case 4:
          System.out.print("Digite um codigo de estudante que existe na lista: ");
          int codigoRemocao = k.nextInt();
          ControllerEstudante.removerEstudante(codigoRemocao);
          break;
        case 5:
          ControllerEstudante.ordenarEstudantes();
          break;
        case 8:
          System.out.println("Saiu do menu principal");
          break;
        default:
          System.out.println("Opcao invalida");
          break;
      }

    } while (opcao != 8);
  }
}

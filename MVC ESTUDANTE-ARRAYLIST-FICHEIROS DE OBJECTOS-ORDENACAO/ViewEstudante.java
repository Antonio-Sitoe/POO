import java.util.Scanner;

public class ViewEstudante {
  static Scanner R = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    int opcao;
    do {
      System.out.println("++MENU PRINCIPAL++");
      System.out.println("1-Adicionar Estudante: ");
      System.out.println("2-Listar Estudante: ");
      System.out.println("3-Actualizar Estudante: ");
      System.out.println("4-Remover Estudante: ");
      System.out.println("5-Ordenar Estudantes: ");
      System.out.println("0-Sair: ");
      System.out.print("Digite uma opcao: ");
      opcao = R.nextInt();

      switch (opcao) {
        case 1:
          ControllerEstudante.adicionarEStudantes();
          break;
        case 2:
          ControllerEstudante.listarEstudantes();
          break;
        case 3:
          System.out.println("Digite um codigo que existe na lista: ");
          int codigoActualizacao = R.nextInt();
          ControllerEstudante.actualizarEstudante(codigoActualizacao);
          break;
        case 4:
          System.out.println("Digite um codigo que existe na lista: ");
          int codigoRemocao = R.nextInt();
          ControllerEstudante.removerEstudante(codigoRemocao);
          break;
        case 5:
          ControllerEstudante.ordenarEstudante();
          break;
        case 0:
          System.out.println("SAIU DO MENU PRINCIPAL");
          break;
        default:
          System.out.println("OPCAO INVALIDA");
          break;
      }
    } while (opcao != 0);
  }
}

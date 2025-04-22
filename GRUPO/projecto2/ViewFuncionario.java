import java.util.Scanner;

public class ViewFuncionario {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    int opcao;
    do {
      System.out.println("++ MENU FUNCIONÁRIO ++");
      System.out.println("1 - Adicionar");
      System.out.println("2 - Listar");
      System.out.println("3 - Actualizar");
      System.out.println("4 - Remover");
      System.out.println("5 - Ordenar");
      System.out.println("0 - Sair");
      System.out.print("Opção: ");
      opcao = sc.nextInt();

      switch (opcao) {
        case 1 -> ControllerFuncionario.adicionarFuncionario();
        case 2 -> ControllerFuncionario.listarFuncionarios();
        case 3 -> {
          System.out.print("Código do funcionário: ");
          ControllerFuncionario.actualizarFuncionario(sc.nextInt());
        }
        case 4 -> {
          System.out.print("Código do funcionário: ");
          ControllerFuncionario.removerFuncionario(sc.nextInt());
        }
        case 5 -> ControllerFuncionario.ordenarFuncionarios();
        case 0 -> System.out.println("SAIU DO MENU.");
        default -> System.out.println("Opção inválida.");
      }
    } while (opcao != 0);
  }
}

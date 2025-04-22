import java.io.IOException;
import java.util.Scanner;

public class ViewFuncionario {
  public static void main(String[] args) throws IOException {
    Scanner k = new Scanner(System.in);
    int opcao;

    do {
      System.out.println("\n=== MENU FUNCIONÁRIO ===");
      System.out.println("1 - Adicionar Funcionário");
      System.out.println("2 - Listar Funcionários");
      System.out.println("3 - Actualizar Funcionário");
      System.out.println("4 - Remover Funcionário");
      System.out.println("5 - Ordenar Funcionários");
      System.out.println("0 - Sair");
      System.out.print("Opção: ");
      opcao = k.nextInt();

      switch (opcao) {
        case 1:
          ControllerFuncionario.adicionarFuncionario();
          break;
        case 2:
          ControllerFuncionario.listarFuncionarios();
          break;
        case 3:
          System.out.print("Digite o código do funcionário: ");
          int codAtualizar = k.nextInt();
          ControllerFuncionario.actualizarFuncionario(codAtualizar);
          break;
        case 4:
          System.out.print("Digite o código do funcionário: ");
          int codRemover = k.nextInt();
          ControllerFuncionario.removerFuncionario(codRemover);
          break;
        case 5:
          ControllerFuncionario.ordenarFuncionarios();
          break;
        case 0:
          System.out.println("Encerrando...");
          break;
        default:
          System.out.println("Opção inválida!");
      }
    } while (opcao != 0);
  }
}

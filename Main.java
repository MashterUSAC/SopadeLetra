import java.util.Scanner;

public class Main {
    
    public void SopadeLetra() {
        System.out.println("------ BIENVENIDO, ELIGE UNA OPCIÓN ------");
        System.out.println("1.  NUEVA PARTIDA");
        System.out.println("2.  TOP PUNTAJES");
        System.out.println("3.  HISTORIAL PUNTAJES");

        try (Scanner SAAS = new Scanner(System.in)) {
            int opcion = SAAS.nextInt();

            switch (opcion) {
                case 1:
                    nuevaPartida();
                    break;
                case 2:
                    topPuntajes();
                    break;
                case 3:
                    historialPuntajes();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    private void nuevaPartida() {
        System.out.println("Iniciando nueva partida...");
        System.out.println("Ingrese su nombre de Usuario: ");
        (Scanner nombre = new Scanner(System.in)) {
            String nombreUsuario = nombre.nextLine();
            System.out.println("Bienvenido " + nombreUsuario);
        }
    }

    private void topPuntajes() {
        System.out.println("---- Top Puntajes ----");
    }

    private void historialPuntajes() {
        System.out.println("Historial Puntajes");
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.SopadeLetra();
    }
}
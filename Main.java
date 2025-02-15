import java.util.Scanner;
import java.util.Random;

public class Main {

    private String[] palabrasObjetivo = new String[5]; // Almacenar las palabras
    private static final int TAMANO = 15;
    private static final char VACIO = '.';
    private char[][] sopaDeLetras = new char[TAMANO][TAMANO];
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void SopadeLetra() {
        System.out.println("------ ELIGE UNA OPCIÓN ------");
        System.out.println("1.  JUGAR");
        System.out.println("2.  TOP PUNTAJES");
        System.out.println("3.  HISTORIAL PUNTAJES");

        int opcion = scanner.nextInt();
        scanner.nextLine(); 

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

    private void nuevaPartida() {
        System.out.println("------ QUE DESEA HACER? ------");
        System.out.println("1. Insertar");
        int Daisy = scanner.nextInt();
        scanner.nextLine(); 
        if (Daisy == 1) {
            Insertar();
        }
    }

    private void Insertar() {
        System.out.println("--- Ingrese 5 palabras para buscar (Debe ser entre 3 a 8 caracteres) ---");
        for (int i = 0; i < 5; i++) {
            System.out.println("Ingrese una nueva palabra ");
            String palabra = scanner.nextLine().toUpperCase();
            int cantidad = palabra.length();
            if (cantidad < 3 || cantidad > 8) {
                System.out.println("Debe ser entre 3 a 8 caracteres");
                i--;
            } else {
                palabrasObjetivo[i] = palabra;
            }
        }
        generarSopaDeLetras();
        imprimirSopa();
        buscarPalabras();
    }

    private void generarSopaDeLetras() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                sopaDeLetras[i][j] = VACIO;
            }
        }

        for (String palabra : palabrasObjetivo) {
            boolean colocada = false;
            while (!colocada) {
                int fila = random.nextInt(TAMANO);
                int columna = random.nextInt(TAMANO);
                int direccion = random.nextInt(2);

                if (direccion == 0 && puedeColocarHorizontal(fila, columna, palabra)) {
                    for (int k = 0; k < palabra.length(); k++) {
                        sopaDeLetras[fila][columna + k] = palabra.charAt(k);
                    }
                    colocada = true;
                } else if (direccion == 1 && puedeColocarVertical(fila, columna, palabra)) {
                    for (int k = 0; k < palabra.length(); k++) {
                        sopaDeLetras[fila + k][columna] = palabra.charAt(k);
                    }
                    colocada = true;
                }
            }
        }

        llenarConLetrasAleatorias();
    }

    private boolean puedeColocarHorizontal(int fila, int columna, String palabra) {
        if (columna + palabra.length() > TAMANO) return false;
        for (int k = 0; k < palabra.length(); k++) {
            if (sopaDeLetras[fila][columna + k] != VACIO) return false;
        }
        return true;
    }

    private boolean puedeColocarVertical(int fila, int columna, String palabra) {
        if (fila + palabra.length() > TAMANO) return false;
        for (int k = 0; k < palabra.length(); k++) {
            if (sopaDeLetras[fila + k][columna] != VACIO) return false;
        }
        return true;
    }

    private void llenarConLetrasAleatorias() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (sopaDeLetras[i][j] == VACIO) {
                    sopaDeLetras[i][j] = (char) ('A' + random.nextInt(26));
                }
            }
        }
    }

    private void imprimirSopa() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(sopaDeLetras[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void buscarPalabras() {
        while (true) {
            System.out.println("Ingrese una palabra encontrada (o 'salir' para terminar): ");
            if (scanner.hasNextLine()) {
                String palabra = scanner.nextLine().toUpperCase();
                if (palabra.equalsIgnoreCase("salir")) {
                    break;
                }
                if (reemplazarPalabra(palabra)) {
                    System.out.println("Palabra encontrada y reemplazada con asteriscos.");
                } else {
                    System.out.println("Palabra no encontrada.");
                }
                imprimirSopa();
            }
        }
    }

    private boolean reemplazarPalabra(String palabra) {
        // Buscar y reemplazar palabra horizontalmente
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j <= TAMANO - palabra.length(); j++) {
                boolean encontrada = true;
                for (int k = 0; k < palabra.length(); k++) {
                    if (sopaDeLetras[i][j + k] != palabra.charAt(k)) {
                        encontrada = false;
                        break;
                    }
                }
                if (encontrada) {
                    for (int k = 0; k < palabra.length(); k++) {
                        sopaDeLetras[i][j + k] = '*';
                    }
                    return true;
                }
            }
        }
        // Buscar y reemplazar palabra verticalmente
        for (int i = 0; i <= TAMANO - palabra.length(); i++) {
            for (int j = 0; j < TAMANO; j++) {
                boolean encontrada = true;
                for (int k = 0; k < palabra.length(); k++) {
                    if (sopaDeLetras[i + k][j] != palabra.charAt(k)) {
                        encontrada = false;
                        break;
                    }
                }
                if (encontrada) {
                    for (int k = 0; k < palabra.length(); k++) {
                        sopaDeLetras[i + k][j] = '*';
                    }
                    return true;
                }
            }
        }
        return false;
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
import java.util.Scanner;

public class Main {

    private String[] palabrasBuscar = new String[5]; // Arreglo para almacenar las palabras

    // Códigos de escape ANSI para colores
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public void SopadeLetra() {
        System.out.println("------ ELIGE UNA OPCIÓN ------");
        System.out.println("1.  JUGAR");
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
        System.out.println("------ QUE DESEA HACER? ------");
        try (Scanner nombre = new Scanner(System.in)) {
            System.out.println("1. Insertar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Salir");
            int Daisy = nombre.nextInt();
            
            switch (Daisy) {
                case 1:
                    Insertar();
                    break;
                case 2:
                    Modificar();
                    break;
                case 3:
                    Eliminar();
                    break;
                case 4:
                    SopadeLetra();
                    break;
                default:
                    break;
            }
        }
    }

    private void Insertar() {
        System.out.println("--- Ingrese 5 palabras para buscar (Debe ser entre 3 a 8 carácteres) ---");
        try (Scanner Palabras = new Scanner(System.in)) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Ingrese una nueva palabra ");
                String palabra = Palabras.nextLine();
                int cantidad = palabra.length();
                if (cantidad < 3 || cantidad > 8) {
                    System.out.println("Debe ser entre 3 a 8 carácteres");
                    i--; // Decrementar el contador para pedir la palabra nuevamente
                } else {
                    palabrasBuscar[i] = palabra; // Guardar la palabra en el arreglo
                    System.out.println("Palabra " + (i + 1) + ": " + palabra);
                }
            }
        }
        crearSopaDeLetras(); // Llamar al método para crear la sopa de letras
    }

    private void crearSopaDeLetras() {
        char[][] sopaDeLetras = new char[15][15];

        // Inicializar la sopa de letras con caracteres aleatorios
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                sopaDeLetras[i][j] = (char) ('A' + Math.random() * 26);
            }
        }

        // Colocar las palabras en la sopa de letras
        for (String palabra : palabrasBuscar) {
            boolean colocada = false;
            while (!colocada) {
                int direccion = (int) (Math.random() * 2); // 0 para horizontal, 1 para vertical
                if (direccion == 0) { // Horizontal
                    int fila = (int) (Math.random() * 15);
                    int columna = (int) (Math.random() * (15 - palabra.length()));
                    if (puedeColocarHorizontal(sopaDeLetras, fila, columna, palabra)) {
                        for (int k = 0; k < palabra.length(); k++) {
                            sopaDeLetras[fila][columna + k] = palabra.charAt(k);
                        }
                        colocada = true;
                    }
                } else { // Vertical
                    int fila = (int) (Math.random() * (15 - palabra.length()));
                    int columna = (int) (Math.random() * 15);
                    if (puedeColocarVertical(sopaDeLetras, fila, columna, palabra)) {
                        for (int k = 0; k < palabra.length(); k++) {
                            sopaDeLetras[fila + k][columna] = palabra.charAt(k);
                        }
                        colocada = true;
                    }
                }
            }
        }

        // Imprimir la sopa de letras con las palabras en color rojo
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boolean esParteDePalabra = false;
                for (String palabra : palabrasBuscar) {
                    if (esParteDePalabra(sopaDeLetras, i, j, palabra)) {
                        esParteDePalabra = true;
                        break;
                    }
                }
                if (esParteDePalabra) {
                    System.out.print(ANSI_RED + sopaDeLetras[i][j] + ANSI_RESET + " ");
                } else {
                    System.out.print(sopaDeLetras[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private boolean puedeColocarHorizontal(char[][] sopaDeLetras, int fila, int columna, String palabra) {
        if (columna + palabra.length() > 15) {
            return false;
        }
        for (int k = 0; k < palabra.length(); k++) {
            if (sopaDeLetras[fila][columna + k] != (char) ('A' + Math.random() * 26)) {
                return false;
            }
        }
        return true;
    }

    private boolean puedeColocarVertical(char[][] sopaDeLetras, int fila, int columna, String palabra) {
        if (fila + palabra.length() > 15) {
            return false;
        }
        for (int k = 0; k < palabra.length(); k++) {
            if (sopaDeLetras[fila + k][columna] != (char) ('A' + Math.random() * 26)) {
                return false;
            }
        }
        return true;
    }

    private boolean esParteDePalabra(char[][] sopaDeLetras, int fila, int columna, String palabra) {
        if (columna + palabra.length() <= 15) {
            boolean esHorizontal = true;
            for (int k = 0; k < palabra.length(); k++) {
                if (sopaDeLetras[fila][columna + k] != palabra.charAt(k)) {
                    esHorizontal = false;
                    break;
                }
            }
            if (esHorizontal) {
                return true;
            }
        }
        if (fila + palabra.length() <= 15) {
            boolean esVertical = true;
            for (int k = 0; k < palabra.length(); k++) {
                if (sopaDeLetras[fila + k][columna] != palabra.charAt(k)) {
                    esVertical = false;
                    break;
                }
            }
            if (esVertical) {
                return true;
            }
        }
        return false;
    }

    private void Modificar() {
        System.out.println("--- Ingrese palabra que desea Modificar ---");
    }

    private void Eliminar() {
        System.out.println("--- Introduce palabra que desea eliminar ---");
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    ListaEnlazadaTicket ticketsResueltos = new ListaEnlazadaTicket();
    ColaDinamica ticketsCola = new ColaDinamica();
    Scanner reader = new Scanner(System.in);
    int opcion;

    // Herramientas
    public void limpiaConsola() {
        try {
            String sistemOperativo = System.getProperty("os.name");
            if (sistemOperativo.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pausa() {
        System.out.println("\n Presione Enter para continuar...");
        reader.nextLine(); 
    }

    // Operaciones de menus
    public void buscarTicket() {
        limpiaConsola();
        int numeroID;

        System.out.println("Digite el número de id del ticket: ");
        numeroID = reader.nextInt();
        reader.nextLine();

        Ticket buscado = ticketsResueltos.buscar(numeroID);

        if (buscado == null) {
            System.out.println("El ticket está pendiente");
        } else {
            System.out.println(buscado);
        }

        pausa();
    }

    public void crearTicket() {
        limpiaConsola();

        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = reader.nextLine();

        System.out.print("Ingrese la descripción del problema: ");
        String descripcion = reader.nextLine();

        System.out.print("Ingrese la prioridad del ticket (1: Alta, 2: Media, 3: Baja): ");
        int prioridad = reader.nextInt();
        reader.nextLine();

        ticketsCola.insertar(nombreCompleto, descripcion, prioridad);

        System.out.println("\nSe ha creado su ticket con exito");

        pausa();
    }

    public void verFrente() {
        limpiaConsola();

        if (ticketsCola.verFrente() == null) {
            System.out.println("La cola está vacía");
        } else {
            System.out.println("\n--- TICKET AL FRENTE DE LA COLA ---");
            System.out.println("ID: " + ticketsCola.getFrenteId());
            System.out.println("Usuario: " + ticketsCola.getFrenteNombreCompleto());
            System.out.println("Descripción: " + ticketsCola.getFrenteDescripcion());
            System.out.println("Fecha de Creación: " + ticketsCola.getFrenteFechaCreacion());
            System.out.println("-------------------------------------");
        }

        pausa();
    }

    public void resolverTicket() {
        limpiaConsola();

        Ticket ticketAResolver = ticketsCola.resolverTicket();

        if (ticketAResolver == null) {
            System.out.println("La cola está vacía");
        } else {
            LocalDateTime fechaResolucion = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            ticketAResolver.setFechaResolucion(fechaResolucion);

            ticketsResueltos.insertarInicio(ticketAResolver);

            System.out.println("===============================================");
            System.out.println(" Ticket #" + ticketAResolver.getId() + " resuelto exitosamente");
            System.out.println(" Cliente: " + ticketAResolver.getNombreCompleto());
            System.out.println(" Fecha de resolución: " + fechaResolucion);
            System.out.println("===============================================");
        }

        pausa();
    }

    // Menus
    public void menuAdmin() {
        while (true) {
            limpiaConsola();
            System.out.println("---MENU DE ADMINISTRADOR---");
            System.out.println();
            System.out.println("1. Ver frente de cola");
            System.out.println("2. Resolver Ticket");
            System.out.println("0. Regresar al menu principal");
            System.out.print("Elija una opción: ");
            opcion = reader.nextInt();
            reader.nextLine();

            switch (opcion) {
                case 0: return;
                case 1: verFrente(); break;
                case 2: resolverTicket(); break;
                default:
                    System.out.println("");
                    System.out.println(" *** Opción Inválida, debe hacer una pausa y volver al menú. ***");
                    pausa();
                    break;
            }
        }
    }

    public void menuUsuario() {
        while (true) {
            limpiaConsola();
            System.out.println("---MENU DE USUARIO---");
            System.out.println();
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket resuelto");
            System.out.println("0. Regresar al menu principal");
            System.out.print("Elija una opción: ");
            opcion = reader.nextInt();
            reader.nextLine();

            switch (opcion) {
                case 0: return;
                case 1: crearTicket(); break;
                case 2: buscarTicket(); break;
                default:
                    System.out.println("");
                    System.out.println(" *** Opción Inválida, debe hacer una pausa y volver al menú. ***");
                    pausa();
                    break;
            }
        }
    }

    public void menu() {
        while (true) {
            limpiaConsola();
            System.out.println("---MENU PRINCIPAL---");
            System.out.println();
            System.out.println("1. Menu de Usuario");
            System.out.println("2. Menu de Administrador");
            System.out.println("0. Salir del programa");
            System.out.print("Elija una opción: ");
            opcion = reader.nextInt();
            reader.nextLine();

            switch (opcion) {
                case 0: System.exit(0);
                case 1: menuUsuario(); break;
                case 2: menuAdmin(); break;
                default:
                    System.out.println("");
                    System.out.println(" *** Opción Inválida, debe hacer una pausa y volver al menú. ***");
                    pausa();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.menu();
    }
}
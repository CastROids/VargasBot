import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestivMain {
        public static void main(String[] args) throws InputMismatchException {
                Scanner scanner = new Scanner(System.in);

                Inventario inventario = new Inventario();
                ArrayList<Venta> listaVentas = new ArrayList<>();
                ArrayList<Perfil> listaPerfiles = new ArrayList<>();
                inicializarInventario(inventario);

                Administrador admin = new Administrador();
                Vendedor vendedor = new Vendedor();

                admin.setNombreUsuario("Admin");
                admin.setContrasenia("Admin123");
                admin.setIdUsuario(0);
                vendedor.setIdUsuario(01);
                vendedor.setNombreUsuario("Vendedor");
                vendedor.setContrasenia("1234");
                admin.getListaVendedores().add(vendedor);
                listaPerfiles.add(admin);
                listaPerfiles.add(vendedor);

                String salir = "a";

                do {
                        boolean login = false;
                        int indiceUsuario = -1;
                        String usuario, contrasenia;

                        do {
                                if (!salir.equals("e")) {

                                        System.out.println("Por favor, inicie sesión:");
                                        System.out.println("Usuario: ");
                                        usuario = scanner.nextLine();
                                        System.out.println("Contraseña: ");
                                        contrasenia = scanner.nextLine();
                                        for (int i = 0; i < listaPerfiles.size(); i++) {
                                                login = listaPerfiles.get(i).iniciarSesion(usuario, contrasenia);
                                                if (login) {
                                                        indiceUsuario = i;
                                                        i = listaPerfiles.size();
                                                }
                                        }
                                        if (!login) {
                                                System.out.println(
                                                                "Usuario o contraseña incorrectos, intente nuevamente\n");
                                        }
                                }
                        } while (!login);

                        if (listaPerfiles.get(indiceUsuario) instanceof Administrador && salir != "e") {
                                int opcion = -1, opcionInterior = -1;
                                Administrador objAdmin = (Administrador) listaPerfiles.get(indiceUsuario);

                                do {
                                        System.out.println("\nBienvenido Administrador. Seleccione una opción:");
                                        System.out.println("1. Generar reporte");
                                        System.out.println("2. Gestionar inventario");
                                        System.out.println("3. Consultar inventario");
                                        System.out.println("4. Cerrar sesión");

                                        do {
                                                try {
                                                        opcion = scanner.nextInt();
                                                } catch (InputMismatchException e) {
                                                        System.out.println("Debe ingresar una cantidad válida\n");
                                                        scanner.next();
                                                }
                                                if (opcion < 1 || opcion > 4) {
                                                        System.out.println("Ingrese una cantidad valida");
                                                }
                                        } while (opcion < 1 || opcion > 4);

                                        scanner.nextLine();

                                        switch (opcion) {
                                                case 1:
                                                        boolean bandera = false;
                                                        System.out.println("ID\tNOMBRE");
                                                        for (int i = 0; i < objAdmin.getListaVendedores().size(); i++) {
                                                                System.out.println(objAdmin.getListaVendedores().get(i)
                                                                                .getIdUsuario() + "\t"
                                                                                + objAdmin.getListaVendedores().get(i)
                                                                                                .getNombreUsuario());
                                                        }

                                                        System.out.println(
                                                                        "Ingrese el id del vendedor que desea en su reporte");
                                                        do {
                                                                try {
                                                                        opcionInterior = scanner.nextInt();
                                                                } catch (InputMismatchException e) {
                                                                        System.out.println(
                                                                                        "Debe ingresar una cantidad válida: un valor numérico entero \n");

                                                                        scanner.next();
                                                                }
                                                        } while (opcionInterior < 0);

                                                        for (int i = 0; i < objAdmin.getListaVendedores().size(); i++) {
                                                                if (opcionInterior == objAdmin.getListaVendedores()
                                                                                .get(i).getIdUsuario()) {
                                                                        objAdmin.generarReporteVendedor(objAdmin
                                                                                        .getListaVendedores().get(i));
                                                                        i = objAdmin.getListaVendedores().size();
                                                                        bandera = true;
                                                                }
                                                        }
                                                        if (!bandera) {
                                                                System.out.println("No existe vendedor con ese id");
                                                        }

                                                        break;
                                                case 2:
                                                        objAdmin.gestionarInventario(inventario);
                                                        break;
                                                case 3:
                                                        objAdmin.consultarInventario(inventario);
                                                        break;
                                                case 4:
                                                        objAdmin.cerrarSesion();
                                                        break;
                                                default:
                                                        System.out.println("Opción no válida.");
                                        }
                                } while (opcion != 4);
                        } else if (listaPerfiles.get(indiceUsuario) instanceof Vendedor && salir != "e") {
                                Vendedor objVendedor = (Vendedor) listaPerfiles.get(indiceUsuario);
                                int opcion = -1;
                                do {
                                        System.out.println("\nBienvenido Vendedor. Seleccione una opción:");
                                        System.out.println("1. Realizar venta");
                                        System.out.println("2. Consultar historial de ventas");
                                        System.out.println("3. Cerrar sesion");
                                        do {
                                                try {
                                                        opcion = scanner.nextInt();
                                                } catch (InputMismatchException e) {
                                                        System.out.println("Debe ingresar una cantidad válida\n");
                                                        scanner.next();
                                                }
                                        } while (opcion < 1 || opcion > 4);

                                        scanner.nextLine();

                                        switch (opcion) {
                                                case 1:
                                                        objVendedor.realizarVenta(inventario);
                                                        break;
                                                case 2:
                                                        objVendedor.consultarHistorialVentas();
                                                        break;
                                                case 3:
                                                        objVendedor.cerrarSesion();
                                                        break;

                                                default:
                                                        System.out.println("Opción no válida.");
                                        }
                                } while (opcion != 4);
                        }
                } while (salir != "e");

        }

        public static void inicializarInventario(Inventario inventario) {
                Producto arduinoUno = new Producto(1, "Arduino Uno", "Placa base de microcontrolador", 25.0f,
                                "Placas de desarrollo", 20);
                Producto arduinoMega = new Producto(2, "Arduino Mega",
                                "Placa base de microcontrolador con más pines y memoria", 45.0f, "Placas de desarrollo",
                                15);
                Producto sensorTemperatura = new Producto(3, "Sensor de Temperatura", "Sensor digital de temperatura",
                                5.5f, "Sensores", 30);
                Producto motorPasoAPaso = new Producto(4, "Motor Paso a Paso",
                                "Motor eléctrico que divide una vuelta completa en un número de pasos", 12.75f,
                                "Motores", 10);
                Producto servoMotor = new Producto(5, "Servo Motor",
                                "Motor que puede orientarse a una posición específica", 14.0f, "Motores", 25);
                Producto resistencia1k = new Producto(6, "Resistencia 1kΩ", "Resistencia de 1k ohmios", 0.25f,
                                "Componentes pasivos", 100);
                Producto capacitor100uF = new Producto(7, "Capacitor 100µF",
                                "Capacitor electrolítico de 100 microfaradios", 0.35f, "Componentes pasivos", 50);
                Producto ledRojo = new Producto(8, "LED Rojo", "Diodo emisor de luz roja", 0.1f, "Componentes activos",
                                200);
                Producto displayLCD = new Producto(9, "Display LCD 16x2", "Pantalla LCD de 16 columnas y 2 filas", 5.0f,
                                "Displays", 25);
                Producto breadboard = new Producto(10, "Breadboard", "Tablero de pruebas sin soldadura", 3.5f,
                                "Herramientas", 30);
                Producto jumperCables = new Producto(11, "Cables Jumper", "Cables para prototipado rápido", 4.5f,
                                "Herramientas", 40);
                Producto potenciometro = new Producto(12, "Potenciómetro", "Resistencia variable ajustable manualmente",
                                1.25f, "Componentes pasivos", 35);
                Producto sensorProximidad = new Producto(13, "Sensor de Proximidad",
                                "Sensor para detectar la presencia de objetos cercanos sin contacto físico", 7.0f,
                                "Sensores", 20);
                Producto raspberryPi = new Producto(14, "Raspberry Pi 4", "Ordenador de placa reducida", 55.0f,
                                "Placas de desarrollo", 15);
                Producto kitRobotica = new Producto(15, "Kit de Robótica",
                                "Conjunto de componentes para ensamblar un robot", 80.0f, "Kits", 5);

                inventario.agregarNuevoProducto(arduinoUno);
                inventario.agregarNuevoProducto(arduinoMega);
                inventario.agregarNuevoProducto(sensorTemperatura);
                inventario.agregarNuevoProducto(motorPasoAPaso);
                inventario.agregarNuevoProducto(servoMotor);
                inventario.agregarNuevoProducto(resistencia1k);
                inventario.agregarNuevoProducto(capacitor100uF);
                inventario.agregarNuevoProducto(ledRojo);
                inventario.agregarNuevoProducto(displayLCD);
                inventario.agregarNuevoProducto(breadboard);
                inventario.agregarNuevoProducto(jumperCables);
                inventario.agregarNuevoProducto(potenciometro);
                inventario.agregarNuevoProducto(sensorProximidad);
                inventario.agregarNuevoProducto(raspberryPi);
                inventario.agregarNuevoProducto(kitRobotica);
        }
}

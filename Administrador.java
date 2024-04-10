import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Administrador extends Perfil {
    ArrayList<Vendedor> listaVendedores = new ArrayList<>();

    public void generarReporteVendedor(Vendedor vendedor) throws InputMismatchException {
        System.out.println("------------------ REPORTE DE VENDEDOR --------------------");
        System.out.println("Id: " + vendedor.getIdUsuario() + "\tNombre: " + vendedor.getNombreUsuario() + "\n");
        for (int i = 0; i < vendedor.getListaVentas().size(); i++) {
            System.out.println(vendedor.getListaVentas().get(i).getIdVenta() + "\t|"
                    + vendedor.getListaVentas().get(i).getFecha());
            for (int j = 0; j < vendedor.getListaVentas().get(i).getListaProductos().size(); j++) {
                System.out
                        .println("Producto: " + vendedor.getListaVentas().get(i).getListaProductos().get(j).getNombre()
                                + "\tx" + vendedor.getListaVentas().get(i).getListaCantidades().get(j) + "\n");
            }
            System.out.println("Total: " + vendedor.getListaVentas().get(i).getPrecioTotal() + "\n");
        }
        System.out.println("------------------ -------------------- --------------------");
    }

    public void gestionarInventario(Inventario inventario) throws InputMismatchException {
        int opcion;
        System.out.println(" Id\t| Nombre\t| Categoría\t| Stock\t|Precio");
        for (int i = 0; i < inventario.getListaProductos().size(); i++) {
            System.out.println(inventario.getListaProductos().get(i).getIdProducto() + "\t|"
                    + inventario.getListaProductos().get(i).getNombre() + "\t|"
                    + inventario.getListaProductos().get(i).getCategoria() + "\t|"
                    + inventario.getListaProductos().get(i).getStock() + "\t| $"
                    + inventario.getListaProductos().get(i).getPrecio());
        }
        System.out.println(
                "Ingrese el numero correspondiente a la opcion que desea realizar\n1. Editar Stock\t 2. Eliminar producto\t| 3. Salir\n");
        opcion = cin.nextInt();
        do {
            try {
                opcion = cin.nextInt();
                if (opcion < 1 && opcion > 4) {
                    System.out.println("Debe ingresar una opcion valida , 1, 2, o 3, intente nuevamente\n");
                }
            } catch (InputMismatchException e) {
                opcion = -1;
                System.out.println("Debe ingresar una opcion válida\n");
                cin.next();
            }
        } while (opcion < 1);
        String respuesta;
        switch (opcion) {
            case 1:
                do {
                    int idProducto = -1, indice = -1, nuevaCantidad = -1;
                    for (int i = 0; i < inventario.getListaProductos().size(); i++) {
                        System.out.println(inventario.getListaProductos().get(i).getIdProducto() + "\t"
                                + inventario.getListaProductos().get(i).getNombre() + "\t"
                                + inventario.getListaProductos().get(i).getPrecio() + "\tx"
                                + inventario.getListaProductos().get(i).getStock());
                    }
                    System.out.println("Ingrese el id del producto que desea editar");
                    try {
                        idProducto = cin.nextInt();
                    } catch (Exception e) {
                        System.out
                                .println("Debe ingresar una cantidad válida: un valor numérico entero de 4 digitos\n");
                        cin.next();
                    }
                    indice = inventario.buscarProducto(idProducto);

                    System.out.println("Ingrese la cantidad de producto que hay en stock");
                    do {
                        try {
                            nuevaCantidad = cin.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar una cantidad válida: un valor numérico entero\n");
                            cin.next();
                        }
                        if (nuevaCantidad < 0) {
                            System.out.println("Ingrese una cantidad valida, mayor a 0");
                        }
                    } while (nuevaCantidad < 0);

                    inventario.getListaProductos().get(indice).setStock(nuevaCantidad);
                    System.out.println("¿Desea editar otro producto?\n1) Sí\tOtro) No");
                    respuesta = cin.nextLine();
                } while (respuesta == "1");
                break;

            case 2:
                do {

                    int idProducto = -1, indice = -1, nuevaCantidad = -1;
                    for (int i = 0; i < inventario.getListaProductos().size(); i++) {
                        System.out.println(inventario.getListaProductos().get(i).getIdProducto() + "\t"
                                + inventario.getListaProductos().get(i).getNombre() + "\t"
                                + inventario.getListaProductos().get(i).getPrecio() + "\tx"
                                + inventario.getListaProductos().get(i).getStock());
                    }
                    System.out.println("Ingrese el id del producto que desea eliminar");
                    try {
                        idProducto = cin.nextInt();
                    } catch (InputMismatchException e) {
                        System.out
                                .println("Debe ingresar una cantidad válida: un valor numérico entero de 4 digitos\n");
                        cin.next();
                    }

                    inventario.eliminarProducto(idProducto);

                    System.out.println("¿Desea editar otro producto?\n1) Sí\tOtro) No");
                    respuesta = cin.nextLine();
                } while (respuesta == "1");

                break;
            default:
                cerrarSesion();
                break;
        }
    }

    public void consultarInventario(Inventario inventario) {
        System.out.println(" Id\t| Nombre\t| Categoría\t| Stock\t|Precio");
        for (int i = 0; i < inventario.getListaProductos().size(); i++) {
            System.out.println(inventario.getListaProductos().get(i).getIdProducto() + "\t|"
                    + inventario.getListaProductos().get(i).getNombre() + "\t|"
                    + inventario.getListaProductos().get(i).getCategoria() + "\t|"
                    + inventario.getListaProductos().get(i).getStock() + "\t| $"
                    + inventario.getListaProductos().get(i).getPrecio());
        }
    }

    @Override
    public void cerrarSesion() {
        String contrasenaIngresada;
        int h = 3;
        System.out.println("Ingrese la contraseña para confirmar que quiere cerrar sesion");
        contrasenaIngresada = cin.nextLine();
        while (!contrasenaIngresada.equals(this.contrasenia) && h < 0) {
            h--;
            System.out.println("Contraseña incorrecta, intente nuevamente, le quedan " + h + " intentos");
            contrasenaIngresada = cin.nextLine();
        }
    }

    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }
}

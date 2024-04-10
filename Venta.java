import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;

public class Venta {

    Scanner cin = new Scanner(System.in);
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ArrayList<Integer> listaCantidades = new ArrayList<>();
    private int idVenta;
    private LocalDate fecha;
    private float precioTotal = 0;

    public Venta(int idVenta) {
        this.idVenta = idVenta;
        fecha = LocalDate.now();
    }

    public Scanner getCin() {
        return cin;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public ArrayList<Integer> getListaCantidades() {
        return listaCantidades;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public float calcularPrecioTotal() {
        for (int i = 0; i < listaProductos.size(); i++) {
            precioTotal += listaProductos.get(i).getPrecio() * listaCantidades.get(i);
        }
        return precioTotal;
    }

    public void agregarProducto(Producto productoNuevo) throws InputMismatchException {
        int cantidad = -1;

        listaProductos.add(productoNuevo);
        System.out.println("Ingrese la cantidad de unidadez que desea comprar de dicho producto");
        do {
            try {
                cantidad = cin.nextInt();
                if (cantidad < 1) {
                    System.out.println("Debe ingresar una cantidad valida mayor a 0, intente nuevamente\n");
                }
            } catch (Exception e) {
                cantidad = -1;
                System.out.println("Debe ingresar una cantidad válida: un valor numérico entero\n");
                cin.next();
            }
        } while (cantidad < 1);

        listaCantidades.add(cantidad);
    }

}

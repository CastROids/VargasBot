import java.util.ArrayList;

/**
 * Vendedor
 */
public class Vendedor extends Perfil {
    ArrayList<Venta> listaVentas = new ArrayList<>();

    public void realizarVenta(Inventario inventario){
        int idVenta = listaVentas.size();
        int idProducto, indice;
        String respuesta;
        Venta nuevaVenta = new Venta(idVenta);

        do{
            for(int i=0; i<inventario.getListaProductos().size();i++){
                System.out.println(inventario.getListaProductos().get(i).getIdProducto()+"\t"+inventario.getListaProductos().get(i).getNombre()+"\t"+inventario.getListaProductos().get(i).getPrecio()+"\tx"+inventario.getListaProductos().get(i).getStock());
            }
            System.out.println("Ingrese el id del producto que desea añadir");
            idProducto = cin.nextInt();
            indice= inventario.buscarProducto(idProducto);

            Producto producto = new Producto(inventario.getListaProductos().get(indice).getIdProducto(), inventario.getListaProductos().get(indice).getNombre(),inventario.getListaProductos().get(indice).getDescripcion(), inventario.getListaProductos().get(indice).getPrecio(),inventario.getListaProductos().get(indice).getCategoria(), inventario.getListaProductos().get(indice).getStock());
            
            nuevaVenta.agregarProducto(producto);
            System.out.println("¿Desea agregar otro producto a la compra?\n1) Sí\tOtro) No");
            respuesta=cin.nextLine();
        }while(respuesta=="1");

        for(int i=0; i<nuevaVenta.getListaProductos().size(); i++){
            for(int j=0; j<inventario.getListaProductos().size(); j++){
                if(nuevaVenta.getListaProductos().get(i).getIdProducto()==inventario.getListaProductos().get(j).getIdProducto()){
                    int nuevoStock = inventario.getListaProductos().get(j).getStock()-nuevaVenta.getListaCantidades().get(i);
                    inventario.getListaProductos().get(j).setStock(nuevoStock);
                }
            }
        }

        listaVentas.add(nuevaVenta);
    }

    public void consultarHistorialVentas(){

        for(int i=0; i<listaVentas.size(); i++){
            System.out.println(listaVentas.get(i).getIdVenta()+"\t|"+listaVentas.get(i).getFecha());
            for(int j=0; j<listaVentas.get(i).getListaProductos().size(); j++){
                System.out.println("Producto: "+listaVentas.get(i).getListaProductos().get(j).getNombre()+"\tx"+listaVentas.get(i).getListaCantidades().get(j)+"\n");
            }
            System.out.println("Total: "+listaVentas.get(i).getPrecioTotal()+"\n");
        }

    }

    @Override
    public void cerrarSesion() {
        System.out.println("Cerrando sesion...");
    }

    public ArrayList<Venta> getListaVentas() {
        return listaVentas;
    }
    
}
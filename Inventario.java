import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Inventario {
    Scanner cin = new Scanner(System.in);
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public void agregarNuevoProducto(Producto producto){
        listaProductos.add(producto);
    }

    public void eliminarProducto(int idProducto){
        for(int i= 0; i<listaProductos.size(); i++){
            if(idProducto == listaProductos.get(i).getIdProducto()){
                listaProductos.remove(i);
                i=listaProductos.size();
            }
        }
    }

    public int buscarProducto(int idProducto){
        int indiceProducto=-1;
        for(int i= 0; i<listaProductos.size(); i++){
            if(idProducto == listaProductos.get(i).getIdProducto()){
                System.out.println(listaProductos.get(i).getIdProducto()+"\t|"+listaProductos.get(i).getNombre()+"\t|"+listaProductos.get(i).getCategoria()+"\t|"+listaProductos.get(i).getStock()+"\t| $"+listaProductos.get(i).getPrecio());
       
                indiceProducto = i;
                i=listaProductos.size();
            }
        }
        if(indiceProducto<0){
            System.out.println("Ese producto no existe");
            return (Integer) null;
        }else{
            return indiceProducto;
        }
    }
    public void actualizarStock(int idProducto) throws InputMismatchException{
        int nuevaCantidad;
        for(int i= 0; i<listaProductos.size(); i++){
            if(idProducto == listaProductos.get(i).getIdProducto()){
               System.out.println("Actualmente, hay "+listaProductos.get(i).getStock()+" unidades, ingrese la nueva cantidad\n");
               do {
                    try {
                        nuevaCantidad = cin.nextInt();
                        if(nuevaCantidad<1){
                            System.out.println("Debe ingresar una cantidad valida, mayor que 0, intente nuevamente\n");
                        }
                    } catch (InputMismatchException e) {
                        nuevaCantidad=-1;
                        System.out.println("Debe ingresar una nuevaCantidad válida\n");
                    }
                } while (nuevaCantidad<1); 
                i=listaProductos.size();
            }
        }
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    
}

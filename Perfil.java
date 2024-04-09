import java.util.Scanner;

public abstract class Perfil {
    Scanner cin = new Scanner(System.in);
    protected int idUsuario;
    protected String nombreUsuario;
    protected String contrasenia;

    public abstract void cerrarSesion();

    public boolean iniciarSesion(String usuario, String contrase){ 
        boolean estado= false;

        if(nombreUsuario.equals(usuario) && contrasenia.equals(contrase)){
            estado=true;
        }

        return estado;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }
}

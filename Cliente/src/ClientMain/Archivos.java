package ClientMain;

public class Archivos {
    public Archivos(String nombre, String fechaCreacion, int cantPal, String direccion) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.cantPal = cantPal;
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public int getCantPal() {
        return cantPal;
    }
    public String getDireccion() {
        return direccion;
    }
    private String nombre;
    private String fechaCreacion;
    private int cantPal;
    private String direccion;

}

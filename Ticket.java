import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Ticket {

    private static int cantidad = 0; // Entero consecutivo estático según la consigna

    private int id;
    private String nombreCompleto;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
    private int prioridad;

    public Ticket(String nombreCompleto, String descripcion, int prioridad) {
        cantidad++;
        this.id = cantidad;
        this.nombreCompleto = nombreCompleto;
        this.descripcion = descripcion;
        this.fechaCreacion = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.fechaResolucion = null;
        this.prioridad = prioridad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\nNombre Completo: " + nombreCompleto +
                "\nDescripción: " + descripcion + "\nFecha de Creación: " + fechaCreacion + 
                "\nFecha de Resolución: " + fechaResolucion + "\n";
    }
}

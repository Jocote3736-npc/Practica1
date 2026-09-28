import java.time.LocalDateTime;

public class ColaDinamica {

    private class Nodo {
        Ticket ticket;
        Nodo siguiente;

        Nodo(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }
    }

    private Nodo frente;

    public ColaDinamica() {
        frente = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    // Inserción con prioridad 
    public void insertar(String nombreCompleto, String descripcion, int prioridad) {
        Ticket ticket = new Ticket(nombreCompleto, descripcion, prioridad);
        Nodo nuevo = new Nodo(ticket);

        if (estaVacia() || ticket.getPrioridad() < frente.ticket.getPrioridad()) {
            nuevo.siguiente = frente;
            frente = nuevo;
        } else {
            Nodo actual = frente;
            while (actual.siguiente != null && actual.siguiente.ticket.getPrioridad() <= ticket.getPrioridad()) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    public Ticket verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.ticket;
    }

    public Ticket resolverTicket() {
        if (estaVacia()) {
            return null;
        }
        Ticket ticketExtraido = frente.ticket;
        frente = frente.siguiente;
        return ticketExtraido;
    }

    // Getters
    public int getFrenteId() {
        Ticket frenteTicket = verFrente();
        return (frenteTicket != null) ? frenteTicket.getId() : -1;
    }

    public String getFrenteNombreCompleto() {
        Ticket frenteTicket = verFrente();
        return (frenteTicket != null) ? frenteTicket.getNombreCompleto() : "";
    }

    public String getFrenteDescripcion() {
        Ticket frenteTicket = verFrente();
        return (frenteTicket != null) ? frenteTicket.getDescripcion() : "";
    }

    public LocalDateTime getFrenteFechaCreacion() {
        Ticket frenteTicket = verFrente();
        return (frenteTicket != null) ? frenteTicket.getFechaCreacion() : null;
    }
}

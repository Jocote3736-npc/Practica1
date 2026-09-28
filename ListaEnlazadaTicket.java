public class ListaEnlazadaTicket {

    private class Nodo {
        Ticket ticket;
        Nodo siguiente;

        Nodo(Ticket ticket) {
            this.ticket = ticket;
            this.siguiente = null;
        }
    }

    private Nodo primero;

    public ListaEnlazadaTicket() {
        primero = null;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    public void insertarInicio(Ticket ticket) {
        Nodo nuevo = new Nodo(ticket);
        nuevo.siguiente = primero;
        primero = nuevo;
    }

    public Ticket buscar(int id) {
        if (estaVacia()) {
            return null;
        }
        Nodo temp = primero;
        while (temp != null) {
            if (temp.ticket.getId() == id) {
                return temp.ticket;
            }
            temp = temp.siguiente;
        }
        return null;
    }
}
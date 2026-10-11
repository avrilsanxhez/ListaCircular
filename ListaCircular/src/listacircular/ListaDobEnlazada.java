package listacircular;

public class ListaDobEnlazada<T> implements IList<T>, Iterable<T> {

    protected NodoSimple<T> inicio;
    protected int nElementos;

    private NodoSimple<T> actual;

    public ListaDobEnlazada() {
        inicio = null;
        nElementos = 0;
        actual = null;
    }

    private class NodoSimple<T> {

        private T dato;
        private NodoSimple<T> sig;
        private NodoSimple<T> ant;   //apuntador al nodo anterior owo

        public NodoSimple(T dato) {
            this.dato = dato;
            this.sig = null;
            this.ant = null;
        }
    }

    @Override
    public void append(T elemento) throws ListException {
        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);

        if (inicio == null) { //si la lista está vacía el nodo se apunta a si mismo
            inicio = nodoNuevo;
            inicio.sig = inicio;
            inicio.ant = inicio;
        } else {
            NodoSimple<T> cola = inicio.ant; //último nodo
            nodoNuevo.sig = inicio;
            nodoNuevo.ant = cola;
            cola.sig = nodoNuevo;
            inicio.ant = nodoNuevo;
        }

        nElementos++;
    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        if (index < 0 || index > nElementos) {
            throw new ListException("Índice fuera de rango");
        }

        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);

        if (nElementos == 0) {
            inicio = nodoNuevo;
            inicio.sig = inicio;
            inicio.ant = inicio;
        } else if (index == 0) { //insertar al inicio de la lista
            NodoSimple<T> cola = inicio.ant;
            nodoNuevo.sig = inicio;
            nodoNuevo.ant = cola;
            inicio.ant = nodoNuevo;
            cola.sig = nodoNuevo;
            inicio = nodoNuevo;
        } else { //insertar en medio o al final de la listap
            NodoSimple<T> nodo = inicio;
            for (int i = 0; i < index - 1; i++) {
                nodo = nodo.sig;
            }
            nodoNuevo.sig = nodo.sig;
            nodoNuevo.ant = nodo;
            nodo.sig.ant = nodoNuevo;
            nodo.sig = nodoNuevo;
        }

        nElementos++;
    }

    @Override
    public T get(int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de rango");
        }

        NodoSimple<T> nodo = inicio;
        for (int i = 0; i < index; i++) {
            nodo = nodo.sig;
        }
        return nodo.dato;
    }

    @Override
    public T remove(int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de rango");
        }

        T datoEliminado;

        if (nElementos == 1) {
            datoEliminado = inicio.dato;
            inicio = null;
        } else if (index == 0) {
            datoEliminado = inicio.dato;
            NodoSimple<T> cola = inicio.ant;
            inicio = inicio.sig;
            inicio.ant = cola;
            cola.sig = inicio;
        } else {
            NodoSimple<T> nodo = inicio;
            for (int i = 0; i < index - 1; i++) {
                nodo = nodo.sig;
            }
            NodoSimple<T> aEliminar = nodo.sig;
            datoEliminado = aEliminar.dato;
            nodo.sig = aEliminar.sig;
            aEliminar.sig.ant = nodo;
        }

        nElementos--;
        return datoEliminado;
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {
        int idx = indexOf(elemento);
        if (idx == -1) {
            return false;
        }
        remove(idx);
        return true;
    }

    @Override
    public int indexOf(T elemento) {
        if (inicio == null) {
            return -1;
        }
        NodoSimple<T> nodo = inicio;
        for (int i = 0; i < nElementos; i++) {
            if (nodo.dato.equals(elemento)) {
                return i;
            }
            nodo = nodo.sig;
        }
        return -1;
    }

    @Override
    public void set(T elemento, int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de rango");
        }
        NodoSimple<T> nodo = inicio;
        for (int i = 0; i < index; i++) {
            nodo = nodo.sig;
        }
        nodo.dato = elemento;
    }

    @Override
    public void clear() {
        inicio = null;
        nElementos = 0;
        actual = null;
    }

    @Override
    public boolean empty() {
        return nElementos == 0;
    }

    @Override
    public int size() {
        return nElementos;
    }

    public void irInicio() {
        actual = inicio;
    }

    public void irFinal() {
        if (inicio != null) {
            actual = inicio.ant;   //la cola es el anterior de la cabeza
        }
    }

    //devuelve el dato del elemento actual y mueve el iterador hacia el elemento anterior
    public T previous() throws ListException {
        if (inicio == null) {
            throw new ListException("Lista vacía");
        }
        if (actual == null) {
            actual = inicio;   //si no se ha posicionado empieza en la cabeza
        }
        T datoActual = actual.dato;
        actual = actual.ant;   //salto circular automático owo
        return datoActual;
    }

    public void invertir() {
        if (inicio == null || nElementos == 1) {
            return;
        }
        NodoSimple<T> nodo = inicio;
        NodoSimple<T> temp;
        for (int i = 0; i < nElementos; i++) {
            temp = nodo.sig;
            nodo.sig = nodo.ant;
            nodo.ant = temp;
            nodo = temp;
        }
        inicio = inicio.ant;
    }

    public void concatenar(ListaDobEnlazada<T> otraLista) {
        NodoSimple<T> nodo = otraLista.inicio;
        for (int i = 0; i < otraLista.nElementos; i++) {
            append(nodo.dato);
            nodo = nodo.sig;
        }
    }

    @Override
    public java.util.Iterator<T> Iterator() {
        return iterator();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private NodoSimple<T> nodo = inicio;
            private int visitados = 0;

            @Override
            public boolean hasNext() {
                return visitados < nElementos;
            }

            @Override
            public T next() {
                T dato = nodo.dato;
                nodo = nodo.sig;
                visitados++;
                return dato;
            }
        };
    }

    @Override
    public String toString() {
        if (inicio == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        NodoSimple<T> nodo = inicio;
        for (int i = 0; i < nElementos; i++) {
            sb.append(nodo.dato);
            if (i < nElementos - 1) {
                sb.append(", ");
            }
            nodo = nodo.sig;
        }
        return sb.append("]").toString();
    }

}

package listacircular;

public class ListaCircular {

    public static void main(String[] args) {
        ListaDobEnlazada<String> lista = new ListaDobEnlazada<>();
        
        lista.append("Jose");
        lista.append("Arcadio");
        lista.append("Aureliano");
        lista.append("Ursula");
        lista.append("Gerinaldo");
        System.out.println("Lista: " + lista);
        
        lista.irInicio();
        System.out.println("\nRecorrido hacia adelante:");
        for (int i = 0; i < 10; i++) {
            System.out.print(lista.previous() + " ");            
        }
        
        lista.irFinal();
        System.out.println("\nRecorrido hacia atrás:");
        for (int i = 0; i < 8; i++) {
            System.out.print(lista.previous() + " ");            
        }
        
        lista.insert("Amaranta", 0);        
        lista.insert("Rebeca", 3);        
        System.out.println("\nLista: " + lista);
        
        lista.remove(0);        
        System.out.println("\nEliminar índice 0: " + lista);
        
        System.out.println("get(2): " + lista.get(2));        
        lista.set("Remedios", 2);        
        System.out.println("\nLista: " + lista);
        System.out.println("indexOf Arcadio: " + lista.indexOf("Arcadio"));
        
        lista.removeObj("Jose");
        System.out.println("\nLista: " + lista);        
        
        lista.invertir();
        System.out.println("Invertida: " + lista);        
        
        System.out.print("\nfor each: ");
        for (String s : lista) {
            System.out.print(s + " ");
        }
    }
    
}

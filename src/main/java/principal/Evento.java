package principal;
public class Evento {
    public char hora;
    public int numJugadores;
    public boolean letal;
    public String evento;
    
    Evento(char horario, int numeroPersonajes, boolean letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        letal = letalidad;
        this.evento = evento;
    }
    
    public String realizarEvento(Object args){
        return String.format(evento, args);
    }
    
    public String realizarEvento(Object args, Object args2){
        return String.format(evento, args, args2);
    }
    
    public String realizarEvento(Object args, Object args2, Object args3){
        return String.format(evento, args, args2, args3);
    }
    
    public int getNumeroPersonajes(){
        return numJugadores;
    }
}
package principal;
public class Evento {
    public char hora;
    public int numJugadores;
    public char tipoLetal;
    //Tipos letalidad: N -> Muere un jugador. T -> Mueren todos. S -> Sobrevive un jugador. O -> No muere nadie.
    public String evento;
    
    Evento(char horario, int numeroPersonajes, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        tipoLetal = letalidad;
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
    
    public String realizarEvento(Object args, Object args2, Object args3, Object args4){
        return String.format(evento, args, args2, args3, args4);
    }
    
    public int getNumeroPersonajes(){
        return numJugadores;
    }
}
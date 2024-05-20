package principal;
public class Evento {
    public char hora;
    public int numJugadores, numAreas, numObjetos;
    public char tipoLetal;
    //Tipos letalidad: N -> Muere un jugador. T -> Mueren todos. S -> Sobrevive un jugador. O -> No muere nadie.
    public String evento;
    /*
    Guia de signos:
        - &pXG -> Jugador
        - &oXG -> Objeto
        - &aXG -> Area
        x -> numero
        g -> genero (S preguntar / N ignorar)
            Los simbolos '+, -, =' y '@, #, $' ayudan al codigo a saber que palabra corresponde a cada genero.
        (/) significa una palabra a escoger. Usará -1 antes de los parentesis para indicar donde debe de buscar.
    */
    public String muerte;
    //Se refiere a si el evento hace referencia al area en la que esta o a otra
    public boolean esta, encuentra;
    
    Evento(char horario, int numeroPersonajes, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        tipoLetal = letalidad;
        this.evento = evento;
        numAreas = 0;
        numObjetos = 0;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroAreas, int numeroObjetos, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        numAreas = numeroAreas;
        numObjetos = numeroObjetos;
        tipoLetal = letalidad;
        this.evento = evento;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroAreas, boolean esta, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        numAreas = numeroAreas;
        this.esta = esta;
        tipoLetal = letalidad;
        this.evento = evento;
    }
    
    Evento(char horario, int numeroPersonajes, char letalidad, String evento, String causaMuerte){
        hora = horario;
        numJugadores = numeroPersonajes;
        tipoLetal = letalidad;
        this.evento = evento;
        numAreas = 0;
        numObjetos = 0;
        muerte = causaMuerte;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroAreas, int numeroObjetos, char letalidad, String evento, String causaMuerte){
        hora = horario;
        numJugadores = numeroPersonajes;
        numAreas = numeroAreas;
        numObjetos = numeroObjetos;
        tipoLetal = letalidad;
        this.evento = evento;
        muerte = causaMuerte;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroObjetos, char letalidad, String evento, String causaMuerte){
        hora = horario;
        numJugadores = numeroPersonajes;
        numObjetos = numeroObjetos;
        tipoLetal = letalidad;
        this.evento = evento;
        muerte = causaMuerte;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroObjetos, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        numObjetos = numeroObjetos;
        tipoLetal = letalidad;
        this.evento = evento;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroObjetos, char letalidad, String evento, boolean encuentra){
        hora = horario;
        numJugadores = numeroPersonajes;
        numObjetos = numeroObjetos;
        tipoLetal = letalidad;
        this.evento = evento;
        this.encuentra = encuentra;
    }
    
    public String realizarEvento(Personaje p1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Area a1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Personaje p3, Area a1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        
        if(devolver.contains("&p3n")){
            devolver = replaceJugadorTresNP(p3.nombre, devolver);
        }
        if(devolver.contains("&p3s")){
            devolver = replaceJugadorTresP(p3, devolver);
        }
        
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Personaje p3, Personaje p4, Area a1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        
        if(devolver.contains("&p3n")){
            devolver = replaceJugadorTresNP(p3.nombre, devolver);
        }
        if(devolver.contains("&p3s")){
            devolver = replaceJugadorTresP(p3, devolver);
        }
        
        if(devolver.contains("&p4n")){
            devolver = replaceJugadorCuatroNP(p4.nombre, devolver);
        }
        if(devolver.contains("&p4s")){
            devolver = replaceJugadorCuatroP(p4, devolver);
        }
        
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Personaje p3){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        
        if(devolver.contains("&p3n")){
            devolver = replaceJugadorTresNP(p3.nombre, devolver);
        }
        if(devolver.contains("&p3s")){
            devolver = replaceJugadorTresP(p3, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Area a1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Objeto o1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&o1n")){
            devolver = replaceObjetoUnoNP(o1.nombre, devolver);
        }
        if(devolver.contains("&o1s")){
            devolver = replaceObjetoUnoP(o1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Area a1, Objeto o1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        
        if(devolver.contains("&o1n")){
            devolver = replaceObjetoUnoNP(o1.nombre, devolver);
        }
        if(devolver.contains("&o1s")){
            devolver = replaceObjetoUnoP(o1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Area a1, Objeto o1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        
        if(devolver.contains("&o1n")){
            devolver = replaceObjetoUnoNP(o1.nombre, devolver);
        }
        if(devolver.contains("&o1s")){
            devolver = replaceObjetoUnoP(o1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Personaje p3, Area a1, Objeto o1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        if(devolver.contains("&p3n")){
            devolver = replaceJugadorTresNP(p3.nombre, devolver);
        }
        if(devolver.contains("&p3s")){
            devolver = replaceJugadorTresP(p3, devolver);
        }
        if(devolver.contains("&a1n")){
            devolver = replaceAreaUnoNP(a1.nombre, devolver);
        }
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(a1, devolver);
        }
        if(devolver.contains("&o1n")){
            devolver = replaceObjetoUnoNP(o1.nombre, devolver);
        }
        if(devolver.contains("&o1s")){
            devolver = replaceObjetoUnoP(o1, devolver);
        }
        
        return devolver;
    }
    
    public String realizarEvento(Personaje p1, Personaje p2, Personaje p3, Personaje p4){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            devolver = replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            devolver = replaceJugadorUnoP(p1, devolver);
        }
        if(devolver.contains("&p2n")){
            devolver = replaceJugadorDosNP(p2.nombre, devolver);
        }
        if(devolver.contains("&p2s")){
            devolver = replaceJugadorDosP(p2, devolver);
        }
        if(devolver.contains("&p3n")){
            devolver = replaceJugadorTresNP(p3.nombre, devolver);
        }
        if(devolver.contains("&p3s")){
            devolver = replaceJugadorTresP(p3, devolver);
        }
        if(devolver.contains("&p4n")){
            devolver = replaceJugadorCuatroNP(p3.nombre, devolver);
        }
        if(devolver.contains("&p4s")){
            devolver = replaceJugadorCuatroP(p3, devolver);
        }
        
        return devolver;
    }
    
    public int getNumeroPersonajes(){
        return numJugadores;
    }
    
    private String replaceJugadorUnoNP(String nombre, String toReplace){
        String devolver = toReplace;
        
        while(devolver.contains("&p1n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p1n"), devolver.indexOf("&p1n") + 4), nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorDosNP(String nombre, String toReplace){
        String devolver = toReplace;
        
        while(devolver.contains("&p2n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p2n"), devolver.indexOf("&p2n") + 4), nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorUnoP(Personaje p1, String toReplace){
        String genero = p1.pronombres;
        String devolver = toReplace;
        
        while(devolver.contains("p1(")){//
            if(genero.equals("M")){
                String parentesis = devolver.substring(devolver.indexOf("p1("), devolver.indexOf("p1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('+') + 1, parentesis.indexOf("@"));
                devolver = devolver.replace(parentesis, palabra);
            }else if(genero.equals("F")){
                String parentesis = devolver.substring(devolver.indexOf("p1("), devolver.indexOf("p1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('-') + 1, parentesis.indexOf("#"));
                devolver = devolver.replace(parentesis, palabra);
            }else{
                String parentesis = devolver.substring(devolver.indexOf("p1("), devolver.indexOf("p1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('=') + 1, parentesis.indexOf("$"));
                devolver = devolver.replace(parentesis, palabra);
            }
        }
        
        while(devolver.contains("&p1s")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p1s"), devolver.indexOf("&p1s") + 4), p1.nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorDosP(Personaje p1, String toReplace){
        String genero = p1.pronombres;
        String devolver = toReplace;
        
        while(devolver.contains("p2(")){
            if(genero.equals("M")){
                String parentesis = devolver.substring(devolver.indexOf("p2("), devolver.indexOf("p2)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('+') + 1, parentesis.indexOf("@"));
                devolver = devolver.replace(parentesis, palabra);
            }else if(genero.equals("F")){
                String parentesis = devolver.substring(devolver.indexOf("p2("), devolver.indexOf("p2)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('-') + 1, parentesis.indexOf("#"));
                devolver = devolver.replace(parentesis, palabra);
            }else{
                String parentesis = devolver.substring(devolver.indexOf("p2("), devolver.indexOf("p2)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('=') + 1, parentesis.indexOf("$"));
                devolver = devolver.replace(parentesis, palabra);
            }
        }
        
        while(devolver.contains("&p2s")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p2s"), devolver.indexOf("&p2s") + 4), p1.nombre);
        }
        
        return devolver;
    }
    
    private String replaceAreaUnoNP(String nombre, String toReplace){
        String devolver = toReplace;
        
        while(devolver.contains("&a1n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&a1n"), devolver.indexOf("&a1n") + 4), nombre);
        }
        
        return devolver;
    }
    
    private String replaceAreaUnoP(Area area, String toReplace){
        String genero = area.genero;
        String devolver = toReplace;
        
        while(devolver.contains("a1(")){
            if(genero.equals("M")){
                String parentesis = devolver.substring(devolver.indexOf("a1("), devolver.indexOf("a1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('+') + 1, parentesis.indexOf("@"));
                devolver = devolver.replace(parentesis, palabra);
            }else{
                String parentesis = devolver.substring(devolver.indexOf("a1("), devolver.indexOf("a1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('-') + 1, parentesis.indexOf("#"));
                devolver = devolver.replace(parentesis, palabra);
            }
        }
        
        while(devolver.contains("&a1s")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&a1s"), devolver.indexOf("&a1s") + 4), area.nombre);
        }
        
        return devolver;
    }
    
    private String replaceObjetoUnoNP(String nombre, String toReplace){
        String devolver = toReplace;
        
        while(devolver.contains("&o1n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&o1n"), devolver.indexOf("&o1n") + 4), nombre);
        }
        
        return devolver;
    }
    
    private String replaceObjetoUnoP(Objeto o1, String toReplace){
        String genero = o1.genero;
        String devolver = toReplace;
        
        while(devolver.contains("o1(")){
            if(genero.equals("M")){
                String parentesis = devolver.substring(devolver.indexOf("o1("), devolver.indexOf("o1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('+') + 1, parentesis.indexOf("@"));
                devolver = devolver.replace(parentesis, palabra);
            }else{
                String parentesis = devolver.substring(devolver.indexOf("o1("), devolver.indexOf("o1)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('-') + 1, parentesis.indexOf("#"));
                devolver = devolver.replace(parentesis, palabra);
            }
        }
        
        while(devolver.contains("&o1s")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&o1s"), devolver.indexOf("&o1s") + 4), o1.nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorTresNP(String nombre, String toReplace){
        String devolver = toReplace;
        
        while(devolver.contains("&p3n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p3n"), devolver.indexOf("&p3n") + 4), nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorTresP(Personaje p1, String toReplace){
        String genero = p1.pronombres;
        String devolver = toReplace;
        
        while(devolver.contains("p3(")){
            if(genero.equals("M")){
                String parentesis = devolver.substring(devolver.indexOf("p3("), devolver.indexOf("p3)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('+') + 1, parentesis.indexOf("@"));
                devolver = devolver.replace(parentesis, palabra);
            }else if(genero.equals("F")){
                String parentesis = devolver.substring(devolver.indexOf("p3("), devolver.indexOf("p3)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('-') + 1, parentesis.indexOf("#"));
                devolver = devolver.replace(parentesis, palabra);
            }else{
                String parentesis = devolver.substring(devolver.indexOf("p3("), devolver.indexOf("p3)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('=') + 1, parentesis.indexOf("$"));
                devolver = devolver.replace(parentesis, palabra);
            }
        }
        
        while(devolver.contains("&p3s")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p3s"), devolver.indexOf("&p3s") + 4), p1.nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorCuatroNP(String nombre, String toReplace){
        String devolver = toReplace;
        
        while(devolver.contains("&p4n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p4n"), devolver.indexOf("&p4n") + 4), nombre);
        }
        
        return devolver;
    }
    
    private String replaceJugadorCuatroP(Personaje p1, String toReplace){
        String genero = p1.pronombres;
        String devolver = toReplace;
        
        while(devolver.contains("p4(")){
            if(genero.equals("M")){
                String parentesis = devolver.substring(devolver.indexOf("p4("), devolver.indexOf("p4)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('+') + 1, parentesis.indexOf("@"));
                devolver = devolver.replace(parentesis, palabra);
            }else if(genero.equals("F")){
                String parentesis = devolver.substring(devolver.indexOf("p4("), devolver.indexOf("p4)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('-') + 1, parentesis.indexOf("#"));
                devolver = devolver.replace(parentesis, palabra);
            }else{
                String parentesis = devolver.substring(devolver.indexOf("p4("), devolver.indexOf("p4)") + 3);
                String palabra = parentesis.substring(parentesis.indexOf('=') + 1, parentesis.indexOf("$"));
                devolver = devolver.replace(parentesis, palabra);
            }
        }
        
        while(devolver.contains("&p4s")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p4s"), devolver.indexOf("&p4s") + 4), p1.nombre);
        }
        
        return devolver;
    }
}
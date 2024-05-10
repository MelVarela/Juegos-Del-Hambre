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
        (/) palabra a escoger
    */
    
    Evento(char horario, int numeroPersonajes, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        tipoLetal = letalidad;
        this.evento = evento;
    }
    
    Evento(char horario, int numeroPersonajes, int numeroAreas, int numeroObjetos, char letalidad, String evento){
        hora = horario;
        numJugadores = numeroPersonajes;
        numAreas = numeroAreas;
        numObjetos = numeroObjetos;
        tipoLetal = letalidad;
        this.evento = evento;
    }
    
    public String realizarEvento(Personaje p1){
        String devolver = evento;
        
        if(devolver.contains("&p1n")){
            return replaceJugadorUnoNP(p1.nombre, devolver);
        }
        if(devolver.contains("&p1s")){
            return replaceJugadorUnoP(p1, devolver);
        }
        
        return "error linea 32 de Evento.java";
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
package principal;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;

public class Area {

    public String nombre;
    public ArrayList<Personaje> personajes = new ArrayList<>();
    public ArrayList<Area> conexiones = new ArrayList<>();
    public ArrayList<Evento> eventos = new ArrayList<>();
    public ArrayList<String> muertos = new ArrayList<>();
    public int presentes = 0;
    public int disponibles = 0;
    public String genero;

    private GestorObjetos gestor = new GestorObjetos();
    private ArrayList<Personaje> jugRemove = new ArrayList<>();
    
    private Random alt = new Random();
    
    Area(String nombre, String genero){
        this.nombre = nombre;
        this.genero = genero;
    }

    public void añadirConexion(Area conexion) {
        conexiones.add(conexion);
    }

    public String getStringConexiones() {
        String devolver = "";

        for (Area conexion : conexiones) {
            devolver += conexion.nombre + ", ";
        }

        return devolver;
    }

    public void añadirPersonaje(Personaje personaje) {
        personajes.add(personaje);
        presentes++;
    }

    public void eliminarPersonaje(Personaje personaje) {
        personajes.remove(personaje);
        presentes--;
    }

    public DefaultListModel devolverModeloJugadores() {
        DefaultListModel devolver = new DefaultListModel();

        for (Personaje jugadore : personajes) {
            devolver.addElement(jugadore.nombre);
        }

        return devolver;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
        for (Personaje personaje : personajes) {
            presentes++;
        }
    }

    public void añadirEvento(Evento evento) {
        eventos.add(evento);
    }

    public String realizarEventos(char horaDia) {
        String textoEventos = "";
        jugRemove = new ArrayList<>();
        disponibles = presentes;

        for (Personaje jugador : personajes) {
            if(!(jugador.vivo)){
                jugRemove.add(jugador);
                jugador.accion = true;
            }
            if (jugador.accion) {
//                System.out.println("Se ha saltado la accion de "+ jugador +" ya que ya habia actuado.");
                continue;
            }
            int i = alt.nextInt(0, 100);
            if (i > 15 + jugador.ganasMover || (horaDia == 'P')) {
                textoEventos += evento(jugador, horaDia);
                jugador.ganasMover += 5;
            }else if((i < 30) && !(jugador.equipamiento.isEmpty())){
                textoEventosObjeto(jugador, horaDia);
                jugador.ganasMover += 5;
            }else {
                jugador.ganasMover = 0;
                Area ar = moverArea(jugador);
                String moverse = "\n&p1n se ha movido a1(+al@-a la#a1) &a1s\n";
                textoEventos += reemplazarMoverse(moverse, jugador.nombre, ar);
                jugador.setAccion(true);
                jugRemove.add(jugador);
                presentes--;
                disponibles--;
//                System.out.println("Se ha movido "+ jugador + ". Disponibles es ahora: " + disponibles);
            }
        }
        for (int i = 0; i < jugRemove.size(); i++) {
            personajes.remove(jugRemove.get(i));
        }
        return textoEventos;
    }

    public ArrayList<String> darMuertos() {
        return muertos;
    }

    public void limpiarMuertos() {
        muertos = new ArrayList<>();
    }

    public Area moverArea(Personaje personaje) {
        Area area = conexiones.get(alt.nextInt(0, conexiones.size()));
        area.añadirPersonaje(personaje);
        return area;
    }

    
    //Los eventos funcionan de la siguiente manera:
        /*
         * Se realiza un blucle for each de la lista de jugadores interna del area.
         * Cada jugador, tiene un 15% de posibilidades de moverse de area.
         * En caso de que no se mueva, realizará un evento.
         * 
         * El evento sucede al azar, y antes de realizarlo se asegura de que haya suficientes jugadores para llevarlo a cabo.
         * Si el evento necesita mas de un personaje, se asegurará de que ninguno esté repetido.
         * Si el evento es letal, matara al personaje.
         */
    private String evento(Personaje jugador, char horaDia) {
        String textoEventos = "";
        
        ArrayList<Objeto> usados = new ArrayList<>();

        Evento ev = eventos.get(alt.nextInt(eventos.size()));
//        System.out.println("Vamos a realizar el turno de: "+ jugador);
        
        int jug = ev.getNumeroPersonajes();
        
        if(horaDia == 'P'){
            while ((jug > disponibles && jug != 1) || !(ev.hora == horaDia)) {
                ev = eventos.get(alt.nextInt(eventos.size()));
                jug = ev.getNumeroPersonajes();
//                System.out.println("b1");
            }
        }else if(jugadoresDisponibles(jugador)){
            while ((jug > numJugadoresDisponibles(jugador) && jug != 1) || (!(ev.hora == horaDia) && !(ev.hora == 'U'))) {
                ev = eventos.get(alt.nextInt(eventos.size()));
                jug = ev.getNumeroPersonajes();
//                System.out.println("b2");
//                System.out.println("Disponibles: "+disponibles);
//                System.out.println("Jugadores necesarios:" + jug);
//                System.out.println("Para: "+ jugador.nombre);
            }
        }else{
            while((jug > 1) || ((!(ev.hora == horaDia) && !(ev.hora == 'U')))){
                ev = eventos.get(alt.nextInt(eventos.size()));
                jug = ev.getNumeroPersonajes();
//                System.out.println("DING DING DING");
//                System.out.println("Jugador: "+ jugador);
//                System.out.println(jugadoresDisponibles(jugador));
            }
        }
        
        switch (jug) {
            case 1 -> {
                if(ev.numObjetos != 0){
                    usados = gestionarObjetos(jug, ev.numObjetos, jugador, ev);
                }
                textoEventos += textoEvento(jugador, this, ev, usados);
                jugador.setAccion(true);
                disponibles--;
                if ((ev.tipoLetal != 'O') && jugador.vivo) {
                    int indtemp = personajes.indexOf(jugador);
                    matar(indtemp, jugador, ev);
                    presentes--;
                }
//                System.out.println("Ha terminado de jugar "+ jugador + " y solo ha participado el. Disponibles es ahora: " + disponibles);
            }
            case 2 -> {
                if(ev.numObjetos != 0){
                    usados = gestionarObjetos(jug, ev.numObjetos, jugador, ev);
                }
                Personaje p2 = personajes.get(alt.nextInt(personajes.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2) || p2.accion) {
                    p2 = personajes.get(alt.nextInt(0, personajes.size()));
//                    System.out.println("b3");
//                    System.out.println("Disponibles: "+disponibles);
//                    System.out.println("Num jug ev: "+ev.numJugadores);
//                    System.out.println("p2: "+p2);
//                    System.out.println("Muerto p2?:"+ jugRemove.indexOf(p2));
//                    System.out.println("Comparando con: "+ jugador);
//                    System.out.println(jugadoresDisponibles(jugador));
                }
                textoEventos += textoEvento(jugador, p2, this, ev, usados);
                jugador.setAccion(true);
                personajes.get(personajes.indexOf(p2)).setAccion(true);
                disponibles -= 2;
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = personajes.indexOf(jugador);
                            matar(indtemp, jugador, ev);
                            presentes--;
                            p2.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = personajes.indexOf(jugador);
                            int indtemp2 = personajes.indexOf(p2);
                            matar(indtemp, jugador, ev);
                            matar(indtemp2, p2, ev);
                            presentes -= 2;
                        }
                    }
                }
//                System.out.println("Ha terminado de jugar "+ jugador + " y han participado dos personas. Disponibles es ahora: " + disponibles);
            }
            case 3 -> {
                if(ev.numObjetos != 0){
                    usados = gestionarObjetos(jug, ev.numObjetos, jugador,ev);
                }
                Personaje p2 = personajes.get(alt.nextInt(personajes.size()));
                Personaje p3 = personajes.get(alt.nextInt(personajes.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2) || p2.accion) {
                    p2 = personajes.get(alt.nextInt(personajes.size()));
//                    System.out.println("b4");
//                    System.out.println("Disponibles: "+disponibles);
//                    System.out.println("Num jug ev: "+ev.numJugadores);
//                    System.out.println("p2: "+p2);
//                    System.out.println(jugRemove.indexOf(p2));
//                    System.out.println(jugador);
//                    System.out.println(jugadoresDisponibles(jugador));
                }
                while (p3.nombre.equals(jugador.nombre) || jugRemove.contains(p3) || p3.nombre.equals(p2.nombre) || p3.accion) {
                    p3 = personajes.get(alt.nextInt(personajes.size()));
//                    System.out.println("b5");
//                    System.out.println("Disponibles: "+disponibles);
//                    System.out.println("Num jug ev: "+ev.numJugadores);
//                    System.out.println("p2: "+p2);
//                    System.out.println(jugRemove.indexOf(p2));
//                    System.out.println(jugador);
//                    System.out.println(jugadoresDisponibles(jugador));
                }
                textoEventos += textoEvento(jugador, p2, p3, this, ev, usados);
                jugador.setAccion(true);
                personajes.get(personajes.indexOf(p2)).setAccion(true);
                personajes.get(personajes.indexOf(p3)).setAccion(true);
                disponibles -= 3;
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = personajes.indexOf(jugador);
                            matar(indtemp, jugador, ev);
                            presentes--;
                            p2.matoA(jugador);
                            p3.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = personajes.indexOf(jugador);
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            matar(indtemp, jugador, ev);
                            matar(indtemp2, p2, ev);
                            matar(indtemp3, p3, ev);
                            presentes -= 3;
                        }
                        case 'S'->{
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            matar(indtemp2, p2, ev);
                            matar(indtemp3, p3, ev);
                            presentes -= 2;
                            jugador.matoA(p2, p3);
                        }
                    }
                }
//                System.out.println("Ha terminado de jugar "+ jugador + " y han participado tres personas. Disponibles es ahora: " + disponibles);
            }
            case 4->{
                if(ev.numObjetos != 0){
                    usados = gestionarObjetos(jug, ev.numObjetos, jugador, ev);
                }
                Personaje p2 = personajes.get(alt.nextInt(personajes.size()));
                Personaje p3 = personajes.get(alt.nextInt(personajes.size()));
                Personaje p4 = personajes.get(alt.nextInt(personajes.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2) || p2.accion) {
                    p2 = personajes.get(alt.nextInt(personajes.size()));
//                    System.out.println("b6");
//                    System.out.println("Disponibles: "+disponibles);
//                    System.out.println("Num jug ev: "+ev.numJugadores);
//                    System.out.println("p2: "+p2);
//                    System.out.println(jugRemove.indexOf(p2));
//                    System.out.println(jugador);
//                    System.out.println(jugadoresDisponibles(jugador));
                }
                while (p3.nombre.equals(jugador.nombre) || jugRemove.contains(p3) || p3.nombre.equals(p2.nombre) || p3.accion) {
                    p3 = personajes.get(alt.nextInt(personajes.size()));
//                    System.out.println("b7");
//                    System.out.println("Disponibles: "+disponibles);
//                    System.out.println("Num jug ev: "+ev.numJugadores);
//                    System.out.println("p2: "+p2);
//                    System.out.println("p3: "+p3);
//                    System.out.println(jugRemove.indexOf(p2));
//                    System.out.println(jugador);
//                    System.out.println(jugadoresDisponibles(jugador));
                }
                while (p4.nombre.equals(jugador.nombre) || jugRemove.contains(p4) || p4.nombre.equals(p2.nombre) || p4.nombre.equals(p3.nombre) || p4.accion) {
                    p4 = personajes.get(alt.nextInt(personajes.size()));
//                    System.out.println("b8");
//                    System.out.println("Disponibles: "+disponibles);
//                    System.out.println("Num jug ev: "+ev.numJugadores);
//                    System.out.println("p2: "+p2);
//                    System.out.println("p3: "+p3);
//                    System.out.println("p4: "+p4);
//                    System.out.println(jugRemove.indexOf(p2));
//                    System.out.println(jugador);
//                    System.out.println(jugadoresDisponibles(jugador));
                }
                textoEventos += textoEvento(jugador, p2, p3, p4, this, ev, usados);
                jugador.setAccion(true);
                personajes.get(personajes.indexOf(p2)).setAccion(true);
                personajes.get(personajes.indexOf(p3)).setAccion(true);
                personajes.get(personajes.indexOf(p4)).setAccion(true);
                disponibles -= 4;
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = personajes.indexOf(jugador);
                            matar(indtemp, jugador, ev);
                            presentes--;
                            p2.matoA(jugador);
                            p3.matoA(jugador);
                            p4.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = personajes.indexOf(jugador);
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            int indtemp4 = personajes.indexOf(p4);
                            matar(indtemp, jugador, ev);
                            matar(indtemp2, p2, ev);
                            matar(indtemp3, p3, ev);
                            matar(indtemp4, p4, ev);
                            presentes -= 4;
                        }
                        case 'S'->{
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            int indtemp4 = personajes.indexOf(p4);
                            matar(indtemp2, p2, ev);
                            matar(indtemp3, p3, ev);
                            matar(indtemp4, p4, ev);
                            presentes -= 3;
                            jugador.matoA(p2, p3, p4);
                        }
                    }
                }
                System.out.println("Ha terminado de jugar "+ jugador + " y han participado cuatro personas. Disponibles es ahora: " + disponibles);
            }
        }
        return textoEventos;
    }
    
    private String textoEventosObjeto(Personaje jugador, char horaDia){
        String textoEventos = "";
    }

    private void matar(int indtemp, Personaje jugador, Evento evento) {
        personajes.get(indtemp).morir();
        jugRemove.add(jugador);
        muertos.add(jugador.nombre);
        jugador.setCausaMuerte(evento.muerte);
    }
    
    private String reemplazarMoverse(String toReplace, String jugador, Area area){
        String devolver = toReplace;
        
        while(devolver.contains("&p1n")){
            devolver = devolver.replace(devolver.substring(devolver.indexOf("&p1n"), devolver.indexOf("&p1n") + 4), jugador);
        }
        
        if(devolver.contains("&a1s")){
            devolver = replaceAreaUnoP(area, devolver);
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
    
    private boolean jugadoresDisponibles(Personaje p){
        for (Personaje personaje : personajes) {
            if((!personaje.accion) && (!(p.nombre.equals(personaje.nombre)) && (jugRemove.indexOf(personaje) == -1))){
                System.out.println(personaje.accion);
                System.out.println(p.nombre);
                System.out.println(personaje.nombre);
                System.out.println(p.nombre.equals(personaje.nombre));
                System.out.println(jugRemove.indexOf(personaje) != -1);
                System.out.println("Compatible:" + personaje);
                return true;
            }
        }
        return false;
    }
    
    private int numJugadoresDisponibles(Personaje p){
        int devolver = 0;
        for (Personaje personaje : personajes) {
            if((!personaje.accion) && (!(p.nombre.equals(personaje.nombre)) && (jugRemove.indexOf(personaje) == -1))){
                devolver++;
            }
        }
        return devolver;
    }
    
    private String textoEvento(Personaje jugador, Area area, Evento ev, ArrayList<Objeto> objetos){
        if(ev.numAreas == 0 && ev.numObjetos == 0){
            return ev.realizarEvento(jugador) + "\n";
        }else if(ev.numAreas == 1 && ev.numObjetos == 0){
            if(ev.esta){
                return ev.realizarEvento(jugador, area) + "\n";
            }else{
                return ev.realizarEvento(jugador, conexiones.get(alt.nextInt(0, conexiones.size()))) + "\n";
            }
        }else if(ev.numAreas == 1 && ev.numObjetos == 1){
            if(ev.esta){
                return ev.realizarEvento(jugador, area, objetos.get(0)) + "\n";
            }else{
                return ev.realizarEvento(jugador, conexiones.get(alt.nextInt(0, conexiones.size())), objetos.get(0)) + "\n";
            }
        }else if(ev.numAreas == 0 && ev.numObjetos == 1){
            return ev.realizarEvento(jugador, objetos.get(0)) + "\n";
        }
        return null;
    }
    
    private String textoEvento(Personaje jugador, Personaje jugador2, Area area, Evento ev, ArrayList<Objeto> objetos){
        if(ev.numAreas == 0 && ev.numObjetos == 0){
            return ev.realizarEvento(jugador, jugador2) + "\n";
        }else if(ev.numAreas == 1 && ev.numObjetos == 0){
            if(ev.esta){
                return ev.realizarEvento(jugador, jugador2, area) + "\n";
            }else{
                return ev.realizarEvento(jugador, jugador2, conexiones.get(alt.nextInt(0, conexiones.size()))) + "\n";
            }
        }else if(ev.numAreas == 1 && ev.numObjetos == 1){
            if(ev.esta){
                return ev.realizarEvento(jugador, jugador2, area, objetos.get(0)) + "\n";
            }else{
                return ev.realizarEvento(jugador, jugador2, conexiones.get(alt.nextInt(0, conexiones.size())), objetos.get(0)) + "\n";
            }
        }
        return null;
    }
    
    private String textoEvento(Personaje jugador, Personaje jugador2, Personaje jugador3, Area area, Evento ev, ArrayList<Objeto> objetos){
        if(ev.numAreas == 0 && ev.numObjetos == 0){
            return ev.realizarEvento(jugador, jugador2, jugador3) + "\n";
        }else if(ev.numAreas == 1 && ev.numObjetos == 0){
            if(ev.esta){
                return ev.realizarEvento(jugador, jugador2, jugador3, area) + "\n";
            }else{
                return ev.realizarEvento(jugador, jugador2, jugador3, conexiones.get(alt.nextInt(0, conexiones.size()))) + "\n";
            }
        }else if(ev.numAreas == 1 && ev.numObjetos == 1){
            return null;
        }
        return null;
    }
    
    private String textoEvento(Personaje jugador, Personaje jugador2, Personaje jugador3, Personaje jugador4, Area area, Evento ev, ArrayList<Objeto> objetos){
        if(ev.numAreas == 0 && ev.numObjetos == 0){
            return ev.realizarEvento(jugador) + "\n";
        }else if(ev.numAreas == 1 && ev.numObjetos == 0){
            if(ev.esta){
                return ev.realizarEvento(jugador, jugador2, jugador3, jugador4, area) + "\n";
            }else{
                return ev.realizarEvento(jugador, jugador2, jugador3, jugador4, conexiones.get(alt.nextInt(0, conexiones.size()))) + "\n";
            }
        }else if(ev.numAreas == 1 && ev.numObjetos == 1){
            return null;
        }
        return null;
    }
    
    private ArrayList<Objeto> gestionarObjetos(int numJugadores, int numObjetos, Personaje personaje, Evento ev){
        ArrayList<Objeto> devolver = new ArrayList<>();
        if(numJugadores == 1){
            switch(numObjetos){
                case 1->{
                    if(ev.encuentra){
                        Objeto obj = gestor.getRandom();
                        devolver.add(obj);
                        personaje.addObject(obj);
                    }else{
                        devolver.add(personaje.getRandomObject());
                    }
                }
            }
        }
        
        return devolver;
    }
    
}

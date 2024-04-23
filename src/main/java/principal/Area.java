package principal;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;

public class Area {

    public String nombre;
    public ArrayList<Personaje> jugadores = new ArrayList<>();
    public ArrayList<Area> conexiones = new ArrayList<>();
    public ArrayList<Evento> eventos = new ArrayList<>();
    public ArrayList<String> muertos = new ArrayList<>();
    public int presentes = 0;

    private ArrayList<Personaje> jugRemove = new ArrayList<>();

    Area(String nombre) {
        this.nombre = nombre;
    }

    public void añadirConexion(Area conexion) {
        conexiones.add(conexion);
    }

    public String getStringConexiones() {
        String devolver = "";

        for (Area conexion : conexiones) {
            devolver += " " + conexion.nombre + ",";
        }

        return devolver;
    }

    public void añadirPersonaje(Personaje personaje) {
        jugadores.add(personaje);
        presentes++;
    }

    public void eliminarPersonaje(Personaje personaje) {
        jugadores.remove(personaje);
        presentes--;
    }

    public String getStringJugadores() {
        String devolver = "";

        for (Personaje personaje : jugadores) {
            devolver += personaje + ",";
        }

        return devolver;
    }

    public DefaultListModel devolverModeloJugadores() {
        DefaultListModel devolver = new DefaultListModel();

        for (Personaje jugadore : jugadores) {
            devolver.addElement(jugadore.nombre);
        }

        return devolver;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        jugadores = personajes;
        for (Personaje personaje : personajes) {
            presentes++;
        }
    }

    public void añadirEvento(Evento evento) {
        eventos.add(evento);
    }

    public String realizarEventos(char horaDia) {
        String textoEventos = "";
        Random alt = new Random();
        jugRemove = new ArrayList<>();

        for (Personaje jugador : jugadores) {
            if (jugador.accion) {
                continue;
            }
            int i = alt.nextInt(0, 100);
            if (i > 15 + jugador.ganasMover || (horaDia == 'P')) {
                textoEventos += evento(jugador, horaDia);
                jugador.ganasMover += 5;
            } else {
                jugador.ganasMover = 0;
                Area ar = moverArea(jugador);
                textoEventos += String.format("\n%s se ha movido a %s\n", jugador.nombre, ar.nombre);
                jugador.setAccion(true);
                jugRemove.add(jugador);
                presentes--;
            }
        }
        for (int i = 0; i < jugRemove.size(); i++) {
            jugadores.remove(jugRemove.get(i));
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
        Random alt = new Random();
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

        Random alt = new Random();
        Evento ev = eventos.get(alt.nextInt(eventos.size()));
        
        int jug = ev.getNumeroPersonajes();
        
        if(horaDia == 'P'){
            while (jug > presentes || !(ev.hora == horaDia)) {
                ev = eventos.get(alt.nextInt(eventos.size()));
                jug = ev.getNumeroPersonajes();
            }
        }else{
            while (jug > presentes || (!(ev.hora == horaDia) && !(ev.hora == 'U'))) {
                ev = eventos.get(alt.nextInt(eventos.size()));
                jug = ev.getNumeroPersonajes();
            }
        }
        
        
        
        
        switch (jug) {
            case 1 -> {
                textoEventos += ev.realizarEvento(jugador.nombre) + "\n";
                jugador.setAccion(true);
                if ((ev.tipoLetal != 'O') && jugador.vivo) {
                    int indtemp = jugadores.indexOf(jugador);
                    jugadores.get(indtemp).morir();
                    jugRemove.add(jugador);
                    muertos.add(jugador.nombre);
                    presentes--;
                }
            }
            case 2 -> {
                Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2)) {
                    p2 = jugadores.get(alt.nextInt(0, jugadores.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = jugadores.indexOf(jugador);
                            jugadores.get(indtemp).morir();
                            jugRemove.add(jugador);
                            muertos.add(jugador.nombre);
                            presentes--;
                            p2.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = jugadores.indexOf(jugador);
                            int indtemp2 = jugadores.indexOf(p2);
                            jugadores.get(indtemp).morir();
                            jugadores.get(indtemp2).morir();
                            jugRemove.add(jugador);
                            jugRemove.add(p2);
                            muertos.add(jugador.nombre);
                            muertos.add(p2.nombre);
                            presentes -= 2;
                        }
                    }
                }
            }
            case 3 -> {
                Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                Personaje p3 = jugadores.get(alt.nextInt(jugadores.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2)) {
                    p2 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                while (p3.nombre.equals(jugador.nombre) || jugRemove.contains(p3) || p3.nombre.equals(p2.nombre)) {
                    p3 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre, p3.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = jugadores.indexOf(jugador);
                            jugadores.get(indtemp).morir();
                            jugRemove.add(jugador);
                            muertos.add(jugador.nombre);
                            presentes--;
                            p2.matoA(jugador);
                            p3.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = jugadores.indexOf(jugador);
                            int indtemp2 = jugadores.indexOf(p2);
                            int indtemp3 = jugadores.indexOf(p3);
                            jugadores.get(indtemp).morir();
                            jugadores.get(indtemp2).morir();
                            jugadores.get(indtemp3).morir();
                            jugRemove.add(jugador);
                            jugRemove.add(p2);
                            jugRemove.add(p3);
                            muertos.add(jugador.nombre);
                            muertos.add(p2.nombre);
                            muertos.add(p3.nombre);
                            presentes -= 3;
                        }
                        case 'S'->{
                            int indtemp2 = jugadores.indexOf(p2);
                            int indtemp3 = jugadores.indexOf(p3);
                            jugadores.get(indtemp2).morir();
                            jugadores.get(indtemp3).morir();
                            jugRemove.add(p2);
                            jugRemove.add(p3);
                            muertos.add(p2.nombre);
                            muertos.add(p3.nombre);
                            presentes -= 2;
                            jugador.matoA(p2, p3);
                        }
                    }
                }
            }
            case 4->{
                Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                Personaje p3 = jugadores.get(alt.nextInt(jugadores.size()));
                Personaje p4 = jugadores.get(alt.nextInt(jugadores.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2)) {
                    p2 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                while (p3.nombre.equals(jugador.nombre) || jugRemove.contains(p3) || p3.nombre.equals(p2.nombre)) {
                    p3 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                while (p4.nombre.equals(jugador.nombre) || jugRemove.contains(p4) || p4.nombre.equals(p2.nombre) || p4.nombre.equals(p3.nombre)) {
                    p4 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre, p3.nombre, p4.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = jugadores.indexOf(jugador);
                            jugadores.get(indtemp).morir();
                            jugRemove.add(jugador);
                            muertos.add(jugador.nombre);
                            presentes--;
                            p2.matoA(jugador);
                            p3.matoA(jugador);
                            p4.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = jugadores.indexOf(jugador);
                            int indtemp2 = jugadores.indexOf(p2);
                            int indtemp3 = jugadores.indexOf(p3);
                            int indtemp4 = jugadores.indexOf(p4);
                            jugadores.get(indtemp).morir();
                            jugadores.get(indtemp2).morir();
                            jugadores.get(indtemp3).morir();
                            jugadores.get(indtemp4).morir();
                            jugRemove.add(jugador);
                            jugRemove.add(p2);
                            jugRemove.add(p3);
                            jugRemove.add(p4);
                            muertos.add(jugador.nombre);
                            muertos.add(p2.nombre);
                            muertos.add(p3.nombre);
                            muertos.add(p4.nombre);
                            presentes -= 4;
                        }
                        case 'S'->{
                            int indtemp2 = jugadores.indexOf(p2);
                            int indtemp3 = jugadores.indexOf(p3);
                            int indtemp4 = jugadores.indexOf(p4);
                            jugadores.get(indtemp2).morir();
                            jugadores.get(indtemp3).morir();
                            jugadores.get(indtemp4).morir();
                            jugRemove.add(p2);
                            jugRemove.add(p3);
                            jugRemove.add(p4);
                            muertos.add(p2.nombre);
                            muertos.add(p3.nombre);
                            muertos.add(p4.nombre);
                            presentes -= 3;
                            jugador.matoA(p2, p3, p4);
                        }
                    }
                }
            }
        }
        return textoEventos;
    }
}

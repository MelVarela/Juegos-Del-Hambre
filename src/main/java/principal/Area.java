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
            if (i > 15) {
                textoEventos += evento(jugador, horaDia);
            } else {
                Area ar = moverArea(jugador);
                textoEventos += String.format("%s se ha movido a %s\n", jugador.nombre, ar.nombre);
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
        
        
        while (jug > presentes || (!(ev.hora == horaDia) || ev.hora == 'U')) {
            ev = eventos.get(alt.nextInt(eventos.size()));
            jug = ev.getNumeroPersonajes();
        }
        
        
        
        switch (jug) {
            case 1 -> {
                textoEventos += ev.realizarEvento(jugador.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.letal && jugador.vivo) {
                    int indtemp = jugadores.indexOf(jugador);
                    jugadores.get(indtemp).morir();
                    jugRemove.add(jugador);
                    muertos.add(jugador.nombre);
                    presentes--;
                }
            }
            case 2 -> {
                Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                while (p2.nombre.equals(jugador.nombre) || !(p2.vivo)) {
                    p2 = jugadores.get(alt.nextInt(0, jugadores.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.letal) {
                    int indtemp = jugadores.indexOf(jugador);
                    jugadores.get(indtemp).morir();
                    jugRemove.add(jugador);
                    muertos.add(jugador.nombre);
                    presentes--;
                }
            }
            case 3 -> {
                Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                Personaje p3 = jugadores.get(alt.nextInt(jugadores.size()));
                while (p2.nombre.equals(jugador.nombre) || !(p2.vivo)) {
                    p2 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                while (p3.nombre.equals(jugador.nombre) || !(p3.vivo) || p3.nombre.equals(p2.nombre)) {
                    p3 = jugadores.get(alt.nextInt(jugadores.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre, p3.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.letal) {
                    int indtemp = jugadores.indexOf(jugador);
                    jugadores.get(indtemp).morir();
                    jugRemove.add(jugador);
                    muertos.add(jugador.nombre);
                    presentes--;
                }
            }
        }
        return textoEventos;
    }
}

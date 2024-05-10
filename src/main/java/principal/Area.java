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
    public String genero;

    private ArrayList<Personaje> jugRemove = new ArrayList<>();
    
    private Random alt = new Random();
    
    Area(String nombre) {
        this.nombre = nombre;
    }
    
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

        for (Personaje jugador : personajes) {
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
                    int indtemp = personajes.indexOf(jugador);
                    matar(indtemp, jugador);
                    presentes--;
                }
            }
            case 2 -> {
                Personaje p2 = personajes.get(alt.nextInt(personajes.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2)) {
                    p2 = personajes.get(alt.nextInt(0, personajes.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = personajes.indexOf(jugador);
                            matar(indtemp, jugador);
                            presentes--;
                            p2.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = personajes.indexOf(jugador);
                            int indtemp2 = personajes.indexOf(p2);
                            matar(indtemp, jugador);
                            matar(indtemp2, p2);
                            presentes -= 2;
                        }
                    }
                }
            }
            case 3 -> {
                Personaje p2 = personajes.get(alt.nextInt(personajes.size()));
                Personaje p3 = personajes.get(alt.nextInt(personajes.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2)) {
                    p2 = personajes.get(alt.nextInt(personajes.size()));
                }
                while (p3.nombre.equals(jugador.nombre) || jugRemove.contains(p3) || p3.nombre.equals(p2.nombre)) {
                    p3 = personajes.get(alt.nextInt(personajes.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre, p3.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = personajes.indexOf(jugador);
                            matar(indtemp, jugador);
                            presentes--;
                            p2.matoA(jugador);
                            p3.matoA(jugador);
                        }
                        case 'T'->{
                            int indtemp = personajes.indexOf(jugador);
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            matar(indtemp, jugador);
                            matar(indtemp2, p2);
                            matar(indtemp3, p3);
                            presentes -= 3;
                        }
                        case 'S'->{
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            matar(indtemp2, p2);
                            matar(indtemp3, p3);
                            presentes -= 2;
                            jugador.matoA(p2, p3);
                        }
                    }
                }
            }
            case 4->{
                Personaje p2 = personajes.get(alt.nextInt(personajes.size()));
                Personaje p3 = personajes.get(alt.nextInt(personajes.size()));
                Personaje p4 = personajes.get(alt.nextInt(personajes.size()));
                while (p2.nombre.equals(jugador.nombre) || jugRemove.contains(p2)) {
                    p2 = personajes.get(alt.nextInt(personajes.size()));
                }
                while (p3.nombre.equals(jugador.nombre) || jugRemove.contains(p3) || p3.nombre.equals(p2.nombre)) {
                    p3 = personajes.get(alt.nextInt(personajes.size()));
                }
                while (p4.nombre.equals(jugador.nombre) || jugRemove.contains(p4) || p4.nombre.equals(p2.nombre) || p4.nombre.equals(p3.nombre)) {
                    p4 = personajes.get(alt.nextInt(personajes.size()));
                }
                textoEventos += ev.realizarEvento(jugador.nombre, p2.nombre, p3.nombre, p4.nombre) + "\n";
                jugador.setAccion(true);
                if (ev.tipoLetal != 'O') {
                    switch(ev.tipoLetal){
                        case 'N'->{
                            int indtemp = personajes.indexOf(jugador);
                            matar(indtemp, jugador);
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
                            matar(indtemp, jugador);
                            matar(indtemp2, p2);
                            matar(indtemp3, p3);
                            matar(indtemp4, p4);
                            presentes -= 4;
                        }
                        case 'S'->{
                            int indtemp2 = personajes.indexOf(p2);
                            int indtemp3 = personajes.indexOf(p3);
                            int indtemp4 = personajes.indexOf(p4);
                            matar(indtemp2, p2);
                            matar(indtemp3, p3);
                            matar(indtemp4, p4);
                            presentes -= 3;
                            jugador.matoA(p2, p3, p4);
                        }
                    }
                }
            }
        }
        return textoEventos;
    }

    private void matar(int indtemp, Personaje jugador) {
        personajes.get(indtemp).morir();
        jugRemove.add(jugador);
        muertos.add(jugador.nombre);
    }
    
}

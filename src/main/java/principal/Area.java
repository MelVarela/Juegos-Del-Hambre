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
    
    Area(String nombre){
        this.nombre = nombre;
    }
    
    public void añadirConexion(Area conexion){
        conexiones.add(conexion);
    }
    
    public String getStringConexiones(){
        String devolver = "";
        
        for (Area conexion : conexiones) {
            devolver += " " + conexion.nombre + ",";
        }
        
        return devolver;
    }
    
    public void añadirPersonaje(Personaje personaje){
        jugadores.add(personaje);
        presentes++;
    }
    
    public void eliminarPersonaje(Personaje personaje){
        jugadores.remove(personaje);
        presentes--;
    }
    
    public String getStringJugadores(){
        String devolver = "";
        
        for (Personaje personaje : jugadores) {
            devolver += personaje + ",";
        }
        
        return devolver;
    }
    
    public DefaultListModel devolverModeloJugadores(){
        DefaultListModel devolver = new DefaultListModel();
        
        for (Personaje jugadore : jugadores) {
            devolver.addElement(jugadore.nombre);
        }
        
        return devolver;
    }
    
    public void setPersonajes(ArrayList<Personaje> personajes){
        jugadores = personajes;
        for (Personaje personaje : personajes) {
            presentes++;
        }
    }
    
    public void añadirEvento(Evento evento){
        eventos.add(evento);
    }
    
    public String realizarEventos(){
        String textoEventos = "";
        Random alt = new Random();
        ArrayList<Personaje> jugRemove = new ArrayList<>();
        for (Personaje jugador : jugadores) {
            int i = alt.nextInt(0, 100);
            if(i > 15){
                int ev = alt.nextInt(eventos.size());
                int jug = eventos.get(ev).getNumeroPersonajes();
                System.out.println("Presentes: "+ presentes);
                while(jug > presentes){
                    System.out.println("Necesarios: "+ jug);
                    ev = alt.nextInt(eventos.size());
                    jug = eventos.get(ev).getNumeroPersonajes();
                    System.out.println("Bucle accion decision");
                }
                switch(jug){
                    case 1->{
                        textoEventos += eventos.get(ev).realizarEvento(jugador.nombre) + "\n";
                        if(eventos.get(ev).letal && jugador.vivo){
                            int indtemp = jugadores.indexOf(jugador);
                            jugadores.get(indtemp).morir();
                            jugRemove.add(jugador);
                            muertos.add(jugador.nombre);
                            presentes--;
                        }
                    }
                    case 2->{
                        Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                        while(p2.nombre.equals(jugador.nombre) || !(p2.vivo)){
                            p2 = jugadores.get(alt.nextInt(0, jugadores.size()));
                        }
                        textoEventos += eventos.get(ev).realizarEvento(jugador.nombre, p2.nombre) + "\n";
                        if(eventos.get(ev).letal){
                            int indtemp = jugadores.indexOf(jugador);
                            jugadores.get(indtemp).morir();
                            jugRemove.add(jugador);
                            muertos.add(jugador.nombre);
                            presentes--;
                        }
                    }
                    case 3->{
                        Personaje p2 = jugadores.get(alt.nextInt(jugadores.size()));
                        Personaje p3 = jugadores.get(alt.nextInt(jugadores.size()));
                        while(p2.nombre.equals(jugador.nombre) || !(p2.vivo)){
                            p2 = jugadores.get(alt.nextInt(jugadores.size()));
                        }
                        while(p3.nombre.equals(jugador.nombre) || !(p3.vivo) || p3.equals(p2.nombre)){
                            p3 = jugadores.get(alt.nextInt(jugadores.size()));
                        }
                        textoEventos += eventos.get(ev).realizarEvento(jugador.nombre, p2.nombre, p3.nombre) + "\n";
                        if(eventos.get(ev).letal){
                            int indtemp = jugadores.indexOf(jugador);
                            jugadores.get(indtemp).morir();
                            jugRemove.add(jugador);
                            muertos.add(jugador.nombre);
                            presentes--;
                        }
                    }
                }
            }else{
                Area ar = moverArea(jugador);
                textoEventos += String.format("%s se ha movido a %s\n", jugador.nombre, ar.nombre);
                jugRemove.add(jugador);
                presentes--;
            }
        }
        for(int i = 0; i < jugRemove.size(); i++){
            jugadores.remove(jugRemove.get(i));
        }
        return textoEventos;
    }
    
    public ArrayList<String> darMuertos(){
        return muertos;
    }
    
    public void limpiarMuertos(){
        muertos = new ArrayList<>();
    }
    
    public Area moverArea(Personaje personaje){
        Random alt = new Random();
        Area area = conexiones.get(alt.nextInt(0, conexiones.size()));
        area.añadirPersonaje(personaje);
        return area;
    }
}
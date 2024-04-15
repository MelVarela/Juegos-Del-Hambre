package principal;

import java.util.ArrayList;

public class Equipo {
    String nombre;
    ArrayList<Personaje> miembros = new ArrayList<>();
    
    Equipo(String nombre){
        this.nombre = nombre;
    }
    
    public Personaje getMiembro(String nombre){
        for (Personaje miembro : miembros) {
            if(miembro.nombre.equals(nombre)){
                return miembro;
            }
        }
        return new Personaje();
    }
}
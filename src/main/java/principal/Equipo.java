package principal;

import java.util.ArrayList;

public class Equipo {
    String nombre;
    private ArrayList<Personaje> miembros = new ArrayList<>();
    int numMiembros;
    
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
    
    public void añadirMiembro(Personaje personaje){
        miembros.add(personaje);
        numMiembros++;
    }
    
    public void borrarMiembro(Personaje personaje){
        miembros.remove(personaje);
        numMiembros--;
    }
    
    public ArrayList<Personaje> getMiembros(){
        return miembros;
    }
}
package principal;

import java.util.ArrayList;
import java.util.Random;

public class Personaje {
    String nombre, pronombres, victimas, causaMuerte;
    int vida, cordura, carisma, killCount, ganasMover;
    boolean vivo, accion;
    ArrayList<Objeto> equipamiento = new ArrayList<>();
    Random alt = new Random();
    
    Personaje(String nombre, String pronombres){
        this.nombre = nombre;
        this.pronombres = pronombres;
        vivo = true;
        accion = false;
    }
    
    Personaje(String nombre, String pronombres, int vida, int cordura, int carisma){
        this.nombre = nombre;
        this.pronombres = pronombres;
        this.vida = vida;
        this.cordura = cordura;
        this.carisma = carisma;
        vivo = true;
        killCount = 0;
        victimas = "";
        causaMuerte = "";
        ganasMover = 0;
    }
    
    Personaje(String nombre){
        
    }
    
    Personaje(){
        
    }
    
    public void morir(){
        vivo = false;
        accion = false;
    }
    
    public void setAccion(boolean valor){
        accion = valor;
    }
    
    public void matoA(Personaje victima){
        killCount++;
        victimas += victima.nombre + ", ";
    }
    
    public void matoA(Personaje victima1, Personaje victima2){
        killCount += 2;
        victimas += victima1.nombre + ", " + victima2.nombre + ", ";
    }
    
    public void matoA(Personaje victima1, Personaje victima2, Personaje victima3){
        killCount += 3;
        victimas += victima1.nombre + ", " + victima2.nombre + ", " + victima3.nombre + ", ";
    }
    
    public String getKillCount(){
        String devolver = "";
        devolver += killCount;
        return devolver;
    }
    
    public void setCausaMuerte(String causa){
        causaMuerte = causa;
    }
    
    public Objeto getObjet(int index){
        return equipamiento.get(index);
    }
    
    public Objeto getRandomObject(){
        return equipamiento.get(alt.nextInt(equipamiento.size()));
    }
    
    public void addObject(Objeto objeto){
        equipamiento.add(objeto);
    }
    
    @Override
    public String toString(){
        return String.format("%s, Acc: %b, Vivo: %b", nombre, accion, vivo);
    }
}
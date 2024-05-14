package principal;

import java.util.ArrayList;

public class Personaje {
    String nombre, pronombres, victimas, causaMuerte;
    int vida, movimiento, carisma, killCount, ganasMover;
    boolean vivo, accion;
    ArrayList<Objeto> equipamiento = new ArrayList<>();
    
    Personaje(String nombre, String pronombres){
        this.nombre = nombre;
        this.pronombres = pronombres;
        vivo = true;
        accion = false;
    }
    
    Personaje(String nombre, String pronombres, int vida, int movimiento, int carisma){
        this.nombre = nombre;
        this.pronombres = pronombres;
        this.vida = vida;
        this.movimiento = movimiento;
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
    
    @Override
    public String toString(){
        return String.format("%s, Acc: %b, Vivo: %b", nombre, accion, vivo);
    }
}
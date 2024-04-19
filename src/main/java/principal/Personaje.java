package principal;
public class Personaje {
    String nombre, pronombres;
    int vida, movimiento, carisma;
    boolean vivo, accion;
    
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
    }
    
    Personaje(){
        
    }
    
    public void morir(){
        vivo = false;
    }
    
    public void setAccion(boolean valor){
        accion = valor;
    }
}
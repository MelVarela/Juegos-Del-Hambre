package principal;

import java.util.ArrayList;

public class Objeto {
    String nombre, genero;
    int usos;
    boolean punzante, distancia, municion;
    ArrayList<Objeto> municionPara = new ArrayList<>();
    ArrayList<Evento> eventosAsociados = new ArrayList<>();

    Objeto(String nombre, String genero, int usos) {
        this.nombre = nombre;
        this.genero = genero;
        this.usos = usos;
    }
    
    public void setPunzante(){
        punzante = true;
    }
    
    public void setDistancia(){
        distancia = true;
    }
    
    public void setMunicion(Objeto para){
        municion = true;
        municionPara.add(para);
    }
    
    public void añadirArma(Objeto para){
        municionPara.add(para);
    }
    
    public void añadirEventoAsociado(Evento evento){
        eventosAsociados.add(evento);
    }
    
}
package principal;

import java.util.ArrayList;
import java.util.Random;

public class GestorObjetos {
        
        Random alt = new Random();
        ArrayList<Objeto> objetos = new ArrayList<>();
        
        Objeto hacha = new Objeto("Hacha", "F", 90);
        Objeto espada = new Objeto("Espada", "F", 90);
        Objeto cuchillo = new Objeto("Cuchillo", "M", 90);
        Objeto navaja = new Objeto("Navaja", "F", 90);
        Objeto mechero = new Objeto("Mechero", "M", 5);
        Objeto suministros = new Objeto("Suministros", "M", 1);
        Objeto cantimplora = new Objeto("Cantimplora", "F", 90);
        Objeto explosivo = new Objeto("Explosivo", "M", 1);
        Objeto aguaFresca = new Objeto("Agua fresca", "F", 2);
        Objeto veneno = new Objeto("Veneno", "M", 1);
        Objeto arco = new Objeto("Arco", "M", 90);
        Objeto flecha = new Objeto("Flecha", "F", 1);
        Objeto pistola = new Objeto("Pistola", "F", 0);
        Objeto bala = new Objeto("Bala", "F", 0);
        Objeto ballesta = new Objeto("Ballesta", "F", 0);
        
    GestorObjetos(){
        setPunzantes();
        setDistancia();
        setMunicion();
        
        eventos();
        
        objetos.add(hacha);
        objetos.add(espada);
        objetos.add(cuchillo);
        objetos.add(navaja);
        objetos.add(mechero);
        objetos.add(suministros);
        objetos.add(cantimplora);
        objetos.add(explosivo);
        objetos.add(aguaFresca);
        objetos.add(veneno);
        objetos.add(arco);
        objetos.add(flecha);
        objetos.add(pistola);
        objetos.add(bala);
        objetos.add(ballesta);
    }
    
    private void setPunzantes(){
        hacha.setPunzante();
        espada.setPunzante();
        cuchillo.setPunzante();
        navaja.setPunzante();
    }
    
    private void setDistancia(){
        arco.setDistancia();
        ballesta.setDistancia();
    }
    
    private void setMunicion(){
        flecha.setMunicion(arco);
        flecha.añadirArma(bala);
        bala.setMunicion(pistola);
    }
    
    private void eventos(){
        mechero.añadirEventoAsociado(new Evento('N', 1, 1, 'O', "\n&p1n quema unos periodicos para mantener el calor."));
        suministros.añadirEventoAsociado(new Evento('U', 1, 1, 'O', "\n&p1n consume los suministros que tenía."));
        cantimplora.añadirEventoAsociado(new Evento('U', 1, 1, 'O', "\n&p1n bebe de su cantimplora."));
        explosivo.añadirEventoAsociado(new Evento('D', 4, 1, 'S', "\n &p1n activa un exlosivo, matando a &p2n, &p3n y &p4n.", "Explotado"));
        explosivo.añadirEventoAsociado(new Evento('D', 1, 'N', "\n &p1n intenta activar sus explosivos, pero falla y vuela en mil pedazos.", "Terrorismo truncado"));
        aguaFresca.añadirEventoAsociado(new Evento('U', 1, 'O', "\n &p1n bebe agua fresca."));
        veneno.añadirEventoAsociado(new Evento('D', 2, 'N', "\n&p2n come una manzana que le da &p1n, sin saber que estaba envenada con cianuro.", "Envenenamiento."));
        Evento disparar = new Evento('U', 2, 'N', "\n&p1n le dispara a &p2n usando o1(+un@-una#o1) &o1s.");
        arco.añadirEventoAsociado(disparar);
        pistola.añadirEventoAsociado(disparar);
        ballesta.añadirEventoAsociado(disparar);
    }
    
    public Objeto getRandom(){
        return objetos.get(alt.nextInt(objetos.size()));
    }
}
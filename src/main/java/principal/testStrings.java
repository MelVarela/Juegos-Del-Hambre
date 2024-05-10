package principal;
public class testStrings {
    
public static void main(String[] args) {
        
        Personaje p1 = new Personaje("Cassidy", "M");
        Personaje p2 = new Personaje("Ari", "F");
        Personaje p3 = new Personaje("Kolkios", "N");
        Area a1 = new Area("Costa verdecente","F");
        Objeto o1 = new Objeto("Hacha", "F");
        
        //Se rompe por el tema de los ) <-- No puedo mas hay que cambiarlo
        Evento test1 = new Evento('T', 3, 1, 1, 'O', "\np1(+El@-La#=Elle$p1) p1(+tonto@-tonta#=tonte$p1) de &p1s ha asesinado por la espalda a &p2n usando o1(+un@-una#o1) &o1s. Horrorizado por el crimen de &p1n, &p3n huye de a1(+el@-la#a1) &a1s.");
        Evento test2 = new Evento('T', 1, 'O', "\n&p1n sale de paseo.");
        Evento test3 = new Evento('T', 2, 'O', "\n&p1n sale de paseo con &p2n.");
        Evento test4 = new Evento('T', 1, 'O', "\np1(+El@-La#=Elle$p1) p1(+tonto@-tonta#=tonte$p1) de &p1s es imbecil de cojones.");
        Evento test5 = new Evento('T', 1, 1, 0, 'O', "\n&p1n se va de paseo a a1(+el@-la#a1) &a1s.");
        Evento test6 = new Evento('T', 2, 'O', "\n&p1n saluda a &p2n. &p2n le responde a &p1s: 'Eres p1(+tonto@-tonta#=tonte$)'.");
        Evento test7 = new Evento('T', 1, 'O', "\np1(+El@-La#=Elle$p1) p1(+tonto@-tonta#=tonte$p1) de &p1s es imbecil de cojones, pero de p2(+el@-la#=elle$p2) imbecil de &p2s ni hablamos.");
        Evento test8 = new Evento('T', 1, 0, 1, 'O', "\n&p1n se corta sin querer con o1(+un@-una#o1) &o1s");
        
        System.out.println(test1.realizarEvento(p1, p2, p3, a1, o1));
    }
}
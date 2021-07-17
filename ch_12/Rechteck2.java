package geom;
// Definition der Klasse Rechteck als 
// Unterklasse von GeomObjekt

public class Rechteck2 extends GeomObjekt { // Attributdeklarationen
    int b, h;
    // Konstruktor 
    public Rechteck2() {
        b = h = 10; 
    }
    // Methode zum Berechnen der Fläche 
    public int berechneFlaeche() {
        return b * h; 
    }
}
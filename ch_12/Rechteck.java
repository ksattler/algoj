package geom;

// Definition der Klasse Rechteck 
public class Rechteck {
    // Attributdeklarationen 
    int x, y, b, h;
    int farbe;

    // Konstruktoren 
    public Rechteck() {
        x = y = 0;
        b = h = 10; 
    }

    public Rechteck(int xp, int yp, int br, int ho) { 
        x = xp; y = yp;
        b = br; h = ho;
    }

    // Methode zum Verschieben
    public void verschieben(int dx, int dy) {
        x += dx; y += dy;
    }

    // Methode zum Berechnen der Fläche 
    public int berechneFlaeche() {
        return b * h; 
    }
}

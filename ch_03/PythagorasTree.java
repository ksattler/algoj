import java.awt.*;
import javax.swing.JFrame;

class PythagorasTree extends JFrame {
  // phi ist einer der Winkel der entstehenden Dreiecke,
  // tan (phi) kann etwa zwischen 0.5 und 1.1 variiert
  // werden, um zu verwertbaren Bildern zu gelangen.
  double tanphi = 1.0;
  
   // Die eigentliche Zeichenroutine
  public void paint(Graphics g) {
    g.clearRect(0, 0, 500, 500);
    paintTree(g, 200, 400, 280, 400);
  }
  
   // rekursive Pythagoras-Funktion
  void paintTree(Graphics g, double x1, double y1,
		 double x2, double y2 ) {
    // (1) Eckpunkte bestimmen
    double dx = x2 - x1; double dy = y1 - y2;
    double x3 = x1 - dy; double y3 = y1 - dx;
    double x4 = x2 - dy; double y4 = y2 - dx;

    // (2) vollständiges Quadrat zeichnen
    g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    g.drawLine((int)x2, (int)y2, (int)x4, (int)y4);
    g.drawLine((int)x4, (int)y4, (int)x3, (int)y3);
    g.drawLine((int)x1, (int)y1, (int)x3, (int)y3);

    // (3) Koordinaten des neuen Eckpunktes errechnen
    double v = (x3 + x4) / 2 - (dy / 2 * tanphi);
    double w = (y3 + y4) / 2 - (dx / 2 * tanphi);

    if  (dx * dx + dy * dy > 2) {
      // (4) kleine Teilbäume zeichnen
      paintTree(g, x3, y3, v, w);
      paintTree(g, v, w, x4, y4);
    }
  }
}

import algoj.IOUtils;

/**
 * Eine einfache Implementierung eines Tic Tac Toe-Spiels.
 * 
 */
public class TicTacToe {
	
	char spielfeld[][] = { 
			{ ' ', ' ', ' ' },
			{ ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ' } 
	};

	/**
	 * Test ob Spieler gewonnen hat.
	 * 
	 * @param spieler Spielerfarbe ('x' oder 'o')
	 * @return true, wenn gewonnen
	 */
	boolean gewonnen(char spieler) {
		for (int z = 0; z < 3; z++) {
			if (zeileGewonnen(z, spieler) || spalteGewonnen(z, spieler)) 
				return true;
		}
		return diagonaleGewonnen(spieler); 
	}
	
	/**
	 * Test ob Spieler in der gegebenen Zeile gewonnen hat.
	 * 
	 * @param z Zeilennummer
	 * @param farbe Spielerfarbe ('x' oder 'o')
	 * @return true, wenn in Zeile z gewonnen
	 */
	boolean zeileGewonnen(int z, char farbe) {
		boolean sieg = true;
		for (int spalte = 0; spalte < 3; spalte++) {
			sieg = sieg && (spielfeld[z][spalte] == farbe); 
		}
		return sieg;
	}
	
	/**
	 * Test ob Spieler in der gegebenen Spalte gewonnen hat.
	 * 
	 * @param s Spaltennummer
	 * @param farbe Spielerfarbe ('x' oder 'o')
	 * @return true, wenn in Spalte s gewonnen
	 */
	boolean spalteGewonnen(int s, char farbe) {
		boolean sieg = true;
		for (int zeile = 0; zeile < 3; zeile++) {
			sieg = sieg && (spielfeld[zeile][s] == farbe); 
		}
		return sieg;
	}
	
	/**
	 * Test ob Spieler in einer Diagonale gewonnen hat.
	 * 
	 * @param farbe Spielerfarbe ('x' oder 'o')
	 * @return true, wenn in Spalte s gewonnen
	 */
	boolean diagonaleGewonnen(char farbe) {
		boolean sieg = true;
		for (int i = 0; i < 3; i++)
			sieg = sieg && (spielfeld[i][i] == farbe);
		if (sieg) return true;
		sieg = true;
		for (int i = 0; i < 3; i++)
			sieg = sieg && (spielfeld[i][2 - i] == farbe);
		return sieg;
	}
	
	/**
	 * Liefert die Anzahl der freien Felder.
	 * 
	 * @return Anzahl
	 */
	int freieFelder() {
		int i = 0;
		for (int z = 0; z < 3; z++) {
			for (int s = 0; s < 3; s++) {
				if (spielfeld[z][s] == ' ')
					i++;
			}
		}
		return i;
	}
	
	/**
	 * Liefert true, wenn noch ein freies Feld existiert.
	 * 
	 * @return true bei freien Feldern
	 */
	boolean freiesFeld() {
		for (int z = 0; z < 3; z++) {
			for (int s = 0; s < 3; s++) {
				if (spielfeld[z][s] == ' ')
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Wechselt den Spieler.
	 * 
	 * @param sp aktuelle Spielerfarbe
	 * @return neue Spielerfarbe
	 */
	char wechsel(char sp) {
		return sp == 'x' ? 'o' : 'x';
	}
	
	/**
	 * Hilfsmethode für Bestimmung des besten Zugs - wird rekursiv aufgerufen.
	 *  
	 * @param sp Spielerfarbe ('o' oder 'x')
	 * @param pos Feld für Speicherung der Position (Zeile, Spalte)
	 * @param tiefe aktuelle Tiefe des Spielzugs 
	 * @return Bewertung des Zugs
	 */
	int waehleZug(char sp, int[] pos, int tiefe) {
		int besterWert = (sp == 'o' ? -100 : 100);
		int bz = -1, bs = -1;
		
		if (gewonnen('o')) return 100;
		else if (gewonnen('x')) return -100;
		else if (!freiesFeld()) return 0;
		
		for (int z = 0; z < 3; z++) {
			for (int s = 0; s < 3; s++) {
				if (spielfeld[z][s] == ' ') {
					spielfeld[z][s] = sp;
					int wert = waehleZug(wechsel(sp), pos, tiefe + 1) * (10 - tiefe);
					spielfeld[z][s] = ' ';
					// Verbesserung ??
					if ((sp == 'o' && besterWert < wert) || (sp == 'x' && besterWert > wert)) {
						// System.out.println("Zug: " + (z * 3 + s) + ": " + wert + " - " + tiefe);
						bz = z;
						bs = s;
						besterWert = wert;
					}
				}
			}
		}
		pos[0] = bz; pos[1] = bs;
		return besterWert;
	}
	
	
	/**
	 * Liefert den besten Zug für den Computerspieler.
	 * 
	 * @return Feld für den nächsten Zug.
	 */
	int besterZug() {
		int[] pos = new int[2];
		pos[0] = pos[1] = -1;
		int wert = waehleZug('o', pos, 0);
		System.out.println("bester Zug: " + (pos[0] * 3 + pos[1]) + ": " + wert);
		return pos[0] * 3 + pos[1];
	}
	
	/**
	 * Gibt das Spielfeld mit der aktuellen Belegung aus.
	 */
	void ausgabe() {
		System.out.println("+---+---+---+");
		for (int z = 0; z < 3; z++) {
			System.out.print("| ");
			for (int s = 0; s < 3; s++) {
				if (spielfeld[z][s] == ' ')
					System.out.print(z * 3 + s);
				else
					System.out.print(spielfeld[z][s]);
				System.out.print(" | ");
			}
			System.out.println();
			System.out.println("+---+---+---+");
		}
	}
	
	/**
	 * Implementiert den eigentlichen Spielablauf.
	 */
	public void run() {
		int z = 0, s = 0, feld;
		char spieler = 'x';
		boolean fertig = false;

		do {
			if (! freiesFeld()) {
				System.out.println("Unentschieden!");
				fertig = true; 
			} 
			else {
				ausgabe();
				if (spieler == 'x') {
					// Mensch
					System.out.print("Deine Eingabe: ");
					feld = IOUtils.readInt();
				}
				else {
					// Computer
					feld = besterZug();
				}
				s = feld % 3;
				z = feld / 3;

				if (spielfeld[z][s] == ' ') { 
					spielfeld[z][s] = spieler; 
					// auf Sieg testen
					if (gewonnen(spieler)) { 
						ausgabe();
						System.out.println("Spieler " + spieler + " hat gewonnen!"); 
						fertig = true;
					}
					else
					   spieler = wechsel(spieler);
				}
				else 
					System.out.println("Zug ungültig!"); }
		} while (!fertig);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		ttt.run();
	}

}

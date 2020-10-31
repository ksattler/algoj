  static void radixSort(int[] array) {
      for (int i = 0; i < 3; i++)
        radixSortStep(array, i, 10);
  }

  static int getDigit(int val, int dpos, int base) {
      int[] div = { 1, 10, 100, 1000, 10000 };
      return (val / div[dpos]) % base;
  }

  static void radixSortStep(int[] array, int d, int base) {
      int[] histo = new int[base]; // Histogramm
      int[] offset = new int[base]; // aktuelle Position pro Partition

      // temporäres Feld
      int[] tmp = new int[array.length];
      System.arraycopy(array, 0, tmp, 0, array.length);

      // Histogramm konstruieren
      for (int i = 0; i < array.length; i++) {
          int digit = getDigit(array[i], d, base);
          histo[digit]++;
      }
      // kumulierte Häufigkeiten berechnen
      int sum = 0;
      for (int i = 0; i < histo.length; i++) {
          sum += histo[i];
          if (histo[i] > 0) histo[i] = sum - histo[i];
      }

      // Elemente an Position kopieren
      for (int i = 0; i < tmp.length; i++) {
        int digit = getDigit(tmp[i], d, base);
        int pos = histo[digit] + offset[digit]++;
        array[pos] = tmp[i];
      }
  }

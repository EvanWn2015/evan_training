package idv.evan.algorithm.sort;

public class BubbleSort {

	public static void sort(int[] array) {
		for (int i = array.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (array[j] > array[j + 1]) {
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
		}
	}

	public static void flagSort(int[] array) {
		for (int i = array.length - 1; i > 0; --i) {
			boolean swapped = false;
			for (int j = 0; j < i; ++j) {
				if (array[j] > array[j + 1]) {
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
					swapped = true;
				}
				if (!swapped) {
					break;
				}
			}
		}
	}
}

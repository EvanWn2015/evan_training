package idv.evan.algorithm.sort;

import java.util.List;

import com.google.common.collect.Lists;

public class InsertionSort {

	public static void extraSpaceSort(int[] array) {
		List<Integer> sorted = Lists.newArrayList(array.length);
		for (int i = 0; i < array.length; ++i) {
			int n = array[i];
			int index = sorted.size() - 1;
			while (index >= 0 && sorted.get(index) > n) {
				--index;
			}
			sorted.add(index + 1, n);
		}
		for (int i = 0; i < array.length; ++i) {
			array[i] = sorted.get(i);
		}
	}

	public static void inPlacesort(int[] array) {
		int n, j;
		for (int i = 1; i < array.length; ++i) {
			n = array[i];
			for (j = i - 1; j >= 0 && array[j] > n; --j) {
				array[j + 1] = array[j];
			}
			array[j + 1] = n;
		}
	}
}

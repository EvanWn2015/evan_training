package idv.evan.algorithm.sort;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class SelectionSort {

	public static void sortArray(int[] array) {
		List<Integer> unsorted = Lists.newArrayList(Ints.asList(array));
		List<Integer> sorted = Lists.newArrayList();

		while (unsorted.size() > 0) {
			int index = 0, min = unsorted.get(0);
			for (int u : unsorted) {
				if (u < min) {
					index = unsorted.indexOf(u);
					min = u;
				}
			}
			unsorted.remove(index);
			sorted.add(min);
		}
		for (int i = 0; i < array.length; ++i)
			array[i] = sorted.get(i);
	}

	public static <T> void sortCollection(List<T> list) {
		List<T> unsorted = Lists.newArrayList(list);
		List<T> sorted = Lists.newArrayList();

		while (unsorted.size() > 0) {
			int index = 0;
			T t = unsorted.get(0);
			for (T u : unsorted) {
				if (u.hashCode() < t.hashCode()) {
					index = unsorted.indexOf(u);
					t = u;
				}
			}
			unsorted.remove(index);
			sorted.add(t);
		}

		list.clear();
		list.addAll(sorted);
	}

	public static void sortInPlace(int[] array) {
		for (int i = 0, minIndex; i < array.length - 1; ++i) {
			minIndex = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				int tmp = array[minIndex];
				array[minIndex] = array[i];
				array[i] = tmp;
			}
		}
	}

}

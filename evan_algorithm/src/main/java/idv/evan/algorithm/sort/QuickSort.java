package idv.evan.algorithm.sort;

import java.util.List;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

/**
 * 如果Sort Collection<T> Object強型別物件，需改寫equals(), hashCode();
 * 
 * @author evan
 *
 */
public class QuickSort {

	public static void sort(int[] array) {
		List<Integer> list = Lists.newArrayList();
		for (int n : array) {
			list.add(n);
		}
		list = sort(list);

		array = Ints.toArray(list);
		// for (int i = 0; i < array.length; ++i) {
		// array[i] = list.get(i);
		// }
	}

	public static <T> List<T> sort(List<T> list) {
		if (list.size() < 2) {
			return list;
		}

		T pivot = list.get(list.size() / 2);
		list.remove(list.size() / 2);
		List<T> less = Lists.newArrayList();
		List<T> greater = Lists.newArrayList();
		List<T> result = Lists.newArrayList();

		list.parallelStream().forEach(t -> {
			if (t.hashCode() > pivot.hashCode()) {
				greater.add(t);
			} else {
				less.add(t);
			}
		});

		result.addAll(sort(less));
		result.add(pivot);
		result.addAll(sort(greater));
		return result;
	}

	public static void sortInPlace(int[] array, int left, int right) {
		if (right <= left) {
			return;
		}
		int pivotIndex = (left + right) / 2;
		int pivot = array[pivotIndex];
		swapInPlace(array, pivotIndex, right);
		int swapIndex = left;
		for (int i = left; i < right; ++i) {
			if (array[i] <= pivot) {
				swapInPlace(array, i, swapIndex);
				++swapIndex;
			}
		}
		swapInPlace(array, swapIndex, right);
		sortInPlace(array, left, swapIndex - 1);
		sortInPlace(array, swapIndex + 1, right);
	}

	private static void swapInPlace(int[] array, int indexA, int indexB) {
		int tmp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = tmp;
	}
}

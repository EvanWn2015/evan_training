package idv.evan.algorithm.search;

import java.util.List;

/**
 * 二分法搜索 需要條件 Sort 過的 Array or Collection，
 * 如果是Object強型別物件，需改寫equals(), hashCode();
 * @author evan
 */
public class BinarySearch {

	public static int iterativeSearch(int[] array, int num) {
		int left = 0, right = array.length - 1;
		while (left <= right) {
			int middle = (right + left) / 2;
			if (array[middle] == num) {
				return middle;
			}
			if (array[middle] > num) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -1;
	}

	public static <T> int iterativeSearch(List<T> list, T t) {
		int left = 0, right = list.size() - 1;
		while (left <= right) {
			int middle = (right + left) / 2;
			if (t.equals(list.get(middle))) {
				return middle;
			} else {
				if (list.get(middle).hashCode() > t.hashCode()) {
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			}
		}
		return -1;
	}

	public static int divideSearch(int[] array, int num) {
		return search(array, num, 0, array.length - 1);
	}

	private static int search(int[] array, int num, int left, int right) {
		if (left > right) {
			return -1;
		}
		int middle = (right + left) / 2;
		if (array[middle] == num) {
			return middle;
		}
		if (array[middle] > num) {
			return search(array, num, left, middle - 1);
		}
		return search(array, num, middle + 1, right);
	}
}

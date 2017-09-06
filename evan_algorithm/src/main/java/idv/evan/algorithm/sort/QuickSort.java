package idv.evan.algorithm.sort;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.beust.jcommander.internal.Sets;
import com.google.common.collect.Lists;

/**
 * 如果Sort Collection<T> Object強型別物件，需改寫equals(), hashCode();
 * 
 * @author evan
 *
 */
public class QuickSort {

	public static void sort(int[] array) {
		sortInPlaceInt(array, 0, array.length -1);
	}
	
	public static <T> void sort(T[] array) {
		sortInPlace(array, 0, array.length - 1);
	}

	public static <T> void sort(Collection<T> list) {
		@SuppressWarnings("unchecked")
		T[] array = list.toArray((T[]) new Object[list.size()]);
		sortInPlace(array, 0, array.length - 1);
		list.clear();
		list.addAll(Lists.newArrayList(array));
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> multipleMergerSort(List<T> ...lists) {
		List<List<?>> mergerList  = Lists.newArrayList();
		for(int i = 0; i < lists.length; i++) {
			mergerList.add(lists[i]);
		}
		List<T> list = (List<T>) mergerList.stream().flatMap(m -> m.stream()).collect(Collectors.toList());
		sort(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Set<T> multipleMergerSort(Set<T> ...sets) {
		Set<Set<?>> mergerSet  = Sets.newHashSet();
		for(int i = 0; i < sets.length; i++) {
			mergerSet.add(sets[i]);
		}
		
		Set<T> set = (Set<T>) mergerSet.stream().flatMap(m -> m.stream()).collect(Collectors.toSet());
		sort(set);
		return set;
	}
	
	private static <T> void sortInPlace(T[] array, int left, int right) {
		if (right <= left) {
			return;
		}
		int pivotIndex = (left + right) / 2;
		T pivot = array[pivotIndex];
		swapInPlace(array, pivotIndex, right);
		int swapIndex = left;

		for (int i = left; i < right; ++i) {
			if (array[i].hashCode() <= pivot.hashCode()) {
				swapInPlace(array, i, swapIndex);
				++swapIndex;
			}
		}
		swapInPlace(array, swapIndex, right);
		sortInPlace(array, left, swapIndex - 1);
		sortInPlace(array, swapIndex + 1, right);
	}

	private static <T> void swapInPlace(T[] array, int indexA, int indexB) {
		T tmp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = tmp;
	}
	
	public static void sortInPlaceInt(int[] array, int left, int right) {
		if (right <= left) {
			return;
		}
		int pivotIndex = (left + right) / 2;
		int pivot = array[pivotIndex];
		swapInPlaceInt(array, pivotIndex, right);
		int swapIndex = left;
		for (int i = left; i < right; ++i) {
			if (array[i] <= pivot) {
				swapInPlaceInt(array, i, swapIndex);
				++swapIndex;
			}
		}
		swapInPlaceInt(array, swapIndex, right);
		sortInPlaceInt(array, left, swapIndex - 1);
		sortInPlaceInt(array, swapIndex + 1, right);
	}

	private static void swapInPlaceInt(int[] array, int indexA, int indexB) {
		int tmp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = tmp;
	}

}

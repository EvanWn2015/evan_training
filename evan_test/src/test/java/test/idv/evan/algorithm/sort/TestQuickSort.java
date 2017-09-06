package test.idv.evan.algorithm.sort;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import idv.evan.algorithm.sort.QuickSort;

public class TestQuickSort {

	@Test
	public void testSort() {

		int[] array = new int[] {1,6,2,88,4,6,3,22,3};
		QuickSort.sort(array);
		System.out.println(array);
		
		Integer[] arrays = new Integer[] {1, 34, 67, 8, 9, 3 };
		QuickSort.sort(arrays);
		System.out.println(arrays);
		
		String[] arraySrc = new String[] {"1", "((", "DFDS", "3", "4"};
		QuickSort.sort(arraySrc);
		System.out.println(arraySrc);
		
		
		List<String> sList = QuickSort.multipleMergerSort(Lists.newArrayList("4","10"), Lists.newArrayList("DFD","O"));
		System.out.println(sList);
		
		Set<Integer> sSet = QuickSort.multipleMergerSort(Sets.newHashSet(4,10,20), Sets.newHashSet(1,22,3,4));
		System.out.println(sSet);
		
	}
}

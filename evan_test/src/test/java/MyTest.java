
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.beust.jcommander.internal.Lists;
import com.jcraft.jsch.JSchException;

import idv.evan.design_pattern.enumFactory.ColorFactory;
import idv.evan.design_pattern.enumFactory.Green;
import idv.evan.design_pattern.enumFactory.Red;
import idv.evan.tools.command.SSHManager;

public class MyTest {

	@Test
	public void myTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Red red = ColorFactory.RED.newInstance();
		System.out.println(red.print());
		Green green = ColorFactory.GREEN.newInstance();
		System.out.println(green.print());
	}

	/**
	 * 合併 Collection ， 並取得各種計算 LongSummaryStatistics IntSummaryStatistics
	 * DoubleSummaryStatistics
	 */
	@Test
	public void testFlatMapToSummaryStatistics() {
		List<Double> i1 = Lists.newArrayList(1.2, 2.3, 3.4, 4.5, 5.6, 6.7, 10.7);
		List<Double> i2 = Lists.newArrayList(7.1, 8.2, 9.3);
		@SuppressWarnings("unchecked")
		List<List<Double>> array = Lists.newArrayList(i1, i2);
		// LongSummaryStatistics
		// IntSummaryStatistics
		DoubleSummaryStatistics result = array.stream()
				.flatMapToDouble(d -> d.stream().mapToDouble(Double::doubleValue)).summaryStatistics();

		System.out.println(result.getMax());
		System.out.println(result.getMin());
		System.out.println(result.getCount());
		System.out.println(result.getSum());
		System.out.println(result.getAverage());
	}

	/**
	 * 合併 Collection ， 並計算總和
	 */
	@Test
	public void testFlatMapToInt() {
		List<Integer> i1 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
		List<Integer> i2 = Lists.newArrayList(7, 8, 9);

		@SuppressWarnings("unchecked")
		List<List<Integer>> array = Lists.newArrayList(i1, i2);

		Integer result = array.stream().flatMapToInt(i -> i.stream().mapToInt(Integer::intValue)).sum();
		System.out.println(result);
	}

	/**
	 * 合併 Collection ， 並計算平均值
	 */
	@Test
	public void testFlatMapToAverage() {
		List<Integer> i1 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 10);
		List<Integer> i2 = Lists.newArrayList(7, 8, 9);

		@SuppressWarnings("unchecked")
		List<List<Integer>> array = Lists.newArrayList(i1, i2);

		OptionalDouble result = array.stream().flatMapToInt(i -> i.stream().mapToInt(Integer::intValue)).average();
		System.out.println(result.getAsDouble());
	}

	/**
	 * 合併 Collection ， 並排除重複
	 */
	@Test
	public void testFlatMap() {
		List<String> s1 = Arrays.asList("A", "Dhoni", "A");
		List<String> s2 = Arrays.asList("A", "Watson", "Smith");
		List<String> s3 = Arrays.asList("Alex", "Bell", "Broad");
		List<String> s4 = Arrays.asList("Kane", "Nathan", "Vettori");

		@SuppressWarnings("unchecked")
		List<List<String>> array = Lists.newArrayList(s1, s2, s3, s4);

		Set<String> result = array.stream().flatMap(s -> s.stream()).collect(Collectors.toSet());
		System.out.println(result);
	}

	@Test
	public void testConstant() {
		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.NaN);
	}

	@Test
	public void testFilter() {
		String strUserName = "";
		String strPassword = "";
		String strConnectionIP = "";
		Integer intConnectionPort = 0;
		Object[] verifiedValues = new Object[] { strUserName, strPassword, strConnectionIP, intConnectionPort };
		verifiedValues = Arrays.stream(verifiedValues).filter(s -> s == null).peek(System.out::println)
				.toArray(size -> new Object[size]);
		boolean isBuild = verifiedValues.length == 0;
		System.out.println(isBuild);
	}

	/**
	 * 透過 Jsch 對遠端 Serve 下 Command
	 * @throws JSchException
	 */
	@Test
	public void testSSHManager() throws JSchException{
		SSHManager manager = SSHManager.newInstance()
				.withConnectionIP("172.16.34.139")
				.withUserName("evan")
				.withPassword("1108")
				.withConnectionPort(22)
				.build();
		
		manager.sendCommand("ps -ef|head -n 10");
		
	}

}

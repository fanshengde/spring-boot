import java.util.Arrays;

/**
 * Lambda表达式（也称为闭包）是Java 8中最大和最令人期待的语言改变
 * https://blog.csdn.net/u014470581/article/details/54944384
 */

public class Lambda {
	public static void main(String[] args) {

		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
		System.out.println("----------------------");

		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));
		System.out.println("----------------------");

		Arrays.asList("a", "b", "d").forEach(e -> {
			System.out.print(e);
			System.out.print(e);
		});
		System.out.println("\n----------------------");
		String separator = ",";
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));

		System.out.println("\n----------------------");
		Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

	}
}

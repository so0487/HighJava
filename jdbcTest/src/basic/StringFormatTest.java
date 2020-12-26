package basic;

public class StringFormatTest {

	public static void main(String[] args) {
		String a1 = "안녕하세요";
		String a2 = "안녕!!";
		
		String b1 = "abcd";
		String b2 = "abcdefghij";
		
		
		System.out.println(String.format("%-20s %-20s %-20s %-20s",a1, a2, b1, b2 ));
		System.out.println(String.format("%-20s %-20s %-20s %-20s",a2, a1, b2, b1 ));
		System.out.println();
		System.out.printf("%-20s %-20s %-20s %-20s\n",a1, a2, b1, b2);
		System.out.printf("%-20s %-20s %-20s %-20s\n",a2, a1, b2, b1);
		System.out.println();
		System.out.println(a1 + ">>" + a1.length() + " -- " + a1.getBytes().length);
		System.out.println(a2 + ">>" + a2.length() + " -- " + a2.getBytes().length);
		System.out.println(b1 + ">>" + b1.length() + " -- " + b1.getBytes().length);
		System.out.println(b2 + ">>" + b2.length() + " -- " + b2.getBytes().length);
		

	}

}

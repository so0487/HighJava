package basic;

public class Student {
	private int age;
	private String name;
	
	public Student(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	
	public void getInfo(){
		System.out.println("이름은 "+this.name+"이고, 나이는 "+this.age+"입니다.");
	}
	
	
	
	
}

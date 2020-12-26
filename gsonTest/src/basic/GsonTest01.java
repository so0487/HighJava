package basic;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class GsonTest01 {
	
	public static void main(String[] args) {
		System.out.println("GSON의 두 가지 메서드");
		System.out.println();
		
		String jsonStr = "{'name':'tori','age':20}";
		
		Gson gson = new GsonBuilder().create();
		
		
		System.out.println("1. fromJson() 메서드");
		System.out.println("String => Object");
		
		Student student = gson.fromJson(jsonStr, Student.class);
		
		System.out.println("변경 전 : "+jsonStr);
		
		System.out.print("변경 후 : ");
		student.getInfo();
		System.out.println();

		
		System.out.println("2. toJson() 메서드");
		System.out.println("Object => String");
		
		String Str = gson.toJson(student);
		
		student.getInfo();
		System.out.println("변경 후 : "+Str);
		
		
	}
}


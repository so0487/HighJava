package basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
	/*
		Map ==> key값과 value값을 한 쌍으로 관리하는 객체
			- key값은 중복을 허용하지 않고 순서가 없다. (Set의 특징을 갖는다.)
			- value값은 중복을 허용한다.
	*/
		HashMap<String, String> map = new HashMap<>();
		
		// 자료 추가 ==> put(key값, value값);
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => " + map);
		
		// 자료 수정 ==> 데이터를 추가할 때 key값이 같으면 나중에 입력한 값이 저장된다.
		map.put("addr", "서울");
		System.out.println("map => " + map);
		
		// 자료 삭제 ==> remove(key값) : key값이 같은 자료를 찾아 삭제한다.
		//				  반환값 => 삭제된 자료의 value값
		//String removeTel = map.remove("tel");
		//System.out.println("map => " + map);
		//System.out.println("삭제된 값 :" + removeTel);
		
		// 자료 읽기 ==> get(key값)
		System.out.println("이름 : " + map.get("name"));
		System.out.println();
		
		// 키값이 존재하는지 여부를 나타내는 메서드 : containsKey(key값)
		//					==> 해당 key값이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재 여부 : " + map.containsKey("tel"));
		System.out.println("age 키값의 존재 여부 : " + map.containsKey("age"));
		System.out.println();
		
		// 맵에 저장된 모든 key값을 읽어와 맵 데이터를 사용하는 방법
		
		// 방법1. keySet()메서드 이용하기
		//		==> 맵의 모든 key값들을 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("--------------------------------------");
		
		// 방법2. keySet을 향상된 for문으로 사용하기
		//for(String key : map.keySet()){
		for(String key : keySet ){
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("--------------------------------------");
		
		
		// 방법3. ==> value값만 읽어와 처리하기
		//			 values()메서드를 이용한다.
		for(String value : map.values()){
			System.out.println(value);
		}
		System.out.println("--------------------------------------");
		
		/*
		 	방법4. ==> Map에는 Entry라는 내부class가 만들어져 있다.
					이 Entry클래스는 key와 value라는 멤버변수로 구성되어 있다.
					Map에서는 이 Entry클래스들을 Set형식으로 저장하여 관리한다.
					
			- Entry객체 전체를 가져오기(가져온 Entry들은 Set형식으로 되어 있다.)
				==> entrySet()메서드를 이용한다.
		*/
		
		// Entry라는 내부객체 전체 가져오기
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		while(entryIt.hasNext()){
			Map.Entry<String, String> entry = entryIt.next();  // Entry객체 1개 구하기
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		System.out.println("------------------------------------------");
		
	}

}








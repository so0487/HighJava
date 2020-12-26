package basic;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

public class PassWordSecurity {
	 public static void main(String args[]){
		  Frame f = new Frame();     //객체생성 및 초기화
		  
		  f.setTitle("텍스트필드 테스트");    //프레임 이름
		  Panel p = new Panel();     //객체생성 및 초기화

		  

		  TextField tf1 = new TextField("아이디 입력",20); //생성,초기화 및 텍스트가 나올상자의 사이즈 지정
		  TextField tf2 = new TextField("비밀번호 입력",10); //생성,초기화 및 텍스트가 나올상자의 사이즈 지정
		  
		  
		  tf1.selectAll();   //아이디의 입력된 모든 문자열 선택가능
		  tf2.selectAll();   //비밀번호의 입력된 모든 문자열 선택가능
		  
		  tf2.setEchoChar('*');  //비밀번호의 입력을 *모양으로 표시되도록 설정
		  
		  p.add(tf1);     //아이디 입력창을 패널에 추가
		  p.add(tf2);     //비밀번호 입력창을 패널에 추가
		  
		  f.add(p);     //패널을 프레임에 추가
		       
		  f.setSize(300, 300);  //패널의 가로 세로 크기를 설정
		  f.setVisible(true);   //나타내기
		}
}

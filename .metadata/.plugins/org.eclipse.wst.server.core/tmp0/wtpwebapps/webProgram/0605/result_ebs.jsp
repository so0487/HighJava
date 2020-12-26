<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ebs결과</title>
	</head>
	<body>
	<%
	//id
	String id = new String(request.getParameter("id").getBytes("8859_1"),"UTF-8");
// 	String id=request.getParameter("id");
	out.print("ID : "+id);
	
	%><br>
	
	<%
	//pwd
	String pwd = new String(request.getParameter("pwd").getBytes("8859_1"),"UTF-8");
	out.print("PW : "+pwd);
	%><br>
	
	<%
	//pwdcheck
	String pwdcheck = new String(request.getParameter("pwdcheck").getBytes("8859_1"),"UTF-8");
	out.print("패스워드 확인 : "+pwdcheck);
	%><br>
	
	
	<%
	//name
	String name = new String(request.getParameter("name").getBytes("8859_1"),"UTF-8");
	out.print("이름 : "+name);
	%><br>
	<%
		//gender&agree
		String gender = request.getParameter("gender");
		String agree = request.getParameter("agree");

		if (agree == null) {
			out.print("성별 : " + gender+"\n 정보제공 미동의");
		}else{
			out.print("성별 : "+gender+"\n 정보제공 동의");
		}
	%><br>
                             
	<%                     
	//date
	out.print("생년월일 : ");
	String bir_year = request.getParameter("bir_year");
	out.print(bir_year+"/");
	
	String bir_mon = request.getParameter("bir_mon");
	out.print(bir_mon+"/");
	
	String bir_day = request.getParameter("bir_day");
	out.print(bir_day);
	%><br>                 
		                   
	<%                     
	//email       
	out.print("email : ");
	String email = new String(request.getParameter("email").getBytes("8859_1"),"UTF-8");
	out.print(email);
	
	String domain = request.getParameter("domain");
	out.print("@"+domain);
	%><br>                 
	                       
	<%                     
	//tel     
	String tel = request.getParameter("tel");
	
	String phagree = request.getParameter("phagree");
	if (phagree == null) {
		out.print("전화번호 : " + tel+"\n 정보제공 미동의");
	}else{
		out.print("전화번호 : "+tel+"\n 정보제공 동의");
	}
	%><br>                 
	                       
	<%                     
	//check    
	out.print("인증방법 : ");
	String check = request.getParameter("check");
	out.print(check);
	%><br>                 
	                       
	<%                     
	//code
	out.print("인증번호 : ");
	String code = new String(request.getParameter("code").getBytes("8859_1"),"UTF-8");
	out.print(code);
	%> 

	</body>
</html>
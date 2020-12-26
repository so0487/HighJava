<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); //post타입%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>전송결과화면</title>
	</head>
	<body>
<%-- 		<!-- 
			1)get방식으로 데이터를 받는 경우
				기본 : <%String id=request.getParameter("id"); %>
				**getParameter("id")에 해당하는 부분은 html 입력태그에서 name값과 동일
				**한글이 깨져서 보일 경우 : new String(request.getParameter("id").getBytes("8859_1"),"UTF-8")
			2)post방식의 경우
				기본 : <%String id=request.getParameter("id"); %>
				**한글이 깨지는 경우 jsp파일의 html 상단에
				<%request.setCharacterEncoding("UTF-8"); //post타입%>
		 -->
--%>
		 
		 
		 
		<!-- 앞 단에서 보낸 데이터들을 가공해서 DB에 넣는다. -->
		
 		<!-- inputType.html과 연동-->
<%--  		
 		<%String id=request.getParameter("id"); %>
		<%String id_kr = new String(request.getParameter("id").getBytes("8859_1"),"UTF-8");//get타입 %>
		<%=id %>
		<%=id_kr %>
		<%String pw=request.getParameter("pw");
		out.print("브라우저 출력 : "+pw);
		System.out.println("콘솔출력 : "+pw);
		%>

 --%>
		
		
		
<		
		<!-- Copy of form_4.html과 연동 -->
		<%String id= new String(request.getParameter("id").getBytes("8859_1"),"UTF-8");%> <%=id %>
		<%String pwd=request.getParameter("pwd"); %><%=pwd %>
		<%String pwdcheck=request.getParameter("pwdcheck");%><%=pwdcheck %>
		<%String gender=request.getParameter("gender"); %><%=gender %>
		<%String hobby=request.getParameter("hobby"); %><%=hobby %>
		<%String date=request.getParameter("date"); %><%=date %>
		<%String age=request.getParameter("age"); %><%out.print(age); %>
		<%String email=request.getParameter("email"); %><%out.print(email); %>
		<%String memo=request.getParameter("meno"); %><%out.print(memo); %>
	
		
		
	</body>
</html>
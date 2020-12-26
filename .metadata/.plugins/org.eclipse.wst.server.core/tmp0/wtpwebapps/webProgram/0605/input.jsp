<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Input과제</title>
	</head>
	<body>
		<%
		out.print("이메일 : ");
		String email = request.getParameter("email");
		out.print(email);
		%><br>
		
		<%
		out.print("URL : ");
		String url = request.getParameter("url");
		out.print(url);
		%><br>
		
		<%
		out.print("전화번호 : ");
		String tel = request.getParameter("tel");
		out.print(tel);
		%><br>
		
		<%
		out.print("색상 : ");
		String color = request.getParameter("color");
		out.print(color);
		%><br>
		
		<%
		out.print("월 : ");
		String month=request.getParameter("month");
		out.print(month);
		%><br>
		
		<%
		out.print("날짜 : ");
		String date = request.getParameter("date");
		out.print(date);
		%><br>
		
		<%
		out.print("주 : ");
		String week=request.getParameter("week");
		out.print(week);
		%><br>
		
		<%
		out.print("시간 : ");
		String time=request.getParameter("time");
		out.print(time);
		%><br>
		
		<%
		out.print("지역시간 : ");
		String localtimedate=request.getParameter("localtimedate");
		out.print(localtimedate);
		%><br>
		
		<%
		out.print("숫자 : ");
		String number = request.getParameter("number");
		out.print(number);
		%><br>
		
		<%
		out.print("범위 : ");
		String range = request.getParameter("range");
		out.print(range);
		%>
	</body>
</html>
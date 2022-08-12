<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String r = request.getParameter("R");
		if(r.equals("OK")){
			out.print("게시글이 등록되었습니다.");
		}else if(r.equals("NOK")){
			out.print("게시글이 등록되지 않았습니다.");
			out.print("관리자에게 문의해주세요.");
		}
	%>
</body>
</html>
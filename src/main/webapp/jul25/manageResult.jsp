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
		String result = request.getParameter("R");
		if(result.equals("OK")){
			%>
			<script type="text/javascript">
				alert("작업이 완료되었습니다.");
				location.href="entryEmp.jsp"; //페이지 이동
			</script>
			<%
		}else {
			%>
			<script type="text/javascript">
				alert("작업 중 문제가 발생했습니다. 관리자에게 문의해 주세요.");
				location.href="entryEmp.jsp";//페이지 이동
			</script>
			<%
		}
	%>
</body>
</html>
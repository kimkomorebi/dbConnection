<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*, jul25.*"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>조 회 결 과</h2>
		<table border="1">
			<tr>
				<th>사번</th>
				<th>성</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>입사일</th>
				<th>업무코드</th>
				<th>월 급</th>
				<th>커미션</th>
				<th>관리자 사번</th>
				<th>부서번호</th>
				<th>수정/삭제</th>
			</tr>
			<%
				ArrayList al = (ArrayList)request.getAttribute("EMPS");
				int count = al.size(); // 데이터 갯수를 찾는다.
				for(int idx = 0; idx < count; idx++){
					Employee emp = (Employee)al.get(idx);
			%>
			<tr>
				<form action="manageEmp" method="post">
					<input type="hidden" value="<%= emp.getEmp_id() %>" name="ID"/>
					<td><a href="#">
					<%= emp.getEmp_id() %></a>
					</td>
					<td><%= emp.getFirst_name() %></td>
					<td><%= emp.getLast_name() %></td>
					<td><%= emp.getEmail() %></td>
					<td><%= emp.getPhone() %></td>
					<td><%= emp.getHire_date() %></td>
					<td><%= emp.getJob_id() %></td>
					<td><%= emp.getSalary() %></td>
					<td><%= emp.getComm() %></td>
					<td><%= emp.getManager_id() %></td>
					<td><%= emp.getDept_id() %></td>
					<td>
						<input type="submit" value="수정"/>
						<input type="submit" value="삭제"/>
					</td>
				</form>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>
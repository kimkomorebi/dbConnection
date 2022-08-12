<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import ="jul25.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function check(){
			if(confirm("작업을 진행하시겠습니까?")){
				return true;
			}else {
				return false;
			}
		}
	</script>
	<%
		Employee e = (Employee)request.getAttribute("EMP");
	%>
	<div align="center">
		<h2>사 원 상 세 정 보</h2>
		<form action="manageDo" method="post" onSubmit="return check()">
			<input type="hidden" name="ID" value="<%= e.getEmp_id()  %>"/>
			<table border="1">
				<tr><th>사 번</th><td><%= e.getEmp_id()  %></td></tr>
				<tr><th>성</th><td>
				<input type="text" name="FIRST"  value="<%= e.getFirst_name()  %>"/>
				</td></tr>
				<tr><th>이 름</th>
				<td>
					<input type="text" name="NAME" value="<%= e.getLast_name()  %>"/>
				</td>
				</tr>
				<tr><th>이메일</th>
					<td>
						<input type="text" name="EMAIL" value="<%= e.getEmail()  %>"/>
					</td>
				</tr>
				<tr><th>연락처</th>
					<td><input type="text" name="TEL" value="<%= e.getPhone()  %>"/></td>
				</tr>
				<tr><th>입사일</th>
					<td>
						<input type="date" name="HIRE" value="<%= e.getHire_date()  %>"/>
					</td>
				</tr>
				<tr><th>업무코드</th>
					<td>
						<input type="text" name="JOB" value="<%= e.getJob_id()  %>"/>
					</td>
				</tr>
				<tr><th>월 급</th>
					<td>
						<input type="text" name="SALARY" value="<%= e.getSalary()  %>"/>
					</td>
				</tr>
				<tr><th>커미션</th>
					<td>
						<input type="text" name="COMM" value="<%= e.getComm()  %>"/>
					</td>
				</tr>
				<tr><th>관리자 사번</th>
					<td>
						<input type="text" name="MAN" value="<%= e.getManager_id()  %>"/>
					</td>
				</tr>
				<tr><th>부서번호</th>
				<td>
					<input type="text" name="DEPT" value="<%= e.getDept_id()  %>"/>
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제" name="btn"/>
						<input type="submit" value="수정" name="btn"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
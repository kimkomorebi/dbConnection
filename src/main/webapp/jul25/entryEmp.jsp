<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h2>사원 정보 조회 ver1.0</h2>
	<form action="selectEmp" method="post">
		<table border="1">
			<tr><th>사번</th><td><input type="text" name="ID"/></td></tr>
			<tr><th>성</th><td><input type="text" name="FIRST"/></td></tr>
			<tr><th>이름</th><td><input type="text" name="LAST"/></td></tr>
			<tr><th>이메일</th><td><input type="text" name="EMAIL"/></td></tr>
			<tr><th>연락처</th><td><input type="text" name="TEL"/></td></tr>
			<tr><th>입사일</th><td><input type="date" name="HIRE"/></td></tr>
			<tr><th>업무코드</th>
				<td>
					<select name="JOB">
						<option>IT_PROG</option>
						<option>SA_REP</option>
					</select>
				</td>
			</tr>
			<tr><th>월급</th><td><input type="text" name="SALARY"/></td></tr>
			<tr><th>커미션</th><td><input type="text" name="COMM"/></td></tr>
			<tr><th>관리자 사번</th><td><input type="text" name="MAN"/></td></tr>
			<tr><th>부서번호</th><td><input type="text" name="DEPT"/></td></tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="조 회"/>
					<input type="reset"  value="취 소"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
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
	<h2>사원 정보 등록 ver1.0</h2>
	<form action="registerEmp" method="post" onSubmit="return check(this)">
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
			<tr><td colspan="2" align="center">
			<input type="submit" value="등 록" name="btn"/>
			<input type="submit" value="삭 제" name="btn"/>
			<input type="submit" value="변 경" name="btn"/>
			<input type="reset"  value="취 소"/></td></tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	function check(f){
		if(f.ID.value == ''){
			alert("사번을 입력하세요."); return false;
		}
		if(f.FIRST.value == ''){
			alert("성을 입력하세요."); return false;
		}
		if(f.LAST.value == ''){
			alert("이름을 입력하세요."); return false;
		}
		if(f.EMAIL.value == ''){
			alert("이메일을 입력하세요."); return false;
		}
		if(f.TEL.value == ''){
			alert("전화번호를 입력하세요."); return false;
		}
		if(f.HIRE.value == ''){
			alert("입사일을 입력하세요."); return false;
		}
		if(f.SALARY.value == ''){
			alert("월급을 입력하세요."); return false;
		}
		if(f.MAN.value == ''){
			alert("관리자 사번을 입력하세요."); return false;
		}
		if(f.DEPT.value == ''){
			alert("부서 번호를 입력하세요."); return false;
		}
		if(confirm("정말로 등록하시겠습니까?")){
			return true;
		}else {
			return false;
		}
	}
</script>
</body>
</html>
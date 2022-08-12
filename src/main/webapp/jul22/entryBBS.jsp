<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function check(f){
			if(f.ID.value == ''){
				alert("작성자를 입력하세요");
				return false;
			}
			if(f.TITLE.value == ''){
				alert("제목을 입력하세요");
				return false;
			}
			if(f.CONTENT.value ==  ''){
				alert("내용을 입력하세요.");
				return false;
			}
			if(confirm("작업을 진행하시겠습니까?")){
				return true;
			}else {
				return false;
			}
		}
	</script>
	<div align="center">
		<h2>게시글 작성 및 관리</h2>
		<form action="registerBBS" method="post"  name="frm">
			작성자 : <input type="text" name="ID" size="10"/><br/>
			제 목 : <input type="text" name="TITLE" size="20"/><br/>
			내 용 : <textarea rows="5" cols="30" name="CONTENT"></textarea>
			<br/>
			<br/>
			<input type="submit" value="등 록" name="btn"/>
			<input type="submit" value="수 정" name="btn"/>
			<input type="submit" value="삭 제" name="btn"/>
			<input type="submit" value="조 회" name="btn"/>
			<input type="reset" value="취 소" />
		</form>
	</div>
</body>
</html>
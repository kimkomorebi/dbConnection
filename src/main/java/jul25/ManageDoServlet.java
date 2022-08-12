package jul25;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageDoServlet
 */
@WebServlet("/jul25/manageDo")
public class ManageDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String id = request.getParameter("ID"); //사번을 수신
		String btn = request.getParameter("btn"); //버튼 제목을 수신
		String result = ""; //작업의 결과를 위한 변수 선언
		if(btn.equals("삭제")) {
			result = deleteEmp(id);
		}else if(btn.equals("수정")) {
			result = updateEmp(request);
		}
		response.sendRedirect("manageResult.jsp?R="+result);
		//작업의 결과 JSP 이동!
	}
	String updateEmp(HttpServletRequest req) {
		String id = req.getParameter("ID"); //사번 수신
		String f = req.getParameter("FIRST");//성 수신
		String l = req.getParameter("NAME");//이름 수신
		String email = req.getParameter("EMAIL");//이메일 수신
		String tel = req.getParameter("TEL");//연락처 수신
		String hire = req.getParameter("HIRE");//입사일 수신
		String job = req.getParameter("JOB"); //업무코드 수신
		String salary = req.getParameter("SALARY");//월급 수신
		String comm = req.getParameter("COMM");//커미션 수신
		String man = req.getParameter("MAN");//관리자 사번 수신
		String dept = req.getParameter("DEPT"); //부서번호 수신
		String update = "update my_empls set first_name=?,"+
		"last_name=?, email=?, phone_number=?, hire_date=to_date(?, 'YYYY/MM/DD'),"+
		"job_id=?, salary=?, commission_pct=?,"+
		"manager_id=?, department_id=? "+
		"where employee_id=?";
		Connection con = null;
		PreparedStatement  pstmt = null;
		String result = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, f);//성을 설정
			pstmt.setString(2, l);//이름을 설정
			pstmt.setString(3, email);//이메일을 설정
			pstmt.setString(4, tel);//전화번호 설정
			pstmt.setString(5, hire);//입사일 설정
			pstmt.setString(6, job);//업무코드 설정
			pstmt.setInt(7, Integer.parseInt(salary));//월급 설정
			pstmt.setDouble(8, Double.parseDouble(comm));//커미션 설정
			pstmt.setInt(9, Integer.parseInt(man));//관리자 사번 설정
			pstmt.setInt(10, Integer.parseInt(dept));//부서 번호 설정
			pstmt.setInt(11, Integer.parseInt(id));////사번 설정
			pstmt.executeUpdate();// update실행
			result ="OK";
		}catch(Exception e) {
			result = "NOK";
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {}
		}
		return result;
	}
	String deleteEmp(String empId) {
		String delete = "delete from my_empls where " + "employee_id = ? ";
		String result = "";
		Connection con = null; PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, Integer.parseInt(empId));
			pstmt.executeUpdate();//삭제 실행
			result = "OK";
		}catch(Exception e) {
			result = "NOK";
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {
		}
		return result;
	}
}
}
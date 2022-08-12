package jul25;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageEmpServlet
 */
@WebServlet("/jul25/manageEmp")
public class ManageEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageEmpServlet() {
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
		String id = request.getParameter("ID");
		//사번을 조건으로 사원정보를 검색한다.
		//String select = "select * from my_empls where " + "employee_id = "+id+" ";
		String select = "select employee_id, first_name, last_name, email, phone_number, to_char(hire_date, 'YYYY-MM-DD'), job_id, salary, commission_pct,"
				+ "manager_id, department_id from my_empls where employee_id="+id;
		String select2 = "select employee_id, first_name, last_name, email, phone_number, to_char(hire_date, 'YYYY-MM-DD'), job_id, salary, commission_pct,"
				+ "manager_id, department_id from my_empls where employee_id=?";
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Employee emp = new Employee();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			///////////////////Statement를 사용하는 경우
			stmt = con.createStatement();
			rs = stmt.executeQuery(select);
			//////////////////////////////////////////////
			/////////////////PreparedStatement를 사용하는 경우
			//pstmt = con.prepareStatement(select2);
			//pstmt.setInt(1, Integer.parseInt(id));
			//rs = pstmt.executeQuery();
			//////////////////////////////////////////////
			
			rs.next(); // 조회 결과로 이동
			
			emp.setEmp_id(rs.getInt(1)); //사번을 저장
			emp.setFirst_name(rs.getString(2));//성을 저장
			emp.setLast_name(rs.getString(3));//이름을 저장
			emp.setEmail(rs.getString(4));//이메일 저장
			emp.setPhone(rs.getString(5));//전화번호 저장
			emp.setHire_date(rs.getString(6));//입사일 저장
			emp.setJob_id(rs.getString(7)); //업무코드 저장
			emp.setSalary(rs.getInt(8));//월급 저장
			emp.setComm(rs.getDouble(9));//커미션 저장
			emp.setManager_id(rs.getInt(10));//관리자 사번 저장
			emp.setDept_id(rs.getInt(11));
		}catch(Exception e) {
			
		}finally {
			try {
				con.close(); stmt.close(); rs.close();
			}catch(Exception e) {}
		}
		request.setAttribute("EMP", emp);
		RequestDispatcher r = request.getRequestDispatcher("manageEmp.jsp");
		r.forward(request, response);
	}
	
}

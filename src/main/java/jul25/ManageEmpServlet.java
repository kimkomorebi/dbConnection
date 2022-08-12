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
		//����� �������� ��������� �˻��Ѵ�.
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
			///////////////////Statement�� ����ϴ� ���
			stmt = con.createStatement();
			rs = stmt.executeQuery(select);
			//////////////////////////////////////////////
			/////////////////PreparedStatement�� ����ϴ� ���
			//pstmt = con.prepareStatement(select2);
			//pstmt.setInt(1, Integer.parseInt(id));
			//rs = pstmt.executeQuery();
			//////////////////////////////////////////////
			
			rs.next(); // ��ȸ ����� �̵�
			
			emp.setEmp_id(rs.getInt(1)); //����� ����
			emp.setFirst_name(rs.getString(2));//���� ����
			emp.setLast_name(rs.getString(3));//�̸��� ����
			emp.setEmail(rs.getString(4));//�̸��� ����
			emp.setPhone(rs.getString(5));//��ȭ��ȣ ����
			emp.setHire_date(rs.getString(6));//�Ի��� ����
			emp.setJob_id(rs.getString(7)); //�����ڵ� ����
			emp.setSalary(rs.getInt(8));//���� ����
			emp.setComm(rs.getDouble(9));//Ŀ�̼� ����
			emp.setManager_id(rs.getInt(10));//������ ��� ����
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

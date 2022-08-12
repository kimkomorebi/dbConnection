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
		String id = request.getParameter("ID"); //����� ����
		String btn = request.getParameter("btn"); //��ư ������ ����
		String result = ""; //�۾��� ����� ���� ���� ����
		if(btn.equals("����")) {
			result = deleteEmp(id);
		}else if(btn.equals("����")) {
			result = updateEmp(request);
		}
		response.sendRedirect("manageResult.jsp?R="+result);
		//�۾��� ��� JSP �̵�!
	}
	String updateEmp(HttpServletRequest req) {
		String id = req.getParameter("ID"); //��� ����
		String f = req.getParameter("FIRST");//�� ����
		String l = req.getParameter("NAME");//�̸� ����
		String email = req.getParameter("EMAIL");//�̸��� ����
		String tel = req.getParameter("TEL");//����ó ����
		String hire = req.getParameter("HIRE");//�Ի��� ����
		String job = req.getParameter("JOB"); //�����ڵ� ����
		String salary = req.getParameter("SALARY");//���� ����
		String comm = req.getParameter("COMM");//Ŀ�̼� ����
		String man = req.getParameter("MAN");//������ ��� ����
		String dept = req.getParameter("DEPT"); //�μ���ȣ ����
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
			pstmt.setString(1, f);//���� ����
			pstmt.setString(2, l);//�̸��� ����
			pstmt.setString(3, email);//�̸����� ����
			pstmt.setString(4, tel);//��ȭ��ȣ ����
			pstmt.setString(5, hire);//�Ի��� ����
			pstmt.setString(6, job);//�����ڵ� ����
			pstmt.setInt(7, Integer.parseInt(salary));//���� ����
			pstmt.setDouble(8, Double.parseDouble(comm));//Ŀ�̼� ����
			pstmt.setInt(9, Integer.parseInt(man));//������ ��� ����
			pstmt.setInt(10, Integer.parseInt(dept));//�μ� ��ȣ ����
			pstmt.setInt(11, Integer.parseInt(id));////��� ����
			pstmt.executeUpdate();// update����
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
			pstmt.executeUpdate();//���� ����
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
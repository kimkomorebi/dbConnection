package jul25;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectEmpServlet
 */
@WebServlet("/jul25/selectEmp")
public class SelectEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectEmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String id = request.getParameter("ID");
		String first = request.getParameter("FIRST");
		String last = request.getParameter("LAST");
		String email = request.getParameter("EMAIL");
		String tel = request.getParameter("TEL");
		String hire = request.getParameter("HIRE");
		String job = request.getParameter("JOB");
		String salary = request.getParameter("SALARY");
		String comm = request.getParameter("COMM");
		String man = request.getParameter("MAN");
		String dept = request.getParameter("DEPT");
		String select = "select * from my_empls where 1=1 ";
		if(! id.equals("")) select = select + "and employee_id=" +id+ " ";
		if(! first.equals("")) select = select + "and first_name='" +first+ "' " ;
		if(! last.equals("")) select = select + "and last_name='"+last + "' ";
		if(! email.equals("")) select = select + "and email=' " +email+"' ";
		if(! tel.equals("")) select = select + "and phone_number' "+tel+ "' ";
		if(! hire.equals("")) select = select + "and to_char(hire_date, 'YYYY-MM-DD') ='" + hire + "' ";
		if(! job.equals("")) select = select + "and job_id='" + job+ "' ";
		if(! salary.equals("")) select = select + "and salary="+salary;
		if(! comm.equals("")) select = select + " and commission_pct+" + comm;
		if(! man.equals("")) select = select + " and manager_id"+man;
		if(! dept.equals("")) select = select + " and department_id" + dept +" ";
		System.out.println("������ ����: " + select);
		Connection con = null; Statement stmt = null;
		ResultSet rs = null; String result = "";
		ArrayList al = new ArrayList();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			stmt = con.createStatement();
			rs = stmt.executeQuery(select);
			while(rs.next()) {
				int a = rs.getInt("employee_id"); //���(�ѹ�)
				String b = rs.getString("first_name"); //��(���ڿ�)
				String c = rs.getString("last_name"); //�̸�(���ڿ�)
				String d = rs.getString("email"); //�̸���(���ڿ�)
				String e = rs.getString("phone_number"); //��ȭ��ȣ(���ڿ�)
				String f = rs.getString("hire_date"); //�Ի���(���ڿ�? ��¥?)
				String g = rs.getString("job_id"); // ���(���ڿ�)
				int h = rs.getInt("salary"); //����(�ѹ�)
				double i = rs.getDouble("commission_pct");//Ŀ�̼�(�Ǽ�)
				int j = rs.getInt("manager_id");//������ ���(����)
				int k = rs.getInt("department_id");//�μ���ȣ(����)
				
				//��ȸ����� Employee ��ü�� �����Ѵ�.
				Employee emp = new Employee();//DTO ����
				emp.setEmp_id(a); //����� ����
				emp.setFirst_name(b); //���� ����
				emp.setLast_name(c); //�̸��� ����
				emp.setEmail(d); //�̸����� ����
				emp.setPhone(e);//��ȭ��ȣ�� ����
				emp.setHire_date(f);//�Ի����� ����
				emp.setJob_id(g);//�����ڵ带 ����
				emp.setSalary(h);//������ ����
				emp.setComm(i);//Ŀ�̼��� ����
				emp.setManager_id(j);//������ ����� ����
				emp.setDept_id(k);//�μ���ȣ�� ����
				
				//ArrayList�� Employee ��ü�� �����Ѵ�.
				al.add(emp);
			}
		}catch(Exception e) {
			System.out.println("��ȸ �۾� �� ���� �߻�!");
			e.printStackTrace();
		}finally {
			try {
				con.close(); rs.close(); stmt.close();
			}catch(Exception e) {}
		}
		//�������� ��ȸ ���(empList.jsp)�� ��ȯ�Ѵ�.
		//Forward�� �ȴ�. ��? �����ϴ� �����Ͱ� ��ü�̹Ƿ�.
		//��ȸ ����� ����� ArrayList�� empList.jsp�� �����Ѵ�.
		request.setAttribute("EMPS", al);
		RequestDispatcher r = request.getRequestDispatcher("empList.jsp");
		r.forward(request, response);
	}

}

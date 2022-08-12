package jul21;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EntrtyEmpServlet
 */
@WebServlet("/jul21/entryEmp")
public class EntrtyEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntrtyEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String insert = "insert into my_empls values(";
		insert = insert + id+","+"'"+first+"','"+last+"','";
		insert = insert + email+"','"+tel+"',";
		insert = insert + "to_date('"+hire+"','YY/MM/DD'),";
		insert = insert + "'"+job+"',"+salary+",";
		if(comm.equals("")) {
			insert = insert + "null,";
		}else {
			insert = insert + comm+",";
		}
		insert = insert + man+","+dept+")";
		System.out.println(insert);
		Connection con = null; 
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			stmt = con.createStatement(); 
			stmt.executeUpdate(insert); //삽입, 삭제, 변경에 사용하는 메서드
			System.out.println("삽입 성공");
		}catch(Exception e) {
			System.out.println("DB연동이 실패!");
		}finally {
			try {
				con.close(); //접속을 해제한다.
				stmt.close(); //쿼리 실행 객체를 해제한다.
			}catch(Exception e) {
				}
		}
	}
}

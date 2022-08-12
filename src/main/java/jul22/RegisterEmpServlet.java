package jul22;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterEmpServlet
 */
@WebServlet("/jul22/registerEmp")
public class RegisterEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	void insertEmp(HttpServletRequest request) {
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
		Connection con = null;
		PreparedStatement pstmt = null;
		String insert = "insert into my_empls ";
		insert = insert + "values(?,?,?,?,?,to_date(?, 'YY/MM/DD'),?,?,?,?,?)";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(insert);
			//삽입 쿼리에 있는 물음표(?)를 실제 데이터로 바꾼다.
			pstmt.setInt(1, Integer.parseInt(id));
			pstmt.setString(2, first);
			pstmt.setString(3, last);
			pstmt.setString(4, email);
			pstmt.setString(5, tel);
			//String toDate = "to_date('"+hire+"','YY/MM/DD')";
			pstmt.setString(6, hire);
			pstmt.setString(7, job);
			pstmt.setInt(8, Integer.parseInt(salary));
			if(comm.equals("")) {
				pstmt.setString(9, null);
			}else {
				pstmt.setDouble(9, Double.parseDouble(comm));
			}
			pstmt.setInt(10, Integer.parseInt(man));
			pstmt.setInt(11, Integer.parseInt(dept));
			pstmt.executeUpdate(); //삽입,삭제,변경
			System.out.println("삽입 성공!");
		}catch(Exception e) {
			System.out.println("DB처리 중 문제 발생!");
			//e.printStackTrace();
		}finally {
			try {
				con.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
	}
	void deleteEmp(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		
		String delete = "delete from my_empls where employee_id=?";
		String id = request.getParameter("ID");
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, Integer.parseInt(id));
			pstmt.executeUpdate(); //delete 쿼리 실행
			System.out.println("삭제 완료!");
			
		}catch(Exception e) {
			System.out.println("삭제 작업 중 문제 발생!");
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {
				//사번과 이름을 받는다
				
			}
		}
	}
	void updateEmp(HttpServletRequest req) {
		
		String id = req.getParameter("ID");
		String name = req.getParameter("LAST");
		Connection con = null;
		PreparedStatement pstmt = null;
		String update = "update my_empls set last_name=? " + "where employee_id = ?";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(id));
			pstmt.executeUpdate();
			System.out.println("변경 완료!");
		}catch(Exception e) {
			System.out.println("변경 작업 중 문제 발생!");
			//e.printStackTrace();
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {
				
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String btn = request.getParameter("btn"); //버튼 제목 수신
		if(btn.equals("등 록")) {
			insertEmp(request);
		}else if(btn.equals("삭 제")) {
			deleteEmp(request);
		}else if(btn.equals("변 경")) {
			updateEmp(request);
		}
	}
}

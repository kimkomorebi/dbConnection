package jul22;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterBbsServlet
 */
@WebServlet("/jul22/registerBBS")
public class RegisterBbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterBbsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String btn = request.getParameter("btn");
		String result = "";
		String html = "";
		if(btn.equals("�� ��")) {
			result = insertBbs(request);
			html = "insertResult.jsp?R="+result;
		}else if(btn.equals("�� ��")) {
			result = updateBbs(request);
			html = "updateResult.jsp?R="+result;
		}else if(btn.equals("�� ��")) {
			result = deleteBbs(request);
			html = "deleteResult.jsp?R="+result;
		}else if(btn.equals("�� ȸ")) {
			result = selectBbs(request);
			html = "selectResult.jsp?R="+result;
		}
		response.sendRedirect(html);
	}
	String selectBbs(HttpServletRequest request) {
		String select = "select * from test_bbs where 1=1 ";
		String id = request.getParameter("ID");
		String title = request.getParameter("TITLE");
		String cont = request.getParameter("CONTENT");
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		if(! id.equals("")) select = select + "and id='"+id+"' ";
		if(! title.equals("")) select = select + "and title='" +title+"' ";
		if(! cont.equals("")) select = select + "and cont like '%"+cont+"'% ";
		
		ResultSet rs = null;
		String result = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			stmt = con.createStatement();
			rs = stmt.executeQuery(select);
			System.out.println("������ ����:"+select);
			System.out.println("�ۼ���     ������    �۳���");
			System.out.println("==================");
			while(rs.next()) {
				String idd = rs.getString("id");
				String titlee = rs.getString("title");
				String contt = rs.getString("content");
				System.out.println(idd+","+titlee+","+contt);
			}
			System.out.println("��ȸ�� �Ϸ�Ǿ����ϴ�!");
			result = "OK";
		}catch(Exception e) {
			result = "NOK";
		}finally {
			try {
				rs.close(); stmt.close(); con.close();
			}catch(Exception e) {}
		}
		return result;
	}
	String insertBbs(HttpServletRequest request) {
		String id = request.getParameter("ID");
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		Connection con = null;
		PreparedStatement pstmt = null;
		String insert = "insert into test_bbs ";
		insert = insert + "values(?,?,?)";
		String result = ""; //������ ����� ���� ����
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			//ȭ�� �ٲٱ� -  Redirect, Forward
			result = "OK";
			
			System.out.println("������ ���� ����");
		}catch(Exception e) {
			System.out.println("������ ���� ����");
			result = "NOK";
			e.printStackTrace();
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {
			}
		}
		return result;
	}
	String updateBbs(HttpServletRequest request) {
		String id = request.getParameter("ID");
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		Connection con = null;
		PreparedStatement pstmt = null;
		String update = "update test_bbs set content=? " + "where id=? and title=?"; 
		String result = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, content);
			pstmt.setString(2, id);
			pstmt.setString(3, title);
			pstmt.executeUpdate();
			result = "OK";
			System.out.println("������ ���� �Ϸ�");
		}catch(Exception e) {
			System.out.println("������ ���� ����");
			result ="NOK";
		}finally {
			try {
				con.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}
	String deleteBbs(HttpServletRequest request) {
		String id = request.getParameter("ID");
		String delete = "delete from test_bbs where id =?";
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe","hr","hr");
			pstmt = con.prepareStatement(delete);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			result = "OK";
		}catch(Exception e) {
			result = "NOK";
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {}
		}
		return result;
	}
}

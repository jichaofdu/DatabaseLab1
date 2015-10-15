package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;







import java.sql.Statement;

import util.JdbcUtil;

public class DatabaseDao {
	private JdbcUtil util;
	private static DatabaseDao databaseDao;
	private DatabaseDao(){
		util = JdbcUtil.getInstance();
	}
	
	/*本方法用于获取到messageDao*/
	public static DatabaseDao getInstance(){
		if(databaseDao == null){
			databaseDao = new DatabaseDao();
		}
		return databaseDao;
	}
	
	/**
	 * 解释：本方法用于在数据库中创建一个table,创建所需的sql指令通过sql变量传入
	 * @param sql 用于创建数据库的sql指令
	 */
	public void createTable(String sql){
		Connection conn = util.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("[Error] SQL Exception in create table");
		}finally{
			try {
				if(ps != null)   ps.close();
				if(conn != null) conn.close();	
			} catch (SQLException e) {
				System.out.println("[Error] SQL Exception in close ps and conn in create table");
			}
		}
	}
	/**
	 * 插入数据到room
	 * @param attributes
	 * @param values
	 */
	public void insertToRoom(String[] attributes,String[] values){
		String sql_1 = "insert into room(";
		String sql_2 = ") values(";
		String sql_3 = ");";
		
		for(int i = 0;i < 6;i++){
			sql_1 += attributes[i] + ",";
		}
		sql_1 = sql_1.substring(0, sql_1.length()-1);
		String sql = sql_1 + sql_2;
		Connection conn = util.getConnection();
		try {
			for(int i = 0;i <6;i++){
				if(attributes[i].equals("kdno") || attributes[i].equals("kcno")|| attributes[i].equals("ccno")||attributes[i].equals("seat")||attributes[i].equals("registno")){
					sql += (int)Double.parseDouble(values[i]) + ",";
				}else{
					sql += "'" + values[i] + "'" + ",";
				}
			}
			sql = sql.substring(0, sql.length()-1);
			sql = sql += sql_3;
			System.out.println(sql);
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null) conn.close();	
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}

	
	public void insertToStudent(String[] attributes,String[] values){
		String sql_1 = "insert into student(";
		String sql_2 = ") values(";
		String sql_3 = ");";
		
		for(int i = 0;i < 6;i++){
			sql_1 += attributes[i] + ",";
		}
		sql_1 = sql_1.substring(0, sql_1.length()-1);
		String sql = sql_1 + sql_2;
		Connection conn = util.getConnection();
		try {
			for(int i = 0;i <6;i++){
				if(attributes[i].equals("kdno") || attributes[i].equals("kcno")||attributes[i].equals("seat")||attributes[i].equals("registno")||attributes[i].equals("ccno")){
					sql += (int)Double.parseDouble(values[i]) + ",";
				}else{
					sql += "'" + values[i] + "'" + ",";
				}
			}
			sql = sql.substring(0, sql.length()-1);
			sql = sql += sql_3;
			System.out.println(sql);
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null) conn.close();	
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
}

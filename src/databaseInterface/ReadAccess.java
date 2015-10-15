package databaseInterface;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DatabaseDao;
import net.ucanaccess.jdbc.UcanaccessDriver;


public class ReadAccess {
	private Connection ucaConn;
	private ResultSet rs;
	private String[] attributesRoom = {"kdno","kcno","ccno","kdname","exptime","papername"};
	private String[] attributesStudent = {"registno","name","kdno","kcno","ccno","seat"};
	private DatabaseDao dao;
	private String[][] values;
 	
	public ReadAccess(){
		try {
			this.ucaConn = getUcanaccessConnection("E:\\数据库技术\\DB_LAB1\\oralexam.accdb");
			System.out.println("[Tip]成功与数据库建立连接");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao = DatabaseDao.getInstance();
	}
	
	/**
	 * 解释：从room中读取数据
	 */
	public void readDataFromRoom(){
		Statement st = null;
		try {
			try {
				st =this.ucaConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs=st.executeQuery("select * from room");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
	}
	
	/**
	 * 解释：从student中读取数据
	 */
	public void readDataFromStudent(){
		Statement st = null;
		try {
			try {
				st =this.ucaConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs=st.executeQuery("select * from student");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {

		}
	}
	
	public void saveRoom() throws SQLException{
		int num = 47;
		values = new String[num][6];
		int k = 0;
		while (rs.next()) {
			int j=rs.getMetaData().getColumnCount();
			for (int i = 1; i <=j ; ++i) {
				Object o = rs.getObject(i);
				values[k][i-1] = o.toString();
			}
			k++;
		}
		for(int i = 0;i < num;i++){
			dao.insertToRoom(attributesRoom, values[i]);
		}

	}
	
	public void saveStudent() throws SQLException{
		int num = 1815;
		values = new String[num][6];
		int k = 0;
		while (rs.next()) {
			int j=rs.getMetaData().getColumnCount();
			for (int i = 1; i <=j ; ++i) {
				Object o = rs.getObject(i);
				values[k][i-1] = o.toString();
			}
			k++;
		}
		for(int i = 0;i < num;i++){
			dao.insertToStudent(attributesStudent, values[i]);
		}

	}
	
	/**
	 * 解释：本方法用于建立连接
	 * @param pathNewDB
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	private static Connection getUcanaccessConnection(String pathNewDB) throws SQLException,IOException {
		String url = UcanaccessDriver.URL_PREFIX + pathNewDB+";newDatabaseVersion=V2003";
		return DriverManager.getConnection(url);
	}
	

	
	
	
  
}

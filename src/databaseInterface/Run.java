package databaseInterface;

import java.sql.SQLException;

public class Run {
	public static void main(String[] args){
		ReadSqlLine readSql = new ReadSqlLine();
//		readSql.readSqlFromTxt("E:\\数据库技术\\DB_LAB1\\RoomTableCreate.txt");
//		readSql.executeSql();
//		ReadAccess a = new ReadAccess();
//		a.readDataFromStudent();
//		ReadExcel a = new ReadExcel("room");
//		try {
//			a.saveToDatabaseRoom();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		readSql.readSqlFromTxt("E:\\数据库技术\\DB_LAB1\\StudentTableCreate.txt");
		readSql.executeSql();		
//		ReadExcel a = new ReadExcel("student");
//		try {
//			a.saveToDatabaseStudent();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		ReadExcel a = new ReadExcel("student");
		try {
			a.saveToDatabaseStudent();
		} catch (Exception e) {
			e.printStackTrace();
		}


		

	}
}

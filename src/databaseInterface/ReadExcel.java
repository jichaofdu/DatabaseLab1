package databaseInterface;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException; 
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.ss.usermodel.WorkbookFactory; 
import dao.DatabaseDao;

/**
 * 解释：本方法用于读取excel并提供将每条信息存入数据库的方法
 * @author 13302010019-冀超
 *
 */
public class ReadExcel {
	private String tableName;
	private String[] attributes;
	private String[][] values;
	private DatabaseDao dao;
	/**
	 * 解释：本构造方法用于向内存中读取excel
	 * @param fileName
	 */
	public ReadExcel(String fileName){
		dao = DatabaseDao.getInstance();
		try {
			tableName = fileName;
			InputStream inp = new FileInputStream("E:\\数据库技术\\DB_LAB1\\" + tableName + ".xls");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0); 
			int rowNum = sheet.getLastRowNum() + 1;
			int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
	        values = new String[rowNum - 1][columnNum];
	        attributes = new String[columnNum];
	        Row firstRow = sheet.getRow(0);
	        System.out.println(rowNum);
	        for(int i = 0;i < columnNum;i++){
	        	Cell cell = firstRow.getCell(i);
	        	attributes[i] = cell.toString();
	        }
			for(int i = 1;i < rowNum;i++){
				Row row = sheet.getRow(i);
		        for (int j = 0;j < columnNum;j++){ 
		        	Cell cell = row.getCell(j);
		        	if(!cell.toString().equals("")){
		        		 values[i-1][j] = cell.toString();
		        	}else{
		        		values[i-1][j] = "";
		        	}
		            System.out.println(values[i-1][j]);
		        } 
			}
		    inp.close(); 
		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			System.out.println("[Error] error in read excel constructor");
		} 
	}
	
	/**
	 * 解释：本方法用于向数据库中插入数据
	 */
	public void saveToDatabaseRoom(){
		for(int i = 0;i < values.length;i++){
			dao.insertToRoom(attributes, values[i]);
			System.out.println("[Tip] 已经插入一条");
		}
	}
	/**
	 * 解释：本方法用于向数据库中插入数据
	 */
	public void saveToDatabaseStudent(){
		for(int i = 0;i < values.length;i++){
			dao.insertToStudent(attributes, values[i]);
			System.out.println("[Tip] 已经插入一条");
		}
	}
}

package databaseInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import dao.DatabaseDao;

/**
 * 解释：本类用于读取txt文件中的数据库中的创建指令，然后在数据库中运行该指令
 * @author 13302010019 冀超
 *
 */
public class ReadSqlLine {
	private String sql;
	private DatabaseDao dao;
	
	public ReadSqlLine(){
		sql = "";
		dao = DatabaseDao.getInstance();
	}
	
	/**
	 * 解释：本方法用于从SQL中读取对应的sql语句
	 * @param filePath
	 */
	public void readSqlFromTxt(String filePath){
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
            InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(file));
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while( (lineTxt = bufferedReader.readLine()) != null){
	            	sql += lineTxt;
	            }
	            read.close();
	            System.out.println("[Tip] SQL指令已经读入。内容为：" + sql);
			} catch (IOException e) {
				System.out.println("[Error] SQL指令读取失败");
			}
		}else{
			System.out.println("[Error] 文件未成功读取");
		}
	}
	
	/**
	 * 解释：本方法用于执行SQL语句
	 */
	public void executeSql(){
		dao.createTable(sql);
	}
}

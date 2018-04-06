/**
 * 
 */
package araclar;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExceltoDB2 {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws SQLException {

		// C:\Users\TARIM\Desktop
		String fileName = "C:\\Users\\TARIM\\Desktop\\calisma.xlsx";
		Vector dataHolder = read(fileName);
		saveToDatabase(dataHolder);
	}

	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public static Vector read(String fileName) {
		Vector cellVectorHolder = new Vector();
		try {
			FileInputStream myInput = new FileInputStream(fileName);
			// POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			Iterator rowIter = mySheet.rowIterator();
			while (rowIter.hasNext()) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				// Vector cellStoreVector=new Vector();
				ArrayList list = new ArrayList();
				while (cellIter.hasNext()) {
					XSSFCell myCell = (XSSFCell) cellIter.next();
					list.add(myCell);
				}
				cellVectorHolder.addElement(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellVectorHolder;
	}

	@SuppressWarnings("rawtypes")
	private static void saveToDatabase(Vector dataHolder) throws SQLException {
		String yonetimListesi = "";
		String denetimListesi = "";

		System.out.println(dataHolder);
		Iterator iterator = dataHolder.iterator();

		int i = 1;
		while (iterator.hasNext()) {
			List list = (List) iterator.next();
			yonetimListesi = (list).get(0).toString().trim();

			// System.out.println("intibak_yili : " +"\n"+ intibak_yili);
			denetimListesi = (list).get(1).toString().trim();
			// System.out.println("kooperatif_adi : " +"\n"+ kooperatif_adi);

			try {
				Class.forName("org.postgresql.Driver").newInstance();
				Connection con = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/arazi_edindirme?charSet=UTF-8", "postgres", "1234");
				System.out.println("connection made...");
				PreparedStatement stmt = con
						.prepareStatement("INSERT INTO yonetim_kurulu(kooperatif_id,yonetimkurululist) VALUES(?,?)");
				PreparedStatement stmt2 = con
						.prepareStatement("INSERT INTO denetim_kurulu(kooperatif_id,denetimkurululist) VALUES(?,?)");

				stmt.setObject(1, i);
				stmt.setObject(2, yonetimListesi);
				stmt2.setObject(1, i);
				stmt2.setObject(2, denetimListesi);
				System.out.println("index : " + i);
				i++;
				stmt.executeUpdate();
				stmt2.executeUpdate();

				System.out.println("Data is inserted");
				stmt.close();
				stmt2.close();
				con.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

}

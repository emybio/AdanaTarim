/**
 * 
 */
package araclar;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExceltoDB {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String fileName = "C:\\Users\\EMRAHH\\Desktop\\calisma.xlsx";
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
	private static void saveToDatabase(Vector dataHolder) {
		String fesih_yili = "";
		String kooperatif_adi = "";
		String ortak_sayisi = "";
		String son_genel_kurul_tarihi = "";
		String uygulama_projesi = "";
		String kooperatif_ilce_id = "";
		String kooperatif_tur_id = "";
		String kurulus_yili = "";
		String adres = "";
		String telefon = "";
		System.out.println(dataHolder);

		String a = "2   ";
		int b = Integer.parseInt(a.trim());

		System.out.println("int to str : " + b);
		Iterator iterator = dataHolder.iterator();
		while (iterator.hasNext()) {
			List list = (List) iterator.next();
			fesih_yili = (list).get(0).toString().trim();
			kooperatif_adi = (list).get(1).toString().trim();
			ortak_sayisi = ((list.get(2).toString().trim()));
			int ortakSayisi = Integer.parseInt(ortak_sayisi);
			son_genel_kurul_tarihi = list.get(3).toString().trim();
			uygulama_projesi = list.get(4).toString().trim();

			kooperatif_ilce_id = list.get(5).toString().trim();
			int kooperatifIlce = Integer.parseInt(kooperatif_ilce_id);

			kooperatif_tur_id = list.get(6).toString().trim();
			int kooperatifTur = Integer.parseInt(kooperatif_tur_id);

			kurulus_yili = list.get(7).toString().trim();
			adres = list.get(8).toString().trim();
			telefon = list.get(9).toString().trim();

			try {
				Class.forName("org.postgresql.Driver").newInstance();
				Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exceltodb?charSet=UTF-8",
						"postgres", "1234");
				System.out.println("connection made...");
				PreparedStatement stmt = con.prepareStatement(
						"INSERT INTO koop(fesih_yili," + "kooperatif_adi," + "ortak_sayisi," + "son_genel_kurul_tarihi,"
								+ "uygulama_projesi," + "kooperatif_ilce_id," + "kooperatif_tur_id ," + "kurulus_yili ,"
								+ "adres ," + "telefon ) VALUES(?,?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, fesih_yili);
				stmt.setString(2, kooperatif_adi);
				stmt.setInt(3, (ortakSayisi));
				stmt.setString(4, son_genel_kurul_tarihi);
				stmt.setString(5, uygulama_projesi);
				stmt.setInt(6, kooperatifIlce);
				stmt.setInt(7, kooperatifTur);
				stmt.setString(8, kurulus_yili);
				stmt.setString(9, adres);
				stmt.setString(10, telefon);
				stmt.executeUpdate();

				System.out.println("Data is inserted");
				stmt.close();
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

/**
 * 
 */
package araclar;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class ExceltoDB {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

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
	private static void saveToDatabase(Vector dataHolder) {
		String intibak_yili = "";
		String kooperatif_adi = "";
		String ortak_sayisi = "";
		String son_genel_kurul_tarihi = "";
		String uygulama_projesi = "";
		String kooperatif_ilce_id = "";
		String kooperatif_tur_id = "";
		String kurulus_yili = "";
		String adres = "";
		String telefon = "";
		Date islemZamani = null;
		String islemYapan = "";
		boolean aktif;
		boolean dagilmis;
		String vergiNo = "";
		String durum = "";
		String koopBsk = "";
		System.out.println(dataHolder);
		Iterator iterator = dataHolder.iterator();
		while (iterator.hasNext()) {
			List list = (List) iterator.next();
			intibak_yili = (list).get(0).toString().trim();
			System.out.println("intibak_yili : " + intibak_yili);
			kooperatif_adi = (list).get(1).toString().trim();

			ortak_sayisi = ((list.get(2).toString().trim()));
			Double d4 = Double.parseDouble(ortak_sayisi);
			int ortakSayisi = d4.intValue();

			son_genel_kurul_tarihi = list.get(3).toString().trim();
			System.out.println("son_genel_kurul_tarihi : " + son_genel_kurul_tarihi);
			uygulama_projesi = list.get(4).toString().trim();

			kooperatif_ilce_id = list.get(5).toString().trim();

			Double d3 = Double.parseDouble(kooperatif_ilce_id);
			int kooperatifIlce = d3.intValue();

			kooperatif_tur_id = list.get(6).toString().trim();

			Double d = Double.parseDouble(kooperatif_tur_id);
			int kooperatifTur = d.intValue();

			kurulus_yili = list.get(7).toString().trim();
			System.out.println("kuruluþ yýlý : " + kurulus_yili);
			adres = list.get(8).toString().trim();
			telefon = list.get(9).toString().trim();
			islemZamani = null;
			islemYapan = list.get(10).toString().trim();
			Double d2 = Double.parseDouble(islemYapan);
			int islemYapan_id = d2.intValue();

			aktif = null != null;
			dagilmis = null != null;

			vergiNo = list.get(11).toString().toString().trim();
			// System.out.println("vergiNo : " + vergiNo);

			durum = list.get(12).toString().trim();
			koopBsk = list.get(13).toString().trim();
			try {
				Class.forName("org.postgresql.Driver").newInstance();
				Connection con = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/arazi_edindirme?charSet=UTF-8", "postgres", "1234");
				System.out.println("connection made...");
				PreparedStatement stmt = con.prepareStatement("INSERT INTO kooperatif(intibak_yili," + "kooperatif_adi,"
						+ "ortak_sayisi," + "son_genel_kurul_tarihi," + "uygulama_projesi," + "kooperatif_ilce_id,"
						+ "kooperatif_tur_id ," + "kurulus_yili ," + "adres ,"
						+ "telefon,islem_zamani,islem_yapan,aktif,dagilmis,vergi_no,durum,koop_bsk ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				stmt.setString(1, intibak_yili);
				stmt.setString(2, kooperatif_adi);
				stmt.setInt(3, (ortakSayisi));
				stmt.setString(4, son_genel_kurul_tarihi);
				stmt.setString(5, uygulama_projesi);
				stmt.setInt(6, kooperatifIlce);
				stmt.setInt(7, kooperatifTur);
				stmt.setString(8, kurulus_yili);
				stmt.setString(9, adres);
				stmt.setString(10, telefon);
				stmt.setDate(11, islemZamani);
				stmt.setInt(12, islemYapan_id);
				stmt.setBoolean(13, aktif);
				stmt.setBoolean(14, dagilmis);
				stmt.setString(15, vergiNo);
				stmt.setString(16, durum);
				stmt.setString(17, koopBsk);
				

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

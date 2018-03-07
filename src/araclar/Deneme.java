/**
 * 
 */
package araclar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimDurumu;

/**
 * @author Emrah Denizer
 *
 */
public class Deneme {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

	
		EkonomikYatirimDurumu durum = new EkonomikYatirimDurumu("Başvuru Yapıldı");
		EkonomikYatirimDurumu durum1 = new EkonomikYatirimDurumu("Değerlendirme Aşamasında");
		EkonomikYatirimDurumu durum2 = new EkonomikYatirimDurumu("Kabul Edildi");
		EkonomikYatirimDurumu durum3 = new EkonomikYatirimDurumu("Sözleşme İmzalandı");
		EkonomikYatirimDurumu durum4 = new EkonomikYatirimDurumu("Proje Tamamlandı");

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg2.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();

		session.save(durum);
		session.save(durum1);
		session.save(durum2);
		session.save(durum3);
		session.save(durum4);

		session.beginTransaction();
		System.out.println();
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("resource")
	public static void createDoc() throws IOException {

		// Blank Document
		XWPFDocument document = new XWPFDocument();

		XWPFStyles styles = document.createStyles();

		CTFonts fonts = CTFonts.Factory.newInstance();
		fonts.setEastAsia(null);
		fonts.setHAnsi(null);
		fonts.xgetEastAsia();
		fonts.setNil();

		styles.setDefaultFonts(fonts);

		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File("create_table.docx"));

		XWPFTable tableUstBaslik = document.createTable(5, 5);

		tableUstBaslik.setColBandSize(0);
		tableUstBaslik.setInsideHBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
		tableUstBaslik.setInsideVBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
		tableUstBaslik.setRowBandSize(0);

		XWPFTableRow tableRow = tableUstBaslik.getRow(0);
		tableRow.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow.getCell(1).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow.getCell(2).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow.getCell(3).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow.getCell(4).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");

		XWPFTableRow tableRow2 = tableUstBaslik.getRow(0);
		tableRow2.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow2.getCell(1).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow2.getCell(2).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow2.getCell(3).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");
		tableRow2.getCell(4).setText("GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I");

		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addBreak();
		run.addBreak();

		// create table
		XWPFTable table = document.createTable();

		// create first row
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("col one, row one");
		tableRowOne.addNewTableCell().setText("col two, row one");
		tableRowOne.addNewTableCell().setText("col three, row one");

		// create second row
		XWPFTableRow tableRowTwo = table.createRow();
		tableRowTwo.getCell(0).setText("col one, row two");
		tableRowTwo.getCell(1).setText("col two, row two");
		tableRowTwo.getCell(2).setText("col three, row two");

		// create third row
		XWPFTableRow tableRowThree = table.createRow();
		tableRowThree.getCell(0).setText("col one, row three");
		tableRowThree.getCell(1).setText("col two, row three");
		tableRowThree.getCell(2).setText("col three, row three");

		document.write(out);
		out.close();
		System.out.println("create_table.docx written successully");

	}
}
package araclar;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import forms.Arac;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * 
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {
	public static final String FONT = "d:/workspace/Arazi_Edindirme/araziedindirme/WebContent/WEB-INF/assets/fonts/times.ttf";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		List<Arac> listBooks = (List<Arac>) model.get("listBooks");
		System.out.println("listBooks : " + listBooks);
		//
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1257", !BaseFont.EMBEDDED);
		BaseFont bf1 = BaseFont.createFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font french = new Font(bf1, 12);
		BaseFont bf2 = BaseFont.createFont(FONT, BaseFont.CP1250, BaseFont.EMBEDDED);
		Font czech = new Font(bf2, 12);
		BaseFont bf3 = BaseFont.createFont(FONT, "Cp1251", BaseFont.EMBEDDED);
		Font russian = new Font(bf3, 12);

		Font font1 = new Font(bf, 10, Font.BOLD, BaseColor.WHITE);
		// define font for table header row
		Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		font.setColor(BaseColor.BLACK);

		doc.add(new Paragraph("T.C.", font1));
		doc.add(new Paragraph("ADANA VALÝLÝÐÝ", font));
		doc.add(new Paragraph("ÝL GIDA TARIM ve HAYVANCILIK MÜDÜRLÜÐÜ", font));

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 5.0f });
		table.setSpacingBefore(10);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setPadding(2);

		// write table header
		cell.setPhrase(new Phrase("Gün", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gidilen Yer", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gidiþ Saati", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Geliþ Saati", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Araç Plakasý", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Yapýlan Ýþin Özeti", font));
		table.addCell(cell);

		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		String text = cell.toString();
		text = TurkceKarakter(text.toString());

		System.out.println("text: " + text);

		// write table row data   

		for (Arac aBook : listBooks) {
			System.out.println(" PDF Ýslem Yapan : " + aBook.getIslemyapan().getAdi());
			table.addCell(new Phrase(String.valueOf(aBook.getDonemAy()), font));
			table.addCell((new Phrase(String.valueOf(aBook.getIlce().getIsim()), font)));
			table.addCell(aBook.getCikisSaati());
			table.addCell(aBook.getGirisSaati());

			if (!aBook.getResmiPlaka().isEmpty()) {
				table.addCell(aBook.getResmiPlaka());
			} else {
				table.addCell(aBook.getOzelPlaka());
			}

			table.addCell((new Phrase(String.valueOf(aBook.getAciklama()), font)));
		}

		doc.add(table);
		System.out.println(" garip : " + "\u015f");
	}

	public String TurkceKarakter(String text) {

		text = text.replace("Ý", "\u0130");

		text = text.replace("ý", "\u0131");

		text = text.replace("Þ", "\u015e");

		text = text.replace("þ", "\u015f");

		text = text.replace("Ð", "\u011e");

		text = text.replace("ð", "\u011f");

		text = text.replace("Ö", "\u00d6");

		text = text.replace("ö", "\u00f6");

		text = text.replace("ç", "\u00e7");

		text = text.replace("Ç", "\u00c7");

		text = text.replace("ü", "\u00fc");

		text = text.replace("Ü", "\u00dc");

		return text;
	}

}
package araclar;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;

public class ExcelBuilder extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<EkonomikYatirim> yatirimList = (List<EkonomikYatirim>) model.get("yatirimlar");

		Sheet sheet = workbook.createSheet("Ekonomik Yatýrýmlar");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Ýlçe");
		header.createCell(1).setCellValue("Yatýrým Konusu");
		header.createCell(2).setCellValue("Etap No");
		header.createCell(3).setCellValue("Yatýrýmcý Adý");
		header.createCell(4).setCellValue("Proje Adý");
		header.createCell(5).setCellValue("Proje Bedeli");
		header.createCell(6).setCellValue("Hibe Tutarý");
		header.createCell(7).setCellValue("Kapasite");
		header.createCell(8).setCellValue("Birim");
		header.createCell(9).setCellValue("Ýstihdam");
		header.createCell(10).setCellValue("Durum");

		int rowNum = 1;
		for (EkonomikYatirim yatirim : yatirimList) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(yatirim.getIlce().getIsim());
			row.createCell(1).setCellValue(yatirim.getKategori().getKategoriAdi());
			row.createCell(2).setCellValue(yatirim.getEtapNo());
			row.createCell(3).setCellValue(yatirim.getYatirimciAdi());

			row.createCell(4).setCellValue(yatirim.getProjeAdi());
			row.createCell(5).setCellValue(yatirim.getProjeBedeli());
			row.createCell(6).setCellValue(yatirim.getHibeTutari());
			row.createCell(7).setCellValue(yatirim.getKapasite());
			row.createCell(8).setCellValue(yatirim.getKapasiteBirim());
			row.createCell(9).setCellValue(yatirim.getIstihdam());
			row.createCell(10).setCellValue(yatirim.getDurum().getDurumAdi());
		}

	}

	@Override
	protected Workbook createWorkbook() {
		// TODO Auto-generated method stub
		return new XSSFWorkbook();
	}

}
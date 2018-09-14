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
import forms.kirsalkalkinma.gencciftci.GencCiftci;

@SuppressWarnings("unchecked")
public class ExcelBuilder extends AbstractXlsxView {
	@SuppressWarnings("unused")

	String[] kapasiteBirim = { "lt", "da", "buyukbas", "kucukbas", "ton", "adet/yýl", "kw/h", "kg", "kg/Yýl", "Ton/Yýl",
			"m&sup2;" };

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("rapor turu : " + Genel.raporTuru);
		List<EkonomikYatirim> yatirimList = (List<EkonomikYatirim>) model.get("yatirimlar");
		List<GencCiftci> gencCiftciList = (List<GencCiftci>) model.get("gencCiftci");
		if (yatirimList != null) {
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

			int lt = 0, da = 0, buyukbas = 0, kucukbas = 0, ton = 0, adetYýl = 0, kwh, kg, kgYýl, TonYýl = 0, m;

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

				for (String birim : kapasiteBirim)
					if (yatirim.getKapasiteBirim().equals(birim)) {

						TonYýl += yatirim.getKapasite();
					}
				row.createCell(7).setCellValue(yatirim.getKapasite());

				row.createCell(8).setCellValue(yatirim.getKapasiteBirim());
				row.createCell(9).setCellValue(yatirim.getIstihdam());
				row.createCell(10).setCellValue(yatirim.getDurum().getDurumAdi());

			}

			Row row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue("Toplam Proje Sayýsý : " + --rowNum);

			for (int i = 0; i < kapasiteBirim.length; i++) {

				row.createCell(0).setCellValue("Toplam " + kapasiteBirim[i] + --rowNum);
				row = sheet.createRow(++i);
			}
		}

		if (gencCiftciList != null) {
			Sheet sheet = workbook.createSheet("Genç Çiftçi");
			Row header = sheet.createRow(0);
			if (Genel.raporTuru == "tumListe") {
				header.createCell(0).setCellValue("Ýlce");
				header.createCell(6).setCellValue("Mahalle");
			} else {
				header.createCell(0).setCellValue("Mahalle");
			}

			header.createCell(1).setCellValue("Yatýrým Konusu");
			header.createCell(2).setCellValue("Yýl");
			header.createCell(3).setCellValue("Yatýrýmcý Adý");
			header.createCell(4).setCellValue("Hibe Tutarý");
			header.createCell(5).setCellValue("Kapasite");

			int rowNum = 1;
			for (GencCiftci gencCiftci : gencCiftciList) {

				String birim = gencCiftci.getKapasiteBirim();

				Row row = sheet.createRow(rowNum++);

				if (Genel.raporTuru == "ilce") {

					row.createCell(0).setCellValue(gencCiftci.getMahalle().getIsim());

				} else if (Genel.raporTuru == "tumListe") {

					row.createCell(0).setCellValue(gencCiftci.getMahalle().getTip().getIsim());
					row.createCell(6).setCellValue(gencCiftci.getMahalle().getIsim());
				}

				if (gencCiftci.getKategori().getTip().getTip().getIsim() == null
						&& gencCiftci.getKategori().getTip().getIsim() == null) {

					row.createCell(1).setCellValue(gencCiftci.getKategori().getIsim());

				} else if (gencCiftci.getKategori().getTip().getTip().getIsim() == null
						&& gencCiftci.getKategori().getTip().getIsim() != null) {

					row.createCell(1).setCellValue(
							gencCiftci.getKategori().getTip().getIsim() + "-" + gencCiftci.getKategori().getIsim());

				} else {

					row.createCell(1).setCellValue(gencCiftci.getKategori().getTip().getTip().getIsim() + "-"
							+ gencCiftci.getKategori().getTip().getIsim() + "-" + gencCiftci.getKategori().getIsim());

				}
				row.createCell(2).setCellValue(gencCiftci.getYil());

				if (gencCiftci.getYararlaniciSoyadi() != null) {
					row.createCell(3)
							.setCellValue(gencCiftci.getYararlaniciAdi() + " " + gencCiftci.getYararlaniciSoyadi());
				} else {
					row.createCell(3).setCellValue(gencCiftci.getYararlaniciAdi());
				}
				row.createCell(4).setCellValue(gencCiftci.getHibeTutari());
				row.createCell(5).setCellValue(
						gencCiftci.getKapasite() + " " + birim.substring(0, 1).toUpperCase() + birim.substring(1));
			}
		}
	}

	@Override
	protected Workbook createWorkbook() {
		// TODO Auto-generated method stub
		return new XSSFWorkbook();
	}

}
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

	String[] kapasiteBirim = { "lt", "da", "buyukbas", "kucukbas", "ton", "adet/yýl", "kw/h", "kg", "kg/Yýl", "Ton/Yýl",
			"m&sup2;" };

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("rapor turu : " + Genel.raporTuru);
		List<EkonomikYatirim> yatirimList = (List<EkonomikYatirim>) model.get("yatirimlar");
		List<GencCiftci> gencCiftciList = (List<GencCiftci>) model.get("gencCiftci");
		int rowNum = 1, lt = 0, da = 0, buyukbas = 0, kucukbas = 0, ton = 0, adetYýl = 0, kwh = 0, kg = 0, kgYýl = 0,
				TonYýl = 0, m = 0, bilinmeyen = 0, istihdam = 0, yatirimTutari = 0, hibeTutari = 0;

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
				istihdam += yatirim.getIstihdam();
				hibeTutari += yatirim.getHibeTutari();
				yatirimTutari += yatirim.getProjeBedeli();
				switch (yatirim.getKapasiteBirim()) {
				case "lt":
					lt += yatirim.getKapasite();
					break;
				case "da":
					System.out.println("Birim: " + da);
					da += yatirim.getKapasite();
					break;
				case "buyukbas":
					buyukbas += yatirim.getKapasite();
					break;
				case "kucukbas":
					kucukbas += yatirim.getKapasite();
					break;
				case "ton":
					ton += yatirim.getKapasite();
					break;
				case "adet/yýl":
					adetYýl += yatirim.getKapasite();
					break;
				case "kw/h":
					kwh += yatirim.getKapasite();
					break;
				case "kg":
					kg += yatirim.getKapasite();
					break;
				case "kg/Yýl":
					kgYýl += yatirim.getKapasite();
					break;
				case "Ton/Yýl":
					TonYýl += yatirim.getKapasite();
					break;
				case "m&sup2;":
					m += yatirim.getKapasite();
					break;
				default:
					bilinmeyen += yatirim.getKapasite();
					break;
				}
			}

			Row row = sheet.createRow(rowNum);
			System.out.println("rowNum : " + rowNum);
			row.createCell(0).setCellValue("Toplam Proje Sayýsý : " + --rowNum);
			System.out.println("rowNum : " + rowNum);
			Row rowYatirimTutari = sheet.createRow(rowNum);
			Row rowHibeTutari = sheet.createRow(rowNum);
			rowNum += 2;

			Row rowIstihdam = sheet.createRow(rowNum);
			Row rowlt = sheet.createRow(++rowNum);
			Row rowda = sheet.createRow(++rowNum);
			Row rowbuyukbas = sheet.createRow(++rowNum);
			Row rowkucukbas = sheet.createRow(++rowNum);
			Row rowton = sheet.createRow(++rowNum);
			Row rowadetYýl = sheet.createRow(++rowNum);
			Row rowkwh = sheet.createRow(++rowNum);
			Row rowkg = sheet.createRow(++rowNum);
			Row rowkgYýl = sheet.createRow(++rowNum);
			Row rowTonYýl = sheet.createRow(++rowNum);
			Row rowm = sheet.createRow(++rowNum);
			Row rowbilinmeyen = sheet.createRow(++rowNum);
			

			row.createCell(5)
					.setCellValue(String.format("%,8d%n", yatirimTutari));
			row.createCell(6).setCellValue(String.format("%,8d%n", hibeTutari));
			rowIstihdam.createCell(0).setCellValue("Toplam Ýstihdam : " + istihdam);
			rowlt.createCell(0).setCellValue("Toplam Litre : " + String.format("%,8d%n", lt));
			rowda.createCell(0).setCellValue("Toplam Dekar : " + String.format("%,8d%n", da));
			rowbuyukbas.createCell(0).setCellValue("Toplam Büyükbaþ : " + String.format("%,8d%n", buyukbas));
			rowkucukbas.createCell(0).setCellValue("Toplam Küçükbaþ : " + String.format("%,8d%n", kucukbas));
			rowton.createCell(0).setCellValue("Toplam Ton : " + String.format("%,8d%n", ton));
			rowadetYýl.createCell(0).setCellValue("Toplam adet/Yýl : " + String.format("%,8d%n", adetYýl));
			rowkwh.createCell(0).setCellValue("Toplam kw/h : " + String.format("%,8d%n", kwh));
			rowkg.createCell(0).setCellValue("Toplam Kg : " + String.format("%,8d%n", kg));
			rowkgYýl.createCell(0).setCellValue("Toplam kg/Yýl : " + String.format("%,8d%n", kgYýl));
			rowTonYýl.createCell(0).setCellValue("Toplam Ton/Yýl : " + String.format("%,8d%n", TonYýl));
			rowm.createCell(0).setCellValue("Toplam Metrekare : " + String.format("%,8d%n", m));
			rowbilinmeyen.createCell(0).setCellValue("Toplam Bilinmeyen : " + String.format("%,8d%n", bilinmeyen));

		}

		if (gencCiftciList != null)

		{
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
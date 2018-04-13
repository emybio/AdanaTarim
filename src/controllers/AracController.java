/**
 * 
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.json.simple.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import araclar.RolesEnum;
import forms.Arac;
import forms.Kullanici;
import forms.Yerler;
import service.AracService;
import service.AracTalepService;
import service.KullaniciService;
import service.YerEklemeService;

/**
 * @author Emrah Denizer
 *
 */
@Controller
@RequestMapping(value = "/arazi-cikislari")
public class AracController {
	@Autowired
	YerEklemeService yerEklemeService;
	@Autowired
	KullaniciService kullaniciService;
	@Autowired
	AracService aracService;
	@Autowired
	AracTalepService aracTalepService;

	private Arac arac2;

	public String dosyaDurumu = null;
	public String download = "";
	public Kullanici raporAlinanPersonelBilgileri = null;
	public List<Arac> cikisListesi1 = null;
	public List<Arac> gorevBulCikisListesi = null;
	public String donem = "bos";
	private String tusYazisi = "Kaydet";

	@SuppressWarnings("unused")
	@RequestMapping(value = "/arac-islemleri", method = RequestMethod.GET)
	public String aracTakip(ModelMap model, @CookieValue(value = "id", required = false) Long id,
			HttpServletRequest request, @ModelAttribute("arac") Arac arac1, BindingResult result) {

		Kullanici kullanici = null;
		if (id != null) {
			kullanici = kullaniciService.kullaniciGetirr(id);
		}
		if (result.hasErrors() || id == null) {
			return "redirect:/anasayfa";

		}
		if (arac2 == null) {

			arac2 = new Arac();

		} else {

			System.out.println("arac kullanici : " + arac2.getKullaniciList().iterator());
			model.put("kullanici", arac2.getKullaniciList());
			model.put("secilenMahalleID", arac2.getMahalle().getId());
			model.put("secilenMahalleIsim", arac2.getMahalle().getIsim());
		}
		if (dosyaDurumu != null) {

			model.put("dosyaDurumu", dosyaDurumu);
		}

		// model.put("url", url);
		model.put("arac", arac2);
		model.put("title", "Arazi Çıkışları");
		// model.put("kullaniciListesi",
		// kullaniciService.aktifKullaniciListesi('1'));
		model.put("errorMessage", araclar.Genel.errorMessage);

		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));
		model.put("download", download);
		model.put("saatler", araclar.Genel.saatler());
		model.put("aylar", aracService.donemAyGetir());
		model.put("yillar", aracService.donemYilGetir());
		model.put("tusYazisi", tusYazisi);

		model.put("girisYapanKullanici", kullaniciService.aktifKullaniciListesi(kullanici.getBirim(), '1'));

		download = "";
		dosyaDurumu = null;
		if (id != null) {

			if (kullanici.getRoles().getRollAdi().equals(araclar.RolesEnum.ROLE_SUPER_ADMIN.toString())
					|| kullanici.getRoles().getRollAdi().equals(araclar.RolesEnum.ROLE_AUTHORIZED_USER.toString())
					|| kullanici.getRoles().getRollAdi().equals(araclar.RolesEnum.ROLE_ADMIN.toString())) {

				if (gorevBulCikisListesi != null) {
					model.put("aracCikisListesi", gorevBulCikisListesi);

				} else {
					model.put("aracCikisListesi", aracService.tumAracCikislari());
				}

				model.put("kullaniciListesi", aracService.cikisYapanPersonelListesi());

			} else {
				model.put("kullaniciListesi2", kullaniciService.kullanGetir(id));
				if (donem != "bos") {

					model.put("aracCikisListesi", cikisListesi1);

				} else {

					if (gorevBulCikisListesi != null) {
						model.put("aracCikisListesi", gorevBulCikisListesi);
					} else {

						// for (int i = 0; i <
						// aracService.kullaniciyaGoreCikisListesi(id).size();
						// i++)
						// {
						//
						// for (int j = 0; j <
						// aracService.kullaniciyaGoreCikisListesi(id).get(i).getKullaniciList()
						// .size(); j++) {
						// System.out.println("*****************************************");
						// System.out.println(aracService.kullaniciyaGoreCikisListesi(id).get(i).getKullaniciList()
						// .get(j).getAdi());
						// System.out.println("*****************************************");
						// }
						// }
						model.put("aracCikisListesi", aracService.kullaniciyaGoreCikisListesi(id));
					}

				}
			}
			arac2 = null;
			tusYazisi = "Kaydet";
			araclar.Genel.errorMessage = null;
			gorevBulCikisListesi = null;
			return "AraziCikis/AracTakip";
		} else {
			return "redirect:/anasayfa";

		}

	}

	@RequestMapping(value = "/araziCikisEkle")
	public String araziCikisEkle(@ModelAttribute("arac") Arac arac, BindingResult result,
			@CookieValue(value = "id", required = true) Long id, HttpServletRequest request,
			@RequestParam(value = "kullaniciList") Long[] personelID) {
		List<Kullanici> kullaniciListesi = new ArrayList<>();
		Kullanici personel = new Kullanici();
		System.out.println("personelID : " + personelID);
		for (int i = 0; i < personelID.length; i++) {
			personel = kullaniciService.kullaniciGetirr(personelID[i]);
			kullaniciListesi.add(personel);

			System.out.println("personelID[i] : " + personelID[i]);

		}
		arac.setKullaniciList(kullaniciListesi);
		Kullanici islemyapan = new Kullanici();
		islemyapan.setId(id);
		String[] tarih = arac.getTarih().split("-");
		String tarihtekiYil = tarih[0];
		String tarihtekiAy = tarih[1];
		if (!arac.getOzelPlaka().equals("")) {

			arac.setResmiPlaka("");
		}
		arac.setDonemYil(Integer.valueOf(tarihtekiYil));
		arac.setDonemAy(Integer.valueOf(tarihtekiAy));
		arac.setIslemyapan(islemyapan);
		arac.setIslemZamani(new Date());

		try {

			if (arac.getId() == null && aracService.ayniGorevdenVarMi(arac.getMahalle().getId(), arac.getTarih(),
					arac.getCikisSaati(), arac.getGirisSaati())) {
				araclar.Genel.errorMessage = "hata";
				return "redirect:/arazi-cikislari/arac-islemleri";

			} else {
				aracService.save(arac);
			}

			if (result.hasErrors()) {

				System.out.println("BindingResult: " + arac);
			}

			return "redirect:/arazi-cikislari/arac-islemleri";
		} catch (Exception e) {

			String url1 = request.getRequestURI().toString();

			URL url = null;
			HttpURLConnection connection = null;
			try {
				url = new URL(url1);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				int code = connection.getResponseCode();
				System.out.println("hata kodu : " + code);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			System.err.println("*********" + e.getMessage());
			System.err.println(e);

			araclar.Genel.setErrorMessage(e.getMessage());
			return "redirect:/error";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/markageti", method = RequestMethod.POST)
	@ResponseBody
	public byte[] markaGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Yerler> altTipListesi = new ArrayList<Yerler>();
		altTipListesi = yerEklemeService.altTipGetir(altTipId, true);
		Iterator<Yerler> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			Yerler tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping(value = "/duzenle/{id}", method = RequestMethod.GET)
	public String duzenle(@PathVariable("id") Long id) {
		arac2 = aracService.aracCikisGetir(id);
		tusYazisi = "Güncelle";
		for (int i = 0; i < arac2.getKullaniciList().size(); i++) {
			System.out.println("AracController duzenle kullanici list- " + arac2.getKullaniciList().get(i).getAdi());
			System.out.println("AracController duzenle gidilen ilce ve mahalle- " + arac2.getIlce() + " "
					+ arac2.getMahalle().getIsim());
		}
		return "redirect:/arazi-cikislari/arac-islemleri";
	}

	@SuppressWarnings({ "resource", "unused" })
	@RequestMapping(value = "/raporAl", method = RequestMethod.GET)
	public String personelAraCikisRaporu(@RequestParam(value = "id", required = false) Long id,
			@CookieValue(value = "isim", required = true) String isim, HttpServletResponse response)
			throws ParseException, InvalidFormatException, IOException {
		List<Arac> cikisListesi = null;
		if (id == null) {
			cikisListesi = aracService.tumAracCikislari();

		} else {

			cikisListesi1 = aracService.kullaniciyaGoreCikisListesi(id);
			raporAlinanPersonelBilgileri = kullaniciService.kullaniciGetirr(id);
			return "redirect:/arazi-cikislari/araziCikislari";
		}

		String[] isimAyrac = isim.split("\\.");
		String ayrilanIsim = isimAyrac[0];
		String ayrilanSoyIsim = isimAyrac[1];
		String path = "D:\\evraklar\\";
		String filename = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase()
				+ ".docx"/* path to a file */;
		String baslikIsmi = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase();

		XWPFDocument document = new XWPFDocument();

		// ustbaslik logo
		// FileInputStream is = new FileInputStream(path + "bakanlik.png");
		// document.addPictureData(is, 0);

		// create table
		XWPFTable tableUst = document.createTable();
		tableUst.setCellMargins(10, 10, 10, 10);

		// Ã¼stbaÅŸlÄ±k oluÅŸturma
		XWPFTableRow tableUstBaslik = tableUst.getRow(0);
		tableUstBaslik.getCell(0)
				.setText("     GIDA TARIM VE HAYVANCILIK BAKANLIÄ�I                                            ");
		tableUstBaslik.addNewTableCell().setText("                                   ");
		tableUstBaslik.addNewTableCell().setText("                              " + baslikIsmi);
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addBreak();

		// tablo Ã§izgilerini siler
		tableUst.getCTTbl().getTblPr().unsetTblBorders();
		// create table
		XWPFTable table = document.createTable();
		// table.setCellMargins(10, 10, 10, 10);

		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");

		// create first row
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("GÃ¼nler");
		tableRowOne.addNewTableCell().setText("   Gidilen Yer   ");
		tableRowOne.addNewTableCell().setText("GidiÅŸ Saati");
		tableRowOne.addNewTableCell().setText("GeliÅŸ Saati");
		tableRowOne.addNewTableCell().setText("AraÃ§ PlakasÄ±  ");
		tableRowOne.addNewTableCell().setText("     YapÄ±lan Ä°ÅŸin Ã–zeti    ");
		tableRowOne.setHeight(10);

		for (int i = 0; i < cikisListesi.size(); i++) {
			String tamTarih = cikisListesi.get(i).getTarih();
			String[] gunAyraci = tamTarih.split("-");
			String gun = gunAyraci[2];

			// create second row
			XWPFTableRow tableRowTwo = table.createRow();

			tableRowTwo.getCell(0).setText(gun);
			tableRowTwo.getCell(1).setText(
					cikisListesi.get(i).getIlce().getIsim() + "-" + cikisListesi.get(i).getMahalle().getIsim());
			tableRowTwo.getCell(2).setText(cikisListesi.get(i).getCikisSaati());
			tableRowTwo.getCell(3).setText(cikisListesi.get(i).getGirisSaati());

			if (cikisListesi.get(i).getResmiPlaka() == null) {
				tableRowTwo.getCell(4).setText(cikisListesi.get(i).getOzelPlaka());
			} else {
				tableRowTwo.getCell(4).setText(cikisListesi.get(i).getResmiPlaka());
			}

			tableRowTwo.getCell(5).setText(cikisListesi.get(i).getAciklama());

		}

		for (int i = 0; i < 18 - cikisListesi.size(); i++) {

			// create blank row
			XWPFTableRow tableRowTwo = table.createRow();

			tableRowTwo.getCell(0).setText("");
			tableRowTwo.getCell(1).setText("");
			tableRowTwo.getCell(2).setText("");
			tableRowTwo.getCell(3).setText("");
			tableRowTwo.getCell(4).setText("");
			tableRowTwo.getCell(5).setText("");
			tableRowTwo.setHeight(5);
		}
		XWPFParagraph paragraph2 = document.createParagraph();
		XWPFRun run2 = paragraph2.createRun();

		// create table
		XWPFTable tableAlt = document.createTable();
		// table.setCellMargins(10, 10, 10, 10);

		// XWPFTableRow altTableRow1 = tableAlt.getRow(0);
		// altTableRow1.getCell(0).setText("GIDA TARIM VE HAYVANCILIK
		// BAKANLIÄ�I");
		// altTableRow1.addNewTableCell().setText("GIDA TARIM VE HAYVANCILIK
		// BAKANLIÄ�I");
		// altTableRow1.addNewTableCell().setText("GIDA TARIM VE HAYVANCILIK
		// BAKANLIÄ�I");

		XWPFTableRow altTableRow1 = tableAlt.createRow();
		altTableRow1.getCell(0).setText("Arazi GÃ¼n SayÄ±sÄ± " + cikisListesi.size() + " gÃ¼n" + "");
		altTableRow1.addNewTableCell().setText("Tasdik Olunur");

		XWPFTableRow altTableRow2 = tableAlt.createRow();
		altTableRow2.getCell(0).setText("2017/4 DÃ¶neminde TarafÄ±mdan YapÄ±lan Arazi Ã‡alÄ±ÅŸmalarÄ±na Ait Rapordur");
		altTableRow2.addNewTableCell().setText(" .../.../2017");

		XWPFTableRow altTableRow3 = tableAlt.createRow();
		altTableRow3.getCell(0).setText("");
		altTableRow3.addNewTableCell().setText("");

		XWPFTableRow altTableRow4 = tableAlt.createRow();
		altTableRow4.getCell(0).setText(baslikIsmi);
		altTableRow4.addNewTableCell().setText("Memmet OÄŸultekin");

		XWPFTableRow altTableRow5 = tableAlt.createRow();
		altTableRow5.getCell(0).setText("Tekniker");
		altTableRow5.addNewTableCell().setText("Å�ube MÃ¼dÃ¼rÃ¼");

		// tablo Ã§izgilerini siler
		tableAlt.getCTTbl().getTblPr().unsetTblBorders();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date tarih = new Date();
		// path +
		try {
			FileOutputStream out = new FileOutputStream(filename);
			document.write(out);
			out.close();
			dosyaDurumu = "Dosya Başarıyla Oluşturuldu...";
			download = "DOLU";

			// oluÅŸturulan dosyayÄ± indirme linki
			File file = new File(filename);

			response.setContentType(new MimetypesFileTypeMap().getContentType(file));
			response.setContentLength((int) file.length());
			response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
			try {

				InputStream is = new FileInputStream(file);
				FileCopyUtils.copy(is, response.getOutputStream());
				System.out.println("dosya indirildi");
				try {
					file.delete();
					System.out.println("dosya silindi");
				} catch (Exception e) {
					System.out.println("dosya silinemedi.. " + e);
				}

			} catch (Exception e) {
				System.out.println("dosya indirilemedi.. " + e);
			}

			return null;
		} catch (Exception e) {
			dosyaDurumu = "Dosya Oluşturma Başarısız.Lütfen Sitem Yöneticinizle Görüşün..." + e;
			e.printStackTrace();
			return "redirect:/arazi-cikislari/arac-islemleri";
		}

	}

	@RequestMapping(value = "/download")
	public String downloadPOIDoc(HttpServletResponse response, @CookieValue(value = "isim") String isim)
			throws IOException {
		String[] isimAyrac = isim.split("\\.");
		String ayrilanIsim = isimAyrac[0];
		String ayrilanSoyIsim = isimAyrac[1];
		String filename = ayrilanIsim.toUpperCase() + " " + ayrilanSoyIsim.toUpperCase()
				+ ".docx"/* path to a file */;
		String path = "D:\\evraklar\\";
		File file = new File(path + filename);

		response.setContentType(new MimetypesFileTypeMap().getContentType(file));
		response.setContentLength((int) file.length());
		response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));

		InputStream is = new FileInputStream(file);
		FileCopyUtils.copy(is, response.getOutputStream());

		return null;

	}

	@RequestMapping(value = "/araziCikislari")
	public String raporAlmaSayasi(ModelMap model) {
		switch (donem) {
		case "1":
			model.put("aylar", "Ocak");
			break;
		case "2":
			model.put("aylar", "Şubat");
			break;
		case "3":
			model.put("aylar", "Mart");
			break;
		case "4":
			model.put("aylar", "Nisan");
			break;
		case "5":
			model.put("aylar", "Mayıs");
			break;
		case "6":
			model.put("aylar", "Haziran");
			break;
		case "7":
			model.put("aylar", "Temmuz");
			break;
		case "8":
			model.put("aylar", "Ağustos");
			break;
		case "9":
			model.put("aylar", "Eylül");
			break;
		case "10":
			model.put("aylar", "Ekim");
			break;
		case "11":
			model.put("aylar", "Kasım");
			break;
		case "12":
			model.put("aylar", "Aralık");
			break;

		default:
			model.put("aylar", "Dönem Seçiniz..");

			break;
		}
		model.put("title", "Arazi Çıkışları");
		model.put("aracCikisListesi", cikisListesi1);
		model.put("kullanici", raporAlinanPersonelBilgileri);
		return "Raporlar/AraziCikis";
	}

	@RequestMapping(value = "/donemeGoreGetir", method = RequestMethod.GET)
	public String donemeGoreAracCikisi(@CookieValue(value = "id") Long id, ModelMap model,
			@RequestParam(value = "id") Long kullaniciID, @RequestParam(value = "donemAy") Integer donemAy,
			@RequestParam(value = "donemYil") Integer donemYil) {

		List<Arac> donemeGoreAracCikislari = aracService.kullaniciyaGoreCikisListesi(kullaniciID, donemAy, donemYil);
		if (!donemeGoreAracCikislari.isEmpty()) {

			cikisListesi1 = donemeGoreAracCikislari;
			model.put("kullanici", raporAlinanPersonelBilgileri = kullaniciService.kullaniciGetirr(kullaniciID));
			model.put("aracCikisListesi", cikisListesi1);

			donem = donemAy.toString();
			model.put("arac", new Arac());
			// return "AraziCikis/AracTakip";
			return "redirect:/arazi-cikislari/araziCikislari";
		} else {

			if (id == 1 || id == 7 || id == 9) {
				// model.put("girisYapanKullanici",
				// kullaniciService.aktifKullaniciListesi('1'));
				// model.put("kullaniciListesi",
				// aracService.cikisYapanPersonelListesi());
			} else {

				model.put("kullaniciListesi", kullaniciService.kullanGetir(id));
				model.put("girisYapanKullanici", kullaniciService.kullanGetir(id));

			}
			model.put("tusYazisi", tusYazisi);
			model.put("title", "Hata");
			model.put("arac", new Arac());
			model.put("aylar", aracService.donemAyGetir());
			model.put("yillar", aracService.donemYilGetir());
			// model.put("girisYapanKullanici",
			// kullaniciService.aktifKullaniciListesi(donem, '1'));
			araclar.Genel.errorMessage = "hata";
			model.put("errorMessage2", araclar.Genel.errorMessage);
			return "AraziCikis/AracTakip";
		}
	}

	@RequestMapping(value = "/cikisRaporu")
	public String cikisRaporu(@CookieValue(value = "id", required = false) Long id, ModelMap model) {

		if (id == null || cikisListesi1 == null || cikisListesi1.isEmpty()) {

			return "redirect:/arazi-cikislari/arac-islemleri";
		}
		model.put("title", "Görev Dönüş Raporu ");
		model.put("aracCikisListesi", cikisListesi1);

		return "Raporlar/GorevDonusRaporu";
	}

	@RequestMapping(value = "/gorevDonusRaporuYazdir", method = RequestMethod.GET)
	public String gorevDonusRaporu(@CookieValue(value = "id", required = false) Long id,
			@RequestParam(value = "id", required = false) Long cikisId, ModelMap model) {

		cikisListesi1 = aracService.idCikisListesi(cikisId);

		if (id == null) {

			return "redirect:/";
		}

		if (cikisListesi1 == null || cikisListesi1.isEmpty()) {
			model.put("errors", "LÜTFEN SEÇİM YAPINIZ");
			model.put("arac", new Arac());
			model.put("aracCikisListesi", aracService.tumAracCikislari());
			return "AraziCikis/AracTakip";
		}
		model.put("title", "Görev Dönüş Raporu ");
		model.put("aracCikisListesi", cikisListesi1);

		return "Raporlar/GorevDonusRaporu";// return
											// "redirect:/arazi-cikislari/cikisRaporu";
	}

	@RequestMapping(value = "/tipsil", method = RequestMethod.POST)
	public @ResponseBody String tipsil(@RequestParam(value = "id", required = true) Long id,
			@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response,
			HttpServletRequest request) {

		aracService.delete(id);

		response.setCharacterEncoding("UTF-8");
		hitCounter++;

		// create cookie and set it in response

		return "{}";
	}

	@RequestMapping(value = "/vazgec")
	public String vazgec(@ModelAttribute("arac") Arac arac) {
		// arac.getMahalle().getId());
		arac.setId(0l);
		arac.setMahalle(null);
		return "redirect:/arazi-cikislari/arac-islemleri";
	}

	@RequestMapping(value = "/gorevBul")
	public String gorevBul(ModelMap model, @RequestParam(value = "tarih") String tarih,
			@RequestParam(value = "plaka") String plaka, @CookieValue(value = "id", required = true) Long kullaniciId) {

		Kullanici kullanici = kullaniciService.kullaniciGetirr(kullaniciId);

		if (kullanici.getRoles().getRollAdi().equals(RolesEnum.ROLE_SUPER_ADMIN.toString())) {

			gorevBulCikisListesi = aracService.gorevBul(plaka, tarih);

		} else {

			gorevBulCikisListesi = aracService.kullaniciyaGorevBul(plaka, tarih, kullaniciId);
		}

		return "redirect:/arazi-cikislari/arac-islemleri";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewHome() {
		return "Raporlar/home";
	}

	@RequestMapping(value = "/donemeGorePDFGetir", method = RequestMethod.GET)
	public ModelAndView donemeGoreAracCikisiPDF(@CookieValue(value = "id") Long id, ModelMap model,
			@RequestParam(value = "id") Long kullaniciID, @RequestParam(value = "donemAy") Integer donemAy,
			@RequestParam(value = "donemYil") Integer donemYil) {

		List<Arac> donemeGoreAracCikislari = new ArrayList<Arac>();

		List<Arac> listBooks = aracService.kullaniciyaGoreCikisListesi(kullaniciID, donemAy, donemYil);

		for (Arac a : listBooks) {

			donemeGoreAracCikislari.add(new Arac(a.getIslemyapan().getAdi(), a.getResmiPlaka(), a.getOzelPlaka(),
					a.getIlce().getIsim(), a.getMahalle().getIsim(), a.getCikisSaati(), a.getGirisSaati(),
					a.getAciklama(), a.getIslemZamani(), a.getDonemYil(), a.getDonemAy()));

		}

		return new ModelAndView("pdfView", "listBooks", listBooks);

	}

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		// create some sample data
		List<Arac> listBooks = new ArrayList<Arac>();
		Kullanici kullanici = new Kullanici();

		kullanici.setAdi("Ğğ İı Öö Üüu Şş Çç");

		Yerler ilce = new Yerler();

		ilce.setIsim("Çukurova");

		listBooks.add(new Arac(kullanici, "1", "asd", "asd", ilce));
		listBooks.add(new Arac(kullanici, "2", "xyz", "asd", ilce));
		listBooks.add(new Arac(kullanici, "3", "ghj", "asd", ilce));
		listBooks.add(new Arac(kullanici, "4", "hjkh", "asd", ilce));
		listBooks.add(new Arac(kullanici, "5", "jkhjk", "asd", ilce));
		listBooks.add(new Arac(kullanici, "6", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "7", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "8", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "9", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "10", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "11", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "12", "dfg", "asd", ilce));

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "listBooks", listBooks);
	}

	@RequestMapping("/pdfExport")
	public ModelAndView pdfExport(HttpServletResponse response) {

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=exportMy.pdf");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("pdfView");
		mv.addObject("listBooks", preparePersons());
		return mv;
	}

	public List<Arac> preparePersons() {
		List<Arac> listBooks = new ArrayList<Arac>();
		Kullanici kullanici = new Kullanici();

		kullanici.setAdi("Ğğ İı Öö Üüu Şş Çç");

		Yerler ilce = new Yerler();

		ilce.setIsim("Çukurova");

		listBooks.add(new Arac(kullanici, "1", "asd", "asd", ilce));
		listBooks.add(new Arac(kullanici, "2", "xyz", "asd", ilce));
		listBooks.add(new Arac(kullanici, "3", "ghj", "asd", ilce));
		listBooks.add(new Arac(kullanici, "4", "hjkh", "asd", ilce));
		listBooks.add(new Arac(kullanici, "5", "jkhjk", "asd", ilce));
		listBooks.add(new Arac(kullanici, "6", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "7", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "8", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "9", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "10", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "11", "dfg", "asd", ilce));
		listBooks.add(new Arac(kullanici, "12", "dfg", "asd", ilce));

		return listBooks;
	}
}

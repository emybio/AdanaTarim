package controllers.kirsalkalkinma;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import araclar.Birimler;
import araclar.Genel;
import forms.Kullanici;
import forms.Yerler;
import forms.kirsalkalkinma.gencciftci.GencCiftci;
import forms.kirsalkalkinma.gencciftci.GencCiftciKategori;
import service.YerEklemeService;
import service.kirsalkalkinma.GencCiftciKategoriService;
import service.kirsalkalkinma.GencCiftciService;

@Controller
@RequestMapping(value = "/kirsal-kalkinma")
public class GenCiftciController {

	@Autowired
	GencCiftciKategoriService yerEklemeService;
	@Autowired
	YerEklemeService ilceler;

	@Autowired
	GencCiftciService gencCiftciService;
	private GencCiftciKategori tips;

	private GencCiftci gencCiftci;

	private String tusYazisi = "Ekle";

	@RequestMapping(value = "/genc-ciftci", method = RequestMethod.GET)
	public String gencCiftci(@CookieValue(value = "id", required = false) Long id, ModelMap model,
			@ModelAttribute("gencCiftci") GencCiftci gencCiftci1, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) {
		if (gencCiftci == null) {

			gencCiftci = new GencCiftci();
		}

		if (tips == null) {
			tips = new GencCiftciKategori();
		} else {
			if (tips.getTip() != null)
				if (tips.getTip().getTip() == null) // hiï¿½ bir ï¿½ey seï¿½ili
													// deï¿½il
													// misal : bilgisayar
				{
					// System.out.println("bilgisayar seï¿½ili");
					// model.put("altTipListesi",
					// sabitTipsService.altTipGetir(tips.getId(), true));
					tips.getTip().setTip(new GencCiftciKategori(0l));
					tips.getTip().getTip().setTip(new GencCiftciKategori(tips.getTip().getId()));
					tips.getTip().setId(0);
					@SuppressWarnings("rawtypes")
					List sonuc = yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true);
					model.put("altTipListesi", sonuc);
					model.put("modelListesi", sonuc);
					// tips.getTip().getTip().getTip().setId(tips.getId());
				} else if (tips.getTip().getTip().getTip() == null)// bilgisayar
																	// seï¿½ili
																	// dizï¿½stï¿½
																	// seï¿½ili
																	// deï¿½il
				{
					tips.getTip().getTip().setTip(new GencCiftciKategori(tips.getTip().getTip().getId()));
					tips.getTip().getTip().setId(tips.getTip().getId());
					tips.getTip().setId(0);
					// Systgfvem.out.println("dizï¿½stï¿½ seï¿½ili");
					// System.out.println(sabitTipsService.tipsGetir(tips.getTip().getTip().getTip().getId()).getIsim());
					@SuppressWarnings("rawtypes")
					List sonuc = yerEklemeService.altTipGetir(tips.getTip().getTip().getId(), true);
					model.put("altTipListesi",
							yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true));
					model.put("markaListesi", sonuc);
					model.put("modelListesi", sonuc);
				} else {
					// System.out.println("acer seï¿½ili");
					model.put("altTipListesi",
							yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true));
					model.put("markaListesi", yerEklemeService.altTipGetir(tips.getTip().getTip().getId(), true));
					model.put("modelListesi", yerEklemeService.altTipGetir(tips.getTip().getId(), true));
				}
		}
		model.put("title", "Genc Çiftçi Kategori");
		model.put("tips", tips);

		model.put("tipListesi", yerEklemeService.tipGetir());// MOBï¿½LYA VEYA
																// Bï¿½LGï¿½SAYAR
		model.put("ilceListesi", ilceler.altTipGetir(2l, true));
		model.put("gencCiftci", gencCiftci);
		// model.put("gencCiftci", gencCiftciKategori);
		model.put("title", "Genç Çiftçi");
		model.put("tusYazisi", tusYazisi);
		tusYazisi = "Ekle";

		return "KirsalKalkinma/GencCiftci";
	}

	@RequestMapping(value = "/gencCiftciEkle", method = RequestMethod.POST)
	public String gencCiftciEkle(@RequestParam("mahalle") Long mahalle, @RequestParam("slctTipler") Long slctTipler,
			@RequestParam("slctAltTip") Long slctAltTip, ModelMap model,
			@ModelAttribute("gencCiftci") GencCiftci gencCiftci1, BindingResult result, HttpSession session) {
		System.out.println("slctTipler : " + slctTipler + "\n" + "slctAltTip : " + slctAltTip + "\n" + "kategori : "
				+ gencCiftci1.getKategori().getId());

		String birim = session.getAttribute("birim").toString();

		if (birim == null || !birim.equals(Birimler.KIRSAL_KALKINMA)) {

			return "redirect:/";

		}
		if (result.hasErrors()) {

			System.err.println("genc ciftci : " + result.getFieldError());
		}
		Yerler yer = new Yerler();
		yer.setId(mahalle);
		gencCiftci1.setMahalle(yer);

		if (gencCiftci1.getKategori().getId() == null || gencCiftci1.getKategori().getId() == 0l) {

			if (slctAltTip == null || slctAltTip == 0) {
				System.out.println("kategori boþ 2");
				GencCiftciKategori kat = new GencCiftciKategori();

				kat.setId(slctTipler);

				gencCiftci1.setKategori(kat);
			} else {

				GencCiftciKategori kat = new GencCiftciKategori();
				kat.setId(slctAltTip);
				gencCiftci1.setKategori(kat);
				System.out.println("kategori boþ");
			}
		}

		gencCiftciService.save(gencCiftci1);
		gencCiftci = null;
		tusYazisi = "Ekle";
		return "redirect:/kirsal-kalkinma/genc-ciftci";
	}

	@RequestMapping(value = "/sabitler")
	public String sabitlers(HttpSession session, ModelMap model, @CookieValue(value = "id", required = true) Long id) {
		model.put("modelListesi", yerEklemeService.tipGetir());
		if (tips == null) {
			tips = new GencCiftciKategori();
		} else {
			if (tips.getTip() != null)
				if (tips.getTip().getTip() == null) // hiï¿½ bir ï¿½ey seï¿½ili
													// deï¿½il
													// misal : bilgisayar
				{
					// System.out.println("bilgisayar seï¿½ili");
					// model.put("altTipListesi",
					// sabitTipsService.altTipGetir(tips.getId(), true));
					tips.getTip().setTip(new GencCiftciKategori(0l));
					tips.getTip().getTip().setTip(new GencCiftciKategori(tips.getTip().getId()));
					tips.getTip().setId(0);
					@SuppressWarnings("rawtypes")
					List sonuc = yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true);
					model.put("altTipListesi", sonuc);
					model.put("modelListesi", sonuc);
					// tips.getTip().getTip().getTip().setId(tips.getId());
				} else if (tips.getTip().getTip().getTip() == null)// bilgisayar
																	// seï¿½ili
																	// dizï¿½stï¿½
																	// seï¿½ili
																	// deï¿½il
				{
					tips.getTip().getTip().setTip(new GencCiftciKategori(tips.getTip().getTip().getId()));
					tips.getTip().getTip().setId(tips.getTip().getId());
					tips.getTip().setId(0);
					// Systgfvem.out.println("dizï¿½stï¿½ seï¿½ili");
					// System.out.println(sabitTipsService.tipsGetir(tips.getTip().getTip().getTip().getId()).getIsim());
					@SuppressWarnings("rawtypes")
					List sonuc = yerEklemeService.altTipGetir(tips.getTip().getTip().getId(), true);
					model.put("altTipListesi",
							yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true));
					model.put("markaListesi", sonuc);
					model.put("modelListesi", sonuc);
				} else {
					// System.out.println("acer seï¿½ili");
					model.put("altTipListesi",
							yerEklemeService.altTipGetir(tips.getTip().getTip().getTip().getId(), true));
					model.put("markaListesi", yerEklemeService.altTipGetir(tips.getTip().getTip().getId(), true));
					model.put("modelListesi", yerEklemeService.altTipGetir(tips.getTip().getId(), true));
				}
		}
		model.put("title", "Genc Çiftçi Kategori");
		model.put("tips", tips);

		model.put("tipListesi", yerEklemeService.tipGetir());// MOBï¿½LYA VEYA
																// Bï¿½LGï¿½SAYAR
		model.put("tusYazisi", tusYazisi);
		return "KirsalKalkinma/sabitler";
	}

	@RequestMapping(value = "/sabitonay", method = RequestMethod.POST)
	public String ekleme(HttpSession session, HttpServletResponse response,
			@ModelAttribute("tips") GencCiftciKategori yer, @ModelAttribute("kullanici") Kullanici kullanici,
			@CookieValue(value = "id", required = true) Long id) {

		if (yer.getTip().getTip().getTip().getId() == 0)

		{
			yer.setTip(null);
		} else if (yer.getTip().getTip().getId() == 0) {
			yer.setTip(new GencCiftciKategori(yer.getTip().getTip().getTip().getId()));
		} else if (yer.getTip().getId() == 0) {
			yer.setTip(new GencCiftciKategori(yer.getTip().getTip().getId()));
		}
		yer.setEklemezamani(new Date());
		yerEklemeService.ekle(yer);
		tips = yer;
		tips.setId(0);
		tusYazisi = "Kaydet";
		return "redirect:/kirsal-kalkinma/sabitler";
	}

	@RequestMapping(value = "/edit/{id}")
	public String sabitEdit(@PathVariable("id") Long Id, @CookieValue(value = "id", required = true) Long id) {
		tips = yerEklemeService.tipsGetir(Id);
		tusYazisi = "Güncelle";
		return "redirect:/kirsal-kalkinma/sabitler";
	}

	@RequestMapping(value = "/vazgec")
	public String vazgec(HttpSession session) {
		// sabitlerService.ekle(sabit1);
		// sabit = sabit1;
		tips.setId(0);
		tips.setIsim(null);
		tips.setTip(null);
		tips.setDurum(null);
		// tips=new Sabittips(); //boï¿½altï¿½r
		tusYazisi = "Ekle";
		return "redirect:/kirsal-kalkinma/sabitler";
	}

	@RequestMapping(value = "/tipsil", method = RequestMethod.POST)
	public @ResponseBody String tipsil(@RequestParam(value = "id", required = true) Long id,
			@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response,
			@CookieValue(value = "id", required = true) Long cookieId) {
		yerEklemeService.tipsil(id);
		response.setCharacterEncoding("UTF-8");

		hitCounter++;

		// create cookie and set it in response
		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
		response.addCookie(cookie);

		return "{}";
	}

	@RequestMapping(value = "/tipsekle", method = RequestMethod.POST)
	public @ResponseBody byte[] tipsekle(@RequestParam(value = "isim", required = true) String isim,
			@RequestParam(value = "katid", required = true) Long katid,
			@RequestParam(value = "durum", required = true) Boolean durum, HttpServletResponse response,
			@CookieValue(value = "id", required = true) Long id) throws Exception {
		Yerler tipsi = new Yerler();
		tipsi.setIsim(isim);
		tipsi.setTip(new Yerler(katid));
		yerEklemeService.kaydet(tipsi);
		return modellerListesi(katid, durum).toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/markagetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] markaGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			HttpServletResponse response, @CookieValue(value = "id", required = true) Long id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<GencCiftciKategori> altTipListesi = new ArrayList<GencCiftciKategori>();
		altTipListesi = yerEklemeService.altTipGetir(altTipId, true);
		Iterator<GencCiftciKategori> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			GencCiftciKategori tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/alttiplerigetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] altTipleriGetir(@RequestParam(value = "katid", required = true) Long katid,
			HttpServletResponse response) throws Exception {
		System.out.println(katid + "****");
		JSONObject jsonObject = new JSONObject();
		List<GencCiftciKategori> altTipListesi = new ArrayList<GencCiftciKategori>();
		altTipListesi = yerEklemeService.altTipGetir(katid, true);
		Iterator<GencCiftciKategori> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			GencCiftciKategori tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping(value = "/modelgetir", method = RequestMethod.POST)
	public @ResponseBody byte[] modelGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			@RequestParam(value = "durum", required = true) Boolean durum) throws Exception {

		return modellerListesi(altTipId, durum).toJSONString().getBytes("UTF-8");
	}

	@SuppressWarnings("unchecked")
	private JSONArray modellerListesi(Long altTipId, Boolean durum) {
		JSONArray donecek = new JSONArray();
		List<GencCiftciKategori> altTipListesi = new ArrayList<GencCiftciKategori>();
		altTipListesi = yerEklemeService.altTipGetir(altTipId, durum);
		Iterator<GencCiftciKategori> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			GencCiftciKategori tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("isim", tip.getIsim());
			// jsonObject.put("kaydeden", tip.getMemurs().getIsim());

			String drm = "Aktif";
			if (tip.getDurum() == false)
				drm = "Pasif";

			jsonObject.put("durum", drm);

			String date = "Bilinmiyor";
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			if (tip.getEklemezamani() != null)
				date = DATE_FORMAT.format(tip.getEklemezamani());
			jsonObject.put("ekleme", date);
			donecek.add(jsonObject);
		}
		return donecek;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gencCiftciMahalleGetir", method = RequestMethod.POST)
	@ResponseBody
	public byte[] gencCiftciMahalleGetir(@RequestParam(value = "altTipId", required = true) Long altTipId,
			HttpServletResponse response, @CookieValue(value = "id", required = true) Long id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Yerler> altTipListesi = new ArrayList<Yerler>();
		altTipListesi = ilceler.altTipGetir(altTipId, true);
		Iterator<Yerler> iterator = altTipListesi.iterator();
		while (iterator.hasNext()) {
			Yerler tip = iterator.next();
			jsonObject.put(tip.getId(), tip.getIsim());
		}
		return jsonObject.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping(value = "/gencCiftciGuncelle/{id}")
	public String ekonomikYatirimGuncelle(@PathVariable("id") Long id) {

		gencCiftci = gencCiftciService.gencCiftciGetir(id);

		tusYazisi = "Guncelle";
		return "redirect:/kirsal-kalkinma/genc-ciftci";
	}

	@RequestMapping(value = "/gencCiftciSil")
	public String ekonomikYatirimSil(@RequestParam("id") Long id) {

		gencCiftciService.delete(id);

		return "redirect:/kirsal-kalkinma/genc-ciftci-liste";
	}

	@RequestMapping(value = "/gencCiftciVazgec")
	public String ekonomikYatirimVazgec() {
		gencCiftci = null;
		return "redirect:/kirsal-kalkinma/genc-ciftci-liste";
	}

	@RequestMapping(value = "/genc-ciftci-liste")
	public String gencCiftciListesi(ModelMap model) {
		model.put("title", "Genç Çiftçi Listesi");
		model.put("kategoriListesi", gencCiftciService.kategoriListesi());
		model.put("yillar", gencCiftciService.yilListesi());
		model.put("ilceListesi", ilceler.altTipGetir(2l, true));
		model.put("gencCiftci", gencCiftciService.tumGencCiftciler());

		return "KirsalKalkinma/GencCiftciListe";
	}

	@RequestMapping(value = "/gencCiftciRapor")
	public String gencCiftciRapor(ModelMap model) {
		model.put("title", "Genç Çiftçi Raporlar");

		model.put("ilceler", gencCiftciService.ilceListesi());

		return "KirsalKalkinma/GencCiftciRapor";
	}

	@RequestMapping(value = "/ilcelereGoreGencCiftciListele", method = RequestMethod.GET)

	public @ResponseBody String doView(ModelMap model, Map<String, Object> map,
			@RequestParam(value = "ilce", required = true) String ilce, HttpServletRequest request,
			HttpServletResponse response) {

		Gson gson = new Gson();

		return gson.toJson(gencCiftciService.ilceyeGoreJSON(ilce));

	}

	@RequestMapping(value = "/gencCiftciXlsxExport", method = { RequestMethod.GET })
	public ModelAndView xlsxViewExport(HttpServletResponse response,
			@RequestParam(value = "kategori", required = false) Integer a,
			@RequestParam(value = "ilce", required = false) String ilce) throws UnsupportedEncodingException {
		response.setContentType("application/vnd.ms-excel");
		if (null != a) {

			response.setHeader("Content-disposition",
					"attachment; filename=" + a + "_Kategorisinde_" + "Yatirimlar_Listesi" + ".xlsx");
		} else if (null != ilce) {
			String fileName = URLEncoder.encode(ilce + "_Ýlcesi_" + "Yatirimlar_Listesi", "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		}

		else {

			response.setHeader("Content-disposition",
					"attachment; filename=" + "Genc_Ciftci_Yatirimlar_Listesi" + ".xlsx");

		}
<<<<<<< HEAD
		return new ModelAndView("xlsxView", "gencCiftci", yatirimListeleri(a, ilce, a));
=======
		return new ModelAndView("xlsxView", "gencciftci", yatirimListeleri(a, ilce));
>>>>>>> parent of 75583d4... GencCiftci jQuery to Excel
	}

	public List<GencCiftci> yatirimListeleri(@RequestParam(value = "kategori", required = false) Integer etapNo,
			@RequestParam(value = "ilce", required = false) String ilce,
			@RequestParam(value = "yil", required = false) Integer yil) {

		if (etapNo != null) {

			return gencCiftciService.kategoriyeGoreListe(etapNo);

		} else if (ilce != null) {

			return gencCiftciService.ilceyeGoreListe(ilce);
		} else if (yil != null) {

			return gencCiftciService.yillaraGoreListe(yil);
		}

		else {

			return gencCiftciService.tumGencCiftciler();
		}
	}

	@RequestMapping(value = "/filtreyeGoreGencCiftciListeGetir")
	public String filtreyeGoreGencCiftciListeGetir(ModelMap model,
			@RequestParam(value = "ilce", required = false) String ilce,
			@RequestParam(value = "kategori", required = false) String kategori,
			@RequestParam(value = "yil", required = false) Integer yil) {
		model.put("title", "Filtre-Genc Ciftci");
		model.put("yillar", gencCiftciService.yilListesi());
		model.put("ilceListesi", ilceler.altTipGetir(2l, true));
		model.put("kategoriListesi", gencCiftciService.kategoriListesi());
		model.put("secilenYil", yil);
		model.put("secilenIlce", ilce);
		model.put("secilenKategori", kategori);
		model.put("secilenMahalleID", gencCiftciService.mahalleListesi());
		model.put("gencCiftci", gencCiftciService.ilceVeKategoriyeGoreListe(kategori, ilce, yil));
		return "KirsalKalkinma/GencCiftciListe";

	}

	@RequestMapping(value = "/ilceyeVeKategoriyeGoreKayitSayisi")
	public @ResponseBody Long ilceyeVeKategoriyeGoreKayitSayisi(ModelMap model,
			@RequestParam(value = "ilce", required = false) Long ilce,
			@RequestParam(value = "kategori", required = false) Long kategori) {
		System.out.println("kayýt sayýlarý : " + gencCiftciService.ilceyeVeKategoriyeGoreKayitSayisi(kategori, ilce));
		System.out.println("ilce : " + ilce + " kategori : " + kategori);
		return gencCiftciService.ilceyeVeKategoriyeGoreKayitSayisi(kategori, ilce);

	}
}

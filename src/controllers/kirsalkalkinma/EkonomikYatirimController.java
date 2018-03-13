package controllers.kirsalkalkinma;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import forms.Kullanici;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;
import service.YerEklemeService;
import service.kirsalkalkinma.EkonomikYatirimDurumuService;
import service.kirsalkalkinma.EkonomikYatirimKategoriService;
import service.kirsalkalkinma.EkonomikYatirimService;

@Controller
@RequestMapping(value = "/kirsal-kalkinma")
public class EkonomikYatirimController {

	@Autowired
	private EkonomikYatirimService ekonomikYatirimService;
	@Autowired
	private EkonomikYatirimDurumuService ekonomikDurumService;
	@Autowired
	private EkonomikYatirimKategoriService ekonomikYatirimKategoriService;
	@Autowired
	private YerEklemeService yerEklemeService;
	private EkonomikYatirim yatirim;
	private String tusYazisi = "Kaydet";

	@RequestMapping(value = "/ekonomik-yatirimlar")
	public String ekonomikYatirimlar(@CookieValue(value = "id", required = false) Long id, ModelMap model,
			@ModelAttribute("ekonomikYatirim") EkonomikYatirim ekonomikYatirim) {

		if (id == null) {

			System.out.println("ID boþ");
			return "redirect:/anasayfa";
		}
		if (yatirim == null) {

			yatirim = new EkonomikYatirim();
		}

		try {
			model.put("ekonomikYatirim", yatirim);
		} catch (Exception e) {

			model.put("errors", e.getMessage());
			return "error";
		}
		model.put("title", "Kýrsal Kalkýnma");
		model.put("tumEkonomikYatirimListesi", ekonomikYatirimService.tumYatirimListesi());
		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));
		model.put("kategoriListesi", ekonomikYatirimKategoriService.tumEkonomikYatirimKategoriListesi());
		model.put("durumListesi", ekonomikDurumService.tumDurumListesi());
		model.put("tusYazisi", tusYazisi);
		tusYazisi = "Kaydet";
		yatirim = null;

		return "KirsalKalkinma/EkonomikYatirimlar";

	}

	@RequestMapping(value = "/ekle")
	public String ekonomikYatirimKaydet(@CookieValue(value = "id", required = true) Long id,
			@ModelAttribute("ekonomikYatirim") EkonomikYatirim ekonomikYatirim, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {

			System.out.println("Hatalar : " + result.getAllErrors());
		}
		Kullanici kullanici = new Kullanici();

		kullanici.setId(id);
		ekonomikYatirim.setIslemYapan(kullanici);
		ekonomikYatirim.setIslemZamani(new Date());
		try {
			ekonomikYatirimService.save(ekonomikYatirim);

		} catch (Exception e) {

			model.put("errors", e.getMessage());
			System.out.println("girilen deðerler : " + ekonomikYatirim.toString());
			return "error";
		}

		return "redirect:/kirsal-kalkinma/ekonomik-yatirimlar";
	}

	@RequestMapping(value = "/ekonomikYatirimGuncelle/{id}")
	public String ekonomikYatirimGuncelle(@PathVariable("id") Long id) {

		yatirim = ekonomikYatirimService.ekonomikYatirimGetir(id);

		tusYazisi = "Guncelle";
		return "redirect:/kirsal-kalkinma/ekonomik-yatirimlar";
	}

	@RequestMapping(value = "/ekonomikYatirimSil")
	public String ekonomikYatirimSil(@RequestParam("id") Long id) {

		ekonomikYatirimService.delete(id);

		return "redirect:/kirsal-kalkinma/ekonomik-yatirimlar";
	}

	@RequestMapping(value = "/ekonomikYatirimVazgec")
	public String ekonomikYatirimVazgec() {
		yatirim = null;
		return "redirect:/kirsal-kalkinma/ekonomik-yatirimlar";
	}

	@RequestMapping(value = "/ekonomikYatirimRapor")
	public String ekonomikYatirimRapor(ModelMap model) {

		model.addAttribute("title", "Ekonomik Rapor");
		model.put("tumEkonomikYatirimListesi", ekonomikYatirimService.tumYatirimListesi());
		model.put("ilceler", ekonomikYatirimService.ilceListesi());

		return "KirsalKalkinma/EkonomikYatirimRapor";
	}

	@RequestMapping(value = "/xlsxExport", method = { RequestMethod.GET })
	public ModelAndView xlsxViewExport(HttpServletResponse response,
			@RequestParam(value = "etapNo", required = false) Integer a,
			@RequestParam(value = "ilce", required = false) String ilce) throws UnsupportedEncodingException {
		// response.setContentType("application/vnd.ms-excel");
		if (null != a) {
			response.setHeader("Content-disposition",
					"attachment; filename=" + "Ekonomik_Yatirimlar_Listesi" + ".xlsx");
		} else if (null != ilce) {
			String fileName = URLEncoder.encode(ilce + "_Ýlcesi_" + "Yatirimlar_Listesi", "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		}

		else {

			response.setHeader("Content-disposition",
					"attachment; filename=" + a + ".Etap_" + "Yatirimlar_Listesi" + ".xlsx");
		}
		return new ModelAndView("xlsxView", "yatirimlar", yatirimListeleri(a, ilce));
	}

	public List<EkonomikYatirim> yatirimListeleri(@RequestParam(value = "etapNo", required = false) Integer etapNo,
			@RequestParam(value = "ilce", required = false) String ilce) {

		if (etapNo != null) {

			return ekonomikYatirimService.etapNoyaGoreListe(etapNo);

		} else if (ilce != null) {

			return ekonomikYatirimService.ilceyeGoreListe(ilce);
		}

		else {

			return ekonomikYatirimService.tumYatirimListesi();
		}
	}
}

package controllers.kirsalkalkinma;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import forms.Kullanici;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;
import service.YerEklemeService;
import service.kirsalkalkinma.EkonomikYatirimKategoriService;
import service.kirsalkalkinma.EkonomikYatirimService;

@Controller
@RequestMapping(value = "/kirsal-kalkinma")
public class EkonomikYatirimController {

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Order") // 404
	public class OrderNotFoundException extends RuntimeException {
		// ...
	}

	@Autowired
	private EkonomikYatirimService ekonomikYatirimService;
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
		model.put("tusYazisi", tusYazisi);
		tusYazisi = "Kaydet";
		yatirim = null;

		return "KirsalKalkinma/EkonomikYatirimlar";

	}

	@RequestMapping(value = "/ekle")
	public String ekonomikYatirimKaydet(@CookieValue(value = "id", required = true) Long id,
			@ModelAttribute() EkonomikYatirim ekonomikYatirim, BindingResult result, ModelMap model) {

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

			return "error";
		}

		return "redirect:/kirsal-kalkinma/ekonomik-yatirimlar";
	}

	@RequestMapping(value = "/ekonomikYatirimGuncelle/{id}")
	public String ekonomikYatirimGuncelle(@PathVariable("id") Long id) {

		yatirim = ekonomikYatirimService.ekonomikYatirimGetir(id);
		if (yatirim == null)
			throw new OrderNotFoundException();
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

		return "KirsalKalkinma/EkonomikYatirimRapor";
	}

}

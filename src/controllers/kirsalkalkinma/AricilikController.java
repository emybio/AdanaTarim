package controllers.kirsalkalkinma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import forms.Yerler;
import forms.kirsalkalkinma.Aricilik;
import service.YerEklemeService;
import service.kirsalkalkinma.AricilikService;

@Controller
@RequestMapping(value = "/kirsal-kalkinma")
public class AricilikController {

	@Autowired
	private YerEklemeService ilceler;

	@Autowired
	private AricilikService ariService;

	private Aricilik aricilik;

	private String tusYazisi = "Ekle";

	@RequestMapping(value = "aricilik", method = RequestMethod.GET)
	public String aricilik(@ModelAttribute("aricilik") Aricilik aricilik1, ModelMap model) {

		if (aricilik == null) {

			aricilik = new Aricilik();
		}
		model.put("ilceListesi", ilceler.altTipGetir(2l, true));
		model.put("aricilik", aricilik);
		model.put("title", "Arýcýlýk");
		model.put("tusYazisi", tusYazisi);
		aricilik = null;
		tusYazisi = "Ekle";
		return "KirsalKalkinma/AricilikAletEkipman";
	}

	@RequestMapping(value = "aricilikEkle", method = RequestMethod.POST)
	public String aricilikEkle(@ModelAttribute("aricilik") Aricilik aricilik1, BindingResult result, ModelMap model,
			@RequestParam("ilce.id") Long ilce) {

		// Yerler yer = new Yerler();
		// yer.setId(ilce);
		// aricilik1.setIlce(yer);
		if (result.hasErrors()) {
			System.out.println("errors : " + result.getAllErrors());
			model.put("errorMessage", "Giriþlerda Hata Var...");

			return "KirsalKalkinma/AricilikAletEkipman";

		} else {

			ariService.save(aricilik1);

		}

		return "redirect:/kirsal-kalkinma/aricilik";
	}

	@RequestMapping(value = "aricilik-liste", method = RequestMethod.GET)
	public String aricilikListe(@ModelAttribute("aricilik") Aricilik aricilik1, ModelMap model) {

		model.put("tumListe", ariService.tumListe());
		model.put("title", "Arýcýlýk Ödeme Cetveli");
		return "KirsalKalkinma/AricilikListe";
	}

	@RequestMapping(value = "ariciSil", method = RequestMethod.GET)
	public String ariciSil(@RequestParam(value = "id") Long id) {

		ariService.delete(id);

		return "redirect:/kirsal-kalkinma/aricilik-liste";
	}

	@RequestMapping(value = "ariciGuncelle/{id}")
	public String ariciGuncelle(@PathVariable(value = "id") Long id) {

		aricilik = ariService.ariciGetir(id);
		tusYazisi = "Guncelle";
		System.out.println("aricilik 2: " + aricilik.getId());
		return "redirect:/kirsal-kalkinma/aricilik";
	}

	@RequestMapping(value = "/aricilikVazgec")
	public String ekonomikYatirimVazgec() {
		aricilik = null;
		tusYazisi = "Ekle";
		return "redirect:/kirsal-kalkinma/aricilik";
	}

}

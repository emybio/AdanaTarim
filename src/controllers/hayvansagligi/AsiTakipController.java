package controllers.hayvansagligi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import forms.hayvansagligi.AsiKarti;
import service.YerEklemeService;
import service.havansagligi.AsiCesitleriService;
import service.havansagligi.AsiKartiService;

@Controller
@RequestMapping(value = "hayvansagligi")
public class AsiTakipController {
	private static final String sube = "Hayvan+Saðlýðý+ve+Yetiþtiriciliði";
	private String tusYazisi = "Ekle";

	private AsiKarti asiKarti;

	@Autowired
	AsiKartiService asiKartiService;
	@Autowired
	YerEklemeService yerEklemeService;

	@Autowired
	AsiCesitleriService asiCesitleriService;

	@RequestMapping(value = "/asikarti", method = { RequestMethod.GET })
	public String asiKarti(@CookieValue("birim") String birim, @CookieValue("id") Long id,
			@ModelAttribute(value = "asiKarti") AsiKarti asiKarti1, ModelMap model) {
		System.out.println(id == 1);

		if (id != 1)
			if (!birim.equals(sube))
				return "/yetkiyok";

		if (asiKarti == null) {

			asiKarti = new AsiKarti();

		}
		model.put("title", "Aþý Takip Kartý");
		model.put("asiKarti", asiKarti);

		model.put("tusYazisi", tusYazisi);
		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));

		model.put("tumAsiKartListesi", asiKartiService.tumListe());
		model.put("tumAsiCesitleri", asiCesitleriService.tumListe());

		model.put("toplam", asiKartiService.ilceninToplamBorcu((long) 11));

		return "HayvanSagligi/AsiTakipKarti";
	}

	@RequestMapping(value = "/asiKartiEkle", method = RequestMethod.POST)
	public String asiKartiKaydet(@ModelAttribute("asiKarti") AsiKarti kart, BindingResult result, ModelMap model) {

		asiKartiService.save(kart);

		if (result.hasErrors()) {
			System.out.println("resutL : " + result.getFieldErrors());
			model.put("tusYazisi", tusYazisi);

			return "HayvanSagligi/AsiTakipKarti";

		}
		model.put("tusYazisi", tusYazisi);
		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));

		model.put("tumAsiKartListesi", asiKartiService.tumListe());
		model.put("tumAsiCesitleri", asiCesitleriService.tumListe());

		asiKarti = kart;
		kart = asiKarti;

		return "redirect:/hayvansagligi/asikarti";
	}

}

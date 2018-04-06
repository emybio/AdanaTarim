package controllers.kirsalkalkinma.kooperatif;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import forms.Kullanici;
import forms.Yerler;
import forms.kirsalkalkinma.kooperatif.Kooperatif;
import service.YerEklemeService;
import service.kirsalkalkinma.kooperatif.KooperatifService;
import service.kirsalkalkinma.kooperatif.KooperatifTurService;

@Controller
@RequestMapping(value = "/kirsal-kalkinma")
public class KooperatifController {
	@Autowired
	YerEklemeService yerEklemeService;
	@Autowired
	KooperatifTurService koopTurService;

	@Autowired
	KooperatifService koopService;
	private Kooperatif kooperatif;
	private static final String koopTitle = "Kooperatif";
	private String successfullMessage = null;

	private Kooperatif kooperatifTurId;

	private static Map<String, String> contactMap = new HashMap<String, String>();
	static {
		contactMap.put("Cep NO:", "");
		contactMap.put("Adres : ", "");
	}

	@RequestMapping(value = "ilce-listesi")
	public String kooperatifIlceListesi(ModelMap model) {

		model.put("ilceler", koopService.ilceListesi());

		model.put("title", koopTitle);
		return "KirsalKalkinma/kooperatif/KooperatifIlceListesi";

	}

	@RequestMapping(value = "tur-listesi")
	public String kooperatifTurListesi() {

		return "KirsalKalkinma/kooperatif/KooperatifTurListesi";

	}

	@RequestMapping(value = "kooperatif-listesi")
	public String kooperatifListesi() {

		return "KirsalKalkinma/kooperatif/KooperatifListesi";

	}

	@RequestMapping(value = "kooperatifKarti")
	public String kooperatifKarti(@RequestParam(value = "id") Long id, ModelMap model) {
		model.put("kooperatif", koopService.kooperatifKartiGetir(id));
		model.put("title", koopTitle);
		return "KirsalKalkinma/kooperatif/KooperatifKartý";

	}

	@RequestMapping(value = "kooperatifTanimlama", method = RequestMethod.GET)
	public String kooperatifTanimalaGet(@CookieValue(value = "id", required = false) Long id,
			@ModelAttribute(value = "kooperatif") Kooperatif koop, ModelMap model) {
		System.out.println("koop null or not : " + kooperatif);

		if (id == null) {
			return "redirect:/anasayfa";
		}
		if (kooperatif == null) {
			kooperatif = new Kooperatif();

		}

		Kooperatif iletisim = new Kooperatif();
		iletisim.setIletisimBilgileri(contactMap);
		List<Yerler> altTipListesi = yerEklemeService.altTipGetir(2l, true);
		model.put("tipListesi", iletisim);
		model.put("kooperatif", kooperatif);
		model.put("turListesi", koopTurService.tumKooperatiflerler());
		model.put("ilceler", altTipListesi);
		model.put("message", successfullMessage);
		model.put("title", koopTitle);
		kooperatif = null;
		successfullMessage = null;
		return "KirsalKalkinma/kooperatif/KooperatifTanimlama";

	}

	@RequestMapping(value = "/kooperatifEkle", method = RequestMethod.POST)
	public String kooperatifTanimalaPost(ModelMap model, @CookieValue(value = "id", required = true) Long id,
			@RequestParam(value = "denetimKuruluList") String denetimKurulu,
			@RequestParam(value = "yonetimKuruluList") String yonetimKurulu,
			@ModelAttribute(value = "kooperatif") Kooperatif kooperatif1, BindingResult result) {

		if (id.equals(null)) {

			model.put("message", "");
			return "KirsalKalkinma/kooperatif/KooperatifTanimlama";

		}
		Kullanici kullanici = new Kullanici();
		kullanici.setId(id);
		List<String> yonetim = new ArrayList<>();
		List<String> denetim = new ArrayList<>();
		String[] yonetimArray = yonetimKurulu.split(",");
		String[] denetimArray = denetimKurulu.split(",");

		for (String a : yonetimArray) {

			yonetim.add(a.trim());
		}
		for (String a : denetimArray) {

			denetim.add(a.trim());
		}

		kooperatif1.setYonetimKuruluList(yonetim);
		kooperatif1.setDenetimKuruluList(denetim);
		if (kooperatif != null) {
			System.out.println("eklerken id ne durumda : " + kooperatif.getId());
			// kooperatif1.setId(kooperatif.getId());
		}
		if (!result.hasErrors()) {
			System.out.println("hata yok");
			kooperatif1.setIslemZamani(new Date());
			kooperatif1.setIslemYapan(kullanici);
			koopService.save(kooperatif1);
			successfullMessage = "succeed";
		} else {
			successfullMessage = "failed";
			System.out.println("kooperatif: " + result);
			model.put("message", successfullMessage);
			return "KirsalKalkinma/kooperatif/KooperatifTanimlama";
		}
		return "redirect:/kirsal-kalkinma/kooperatifTanimlama";
	}

	@RequestMapping(value = "/ilceyeGoreKooperatifTurleri")
	public String ilceyeGoreKooperatifListesi(@RequestParam(value = "id") Long id,
			@RequestParam(value = "isim", required = false) String isim, ModelMap model) {

		model.put("kooperatif", koopService.ilceyeGoreKooperatifListesi(id));
		model.put("ilceler", isim);
		model.put("title", koopTitle);

		return "KirsalKalkinma/kooperatif/KooperatifTurListesi";

	}

	@RequestMapping(value = "/tureGoreKooperatifler")
	public String tureGoreKooperatifListesi(@RequestParam(value = "id", required = false) Long id, ModelMap model) {

		model.put("title", koopTitle);
		if (kooperatifTurId == null) {
			System.out.println("kooperatifTurId NULL");
			if (!koopService.tureGoreKooperatifListesi(id).isEmpty()) {

				System.out.println("kooperatifTurId NULL2");

				model.put("kooperatif", koopService.tureGoreKooperatifListesi(id));
			} else {

				return "redirect:/kirsal-kalkinma/ilce-listesi";

			}
		}

		else {
			System.out.println("kooperatifTurId NULL DEÐÝL");
			model.put("kooperatif",
					koopService.tureGoreKooperatifListesi(kooperatifTurId.getKooperatifTurID().getId()));
		}
		kooperatifTurId = null;
		return "KirsalKalkinma/kooperatif/KooperatifListesi";

	}

	@RequestMapping(value = "/kooperatifSil")
	public String koopertaifSil(@RequestParam(value = "id") Long id, ModelMap model) {
		model.put("title", koopTitle);
		kooperatifTurId = koopService.kooperatifGetir(id);
		kooperatifTurId.getKooperatifTurID().getId();
		System.out.println("koop türü : " + kooperatifTurId.getKooperatifTurID().getId());
		// model.put("kooperatif",
		// koopService.tureGoreKooperatifListesi(koop.getKooperatifTurID().getId()));
		koopService.delete(id);
		return "redirect:/kirsal-kalkinma/tureGoreKooperatifler?id=" + kooperatifTurId.getKooperatifTurID().getId();

	}

	@RequestMapping(value = "/kooperatifDuzenle/{id}", method = RequestMethod.GET)
	public String koopertaifDuzenle(@PathVariable(value = "id") Long id, ModelMap model) {
		kooperatif = koopService.kooperatifGetir(id);

		System.out.println("Koop ID guncelle: " + kooperatif.getId());

		return "redirect:/kirsal-kalkinma/kooperatifTanimlama";

	}

	@RequestMapping(value = "/tureVeIlceyeGoreKooperatifler")
	public String tureVeIlceyeGoreKooperatifler(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "ilce_id", required = false) String ilce, ModelMap model) {

		model.put("title", koopTitle);

		

		model.put("kooperatif", koopService.tureVeIlceyeGoreKooperatifListesi(id, ilce));

		return "KirsalKalkinma/kooperatif/KooperatifListesi";

	}
}

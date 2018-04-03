/**
 * 
 */
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;

import forms.AracTalep;
import forms.Kullanici;
import service.AracTalepService;
import service.KullaniciService;
import service.YerEklemeService;

/**
 * @author Emrah Denizer
 *
 */
@Controller
@RequestMapping(value = "/arazi-cikislari")
public class AracTalepController {
	@Autowired
	YerEklemeService yerEklemeService;
	@Autowired
	KullaniciService kullaniciService;
	@Autowired
	private AracTalepService aracService;

	private AracTalep aracTalep2;
	public String dosyaDurumu = null;
	public String download = "";
	public Kullanici raporAlinanPersonelBilgileri = null;
	public List<AracTalep> cikisListesi1 = null;
	public List<AracTalep> gorevBulCikisListesi = null;
	public String donem = "bos";
	public String tusYazisi = "Kaydet";

	@RequestMapping(value = "/arac-talep", method = RequestMethod.GET)
	public String aracTalep(ModelMap model, @CookieValue(value = "id", required = false) Long id,
			HttpServletRequest request, @ModelAttribute("aracTalep") AracTalep aracTalep1, BindingResult result)
			throws ParseException {

		if (result.hasErrors() || id == null) {
			System.out.println("arac sayfasınnda hata var...");
			return "redirect:/anasayfa";

		}
		if (aracTalep2 == null) {

			aracTalep2 = new AracTalep();

		}
		model.put("aracTalep", aracTalep2);
		model.put("title", "Arazi Talepleri");
		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));

		gunugecenleriSil();
		model.put("tusYazisi", tusYazisi);

		if (id == 25) {
			model.put("aracTalepListesi", aracService.tumAracCikislari());
		} else {
			model.put("aracTalepListesi", aracService.kullaniciyaGoreCikisListesi(id));
		}
		tusYazisi = "Kaydet";
		return "AraziCikis/AracTalep";

	}

	@RequestMapping(value = "/aracTalepEkle")
	public String araziTalepEkle(@ModelAttribute("aracTalep") AracTalep arac, BindingResult result,
			@CookieValue(value = "id", required = true) Long id, HttpServletRequest request, ModelMap model) {

		Kullanici islemyapan = new Kullanici();
		islemyapan.setId(id);

		arac.setIslemyapan(islemyapan);

		if (result.hasErrors()) {
			model.put("aracTalep", new AracTalep());
			model.put("title", "Arazi Talepleri");
			model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));
			model.put("tusYazisi", tusYazisi);
			System.out.println("BindingResult: " + arac);

			return "AraziCikis/AracTalep";

		} else {

			arac.setIsActive(true);
			arac.setIslemZamani(new Date());

			aracService.save(arac);

		}
		return "redirect:/arazi-cikislari/arac-talep";
	}

	@RequestMapping(value = "/talepDuzenle/{id}", method = RequestMethod.GET)
	public String duzenle(@PathVariable("id") Long id) {
		aracTalep2 = aracService.aracCikisGetir(id);
		tusYazisi = "Güncelle";
		return "redirect:/arazi-cikislari/arac-talep";
	}

	@RequestMapping(value = "/talepSil", method = RequestMethod.POST)
	public @ResponseBody String tipsil(@RequestParam(value = "id", required = true) Long id,
			@CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter, HttpServletResponse response,
			HttpServletRequest request) {

		aracService.delete(id);

		return "{}";
	}

	@RequestMapping(value = "/vazgecTalep")
	public String vazgec(@ModelAttribute("aracTalep") AracTalep arac) {
		arac.setId(null);
		arac.setMahalle(null);

		aracTalep2 = null;

		return "redirect:/arazi-cikislari/arac-talep";
	}

	@RequestMapping(value = "/talepBul")
	public String gorevBul(@RequestParam(value = "tarih") String tarih) {

		gorevBulCikisListesi = aracService.gorevBul(tarih);

		return "redirect:/arazi-cikislari/arac-islemleri";
	}

	private void gunugecenleriSil() throws ParseException {

		List<AracTalep> aracTalepList = aracService.tumAracCikislari();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd", new Locale("tr", "TR"));
		AracTalep aracTalep = new AracTalep();

		for (int i = 0; i < aracTalepList.size(); i++) {

			String talepTarihi = aracTalepList.get(i).getTarih();
			Date date = sdf.parse(talepTarihi);
			Date now = new Date();
			// (1000 * 60 * 60 * 24) tam 1 gün...
			if (date.before(new Date(now.getTime() - (1000 * 60 * 60 * 24)))) {
				aracTalep = aracService.aracCikisGetir(aracTalepList.get(i).getId());

				aracTalep.setIsActive(false);

				aracService.save(aracTalep);

			}

		}
	}
}

package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import forms.AraziIslemHareketleri;
import service.AracService;
import service.AraziService;
import service.RaporlarService;

@Controller
@RequestMapping(value = "/raporlar", method = RequestMethod.GET)
public class RaporlarController {
	@Autowired
	AraziService araziService;
	@Autowired
	RaporlarService raporlarService;
	@Autowired
	AracService aracService;
	public String islemTipi = "";
	public AraziIslemHareketleri arazi;

	@RequestMapping(value = "/satisrapor")
	public ModelAndView giris(@ModelAttribute("araziIslem") AraziIslemHareketleri araziIslemHareketleri, ModelMap model,
			HttpServletRequest request, @CookieValue(value = "id", required = true) Long id) {
		ModelAndView modelAndView = new ModelAndView("Raporlar/SatisRaporlari");
		modelAndView.addObject("title", "Raporlar ");
		modelAndView.addObject("islemListesi", araziService.islemHareketleriListesi());

		if (arazi == null) {

			arazi = new AraziIslemHareketleri();
		}
		modelAndView.addObject("araziIslem", arazi);
		/* yÃ¶netici deÄŸilse geldiÄŸi sayfaya geri dÃ¶ner -BAÅ�LANGIÃ‡ */
		// if (id != 1) {
		// String referer = request.getHeader("Referer");
		// return new ModelAndView("redirect:" + referer);

		/* yÃ¶netici deÄŸilse geldiÄŸi sayfaya geri dÃ¶ner -SON */
		// } else if (id == 3) {
		// System.out.println(id + " is null");
		// return new ModelAndView("redirect:/");
		// }
		// else

		List<AraziIslemHareketleri> araziList = new ArrayList<AraziIslemHareketleri>();
		araziList = araziService.islemHareketleriListesi();
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;

		for (int i = 0; i < araziList.size(); i++) {

			devriIstenenParselSayisiToplami += araziList.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += araziList.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += araziList.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += araziList.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += araziList.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += araziList.get(i).getIzinVerilmeyenParselAlani();

			if (!(araziList.get(i).getDevriIstenenParselAlani() == araziList.get(i).getIzinVerilenParselAlani()
					+ araziList.get(i).getIzinVerilmeyenParselAlani())) {
				System.err.println(i + ". Hata " + "==================VERÄ°TABANI HATALI VERÄ°LER==================");

				System.err.println("Ä°ÅŸlemi Yapan: " + araziList.get(i).getKullanici().getAdi());
				System.err.println("Ä°ÅŸlemi Tarihi: " + araziList.get(i).getIslemZamani());
				System.err.println("Ä°ÅŸlem DetaylarÄ±: ");

				System.err.print("ID: " + araziList.get(i).getId() + "---");

				System.err.println(araziList.get(i).getTarih() + "-----");
				System.err.print(
						"Devri istenen parsel sayÄ±sÄ±: " + araziList.get(i).getDevriIstenenParselSayisi() + "----");
				System.err.println("Devri istenen parsel alanÄ±: " + araziList.get(i).getDevriIstenenParselAlani());

				System.err.print(
						"Ä°zin verilen parsel sayÄ±sÄ±: " + araziList.get(i).getIzinVerilenParselSayisi() + "---");
				System.err.println("Ä°zin verilen parsel alanÄ±: " + araziList.get(i).getIzinVerilenParselAlani());

				System.err.print("Ä°zin verilmeyen parsel sayÄ±sÄ±: " + araziList.get(i).getIzinVerilmeyenParselSayisi()
						+ "---");
				System.err.println(
						"Ä°zin verilmeyen parsel alanÄ±: " + araziList.get(i).getIzinVerilmeyenParselAlani() + "---");
				System.err.println("Fark: ");
				System.err.print("SayÄ±: ");
				System.err.println(
						araziList.get(i).getDevriIstenenParselSayisi() - araziList.get(i).getIzinVerilenParselSayisi()
								+ araziList.get(i).getIzinVerilmeyenParselSayisi());
				System.err.print("Alan: ");
				System.err.println(
						araziList.get(i).getDevriIstenenParselAlani() - araziList.get(i).getIzinVerilenParselAlani()
								+ araziList.get(i).getIzinVerilmeyenParselAlani());

				System.err
						.println(i + ". Hata " + "==================VERÄ°TABANI HATALI VERÄ°LER SON==================");
			}

		}

		modelAndView.addObject("devriIstenenParselSayisi", devriIstenenParselSayisiToplami);
		modelAndView.addObject("devriIstenenParselAlani", devriIstenenParselAlaniToplami);
		modelAndView.addObject("izinVerilenParselSayisi", izinVerilenParselSayisiToplami);
		modelAndView.addObject("izinVerilenParselAlani", izinVerilenParselAlaniToplami);
		modelAndView.addObject("izinVerilmeyenParselSayisi", izinVerilmeyenParselSayisiToplami);
		modelAndView.addObject("izinVerilmeyenParselAlani", izinVerilmeyenParselAlaniToplami);
		modelAndView.addObject("ilceler", araclar.Genel.ilcelers());
		modelAndView.addObject("aylar", araclar.Genel.aylar());

		return modelAndView;
	}

	@RequestMapping(value = "/rapor", method = RequestMethod.GET)
	public @ResponseBody ArrayList<AraziIslemHareketleri> raporlarListesi() {
		// System.out.println(raporlarService.raporlarListesi());

		for (int i = 0; i < raporlarService.raporlarListesi().size(); i++) {

			System.out.println(raporlarService.raporlarListesi().get(i).getIlce().toString());
		}
		return raporlarService.raporlarListesi();
	}

	public ModelAndView toplamAraziSatislari() {
		ModelAndView modelAndView = new ModelAndView("redirect:/raporlar/satisrapor");

		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toplam", method = RequestMethod.GET)
	public @ResponseBody Map<String, Long> list() {

		List<AraziIslemHareketleri> arazi = new ArrayList<AraziIslemHareketleri>();
		arazi = araziService.islemHareketleriListesi();
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;

		for (int i = 0; i < arazi.size(); i++) {

			devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

		}
		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami);
		toplam.put("devriIstenenParselAlaniToplami", (long) devriIstenenParselAlaniToplami);
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami);
		toplam.put("izinVerilenParselAlaniToplami", (long) izinVerilenParselAlaniToplami);
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) izinVerilmeyenParselAlaniToplami);

		return toplam;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ucayliktoplamgetir", method = RequestMethod.GET)
	public @ResponseBody Map<String, Long> ucAylikToplam(@RequestParam(value = "yil") String yil,
			@RequestParam(value = "birinciAy") String birinciAy, @RequestParam(value = "ikinciAy") String ikinciAy) {
		List<AraziIslemHareketleri> arazi = new ArrayList<AraziIslemHareketleri>();
		arazi = araziService.ucAylikRapor(yil, birinciAy, ikinciAy);
		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;
		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;
		for (int i = 0; i < arazi.size(); i++) {

			devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

		}
		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami);
		toplam.put("devriIstenenParselAlaniToplami", (long) devriIstenenParselAlaniToplami);
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami);
		toplam.put("izinVerilenParselAlaniToplami", (long) izinVerilenParselAlaniToplami);
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) izinVerilmeyenParselAlaniToplami);

		return toplam;
	}

	@RequestMapping(value = "/islemTipineGoreUcAylikToplamgetir", method = RequestMethod.GET)
	public @ResponseBody String islemTipineGoreUcAaylikToplamgetir(@RequestParam(value = "islemTipi") String islemTipi,
			@RequestParam(value = "yil") String yil, @RequestParam(value = "birinciAy") String birinciAy,
			@RequestParam(value = "ikinciAy") String ikinciAy) {
		Gson gson = new Gson();

		return gson.toJson(araziService.islemTipineGoreUcAylikRapor(islemTipi, yil, birinciAy, ikinciAy));
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ilceyeGöreListeGetir", method = RequestMethod.GET)
	public @ResponseBody Map<String, Long> ilceyeGoreListeGetir(
			@RequestParam(value = "ilceID", required = true) String ilce,
			@RequestParam(value = "ilceBirinciAy", required = true) String ilkTarih,
			@RequestParam(value = "ilceIkinciAy", required = true) String sonTarih

	) {
		List<AraziIslemHareketleri> arazi = new ArrayList<AraziIslemHareketleri>();

		int devriIstenenParselSayisiToplami = 0, izinVerilenParselSayisiToplami = 0,
				izinVerilmeyenParselSayisiToplami = 0;

		float devriIstenenParselAlaniToplami = 0, izinVerilenParselAlaniToplami = 0,
				izinVerilmeyenParselAlaniToplami = 0;
		arazi = araziService.ilceyeGoreListele2(ilce, ilkTarih, sonTarih);

		for (int i = 0; i < arazi.size(); i++) {

			devriIstenenParselSayisiToplami += arazi.get(i).getDevriIstenenParselSayisi();
			devriIstenenParselAlaniToplami += arazi.get(i).getDevriIstenenParselAlani();

			izinVerilenParselSayisiToplami += arazi.get(i).getIzinVerilenParselSayisi();

			izinVerilenParselAlaniToplami += arazi.get(i).getIzinVerilenParselAlani();

			izinVerilmeyenParselSayisiToplami += arazi.get(i).getIzinVerilmeyenParselSayisi();

			izinVerilmeyenParselAlaniToplami += arazi.get(i).getIzinVerilmeyenParselAlani();

		}

		Map<String, Long> toplam = new JSONObject();

		toplam.put("devriIstenenParselSayisiToplami", (long) devriIstenenParselSayisiToplami);
		toplam.put("devriIstenenParselAlaniToplami", (long) (devriIstenenParselAlaniToplami));
		toplam.put("izinVerilenParselSayisiToplami", (long) izinVerilenParselSayisiToplami);
		toplam.put("izinVerilenParselAlaniToplami", (long) (izinVerilenParselAlaniToplami));
		toplam.put("izinVerilmeyenParselSayisiToplami", (long) izinVerilmeyenParselSayisiToplami);
		toplam.put("izinVerilmeyenParselAlaniToplami", (long) izinVerilmeyenParselAlaniToplami);

		return toplam;
	}
}

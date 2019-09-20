package controllers.donersermaye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.YerEklemeService;
import service.havansagligi.AsiKartiService;

@Controller
@RequestMapping(value = "/doner-sermaye")
public class AsiTahsilatController {
	@Autowired
	private AsiKartiService asiKartiService;
	@Autowired
	private YerEklemeService yerEklemeService;

	@RequestMapping(value = "/asiTahsilat", method = RequestMethod.GET)
	public String asiTahsilatGet(ModelMap model) {

		model.put("ilceListesi", yerEklemeService.altTipGetir(2l, true));

		return "DonerSermaye/AsiTahsilat";
	}

	@RequestMapping(value = "asiTashilat", method = RequestMethod.POST)
	public String asiTahsilatPost() {

		return "DonerSermaye/AsiTahsilat";
	}

	@RequestMapping(value = "/ilceToplamBorcGetir", method = RequestMethod.GET)
	public void testphoto2(@RequestParam(value = "id", required = false) Long id) {

		asiKartiService.ilceninToplamBorcu(id);

	}
}

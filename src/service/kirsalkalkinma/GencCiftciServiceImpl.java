package service.kirsalkalkinma;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.gencciftci.GencCiftciDAO;
import forms.kirsalkalkinma.gencciftci.GencCiftci;

@Service
public class GencCiftciServiceImpl implements GencCiftciService {

	@Autowired
	private GencCiftciDAO gencCiftciDAO;

	@Override
	public void save(GencCiftci gencCiftci) {
		gencCiftciDAO.save(gencCiftci);

	}

	@Override
	public void delete(Long id) {
		gencCiftciDAO.delete(id);

	}

	@Override
	public GencCiftci gencCiftciGetir(Long id) {
		return gencCiftciDAO.gencCiftciGetir(id);
	}

	@Override
	public List<GencCiftci> tumGencCiftciler() {
		return gencCiftciDAO.tumGencCiftciler();
	}

	@Override
	public List<GencCiftci> ilceyeGoreListe(String ilce) {

		return gencCiftciDAO.ilceyeGoreListe(ilce);
	}

	@Override
	public List<GencCiftci> kategoriyeGoreListe(Integer kategori) {

		return gencCiftciDAO.kategoriyeGoreListe(kategori);
	}

	@Override
	public List<GencCiftci> ilceListesi() {
		return gencCiftciDAO.ilceListesi();
	}

	@Override
	public List<GencCiftci> kategoriListesi() {
		return gencCiftciDAO.kategoriListesi();
	}

	@Override
	public JSONArray ilceyeGoreJSON(String ilce) {
		return gencCiftciDAO.ilceyeGoreJSON(ilce);
	}

	@Override
	public Long ilceyeVeYillaraGoreKayitSayisi(Integer yil, String ilce) {
		return gencCiftciDAO.ilceyeVeYillaraGoreKayitSayisi(yil, ilce);
	}

}

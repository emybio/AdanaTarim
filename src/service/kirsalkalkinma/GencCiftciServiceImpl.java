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
		// TODO Auto-generated method stub
		return gencCiftciDAO.ilceListesi();
	}

	@Override
	public List<GencCiftci> kategoriListesi() {
		// TODO Auto-generated method stub
		return gencCiftciDAO.kategoriListesi();
	}

	@Override
	public JSONArray ilceyeGoreJSON(String ilce) {
		// TODO Auto-generated method stub
		return gencCiftciDAO.ilceyeGoreJSON(ilce);
	}

<<<<<<< HEAD
	@Override
	public Long ilceyeVeYillaraGoreKayitSayisi(Integer yil, String ilce) {
		return gencCiftciDAO.ilceyeVeYillaraGoreKayitSayisi(yil, ilce);
	}

	@Override
	public List<GencCiftci> yillaraGoreListe(Integer yil) {
		return gencCiftciDAO.yillaraGoreListe(yil);
	}

	@Override
	public List<GencCiftci> yilListesi() {
		return gencCiftciDAO.yilListesi();
	}

	@Override
	public List<GencCiftci> ilceVeKategoriyeGoreListe(String kategori, String ilce, Integer yil) {
		return gencCiftciDAO.ilceVeKategoriyeGoreListe(kategori, ilce, yil);
	}

	@Override
	public Long ilceyeVeKategoriyeGoreKayitSayisi(Long kategori, Long ilce) {
		return gencCiftciDAO.ilceyeVeKategoriyeGoreKayitSayisi(kategori, ilce);
	}

	@Override
	public List<GencCiftci> mahalleListesi() {
		return gencCiftciDAO.mahalleListesi(); 
	}

=======
>>>>>>> parent of 75583d4... GencCiftci jQuery to Excel
}

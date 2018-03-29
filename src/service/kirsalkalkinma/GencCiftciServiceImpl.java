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

}

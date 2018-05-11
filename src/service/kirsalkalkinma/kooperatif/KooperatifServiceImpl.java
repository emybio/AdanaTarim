package service.kirsalkalkinma.kooperatif;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.kooperatif.KooperatifDAO;
import forms.kirsalkalkinma.kooperatif.Kooperatif;

@Service
public class KooperatifServiceImpl implements KooperatifService {
	@Autowired
	KooperatifDAO koopDAO;

	@Override
	public void save(Kooperatif kooperatif) {
		koopDAO.save(kooperatif);

	}

	@Override
	public void delete(Long id) {
		koopDAO.delete(id);

	}

	@Override
	public Kooperatif kooperatifGetir(Long id) {
		return koopDAO.kooperatifGetir(id);
	}

	@Override
	public List<Kooperatif> tumKooperatiflerler() {
		return koopDAO.tumKooperatiflerler();
	}

	@Override
	public List<Kooperatif> ilceyeGoreKooperatifListesi(Long ilceID) {
		return koopDAO.ilceyeGoreKooperatifListesi(ilceID);
	}

	@Override
	public List<Kooperatif> ilceListesi() {
		return koopDAO.ilceListesi();
	}

	@Override
	public List<Kooperatif> turListesi() {
		return koopDAO.turListesi();
	}

	@Override
	public List<Kooperatif> tureGoreKooperatifListesi(Long turID) {
		return koopDAO.tureGoreKooperatifListesi(turID);
	}

	@Override
	public List<Kooperatif> kooperatifKartiGetir(Long koopId) {
		return koopDAO.kooperatifKartiGetir(koopId);
	}

	@Override
	public List<Kooperatif> tureVeIlceyeGoreKooperatifListesi(Long turID, String ilce) {
		return koopDAO.tureVeIlceyeGoreKooperatifListesi(turID, ilce);
	}

	@Override
	public Long ilceyeVeTureGoreKayitSayisi(Long tur, String ilce) {
		return koopDAO.ilceyeVeTureGoreKayitSayisi(tur, ilce);
	}

	@Override
	public JSONArray tureVeIlceyeGoreKooperatiflerJSON(Long turID, String ilce) {
		return koopDAO.tureVeIlceyeGoreKooperatiflerJSON(turID, ilce);
	}

	@Override
	public List<Kooperatif> durumListesi() {
		return koopDAO.durumListesi();
	}

	@Override
	public List<Kooperatif> durumaGoreGetir(String durum) {
		return koopDAO.durumaGoreGetir(durum);
	}

	@Override
	public List<Kooperatif> koopBul(String koopAdi) {
		return koopDAO.koopBul(koopAdi);
	}

}

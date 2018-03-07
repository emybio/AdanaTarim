/**
 * 
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AracTalepDAO;
import forms.AracTalep;

/**
 * @author lenovo
 *
 */
@Service
public class AracTalepServiceImpl implements AracTalepService {

	@Autowired
	AracTalepDAO aracDao;

	@Override
	public void save(AracTalep arac) {
		aracDao.save(arac);

	}

	@Override
	public void delete(Long id) {
		aracDao.delete(id);

	}

	@Override
	public AracTalep aracCikisGetir(Long id) {

		return aracDao.aracCikisGetir(id);
	}

	@Override
	public List<AracTalep> tumAracCikislari() {
		return aracDao.tumAracCikislari();
	}

	

	@Override
	public List<AracTalep> kullaniciyaGoreCikisListesi(Long kullaniciID) {
		return aracDao.kullaniciyaGoreCikisListesi(kullaniciID);
	}

	@Override
	public List<AracTalep> ozelPlakayaGoreCikisListesi(String plaka) {
		return aracDao.ozelPlakayaGoreCikisListesi(plaka);
	}

	@Override
	public List<AracTalep> resmiPlakayaGoreCikisListesi(String plaka) {
		return aracDao.resmiPlakayaGoreCikisListesi(plaka);
	}

	@Override
	public List<AracTalep> cikisYapanPersonelListesi() {

		return aracDao.cikisYapanPersonelListesi();
	}

	@Override
	public boolean ayniGorevdenVarMi(long mahalleID, String tarih) {
		return aracDao.ayniGorevdenVarMi(mahalleID, tarih);
	}

	@Override
	public List<AracTalep> gorevBul(String tarih) {
		return aracDao.gorevBul(tarih);
	}
}

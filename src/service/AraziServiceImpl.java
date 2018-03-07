package service;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AraziDAO;
import forms.AraziIslemHareketleri;

@Service
public class AraziServiceImpl implements AraziService {
	@Autowired
	AraziDAO araziDAO;

	@Override
	public void save(AraziIslemHareketleri islemHareketleri) {
		araziDAO.save(islemHareketleri);
	}

	@Override
	public List<AraziIslemHareketleri>

			islemHareketleriListesi() {
		return araziDAO.islemHareketleriListesi();
	}

	@Override
	public AraziIslemHareketleri araziIslemGetir(Long id) {
		return araziDAO.araziIslemGetir(id);
	}

	@Override
	public JSONArray islemTipineGoreListele(String islemTipi) {
		return araziDAO.islemTipineGoreListele(islemTipi);
	}

	@Override
	public Long sonIdGetir() {
		return araziDAO.sonIdGetir();
	}

	@Override
	public JSONArray ilceyeGoreListele(String ilce) {
		return araziDAO.ilceyeGoreListele(ilce);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * service.AraziService#islemTipineVePersoneleGöreListele(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public JSONArray islemTipineVePersoneleGoreListele(String islemTipi, Long id) {
		return araziDAO.islemTipineVePersoneleGoreListele(islemTipi, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AraziService#sil(java.lang.Long)
	 */
	@Override
	public void sil(Long id) {
		araziDAO.sil(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AraziService#ilceyeGöreListele2(java.lang.String)
	 */
	@Override
	public List<AraziIslemHareketleri> ilceyeGoreListele2(String ilce, String ilkTarih, String sonTarih) {
		return araziDAO.ilceyeGoreListele2(ilce, ilkTarih, sonTarih);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.AraziService#ucAylikRapor(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<AraziIslemHareketleri> ucAylikRapor(String yil, String ilkAy, String sonAy) {
		return araziDAO.ucAylikRapor(yil, ilkAy, sonAy);
	}

	@Override
	public JSONArray islemTipineGoreUcAylikRapor(String islemTipi, String yil, String ilkAy, String sonAy) {
		return araziDAO.islemTipineGoreUcAylikRapor(islemTipi, yil, ilkAy, sonAy);
	}

}

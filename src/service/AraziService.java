package service;

import java.util.List;

import org.json.simple.JSONArray;

import forms.AraziIslemHareketleri;

public interface AraziService {

	public void save(AraziIslemHareketleri islemHareketleri);

	public void sil(Long id);

	public List<AraziIslemHareketleri> islemHareketleriListesi();

	public AraziIslemHareketleri araziIslemGetir(Long id);

	public JSONArray islemTipineGoreListele(String islemTipi);

	public JSONArray islemTipineVePersoneleGoreListele(String islemTipi, Long id);

	public Long sonIdGetir();

	public JSONArray ilceyeGoreListele(String ilce);

	public List<AraziIslemHareketleri> ilceyeGoreListele2(String ilce, String ilkTarih, String sonTarih);

	public List<AraziIslemHareketleri> ucAylikRapor(String yil, String ilkAy, String sonAy);

	public JSONArray islemTipineGoreUcAylikRapor(String islemTipi, String yil, String ilkAy, String sonAy);
}

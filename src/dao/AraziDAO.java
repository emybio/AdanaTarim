package dao;

import java.util.List;

import org.json.simple.JSONArray;

import forms.AraziIslemHareketleri;

public interface AraziDAO {

	public void save(AraziIslemHareketleri islemHareketleri);

	public void sil(Long id);

	public List<AraziIslemHareketleri> islemHareketleriListesi();

	public AraziIslemHareketleri araziIslemGetir(Long id);

	public JSONArray islemTipineGoreListele(String islemTipi);

	public JSONArray islemTipineVePersoneleGoreListele(String islemTipi, Long id);

	public Long sonIdGetir();

	public JSONArray ilceyeGoreListele(String ilce);

	public JSONArray ayalaraGoreToplamGetir(String yil, String birinciAy, String ikinciAy, String ucuncuAy);

	public List<AraziIslemHareketleri> ilceyeGoreListele2(String ilce, String ilkTarih, String sonTarih);

	public List<AraziIslemHareketleri> ucAylikRapor(String yil, String ilkAy, String sonAy);

	public JSONArray islemTipineGoreUcAylikRapor(String islemTipi, String yil, String ilkAy, String sonAy);
}

/**
 * 
 */
package service;

import java.util.List;

import forms.Arac;

/**
 * @author lenovo
 *
 */
public interface AracService {
	public void save(Arac arac);

	public void delete(Long id);

	public Arac aracCikisGetir(Long id);

	public List<Arac> tumAracCikislari();

	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID);

	public List<Arac> kullaniciyaGoreCikisListesi(Long kullaniciID, Integer donemAy, Integer donemYil);

	public List<Arac> ozelPlakayaGoreCikisListesi(String plaka);

	public List<Arac> resmiPlakayaGoreCikisListesi(String plaka);
	public List<Arac> donemAyGetir();

	public List<Arac> donemYilGetir();
	
	public List<Arac> cikisYapanPersonelListesi();

	public boolean ayniGorevdenVarMi(long mahalleID,String tarih, String cikisSaati,String girisSaati);
	public List<Arac> gorevBul(String plaka, String  tarih);
	
	public List<Arac> idCikisListesi(Long cikisID);
	
	public List<Arac> kullaniciyaGorevBul(String plaka, String tarih, Long kullaniciId);
}

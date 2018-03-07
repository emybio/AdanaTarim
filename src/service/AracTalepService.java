/**
 * 
 */
package service;

import java.util.List;

import forms.AracTalep;

/**
 * @author lenovo
 *
 */
public interface AracTalepService {
	public void save(AracTalep arac);

	public void delete(Long id);

	public AracTalep aracCikisGetir(Long id);

	public List<AracTalep> tumAracCikislari();

	public List<AracTalep> kullaniciyaGoreCikisListesi(Long kullaniciID);

	public List<AracTalep> ozelPlakayaGoreCikisListesi(String plaka);

	public List<AracTalep> resmiPlakayaGoreCikisListesi(String plaka);

	public List<AracTalep> cikisYapanPersonelListesi();

	public boolean ayniGorevdenVarMi(long mahalleID, String tarih);

	public List<AracTalep> gorevBul(String tarih);
}

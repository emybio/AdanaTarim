package service.kirsalkalkinma;

import java.util.List;

import org.json.simple.JSONArray;

import forms.kirsalkalkinma.gencciftci.GencCiftci;

public interface GencCiftciService {
	
	public void save(GencCiftci gencCiftci);

	public void delete(Long id);

	public GencCiftci gencCiftciGetir(Long id);

	public List<GencCiftci> tumGencCiftciler();

	public List<GencCiftci> ilceyeGoreListe(String ilce);

	public List<GencCiftci> kategoriyeGoreListe(Integer kategori);

	public List<GencCiftci> ilceListesi();

	public List<GencCiftci> kategoriListesi();

	public JSONArray ilceyeGoreJSON(String ilce);
	
	public Long ilceyeVeYillaraGoreKayitSayisi(Integer yil,String ilce); 

}

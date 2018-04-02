package dao.kirsalkalkinma.gencciftci;

import java.util.List;

import org.json.simple.JSONArray;

import forms.kirsalkalkinma.gencciftci.GencCiftci;

public interface GencCiftciDAO {
	public void save(GencCiftci gencCiftci);

	public void delete(Long id);

	public GencCiftci gencCiftciGetir(Long id);

	public List<GencCiftci> tumGencCiftciler();

	public List<GencCiftci> ilceyeGoreListe(String ilce);

	public List<GencCiftci> kategoriyeGoreListe(Integer kategori);

	public List<GencCiftci> ilceVeKategoriyeGoreListe(String kategori, String ilce, Integer yil);

	public List<GencCiftci> ilceListesi();

	public List<GencCiftci> kategoriListesi();

	public List<GencCiftci> yilListesi();

	public JSONArray ilceyeGoreJSON(String ilce);
<<<<<<< HEAD

	public Long ilceyeVeYillaraGoreKayitSayisi(Integer yil, String ilce);

	public Long ilceyeVeKategoriyeGoreKayitSayisi(Long kategori, Long ilce);

	public List<GencCiftci> yillaraGoreListe(Integer yil);

	public List<GencCiftci> mahalleListesi();
=======
>>>>>>> parent of 75583d4... GencCiftci jQuery to Excel
}

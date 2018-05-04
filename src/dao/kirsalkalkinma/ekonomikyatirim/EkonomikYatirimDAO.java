package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import org.json.simple.JSONArray;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;

public interface EkonomikYatirimDAO {
	public void save(EkonomikYatirim ekonomikYatirim);

	public void delete(Long id);

	public EkonomikYatirim ekonomikYatirimGetir(Long id);

	public List<EkonomikYatirim> tumYatirimListesi();

	public List<EkonomikYatirim> etapNoyaGoreListe(Integer etapNo);

	public List<EkonomikYatirim> ilceyeGoreListe(String ilce);

	public List<EkonomikYatirim> ilceListesi();

	public JSONArray ilceyeGoreJSON(String ilce);

	public List<EkonomikYatirim> etapNoyaGoreGetir(Integer etapNo);

	public boolean kayitVarmi(int kategori, String isim);

	public List<EkonomikYatirim> ilceVeKategoriyeGoreListe(int etapNo, Long kategori);

	public List<EkonomikYatirim> etapNoLisetsi();

	public List<EkonomikYatirim> kategoriLisetsi();

	public List<EkonomikYatirim> projeAdListesi();
}

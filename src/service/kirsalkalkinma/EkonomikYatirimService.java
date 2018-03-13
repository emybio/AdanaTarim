package service.kirsalkalkinma;

import java.util.List;

import org.json.simple.JSONArray;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;

public interface EkonomikYatirimService {
	public void save(EkonomikYatirim ekonomikYatirim);

	public void delete(Long id);

	public EkonomikYatirim ekonomikYatirimGetir(Long id);

	public List<EkonomikYatirim> tumYatirimListesi();

	public List<EkonomikYatirim> etapNoyaGoreListe(Integer etapNo);

	public List<EkonomikYatirim> ilceyeGoreListe(String ilce);

	public List<EkonomikYatirim> ilceListesi();
	
	public JSONArray ilceyeGoreJSON(String ilce);
}

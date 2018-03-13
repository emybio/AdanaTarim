package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;

public interface EkonomikYatirimDAO {
	public void save(EkonomikYatirim ekonomikYatirim);

	public void delete(Long id);

	public EkonomikYatirim ekonomikYatirimGetir(Long id);

	public List<EkonomikYatirim> tumYatirimListesi();
	
	public List<EkonomikYatirim> etapNoyaGoreListe(Integer etapNo);
	
	public List<EkonomikYatirim> ilceyeGoreListe(String ilce);
	
	public List<EkonomikYatirim> ilceListesi();


}

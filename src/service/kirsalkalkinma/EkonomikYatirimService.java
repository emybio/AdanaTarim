package service.kirsalkalkinma;

import java.util.List;

import forms.kirsalkalkinma.EkonomikYatirim;

public interface EkonomikYatirimService {
	public void save(EkonomikYatirim ekonomikYatirim);

	public void delete(Long id);

	public EkonomikYatirim ekonomikYatirimGetir(Long id);
	
	public List<EkonomikYatirim> tumYatirimListesi();

}

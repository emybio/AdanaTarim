package dao.kirsalkalkinma;

import java.util.List;

import forms.kirsalkalkinma.Aricilik;

public interface AricilikDAO {
	
	public void save(Aricilik ari);

	public void delete(Long id);
	
	public Aricilik ariciGetir(Long id);
	
	public List<Aricilik> tumListe();
 
}

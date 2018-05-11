package service.kirsalkalkinma;

import java.util.List;

import forms.kirsalkalkinma.Aricilik;

public interface AricilikService {

	public void save(Aricilik ari);

	public void delete(Long id);

	public Aricilik ariciGetir(Long id);

	public List<Aricilik> tumListe();

}

package dao.kirsalkalkinma.kooperatif;

import java.util.List;

import forms.kirsalkalkinma.kooperatif.Kooperatif;

public interface KooperatifDAO {

	public void save(Kooperatif kooperatif);

	public void delete(Long id);

	public Kooperatif kooperatifGetir(Long id);

	public List<Kooperatif> tumKooperatiflerler();

	public List<Kooperatif> ilceyeGoreKooperatifListesi(Long ilceID);

	public List<Kooperatif> ilceListesi();

	public List<Kooperatif> turListesi();

	public List<Kooperatif> tureGoreKooperatifListesi(Long turID);

	public List<Kooperatif> kooperatifKartiGetir(Long koopId);

}

package dao.kirsalkalkinma.kooperatif;

import java.util.List;

import forms.kirsalkalkinma.kooperatif.KooperatifTuru;

public interface KooperatifTurDAO {

	public void save(KooperatifTuru kooperatifTuru);

	public void delete(Long id);

	public KooperatifTuru kooperatifGetir(Long id);

	public List<KooperatifTuru> tumKooperatiflerler();
}

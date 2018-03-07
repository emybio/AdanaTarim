package service.kirsalkalkinma.kooperatif;

import java.util.List;

import forms.kirsalkalkinma.kooperatif.KooperatifTuru;

public interface KooperatifTurService {
	public void save(KooperatifTuru kooperatif);

	public void delete(Long id);

	public KooperatifTuru kooperatifGetir(Long id);

	public List<KooperatifTuru> tumKooperatiflerler();
}

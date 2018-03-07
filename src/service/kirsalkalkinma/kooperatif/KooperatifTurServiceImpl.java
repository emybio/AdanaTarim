package service.kirsalkalkinma.kooperatif;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.kooperatif.KooperatifTurDAO;
import forms.kirsalkalkinma.kooperatif.KooperatifTuru;

@Service
public class KooperatifTurServiceImpl implements KooperatifTurService {
	@Autowired
	KooperatifTurDAO koopTurDAO;

	@Override
	public void save(KooperatifTuru kooperatif) {
		koopTurDAO.save(kooperatif);

	}

	@Override
	public void delete(Long id) {
		koopTurDAO.delete(id);

	}

	@Override
	public KooperatifTuru kooperatifGetir(Long id) {
		return koopTurDAO.kooperatifGetir(id);
	}

	@Override
	public List<KooperatifTuru> tumKooperatiflerler() {
		return koopTurDAO.tumKooperatiflerler();
	}

}

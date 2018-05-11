package service.kirsalkalkinma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.AricilikDAO;
import forms.kirsalkalkinma.Aricilik;

@Service
public class AricilikServiceImpl implements AricilikService {
	@Autowired
	AricilikDAO ariDAO;

	@Override
	public void save(Aricilik ari) {
		ariDAO.save(ari);
	}

	@Override
	public void delete(Long id) {
		ariDAO.delete(id);
	}

	@Override
	public Aricilik ariciGetir(Long id) {
		return ariDAO.ariciGetir(id);
	}

	@Override
	public List<Aricilik> tumListe() {
		return ariDAO.tumListe();
	}

}

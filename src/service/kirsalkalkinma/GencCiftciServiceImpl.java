package service.kirsalkalkinma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.gencciftci.GencCiftciDAO;
import forms.kirsalkalkinma.gencciftci.GencCiftci;

@Service
public class GencCiftciServiceImpl implements GencCiftciService {

	@Autowired
	private GencCiftciDAO gencCiftciDAO;

	@Override
	public void save(GencCiftci gencCiftci) {
		gencCiftciDAO.save(gencCiftci);

	}

	@Override
	public void delete(Long id) {
		gencCiftciDAO.delete(id);

	}

	@Override
	public GencCiftci gencCiftciGetir(Long id) {
		return gencCiftciDAO.gencCiftciGetir(id);
	}

	@Override
	public List<GencCiftci> tumGencCiftciler() {
		return gencCiftciDAO.tumGencCiftciler();
	}

}

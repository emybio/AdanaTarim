package dao.kirsalkalkinma.gencciftci;

import java.util.List;

import forms.kirsalkalkinma.gencciftci.GencCiftci;

public interface GencCiftciDAO {
	public void save(GencCiftci gencCiftci);

	public void delete(Long id);

	public GencCiftci gencCiftciGetir(Long id);
	
	public List<GencCiftci> tumGencCiftciler();

}

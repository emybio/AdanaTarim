package forms.kirsalkalkinma.ekonomikyatirim;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ekonomik_yatirim_durumu", schema = "public")
public class EkonomikYatirimDurumu implements java.io.Serializable {

	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "ekonomik_yatirim_durumu_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;

	@Column(name = "durum_adi")
	public String durumAdi;

	public EkonomikYatirimDurumu() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDurumAdi() {
		return durumAdi;
	}

	public void setDurumAdi(String durumAdi) {
		this.durumAdi = durumAdi;
	}

	/**
	 * @param id
	 * @param durumAdi
	 */
	public EkonomikYatirimDurumu(String durumAdi) {
		super();
		this.durumAdi = durumAdi;
	}

}

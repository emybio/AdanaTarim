package forms.kirsalkalkinma.ekonomikyatirim;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ekonomik_yatirim_ust_kategori", schema = "public")
public class EkonomikYatirim_Ust_Kategori implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "ekonomik_yatirim_ust_kategori_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;
	
	@Column(name = "adi")
	public String adi;

	public long getId() {
		return id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EkonomikYatirim_Ust_Kategori(String adi) {
		super();
		this.adi = adi;
	}

	public EkonomikYatirim_Ust_Kategori() {
		// TODO Auto-generated constructor stub
	}
}

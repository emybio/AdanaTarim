package forms.kirsalkalkinma.ekonomikyatirim;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ekonomik_yatirim_kategori", schema = "public")
public class EkonomikYatirim_Kategori implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "ekonomik_yatirim_kategori_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;
	@Column(name = "kategori_adi")
	public String kategoriAdi;

	@ManyToOne
	@JoinColumn(name = "ustId")
	private EkonomikYatirim_Ust_Kategori ustKategori;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKategoriAdi() {
		return kategoriAdi;
	}

	public void setKategoriAdi(String kategoriAdi) {
		this.kategoriAdi = kategoriAdi;
	}

	public EkonomikYatirim_Ust_Kategori getUstKategori() {
		return ustKategori;
	}

	public void setUstKategori(EkonomikYatirim_Ust_Kategori ustKategori) {
		this.ustKategori = ustKategori;
	}

	public EkonomikYatirim_Kategori(String kategoriAdi, EkonomikYatirim_Ust_Kategori ustKategori) {
		super();
		this.kategoriAdi = kategoriAdi;
		this.ustKategori = ustKategori;
	}

	public EkonomikYatirim_Kategori() {
		// TODO Auto-generated constructor stub
	}
}

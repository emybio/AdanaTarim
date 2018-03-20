/**
 * 
 */
package forms.kirsalkalkinma.gencciftci;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Emrah Denizer
 *
 */
@Entity
@Table(name = "genc_ciftci_kategori", schema = "public")
// Veri tabanındaki tablo ismi sabitler mi evet sequence ismi on
public class GencCiftciKategori implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "genc_ciftci_kategori_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private long id;

	@Column(name = "isim")
	private String isim;

	@Column(name = "durum")
	private Boolean durum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islemzamani")
	private Date eklemezamani;

	@ManyToOne
	@JoinColumn(name = "katid")
	private GencCiftciKategori tip;

	public GencCiftciKategori getTip() {

		if (tip == null) {
			tip = new GencCiftciKategori();
		}
		return tip;
	}

	public void setTip(GencCiftciKategori tip) {
		this.tip = tip;
	}

	public GencCiftciKategori() {

	}

	public GencCiftciKategori(Long katId) {
		this.id = katId;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public Date getEklemezamani() {
		return eklemezamani;
	}

	public void setEklemezamani(Date eklemezamani) {
		this.eklemezamani = eklemezamani;
	}

	public Boolean getDurum() {
		return durum;
	}

	public void setDurum(Boolean durum) {
		this.durum = durum;
	}

}

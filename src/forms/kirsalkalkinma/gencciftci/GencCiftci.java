package forms.kirsalkalkinma.gencciftci;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import forms.Yerler;

@Entity
@Table(name = "genc_ciftci", schema = "public")
public class GencCiftci implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "genc_ciftci_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;

	@ManyToOne()
	@JoinColumn(name = "ilce_id")
	public Yerler ilce;

	@ManyToOne()
	@JoinColumn(name = "kategori_id")
	public GencCiftciKategori kategori;

	@Column(name = "yil")
	public int yil;

	@Column(name = "proje_sayisi")
	public int projeSayisi;

	@Column(name = "proje_bedeli")
	public long projeBedeli;

	@Column(name = "hibe_tutari")
	public Float hibeTutari;

	@Column(name = "kapasite")
	public int kapasite;

	@Column(name = "kapasite_birim")
	public String kapasiteBirim;

	@Column(name = "yararlanici_adi")
	public String yararlaniciAdi;

	@Column(name = "yararlanici_soyadi")
	public String yararlaniciSoyadi;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Yerler getIlce() {

		if (ilce == null) {

			ilce = new Yerler();

		}
		return ilce;
	}

	public void setIlce(Yerler ilce) {
		this.ilce = ilce;
	}

	public GencCiftciKategori getKategori() {
		if (kategori == null) {

			kategori = new GencCiftciKategori();
		}

		return kategori;
	}

	public void setKategori(GencCiftciKategori kategori) {
		this.kategori = kategori;
	}

	public Integer getYil() {
		return yil;
	}

	public void setYil(int yil) {
		this.yil = yil;
	}

	public int getProjeSayisi() {
		return projeSayisi;
	}

	public void setProjeSayisi(int projeSayisi) {
		this.projeSayisi = projeSayisi;
	}

	public long getProjeBedeli() {
		return projeBedeli;
	}

	public void setProjeBedeli(long projeBedeli) {
		this.projeBedeli = projeBedeli;
	}

	public Float getHibeTutari() {
		return hibeTutari;
	}

	public void setHibeTutari(Float hibeTutari) {
		this.hibeTutari = hibeTutari;
	}

	public int getKapasite() {
		return kapasite;
	}

	public void setKapasite(int kapasite) {
		this.kapasite = kapasite;
	}

	public String getYararlaniciAdi() {
		return yararlaniciAdi;
	}

	public void setYararlaniciAdi(String yararlaniciAdi) {
		this.yararlaniciAdi = yararlaniciAdi;
	}

	public String getYararlaniciSoyadi() {
		return yararlaniciSoyadi;
	}

	public void setYararlaniciSoyadi(String yararlaniciSoyadi) {
		this.yararlaniciSoyadi = yararlaniciSoyadi;
	}

	@Override
	public String toString() {
		return "GencCiftci [ilce=" + ilce + ", kategori=" + kategori + ", yil=" + yil + ", projeSayisi=" + projeSayisi
				+ ", projeBedeli=" + projeBedeli + ", hibeTutari=" + hibeTutari + ", kapasite=" + kapasite + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hibeTutari == null) ? 0 : hibeTutari.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ilce == null) ? 0 : ilce.hashCode());
		result = prime * result + kapasite;
		result = prime * result + ((kategori == null) ? 0 : kategori.hashCode());
		result = prime * result + (int) (projeBedeli ^ (projeBedeli >>> 32));
		result = prime * result + projeSayisi;
		result = prime * result + ((yararlaniciAdi == null) ? 0 : yararlaniciAdi.hashCode());
		result = prime * result + ((yararlaniciSoyadi == null) ? 0 : yararlaniciSoyadi.hashCode());
		result = prime * result + yil;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GencCiftci other = (GencCiftci) obj;
		if (hibeTutari == null) {
			if (other.hibeTutari != null)
				return false;
		} else if (!hibeTutari.equals(other.hibeTutari))
			return false;
		if (id != other.id)
			return false;
		if (ilce == null) {
			if (other.ilce != null)
				return false;
		} else if (!ilce.equals(other.ilce))
			return false;
		if (kapasite != other.kapasite)
			return false;
		if (kategori == null) {
			if (other.kategori != null)
				return false;
		} else if (!kategori.equals(other.kategori))
			return false;
		if (projeBedeli != other.projeBedeli)
			return false;
		if (projeSayisi != other.projeSayisi)
			return false;
		if (yararlaniciAdi == null) {
			if (other.yararlaniciAdi != null)
				return false;
		} else if (!yararlaniciAdi.equals(other.yararlaniciAdi))
			return false;
		if (yararlaniciSoyadi == null) {
			if (other.yararlaniciSoyadi != null)
				return false;
		} else if (!yararlaniciSoyadi.equals(other.yararlaniciSoyadi))
			return false;
		if (yil != other.yil)
			return false;
		return true;
	}

}

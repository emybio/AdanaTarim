package forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "arazi_cikislari3", schema = "public")
public class Arac implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "arazi_cikislari3_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;
	//
	// @ManyToOne()
	// @JoinColumn(name = "personel")
	// private Kullanici kullanici;

	@ManyToOne()
	@JoinColumn(name = "islemyapan")
	private Kullanici islemyapan;

	@Column(name = "tarih")
	private String tarih;

	@Column(name = "resmi_plaka")
	private String resmiPlaka;

	@Column(name = "ozel_plaka")
	private String ozelPlaka;

	@ManyToOne()
	@JoinColumn(name = "ilce")
	private Yerler ilce;

	@ManyToOne()
	@JoinColumn(name = "mahalle")
	private Yerler mahalle;

	@Column(name = "cikis_saati")
	private String cikisSaati;

	@Column(name = "giris_saati")
	private String girisSaati;

	@Column(name = "aciklama")
	private String aciklama;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islemzamani")
	private Date islemZamani;

	@ManyToMany(fetch = FetchType.EAGER) // fetch = FetchType.EAGER,
											// targetEntity = Kullanici.class
	@JoinColumn(name = "personel")
	private List<Kullanici> kullaniciList;

	@Column(name = "donemyil")
	private int donemYil;

	@Column(name = "donemay")
	private int donemAy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Arac(Long id, Kullanici islemyapan, String tarih, String resmiPlaka, String ozelPlaka, Yerler ilce,
			Yerler mahalle, String cikisSaati, String girisSaati, String aciklama, Date islemZamani,
			List<Kullanici> kullaniciList) {
		super();
		this.id = id;
		this.islemyapan = islemyapan;
		this.tarih = tarih;
		this.resmiPlaka = resmiPlaka;
		this.ozelPlaka = ozelPlaka;
		this.ilce = ilce;
		this.mahalle = mahalle;
		this.cikisSaati = cikisSaati;
		this.girisSaati = girisSaati;
		this.aciklama = aciklama;
		this.islemZamani = islemZamani;
		this.kullaniciList = kullaniciList;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getResmiPlaka() {
		return resmiPlaka;
	}

	public void setResmiPlaka(String resmiPlaka) {
		this.resmiPlaka = resmiPlaka;
	}

	public String getOzelPlaka() {
		return ozelPlaka;
	}

	public void setOzelPlaka(String ozelPlaka) {
		this.ozelPlaka = ozelPlaka;
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

	public Yerler getMahalle() {

		if (mahalle == null) {
			mahalle = new Yerler();
		}
		return mahalle;
	}

	public void setMahalle(Yerler mahalle) {
		this.mahalle = mahalle;
	}

	public String getCikisSaati() {
		return cikisSaati;
	}

	public void setCikisSaati(String cikisSaati) {
		this.cikisSaati = cikisSaati;
	}

	public String getGirisSaati() {
		return girisSaati;
	}

	public void setGirisSaati(String girisSaati) {
		this.girisSaati = girisSaati;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Kullanici getIslemyapan() {
		return islemyapan;
	}

	public void setIslemyapan(Kullanici islemyapan) {
		this.islemyapan = islemyapan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Arac [id=" + id + ", islemyapan=" + islemyapan + ", tarih=" + tarih + ", resmiPlaka=" + resmiPlaka
				+ ", ozelPlaka=" + ozelPlaka + ", ilce=" + ilce + ", mahalle=" + mahalle + ", cikisSaati=" + cikisSaati
				+ ", girisSaati=" + girisSaati + ", aciklama=" + aciklama + ", islemZamani=" + islemZamani
				+ ", kullaniciList=" + kullaniciList + "]";
	}

	/**
	 * @return the kullaniciList
	 */
	public List<Kullanici> getKullaniciList() {
		if (kullaniciList == null)
			kullaniciList = new ArrayList<Kullanici>();

		return kullaniciList;
	}

	/**
	 * @param kullaniciList
	 *            the kullaniciList to set
	 */
	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	
	public Arac(Kullanici islemyapan, String tarih, String resmiPlaka, String ozelPlaka, Yerler ilce, Yerler mahalle,
			String cikisSaati, String girisSaati, String aciklama, Date islemZamani, List<Kullanici> kullaniciList) {
		// this.kullanici = kullanici;
		this.islemyapan = islemyapan;
		this.tarih = tarih;
		this.resmiPlaka = resmiPlaka;
		this.ozelPlaka = ozelPlaka;
		this.ilce = ilce;
		this.mahalle = mahalle;
		this.cikisSaati = cikisSaati;
		this.girisSaati = girisSaati;
		this.aciklama = aciklama;
		this.islemZamani = islemZamani;
		this.kullaniciList = kullaniciList;
	}

	public Arac() {
	}

	public int getDonemYil() {
		return donemYil;
	}

	public void setDonemYil(int donemYil) {
		this.donemYil = donemYil;
	}

	/**
	 * @return the donemAy
	 */
	public int getDonemAy() {
		return donemAy;
	}

	/**
	 * @param donemAy
	 *            the donemAy to set
	 */
	public void setDonemAy(int donemAy) {
		this.donemAy = donemAy;
	}

	public Arac(Kullanici islemyapan, String tarih, String resmiPlaka, String ozelPlaka, Yerler ilce) {
		super();
		this.islemyapan = islemyapan;
		this.tarih = tarih;
		this.resmiPlaka = resmiPlaka;
		this.ozelPlaka = ozelPlaka;
		this.ilce = ilce;
	}

	public Arac(Kullanici islemyapan, String resmiPlaka, String ozelPlaka, Yerler ilce, Yerler mahalle,
			String cikisSaati, String girisSaati, String aciklama, Date islemZamani, int donemYil, int donemAy) {
		super();
		this.islemyapan = islemyapan;
		this.resmiPlaka = resmiPlaka;
		this.ozelPlaka = ozelPlaka;
		this.ilce = ilce;
		this.mahalle = mahalle;
		this.cikisSaati = cikisSaati;
		this.girisSaati = girisSaati;
		this.aciklama = aciklama;
		this.islemZamani = islemZamani;
		this.donemYil = donemYil;
		this.donemAy = donemAy;
	}

	public Arac(String adi, String resmiPlaka2, String ozelPlaka2, String isim, String isim2, String cikisSaati2,
			String girisSaati2, String aciklama2, Date islemZamani2, int donemYil2, int donemAy2) {
		// TODO Auto-generated constructor stub
	}

}

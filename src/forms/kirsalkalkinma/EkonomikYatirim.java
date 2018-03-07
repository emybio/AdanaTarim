package forms.kirsalkalkinma;

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

import forms.Kullanici;
import forms.Yerler;

@Entity
@Table(name = "ekonomik_yatirim", schema = "public")
public class EkonomikYatirim implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "ekonomik_yatirim_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;

	@ManyToOne()
	@JoinColumn(name = "ilce_id")
	private Yerler ilce;

	@ManyToOne()
	@JoinColumn(name = "kategori_id")
	private EkonomikYatirim_Kategori kategori;

	@Column(name = "yatirimci_sayisi")
	private int yatirimciSayisi;

	@Column(name = "proje_bedeli")
	private Long projeBedeli;

	@Column(name = "hibe_tutari")
	private Long hibeTutari;

	@Column(name = "kapasite")
	private Integer kapasite;

	@Column(name = "istihdam")
	private Integer istihdam;

	@Column(name = "etap_no")
	private int etapNo;

	@Column(name = "yatirimci_adi")
	private String yatirimciAdi;

	@Column(name = "proje_adi")
	private String projeAdi;

	@Column(name = "kapasite_birim")
	private String kapasiteBirim;

	public String getKapasiteBirim() {
		return kapasiteBirim;
	}

	public void setKapasiteBirim(String kapasiteBirim) {
		this.kapasiteBirim = kapasiteBirim;
	}

	@ManyToOne()
	@JoinColumn(name = "islemyapan_id")
	private Kullanici islemYapan;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islem_zamani")
	private Date islemZamani;

	public int getEtapNo() {
		return etapNo;
	}

	public Kullanici getIslemYapan() {
		if (islemYapan == null) {

			islemYapan = new Kullanici();
		}

		return islemYapan;
	}

	public void setIslemYapan(Kullanici islemYapan) {

		this.islemYapan = islemYapan;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public void setEtapNo(int etapNo) {
		this.etapNo = etapNo;
	}

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

	public EkonomikYatirim_Kategori getKategori() {

		if (kategori == null) {

			kategori = new EkonomikYatirim_Kategori();
		}

		return kategori;
	}

	public void setKategori(EkonomikYatirim_Kategori kategori) {

		this.kategori = kategori;
	}

	public int getYatirimciSayisi() {

		return yatirimciSayisi;
	}

	public void setYatirimciSayisi(int yatirimciSayisi) {
		this.yatirimciSayisi = yatirimciSayisi;
	}

	public Long getProjeBedeli() {
		return projeBedeli;
	}

	public void setProjeBedeli(Long projeBedeli) {
		this.projeBedeli = projeBedeli;
	}

	public Long getHibeTutari() {
		return hibeTutari;
	}

	public void setHibeTutari(Long hibeTutari) {
		this.hibeTutari = hibeTutari;
	}

	public Integer getKapasite() {
		return kapasite;
	}

	public void setKapasite(Integer kapasite) {
		this.kapasite = kapasite;
	}

	public Integer getIstihdam() {
		return istihdam;
	}

	public void setIstihdam(Integer istihdam) {
		this.istihdam = istihdam;
	}

	public String getYatirimciAdi() {
		return yatirimciAdi;
	}

	public void setYatirimciAdi(String yatirimciAdi) {
		this.yatirimciAdi = yatirimciAdi;
	}

	public String getProjeAdi() {
		return projeAdi;
	}

	public void setProjeAdi(String projeAdi) {
		this.projeAdi = projeAdi;
	}

}

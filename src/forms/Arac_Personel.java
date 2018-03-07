/**
 * 
 */
package forms;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author TARIM
 *
 */
@Entity
@Table(name = "arazi_cikislari3_kullanici", schema = "public")
public class Arac_Personel {

	public Arac aracId;
	public Kullanici kullaniciId;

	public Arac getAracId() {
		return aracId;
	}

	public Kullanici getKullaniciId() {
		return kullaniciId;
	}

}

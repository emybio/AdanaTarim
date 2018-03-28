package araclar;

public enum Birimler {

	ATTA("Arazi Toplulaþtýrma ve Tarýmsal Altyapý"), BITKI_KORUMA("Bitkisel Üretim ve Bitki Saðlýðý"), BALIKCILIK(
			"Balýkçýlýk ve Su Ürünleri"), CAYIR_MERA("Çayýr Mera ve Yem Bitkileri"), GIDA(
					"Gýda ve Yem"), HAYVAN_SAGLIGI("Hayvan Saðlýðý ve Yetiþtiriciliði"), IMI(
							"Ýdari ve Mali Ýþler"), KIRSAL_KALKINMA("Kýrsal Kalkýnma ve Örgütlenme");

	private final String birim;

	private Birimler(String birim) {
		this.birim = birim;
	}

	public String getBirim() {
		return birim;
	}

}

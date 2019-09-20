package controllers.hayvansagligi;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import forms.Kullanici;
import forms.hayvansagligi.AsiCesitleri;

public class AsiCesitleriKaydetme {

	public static void main(String[] args) {

		Kullanici kullanici = new Kullanici();
		kullanici.setId(1);

		Date eklemeTarihi = new Date();

		AsiCesitleri asi1 = new AsiCesitleri("Koyun-Keçi Çiçek", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi2 = new AsiCesitleri("Mavi Dil", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi3 = new AsiCesitleri("Koyun-Keçi Vebasý", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi4 = new AsiCesitleri("Þap Trivalan (Küçükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi5 = new AsiCesitleri("Þap Trivalan (Büyükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi6 = new AsiCesitleri("Þap Tetravalan (Büyükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi7 = new AsiCesitleri("Rev1 Brusella (Küçükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi8 = new AsiCesitleri("S-19 Brusella (Büyükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi9 = new AsiCesitleri("Antrax (Büyükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi10 = new AsiCesitleri("Antrax (Küçükbaþ)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi11 = new AsiCesitleri("Antrax (Tek Týrnaklý)", eklemeTarihi.toString(), kullanici);
		AsiCesitleri asi12 = new AsiCesitleri("LSD (Sýðýrlarýn nodüler ekzantemi)", eklemeTarihi.toString(), kullanici);

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg2.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();

		session.save(asi1);
		session.save(asi2);
		session.save(asi3);
		session.save(asi4);
		session.save(asi5);
		session.save(asi6);
		session.save(asi7);
		session.save(asi8);
		session.save(asi9);
		session.save(asi10);
		session.save(asi11);
		session.save(asi12);

		session.beginTransaction();
		System.out.println();
		session.getTransaction().commit();
		session.close();

	}

}

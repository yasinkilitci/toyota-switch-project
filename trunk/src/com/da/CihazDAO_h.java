
package com.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.util.HibernateUtil;

import com.entity.Cihaz;
import com.entity.Tur;
import com.entity.uretici;

public class CihazDAO_h {

	public Cihaz CihazEkle(String ad, int fiyat, int tur_id, int uretici_id){
		
		Cihaz c = new Cihaz();
		
		try {
				c.setAd(ad);
				c.setFiyat(fiyat);
				c.setTur_id(tur_id);
				c.setUretici_id(uretici_id);
				SessionFactory sf = HibernateUtil.getSessionFactory();
				Session session = sf.openSession();
				session.beginTransaction();
				session.save(c);
				session.getTransaction().commit();
				session.close();
		
		} catch (HibernateException h) {
			// TODO Auto-generated catch block
			h.printStackTrace();
			return null;
		}
		return c;
	}
	
	public Cihaz CihazDetayiniGetir(int Cihazid){
		Cihaz f = new Cihaz();
		Connection conn = ConnectionManager.getConnection();
		String query = "SELECT c.id, c.ad, c.fiyat, t.id AS turid, t.ad AS turad, u.id AS urid, u.ad AS urad "+
"FROM cihaz c INNER JOIN tur t ON c.tur_id = t.id INNER JOIN uretici u ON c.uretici_id = u.id WHERE c.id =?";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, Cihazid);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()){
				
			
				uretici uretici = new uretici(rs.getInt("urid"), rs.getString("urad"));
				Tur Tur = new Tur(rs.getInt("turid"), rs.getString("turad"));
				
				 f = new Cihaz(rs.getInt("id"), rs.getString("ad"), rs.getInt("fiyat"),uretici,Tur);
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
		
		
		
		
	}
	
	public ArrayList<Cihaz> butunCihazlariGetir(){
		
		ArrayList<Cihaz> cihazlar = new ArrayList<Cihaz>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Cihaz", "root", "");
			
			String sorgu = "SELECT c.id, c.ad, c.sene, t.id AS turid, t.ad AS turad, u.id AS urid, u.ad AS urad "+
"FROM cihaz c INNER JOIN tur t ON c.tur_id = u.id INNER JOIN uretici u ON c.uretici_id = u.id";

			
			PreparedStatement psmt = conn.prepareStatement(sorgu);
			
			ResultSet rs= psmt.executeQuery(sorgu);
			
			while (rs.next()){
				
				uretici uretici = new uretici(rs.getInt("urid"), rs.getString("urad"));
				Tur Tur = new Tur(rs.getInt("turid"), rs.getString("turad"));
				
				Cihaz f = new Cihaz(rs.getInt("id"), rs.getString("ad"), rs.getInt("fiyat"),uretici,Tur);
				cihazlar.add(f);
				
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	return cihazlar;	
	}
	
	public ArrayList<Cihaz> TureAitcihazlariGetir(int Turid){
		ArrayList<Cihaz> cihazlar = new ArrayList<Cihaz>();
		Connection conn = ConnectionManager.getConnection();
		String query = "SELECT c.id, c.ad, c.fiyat, t.id AS turid, t.ad AS turad, u.id AS urid, u.ad AS urad FROM cihaz c " +
				"INNER JOIN tur t ON c.tur_id = t.id INNER JOIN uretici u ON c.uretici_id = u.id WHERE c.tur_id =?";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, Turid);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()){
				
				uretici uretici = new uretici(rs.getInt("urid"), rs.getString("urad"));
				Tur Tur = new Tur(rs.getInt("urid"), rs.getString("urad"));
				
				Cihaz f = new Cihaz(rs.getInt("id"), rs.getString("ad"), rs.getInt("fiyat"),uretici,Tur);
				cihazlar.add(f);
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cihazlar;
	}
}

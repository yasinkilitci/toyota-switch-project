
package com.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.Cihaz;
import com.entity.Tur;
import com.entity.uretici;

public class CihazDAO {

	public Cihaz CihazEkle(String ad, int fiyat, int Turid, int ureticiid){
		
		Cihaz f = new Cihaz();
		Connection conn = ConnectionManager.getConnection();
		String query = "INSERT INTO Cihaz values(?,?,?,?,?,'')";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, java.sql.Types.NULL);
			psmt.setString(2, ad);
			psmt.setInt(3, fiyat);
			psmt.setInt(4, Turid);
			psmt.setInt(5, ureticiid);
			psmt.executeUpdate(); // execute insert statement
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return f;
		
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


package com.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.entity.Kul;

public class KulDAO {

	public Kul KulEkle(String kuladi, String adsoyad, String adres, int tel, String sifre){
		
		Kul f = new Kul();
		Connection conn = ConnectionManager.getConnection();
		String query = "INSERT INTO user values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setNull(1,java.sql.Types.NULL);
			psmt.setString(2, kuladi);
			psmt.setString(3, adsoyad);
			psmt.setString(4, adres);
			psmt.setInt(5, tel);
			psmt.setInt(6, 0 /* yetki */);
			psmt.setString(7, sifre);
			psmt.executeUpdate(); // execute insert statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return f;
		
	}
	


public void KulGuncelle(int kulid, String adsoyad, String adres, int tel, String sifre){
		
		Connection conn = ConnectionManager.getConnection();
		String query = "UPDATE `user` SET `adsoyad`=?,`adres`=?,`tel`=?,`sifre`=? WHERE `id`=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1,adsoyad);
			psmt.setString(2,adres);
			psmt.setInt(3, tel);
			psmt.setString(4,sifre);
			psmt.setInt(5, kulid);
			psmt.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Hata olduðunda Yapýlacaklar
			e.printStackTrace();
			
		}
	}
	
	
	public Kul LoginYap(String kullanici, String sifre)
	{
		
		String sorgu = "SELECT * FROM user WHERE kuladi=? and sifre=?";
		ResultSet sorguSonuc = null;
		Kul loginyapankullanici = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sorgu);
			psmt.setString(1, kullanici);
			psmt.setString(2, sifre);
			sorguSonuc = psmt.executeQuery();
			
			
			
			if (sorguSonuc.next()){
				loginyapankullanici = new Kul(sorguSonuc.getInt("id"), sorguSonuc.getString("kuladi"), sorguSonuc.getString("adsoyad"),sorguSonuc.getString("adres"),sorguSonuc.getInt("tel"),sorguSonuc.getInt("yetki"),sorguSonuc.getString("sifre"));
				return loginyapankullanici;
			}
		}
		catch(SQLException e){
			
			e.printStackTrace();
			return null;
		}
		catch(Exception d){
			
			d.printStackTrace();
		}
		
		return null;
	}
	
	public Kul KulDetayiniGetir(int Kulid){
		Kul f = new Kul();
		Connection conn = ConnectionManager.getConnection();
		String query = "SELECT * FROM user WHERE id=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, Kulid);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()){
				
				 f = new Kul(rs.getInt("id"), rs.getString("kuladi"), rs.getString("adsoyad"),rs.getString("adres"),rs.getInt("tel"),rs.getInt("yetki"),rs.getString("sifre"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return f;
	}
	
	public ArrayList<Kul> butunKullariGetir(){
		
		ArrayList<Kul> Kullar = new ArrayList<Kul>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Kul", "root", "");
			
			String sorgu = "SELECT * FROM user";

			PreparedStatement psmt = conn.prepareStatement(sorgu);
			
			ResultSet rs= psmt.executeQuery(sorgu);
			
			while (rs.next()){
				
				Kul f = new Kul(rs.getInt("id"), rs.getString("kuladi"), rs.getString("adsoyad"),rs.getString("adres"),rs.getInt("tel"),rs.getInt("yetki"),rs.getString("sifre"));
				Kullar.add(f);
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	return Kullar;	
	}
	
	public ArrayList<Kul> YetkiyeAitKullariGetir(int yetki){
		ArrayList<Kul> Kullar = new ArrayList<Kul>();
		Connection conn = ConnectionManager.getConnection();
		String query = "SELECT * FROM user WHERE yetki=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, yetki);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()){
				
				Kul f = new Kul(rs.getInt("id"), rs.getString("kuladi"), rs.getString("adsoyad"),rs.getString("adres"),rs.getInt("tel"),rs.getInt("yetki"),rs.getString("sifre"));
				Kullar.add(f);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Kullar;
	}
}

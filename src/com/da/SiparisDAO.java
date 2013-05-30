package com.da;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.Cihaz;
import com.entity.Siparis;

public class SiparisDAO {

	/* Þimdilik null döndürür çünkü sipariþ oluþturmadýk */
	public Siparis SiparisEkle(int k_id, int[] adet, ArrayList<Cihaz> sepet){
		
		Siparis yenisip = null;
		/* yeni ID son verilen sipariþ ID'sinden 1 fazlasý olacak
		 * Bu deðere de 2000 taban uygulayýp sipariþNo olarak kullanacaðýz
		 */
		int siparisNo = 1 + 2000 +  sonSiparisIDGetir();
		int sayac = 0;
		for(Cihaz tempcihaz : sepet)
		{
			int cihazid = tempcihaz.getId();
			tekSiparisEkle(k_id,cihazid,adet[sayac],siparisNo);
			sayac++;
		}
		
		return yenisip;
	}

	private boolean tekSiparisEkle(int k_id, int cihazid, int adet, int sipno)
	{
		Connection conn = ConnectionManager.getConnection();
		String query = "INSERT INTO `order`(`k_id`, `sipno`, `urun_id`, `adet`, `onay`,`tarih`) VALUES (?,?,?,?,?,CURDATE())";
		try {
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1, k_id);
			psmt.setInt(2, sipno);
			psmt.setInt(3, cihazid);
			psmt.setInt(4, adet);
			psmt.setInt(5, 0);
			psmt.executeUpdate(); // execute insert statement
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	


	public int sonSiparisIDGetir()
	{
		/* orders tablosundaki maximum id deðerini döndürüyoruz */
		String query = "SELECT MAX(id) AS 'sonid' FROM `order` WHERE 1";
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement psmt = conn.prepareStatement(query);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				return rs.getInt("sonid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<Siparis> kullaniciyaAitSiparisleriGetir(int kul_id)
	{
			ArrayList<Siparis> yenisip = new ArrayList<Siparis>();
			String query = "SELECT u.kuladi AS kuladi, " +
					"o.sipno AS sipno, " +
					"SUM( c.fiyat * o.adet ) AS toplam, " +
					"o.tarih AS tarih, " +
					"o.onay AS onay "+
					"FROM `order` o, cihaz c, user u "+
					"WHERE `k_id` =? "+
					"AND o.urun_id = c.id "+
					"AND o.k_id = u.id "+
					"GROUP BY o.sipno "+
					"ORDER BY o.sipno";
			try
			{
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement psmt = conn.prepareStatement(query);
				psmt.setInt(1, kul_id);
				ResultSet rs = psmt.executeQuery();
					while(rs.next())
					{
						String kuladi = rs.getString("kuladi");
						int sipno = rs.getInt("sipno");
						int toplam = rs.getInt("toplam");
						Date tarih = rs.getDate("tarih");
						int onay = rs.getInt("onay");
						Siparis eklenecek = null;
						eklenecek = new Siparis(kuladi,sipno,toplam,tarih,onay);
						yenisip.add(eklenecek);
					}
			}
			catch(SQLException istisna)
			{
				istisna.printStackTrace();
			}
			
			return yenisip;
	}
	
	public ArrayList<Siparis> tumSiparisleriGetir()
	{
			ArrayList<Siparis> yenisip = new ArrayList<Siparis>();
			String query = "SELECT u.kuladi AS kuladi, " +
					"o.sipno AS sipno, " +
					"SUM( c.fiyat * o.adet ) AS toplam, " +
					"o.tarih AS tarih, " +
					"o.onay AS onay "+
					"FROM `order` o, cihaz c, user u "+
					"WHERE o.urun_id = c.id "+
					"AND o.k_id = u.id "+
					"GROUP BY o.sipno "+
					"ORDER BY o.sipno";
			try
			{
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement psmt = conn.prepareStatement(query);
				ResultSet rs = psmt.executeQuery();
					while(rs.next())
					{
						String kuladi = rs.getString("kuladi");
						int sipno = rs.getInt("sipno");
						int toplam = rs.getInt("toplam");
						Date tarih = rs.getDate("tarih");
						int onay = rs.getInt("onay");
						Siparis eklenecek = null;
						eklenecek = new Siparis(kuladi,sipno,toplam,tarih,onay);
						yenisip.add(eklenecek);
					}
			}
			catch(SQLException istisna)
			{
				istisna.printStackTrace();
			}
			
			return yenisip;
	}
	
	
	public void siparisOnayla(int sipno)
	{
		String query = "UPDATE `order` SET `onay`=1 WHERE `sipno` = ?";
		try
		{
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1,sipno);
			psmt.executeUpdate();
		}
		catch(SQLException istisna)
		{
			istisna.printStackTrace();
		}
	}
	
	public void siparisOnayKaldir(int sipno)
	{
		String query = "UPDATE `order` SET `onay`=0 WHERE `sipno` = ?";
		try
		{
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1,sipno);
			psmt.executeUpdate();
		}
		catch(SQLException istisna)
		{
			istisna.printStackTrace();
		}
	}
	
	public void siparisIptalEt(int sipno)
	{
		String query = "DELETE FROM `order` WHERE `sipno` = ?";
		try
		{
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setInt(1,sipno);
			psmt.executeUpdate();
		}
		catch(SQLException istisna)
		{
			istisna.printStackTrace();
		}
	}
}

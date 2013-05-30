package com.da;

//Turler.add ile Turler ArrayList ine ekleme yapabiliyoruz.
//t�m ba�lant�lar yaz�m esnas�nda try-catch hatas� veriyor ve onlar� try catch i�ine
//almam�z gerekiyor.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.Tur;




public class TurDAO {
	public ArrayList<Tur> butunTurleriGetir(){
		
		ArrayList<Tur> Turler = new ArrayList<Tur>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/networkdb", "root", "");
	
		String query = "select * from Tur";
	
	
		PreparedStatement psmt = conn.prepareStatement(query);
		
		ResultSet rs = psmt.executeQuery();
		while (rs.next()){
			Tur kat = new Tur(rs.getInt("id"),rs.getString("ad"));
			Turler.add(kat);
			
		}
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return Turler;
	}

}
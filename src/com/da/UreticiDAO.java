package com.da;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.entity.uretici;




public class UreticiDAO {
	public ArrayList<uretici> butunureticileriGetir(){
		
		ArrayList<uretici> ureticiler = new ArrayList<uretici>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/networkdb", "root", "");
	
		String query = "select * from uretici";
	
	
		PreparedStatement psmt = conn.prepareStatement(query);
		
		ResultSet rs = psmt.executeQuery();
		while (rs.next()){
			uretici temp = new uretici(rs.getInt("id"),rs.getString("ad"));
			ureticiler.add(temp);
			
		}
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return ureticiler;
	}

}
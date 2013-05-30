package com.da;
//Bu s�n�fta ba�lant�m�z� ba�ka bir yerden direk olarak almak
//ve her seferinde ayn� kodlar� yazmamak i�in Connection d�nd�ren bir metod olu�turduk.
//Java da connectionstring i DriverManager.getConnection() �eklinde veriyoruz.
//Bunun �ncesinde s�n�fa "com.mysql.jdbc.Driver" � eklemek gerekiyor.
//getConnection da parantez i�inde "jdbc:mysql://" k�sm� mysql ve java i�in sabit
//geri kalanlar sunucu adresi ve veritaban� ad�, sunucu kullan�c� ad�, �ifre alanlar�ndan 
//olu�maktad�r.
//Mysql ba�lant�s�n� yapmak i�in k�t�phanemizde mysql-connector un ekli olmas� gerekiyor.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
public static Connection getConnection(){
	Connection conn=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/networkdb", "root", "");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return conn;
	
}
}

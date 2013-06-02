var $j = jQuery.noConflict();


/* EN BAÞTA ÇALIÞAN FONKSÝYON */
$j( function(){

	/*	alert("Kullanici Ekle Geldi!");*/

});
		
	/* KULLANICI EKLEME KISIMLARI */

	$j("#btnKulEkle").click(
			
			function(){
				$j("#hatayeriSignup").html("").slideUp(1);
				
				var a = $j("#kuladi").val();
				var b = $j("#adsoyad").val();
				var c = $j("#adres").val();
				var d = $j("#tel").val();
				var e = $j("#sifre").val();
				var f = $j("#sifre2").val();
				var g = $j("#eposta").val();
				
				
				if((e!=f)){
					$j("#hatayeriSignup").html("Sifreler Uyusmuyor!").slideDown(500);
					return;
				}
				
				
				if((a=="")||(b=="")||(c=="")||(d=="")||(e=="")||(f=="")||(g=="")){
					$j("#hatayeriSignup").html("Bos Deger Girmeyiniz!").slideDown(500);
					return;
				}
				
				var kulEkleForm =
				{
					kuladi: a,
					adsoyad: b,
					adres: c,
					tel: d ,
					sifre: e,
					eposta: g
				};
				
				$j.ajax({
					url: "./kulekle",
					type: "POST",
					datatype: "JSON",
					data: kulEkleForm,
					success: function(data){
						$j("#contentplaceholder").load("a_uyegiris.jsp");
						alert("Kayit Tamamlandi Lutfen Giris Yapiniz!");
					},
					error: function(xhrequest, textStatus, errorThrown){
					
					$j("#hatayeriSignup").html("Hata: " + xhrequest.responseText).slideDown(500);
					},
					timeOut: 8000
					
					});
				
			});
				
				/* KULLANICI GÜNCELLEME KISIMLARI */

				$j("#btnKulGuncelle").click(
						
						function(){
							$j("#hatayeriSignup").html("").slideUp(1);
							
							var a = $j("#kulid").val();
							var b = $j("#adsoyad").val();
							var c = $j("#adres").val();
							var d = $j("#tel").val();
							var e = $j("#sifre").val();
							var f = $j("#sifre2").val();
							var g = $j("#eposta").val();
							
							
							if((e!=f)){
								$j("#hatayeriSignup").html("Sifreler Uyusmuyor!").slideDown(500);
								return;
							}
							
							
							if((a=="")||(b=="")||(c=="")||(d=="")||(e=="")||(f=="")||(g=="")){
								$j("#hatayeriSignup").html("Bos Deger Girmeyiniz!").slideDown(500);
								return;
							}
							
							var kulEkleForm =
							{
								kulid: a,
								adsoyad: b,
								adres: c,
								tel: d,
								sifre: e,
								eposta: g
							};
							
							$j.ajax({
								url: "./kulekle",
								type: "POST",
								datatype: "JSON",
								data: kulEkleForm,
								success: function(data){
									$j("#contentplaceholder").load("a_anaicreik.jsp");
									alert("Güncelleme Baþarýyla Tamamlandý!");
								},
								error: function(xhr, textStatus, errorThrown){
								
								$j("#hatayeriSignup").html("Error: " + errorThrown + "\n" + "Status: " + textStatus).slideDown(500);
								},
								timeOut: 8000
								
								});	
				
		
				
			});
			
	
	
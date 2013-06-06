var $j = jQuery.noConflict();


/* EN BAÞTA ÇALIÞAN FONKSÝYON */
$j( function(){
	
		$j("#sidemenu").hide();
		$j("#mainmenu").hide();
		$j("#footer").hide();
		$j("#banner").hide();
		$j("#contentplaceholder").hide();
		$j("#contentplaceholder").load("a_anaicerik.jsp");
});

/* BELGE HAZIR OLDUÐUNDA ÇALIÞACAKLAR */
$j(document).ready( function(){

	$j("#banner").fadeIn(1000);
	$j("#mainmenu").show(400,function(){
		
				$j("#sidemenu").fadeIn(200,function(){
					
					$j("#contentplaceholder").fadeIn(200,function(){
								
						$j("#footer").fadeIn(200);
						
					});
					
				});
		
		});
	
	/* MENÜ BUTONLARININ ÝÞLEVLERÝ BURADA */
	
	$j("#menu_hak").click(function(){
		
			$j("#contentplaceholder").load("a_hakkimizda.jsp");
	});
	
	$j("#menu_giris").click(function(){
			
			$j("#contentplaceholder").load("a_uyegiris.jsp");
	});	
	
	$j("#menu_anasayfa").click(function(){
		
		$j("#contentplaceholder").load("a_anaicerik.jsp");
	});	
	
	$j("#menu_cihazekle").click(function(){
		
		$j("#contentplaceholder").load("./Pages_Admin/a_cihazekle.jsp");
	});	
	
	$j("#menu_kulekle").click(function(){
			
			$j("#contentplaceholder").load("a_kulekle.jsp");
		});	
	
	$j("#menu_siparisonay").click(function(){
		$j("#contentplaceholder").load("./Pages_Admin/a_srmgor.jsp");
	});	
	
	$j("#menu_siparisgor").click(function(){
		
		$j("#contentplaceholder").load("./Pages_User/a_srmgor.jsp");
	});	
	
	$j("#menu_bilgun").click(function(){
		
		$j("#contentplaceholder").load("a_kulekle.jsp");
	});
	
	$j("#menu_cihazara").click(function(){
		
		$j("#contentplaceholder").load("./Essentials/a_cihazara.jsp");
	});
	
});
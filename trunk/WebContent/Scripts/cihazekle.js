var $j = jQuery.noConflict();


/* CÝHAZ EKLEME KISIMLARI */

$j("#btnCihazEkle").click(
		
		function(){
			
			$j("#hatayeriCihazEkle").html("").slideUp(1);
			
			var b = $j("#cihazad").val();
			var c = $j("#cihazip").val();
			var d = $j("#cbTur").val();
			var e = $j("#cbUretici").val();
			
			if((b=="")||(c=="")||(d=="")||(e=="")){
				$j("#hatayeriCihazEkle").html("Bos Deger Girmeyiniz!").slideDown(500);
				return;
			}
			
			var formdata = {
					cihazad: b,
					cihazip: c,
					cihaztur: d,
					cihazuretici: e
				};
			
			$j.ajax({
			url: "./cihazekle",
			type: "POST",
			datatype: "JSON",
			data: formdata,
			success: function(data){
				alert("Cihaz Basariyla Eklendi!");
				$j("#cihazad").val("");
				$j("#cihazip").val("");
				$j("#cihaztur").val("");
				$j("#cihazuretici").val("");
			},
			error: function(request, textStatus, errorThrown){
				$j("#hatayeriCihazEkle").html("Hata: " + request.responseText).slideDown(500);
			}
			
			});
			
		});
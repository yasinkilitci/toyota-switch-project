package org.spring.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* Bu s�n�f� biz olu�turduk. S�rekli ayn� kodlarla yeni bir ApplicationContext olu�turmak yerine
 * static de�i�keni d�nd�rd�k.
 */
public class SpringFactoryProvider {
	
	/* En ba�ta �al��t��� i�in �nce bir kez olu�turuluyor... */ 
	private static final AbstractApplicationContext context = buildApplicationContext();
	
			private static AbstractApplicationContext buildApplicationContext()
			{
				return new ClassPathXmlApplicationContext("/main/resources/spring.cfg.xml");
			}
			
			/* ...Sonra talep edildi�inde hep ayn� �rnek d�nd�r�l�yor */
			public static AbstractApplicationContext getApplicationContext()
			{
				return context;
			}
	
}

package org.spring.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* Bu sýnýfý biz oluþturduk. Sürekli ayný kodlarla yeni bir ApplicationContext oluþturmak yerine
 * static deðiþkeni döndürdük.
 */
public class SpringFactoryProvider {
	
	/* En baþta çalýþtýðý için önce bir kez oluþturuluyor... */ 
	private static final AbstractApplicationContext context = buildApplicationContext();
	
			private static AbstractApplicationContext buildApplicationContext()
			{
				return new ClassPathXmlApplicationContext("/main/resources/spring.cfg.xml");
			}
			
			/* ...Sonra talep edildiðinde hep ayný örnek döndürülüyor */
			public static AbstractApplicationContext getApplicationContext()
			{
				return context;
			}
	
}

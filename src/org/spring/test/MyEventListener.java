package org.spring.test;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class MyEventListener implements ApplicationListener<DrawEvent> {

	@Override
	public void onApplicationEvent(DrawEvent arg0) {
		System.out.println(arg0.toString());
	}



}

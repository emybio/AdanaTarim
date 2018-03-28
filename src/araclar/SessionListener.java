package araclar;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(15 * 60); // in seconds

		System.out.println("session created : " + !event.getSession().isNew());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("session invalidate : " + !event.getSession().isNew());
	}
	
	
}

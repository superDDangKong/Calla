//package project.spring.calla.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//@WebListener
//public class SessionManager implements HttpSessionListener{
//	
//	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
//	
//	private Map<String, HttpSession> loginSessions = new HashMap<>();
//	
//    @Override
//    public void sessionCreated(HttpSessionEvent se) {
//    	logger.info("session created: " + se.getSession().getId());
//        HttpSession session = se.getSession();
//        String sessionId = session.getId();
//        loginSessions.put(sessionId, session);
//        logger.info("manager " + loginSessions.toString());
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent se) {
//    	logger.info("session destroyed: " + se.getSession().getId());
//        HttpSession session = se.getSession();
//        String sessionId = session.getId();
//        loginSessions.remove(sessionId);
//    }
//    
//    public Map<String, HttpSession> getLoginSessions() {
//    	return loginSessions;
//    }
//}

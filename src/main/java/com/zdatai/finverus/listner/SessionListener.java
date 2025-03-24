package com.zdatai.finverus.listner;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        HttpSessionListener.super.sessionCreated(se);
        log.info("*** Session Created : {} ***", se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        HttpSessionListener.super.sessionDestroyed(se);
        log.info("*** Session Destroyed : {} ***", se.getSession().getId());
    }
}

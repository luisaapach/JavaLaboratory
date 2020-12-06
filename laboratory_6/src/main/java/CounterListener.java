/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

import Beans.CounterManager;

import javax.servlet.http.*;

/** Listener that keeps track of the number of sessions
 * that the Web application is currently using.
 */

public class CounterListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {
        CounterManager.getSingletonInstance().increment();
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        CounterManager.getSingletonInstance().decrement();
    }

}

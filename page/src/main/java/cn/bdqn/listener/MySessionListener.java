package cn.bdqn.listener;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

@Component
public class MySessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {

    }
}

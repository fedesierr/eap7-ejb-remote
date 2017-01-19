package org.jboss.as.quickstarts.ejb.interceptor;


import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.net.InetAddress;
import java.security.Principal;

import org.jboss.remoting3.Connection;
import org.jboss.remoting3.security.InetAddressPrincipal;
import org.jboss.as.security.remoting.RemotingContext;


public class ClientIpInterceptor {

    @AroundInvoke
    private Object iAmAround(final InvocationContext invocationContext) throws Exception {
        InetAddress remoteAddr = null;
        Connection connection = RemotingContext.getConnection();

        for (Principal p : connection.getPrincipals()) {
            if (p instanceof InetAddressPrincipal) {
                remoteAddr = ((InetAddressPrincipal) p).getInetAddress();
                break;
            }
        }

        System.out.println("IP " + remoteAddr);

        return invocationContext.proceed();
    }
}

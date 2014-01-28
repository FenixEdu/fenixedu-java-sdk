package org.fenixedu.sdk;

public interface Dispatcher {

    void invokeLater(Runnable task);

    void shutdown();
}
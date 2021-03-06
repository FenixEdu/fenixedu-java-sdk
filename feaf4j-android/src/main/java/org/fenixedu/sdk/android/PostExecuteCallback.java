package org.fenixedu.sdk.android;

public interface PostExecuteCallback<T> {

    public void onPostExecute(T result);

    public void onException(Throwable e);

}

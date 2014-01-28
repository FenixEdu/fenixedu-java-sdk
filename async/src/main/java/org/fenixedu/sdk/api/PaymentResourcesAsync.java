package org.fenixedu.sdk.api;

import pt.ist.fenixedu.sdk.exception.FenixEduClientException;

public interface PaymentResourcesAsync {

    void getPersonPayments() throws FenixEduClientException;

}

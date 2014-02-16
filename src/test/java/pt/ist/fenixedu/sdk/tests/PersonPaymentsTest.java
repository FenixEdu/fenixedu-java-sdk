package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.FenixPayment;

public class PersonPaymentsTest extends FenixEduTestCase {

	public PersonPaymentsTest() {
	}

	public PersonPaymentsTest(String name) {
		super(name);
	}

	public void testNotNull() {
		FenixPayment payment = getClient().getPersonPayments();
		
		assertNotNull("Payment is null", payment);
		assertNotNull("Not payed list is null", payment.getPending());
		
		for(FenixPayment.NotPayedEvent notPayed : payment.getPending()) {
			assertNotNull("Not payed Amount is null", notPayed.getAmount());
			assertNotNull("Not payed Description is null", notPayed.getDescription());
			assertNotNull("Not payed period is null", notPayed.getPaymentPeriod());
			assertNotNull("Not payed Entity is null", notPayed.getEntity());
			assertNotNull("Not payed Reference is null", notPayed.getReference());
		}
		
		for(FenixPayment.PaymentEvent payed : payment.getCompleted()) {
			assertNotNull("Payed amount is null", payed.getAmount());
			assertNotNull("Payed date is null", payed.getDate());
			assertNotNull("Payed description is null", payed.getDescription());
			assertNotNull("Payed type is null", payed.getType());
		}
	}
}

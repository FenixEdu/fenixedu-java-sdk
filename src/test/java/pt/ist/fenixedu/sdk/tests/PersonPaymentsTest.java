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
		assertNotNull("Not payed list is null", payment.getNotPayed());
		
		for(FenixPayment.NotPayedEvent notPayed : payment.getNotPayed()) {
			assertNotNull("Not payed Amount is null", notPayed.getAmount());
			assertNotNull("Not payed Description is null", notPayed.getDescription());
			assertNotNull("Not payed End date is null", notPayed.getEndDate());
			assertNotNull("Not payed Entity is null", notPayed.getEntity());
			assertNotNull("Not payed Reference is null", notPayed.getReference());
			assertNotNull("Not payed Start date is null", notPayed.getStartDate());
		}
		
		for(FenixPayment.PaymentEvent payed : payment.getPayed()) {
			assertNotNull("Payed amount is null", payed.getAmount());
			assertNotNull("Payed date is null", payed.getDate());
			assertNotNull("Payed description is null", payed.getDescription());
			assertNotNull("Payed name is null", payed.getName());
		}
	}
}

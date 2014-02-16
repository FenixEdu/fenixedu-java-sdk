package pt.ist.fenixedu.sdk.beans;

import java.util.List;

public class FenixPayment {

    public static class PaymentEvent {
        private String amount;
        private String description;
        private String date;
        private String type;

        public PaymentEvent(String amount, String description, String date) {
            super();
            this.amount = amount;
            this.description = description;
            this.date = date;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
        
    }

    public static class NotPayedEvent {
    	private String description;
    	private PaymentPeriod paymentPeriod;
    	private String entity;
    	private String reference;
    	private String amount;
        
        public static class PaymentPeriod {
        	private String start;
        	private String end;
        	
        	public PaymentPeriod() {
        	}

			public String getStart() {
				return start;
			}

			public void setStart(String start) {
				this.start = start;
			}

			public String getEnd() {
				return end;
			}

			public void setEnd(String end) {
				this.end = end;
			}
        }

        public NotPayedEvent(String description, PaymentPeriod paymentPeriod, String entity, String reference, String amount) {
            super();
            this.description = description;
            this.paymentPeriod = paymentPeriod;
            this.entity = entity;
            this.reference = reference;
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public PaymentPeriod getPaymentPeriod() {
			return paymentPeriod;
		}

		public void setPaymentPeriod(PaymentPeriod paymentPeriod) {
			this.paymentPeriod = paymentPeriod;
		}

		public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

    }

    private List<PaymentEvent> completed;
    private List<NotPayedEvent> pending;

    public FenixPayment(List<PaymentEvent> completed, List<NotPayedEvent> pending) {
        super();
        this.completed = completed;
        this.pending = pending;
    }

    public List<PaymentEvent> getCompleted() {
        return completed;
    }

    public void setPayed(List<PaymentEvent> completed) {
        this.completed = completed;
    }

    public List<NotPayedEvent> getPending() {
        return pending;
    }

    public void setNotPayed(List<NotPayedEvent> pending) {
        this.pending = pending;
    }

}

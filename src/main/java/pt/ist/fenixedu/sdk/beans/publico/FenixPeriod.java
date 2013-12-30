package pt.ist.fenixedu.sdk.beans.publico;

public class FenixPeriod {

    private String start;
    private String end;

    public FenixPeriod(final String start, final String end) {
        this.start = start;
        this.end = end;
    }

    public FenixPeriod() {
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

package org.fenixedu.sdk.domain;

public enum EnrolAction {

    ENROL("yes"), WITHDRAW("no");

    private String action;

    private EnrolAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }

}

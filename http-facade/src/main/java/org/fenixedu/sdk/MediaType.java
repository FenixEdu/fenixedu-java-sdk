package org.fenixedu.sdk;

public enum MediaType {

    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"), APPLICATION_JSON("application/json");

    private String mediaType;

    private MediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

}

package pt.ist.fenixedu.sdk.beans.publico;

public class FenixAbout {

    public static class FenixRSSFeed {
        String description;
        String url;
        
        public FenixRSSFeed() {
        }

        public FenixRSSFeed(final String description, final String uri) {
            this.description = description;
            this.url = uri;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUri(String uri) {
            this.url = uri;
        }
    }

    private String institutionName = null;
    private String institutionUrl = null;
    private FenixRSSFeed[] rssFeeds;

    private FenixAbout() {
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionUrl() {
        return institutionUrl;
    }

    public void setInstitutionUrl(String institutionUrl) {
        this.institutionUrl = institutionUrl;
    }

    public FenixRSSFeed[] getRssFeeds() {
        return rssFeeds;
    }

    public void setRssFeeds(FenixRSSFeed[] rssFeeds) {
        this.rssFeeds = rssFeeds;
    }

}

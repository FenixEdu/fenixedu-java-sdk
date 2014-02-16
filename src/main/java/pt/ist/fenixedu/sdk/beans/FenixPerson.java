package pt.ist.fenixedu.sdk.beans;

import java.util.List;
import java.util.Set;

public class FenixPerson {

    public static class FenixPhoto {
        private String type;
        private String data;
        
        public FenixPhoto() {
        }

        public FenixPhoto(String type, String data) {
            super();
            this.type = type;
            this.data = data;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }

    public enum FenixRoleType {
        TEACHER, ALUMNI, STUDENT;
    }

    public static class FenixRole {
    	public FenixRole() {
    	}

    }

    public static class TeacherFenixRole extends FenixRole {

        private String department;

        public TeacherFenixRole(String department) {
            this.department = department;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

    }

    public static class StudentFenixRole extends FenixRole {

        private List<String> degrees;
        
        public StudentFenixRole() {
        }

        public StudentFenixRole(List<String> degrees) {
            this.degrees = degrees;
        }

        public List<String> getDegrees() {
            return degrees;
        }

        public void setDegrees(List<String> degrees) {
            this.degrees = degrees;
        }

    }

    public static class AlumniFenixRole extends FenixRole {

        public AlumniFenixRole() {
        }

    }

    private String campus;
    private Set<FenixRole> roles;
    private FenixPhoto photo;

    private String name;
    private String username;
    private String email;

    private List<String> personalEmails;
    private List<String> workEmails;
    private List<String> webAddresses;
    private List<String> workWebAddresses;
    
    public FenixPerson() {
    }

    public FenixPerson(String campus, Set<FenixRole> roles, FenixPhoto photo, String name, String username, String email,
            List<String> personalEmails, List<String> workEmails, List<String> webAddresses, List<String> workWebAddresses) {
        super();
        this.campus = campus;
        this.roles = roles;
        this.photo = photo;
        this.name = name;
        this.username = username;
        this.email = email;
        this.personalEmails = personalEmails;
        this.workEmails = workEmails;
        this.webAddresses = webAddresses;
        this.workWebAddresses = workWebAddresses;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Set<FenixRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<FenixRole> roles) {
        this.roles = roles;
    }

    public FenixPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(FenixPhoto photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPersonalEmails() {
        return personalEmails;
    }

    public void setPersonalEmails(List<String> personalEmails) {
        this.personalEmails = personalEmails;
    }

    public List<String> getWorkEmails() {
        return workEmails;
    }

    public void setWorkEmails(List<String> workEmails) {
        this.workEmails = workEmails;
    }

    public List<String> getWebAddresses() {
        return webAddresses;
    }

    public void setWebAddresses(List<String> webAddresses) {
        this.webAddresses = webAddresses;
    }

    public List<String> getWorkWebAddresses() {
        return workWebAddresses;
    }

    public void setWorkWebAddresses(List<String> workWebAddresses) {
        this.workWebAddresses = workWebAddresses;
    }
}

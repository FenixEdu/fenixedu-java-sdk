package pt.ist.fenixedu.sdk.beans.publico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FenixSchedule {

    public static class FenixCourseLoad {

        private String type;
        private BigDecimal totalQuantity;
        private BigDecimal unitQuantity;

        public FenixCourseLoad() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigDecimal getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(BigDecimal totalQuantity) {
            this.totalQuantity = totalQuantity;
        }

        public BigDecimal getUnitQuantity() {
            return unitQuantity;
        }

        public void setUnitQuantity(BigDecimal unitQuantity) {
            this.unitQuantity = unitQuantity;
        }
    }

    public static class FenixRoom {

        private String name;
        private String id;

        public FenixRoom() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public static class FenixLessonOccurence extends FenixInterval {

        private FenixRoom room;

        public FenixLessonOccurence() {
        }

        public FenixRoom getRoom() {
            return room;
        }

        public void setRoom(FenixRoom room) {
            this.room = room;
        }

    }

    public static class FenixShift {

        String name;
        List<String> types = new ArrayList<String>();
        List<FenixLessonOccurence> lessons = new ArrayList<FenixLessonOccurence>();

        public FenixShift() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<FenixLessonOccurence> getLessons() {
            return lessons;
        }

        public void setLessons(List<FenixLessonOccurence> lessons) {
            this.lessons = lessons;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

    }

    List<FenixInterval> lessonPeriods = new ArrayList<FenixInterval>();
    List<FenixCourseLoad> courseLoads = new ArrayList<FenixCourseLoad>();
    List<FenixShift> shifts = new ArrayList<FenixShift>();

    public FenixSchedule() {
    }

    public List<FenixInterval> getLessonPeriods() {
        return lessonPeriods;
    }

    public void setLessonPeriods(List<FenixInterval> lessonPeriods) {
        this.lessonPeriods = lessonPeriods;
    }

    public List<FenixCourseLoad> getCourseLoads() {
        return courseLoads;
    }

    public void setCourseLoads(List<FenixCourseLoad> courseLoads) {
        this.courseLoads = courseLoads;
    }

    public List<FenixShift> getShifts() {
        return shifts;
    }

    public void setShifts(List<FenixShift> shifts) {
        this.shifts = shifts;
    }

}

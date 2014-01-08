package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.publico.FenixSpace;

public class SpaceTest extends FenixEduTestCase {
    private final String campusId = "2465311230081";
    private final String buildingId = "2972117371245";
    private final String floorId = "2723009268080";
    private final String roomId = "";
    private final String day = "08/01/2014";

    public SpaceTest() {
    }

    public SpaceTest(String name) {
        super(name);
    }

    public void testNotNull() {
        assertNotNull(getClient().getSpace(campusId, day, FenixSpace.Campus.class));

        assertNotNull(getClient().getSpace(buildingId, day, FenixSpace.Building.class));

        assertNotNull(getClient().getSpace(floorId, day, FenixSpace.Floor.class));
    }

    public void assertNotNull(FenixSpace.Campus campus) {
        assertNotNull("Campus name is null", campus.getName());
        assertNotNull("Campus id is null", campus.getId());
        assertNotNull("Campus type is null", campus.getType());
        assertNotNull("Campus contained spaces is null", campus.getContainedSpaces());
        //assertNotNull("Campus parent space is null", campus.getParentSpace());
    }

    //public void assertNotNull(FenixSpace space) {
    //fail("Didn't call the specific asserts " + space.getName());
//	}

    public void assertNotNull(FenixSpace.Building building) {
        assertNotNull("Building name is null", building.getName());
        assertNotNull("Building id is null", building.getId());
        assertNotNull("Building type is null", building.getType());
        assertNotNull("Building contained spaces is null", building.getContainedSpaces());
        assertNotNull("Building parent space is null", building.getParentSpace());
    }

    public void assertNotNull(FenixSpace.Floor floor) {
        assertNotNull("Floor name is null", floor.getName());
        assertNotNull("Floor id is null", floor.getId());
        assertNotNull("Floor type is null", floor.getType());
        assertNotNull("Floor contained spaces is null", floor.getContainedSpaces());
        assertNotNull("Floor parent space is null", floor.getParentSpace());
    }

    public void assertNotNull(FenixSpace.Room room) {
        assertNotNull("Room name is null", room.getName());
        assertNotNull("Room id is null", room.getId());
        assertNotNull("Room type is null", room.getType());
        assertNotNull("Room contained spaces is null", room.getContainedSpaces());
        assertNotNull("Room parent space is null", room.getParentSpace());
        assertNotNull("Room Exam Capacity is null", room.getExamCapacity());
        assertNotNull("Room Normal capacity is null", room.getNormalCapacity());
        assertNotNull("Room events list is null", room.getEvents());
    }
}

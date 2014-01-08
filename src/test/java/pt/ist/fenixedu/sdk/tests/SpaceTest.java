package pt.ist.fenixedu.sdk.tests;

import pt.ist.fenixedu.sdk.beans.publico.FenixSpace;

public class SpaceTest extends FenixEduTestCase {
	private final String campusId = "2465311230081";
	private final String buildingId = "2972117371245";
	private final String floorId = "";
	private final String roomId = "";
	private final String day = "";

	public SpaceTest() {
	}

	public SpaceTest(String name) {
		super(name);
	}

	public void testNotNull() {
		 assertNotNull(getClient().getSpace(campusId, day, FenixSpace.Campus.class));
		 
		 assertNotNull(getClient().getSpace(buildingId, day, FenixSpace.Building.class));
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
		
	}
	
	public void assertNotNull(FenixSpace.Floor floor) {
		
	}
	
	public void assertNotNull(FenixSpace.Room room) {
		
	}
}

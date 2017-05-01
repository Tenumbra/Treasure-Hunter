package org.csc301;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(gridTest.class);
		suite.addTestSuite(heapTest.class);
		suite.addTestSuite(nodeTest.class);
		suite.addTestSuite(treasureHuntTest.class);
		// $JUnit-END$
		return suite;
	}

}

package locker2

import grails.test.AbstractCliTestCase

class SpringManipServiceTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testLocker2SpringManipService() {

        execute(["locker2-spring-manip-service"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}

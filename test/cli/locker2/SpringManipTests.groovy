package locker2

import grails.test.AbstractCliTestCase

class SpringManipTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testLocker2SpringManip() {

        execute(["locker2-spring-manip"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}

package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_InserzioniOfferteCurriculum.class);
        suite.addTestSuite(TestR2_Competenze.class);
        suite.addTestSuite(TestR3_IncontroDomandaOfferta.class);
        suite.addTestSuite(TestR4_CaricamentoDaFile.class);
        //$JUnit-END$
        return suite;
    }

}

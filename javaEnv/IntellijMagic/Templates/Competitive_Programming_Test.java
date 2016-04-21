package ${PACKAGE_NAME};

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;

@RunWith(JUnitParamsRunner.class)
public class Test${NAME} {

    private static final ${NAME} INSTANCE = new ${NAME}();

    @BeforeClass
    public static void initialize() {
    }

    @AfterClass
    public static void closing() {
    }

    @Test
    @Parameters(source = Test${NAME}Data.class)
    @TestCaseName("[{index}]")
    public void testRun(String inputString, String expectedOutput) throws UnsupportedEncodingException {
        InputStream is = new ByteArrayInputStream(inputString.getBytes("UTF-8"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        INSTANCE.run(is, ps);

        String actualOutput = baos.toString("UTF-8");
        Assert.assertEquals("Wrong output.", expectedOutput, actualOutput);
    }

    @Test
    @Parameters(method = "ParametersForSampleTest")
    @TestCaseName("[{index}]")
    public void sampleTest(final int a, final int b, final int c) {
        // put asserts here.
    }

    private Object[] ParametersForSampleTest() {
        return new Object[]{
            new Object[]{1,2,3}
        };
    }
}

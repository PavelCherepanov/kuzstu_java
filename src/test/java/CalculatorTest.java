import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuzstu.Calculator;

public class CalculatorTest {
    private Calculator objCalcUnderTest;

    @Before
    public void setUp() {
        objCalcUnderTest = new Calculator();
    }

    @Test
    public void testSum() {
        int a = 1;
        int b = 2;
        int expectedResult = 3;
        int result = objCalcUnderTest.sum(a, b);// значение которое ожидается
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testSubtract() {
        int a = 24;
        int b = 2;
        int expectedResult = 22;// значение которое ожидается
        int result = objCalcUnderTest.subtract(a, b);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testMultiply() {
        int a = 5;
        int b = 6;
        int expectedResult = 30; // значение которое ожидается
        int result = objCalcUnderTest.multiply(a, b);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testDivide() {
        int a = 56;
        int b = 10;
        double expectedResult = 5.6;
        double result = objCalcUnderTest.divide(a, b); // значение которое ожидается
        Assert.assertEquals(expectedResult, result,0.00005);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        int a = 15;
        int b = 0;
        objCalcUnderTest.divide(a, b);
    }
}

package com.app.calc;
import org.junit.jupiter.api.Test;
import com.app.calc.service.CalcService;
import static org.junit.jupiter.api.Assertions.*;

class CalcServiceTest {

    private final CalcService objCalcService = new com.app.calc.service.CalcService();

    @Test
    void testAdd() {
        double result = objCalcService.add(5, 3);
        assertEquals(8, result, "Addition result should be 8");
    }

    @Test
    void testSubtract() {
        double result = objCalcService.subtract(10, 4);
        assertEquals(6, result, "Subtraction result should be 6");
    }

    @Test
    void testMultiply() {
        double result = objCalcService.multiply(7, 6);
        assertEquals(42, result, "Multiplication result should be 42");
    }

    @Test
    void testDivide() {
        double result = objCalcService.divide(20, 4);
        assertEquals(5, result, "Division result should be 5");
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	objCalcService.divide(10, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}
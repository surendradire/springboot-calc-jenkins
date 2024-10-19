package com.app.calc;
import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.app.calc.controller.CalcController;
import com.app.calc.service.CalcService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CalcController.class)
class CalcControllerTest {

	//private final CalcService objCalcService = new com.app.calc.service.CalcService();
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalcService CalcService;

    @Test
    void testShowForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"));
    }

    @Test
    void testAddOperation() throws Exception {
        when(CalcService.add(5, 3)).thenReturn(8.0);

        mockMvc.perform(post("/calculate")
                .param("num1", "5")
                .param("num2", "3")
                .param("operation", "add"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("result", 8.0));
    }

    @Test
    void testSubtractOperation() throws Exception {
        when(CalcService.subtract(10, 4)).thenReturn(6.0);

        mockMvc.perform(post("/calculate")
                .param("num1", "10")
                .param("num2", "4")
                .param("operation", "subtract"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("result", 6.0));
    }

    @Test
    void testMultiplyOperation() throws Exception {
        when(CalcService.multiply(7, 6)).thenReturn(42.0);

        mockMvc.perform(post("/calculate")
                .param("num1", "7")
                .param("num2", "6")
                .param("operation", "multiply"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("result", 42.0));
    }

  // This has been commented.123
    /*
    @Test
    void testDivideOperation() throws Exception {
        when(CalcService.divide(20, 4)).thenReturn(5.0);
        CalcService.perform(post("/calculate")
                .param("num1", "20")
                .param("num2", "4")
                .param("operation", "divide"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("result", 5.0));
        
    } */
    
    
  @Test
    void testDivideByZero() throws Exception {
        when(CalcService.divide(10, 0)).thenThrow(new IllegalArgumentException("Cannot divide by zero"));

        mockMvc.perform(post("/calculate")
                .param("num1", "10")
                .param("num2", "0")
                .param("operation", "divide"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("error", "Cannot divide by zero"));
    }
}

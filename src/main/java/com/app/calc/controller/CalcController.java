package com.app.calc.controller;
import com.app.calc.service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {

    @Autowired
    private CalcService CalcService;

    @GetMapping("/")
    public String showForm() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {

        double result = 0;

        switch (operation) {
            case "add":
                result = CalcService.add(num1, num2);
                break;
            case "subtract":
                result = CalcService.subtract(num1, num2);
                break;
            case "multiply":
                result = CalcService.multiply(num1, num2);
                break;
            case "divide":
                try {
                    result = CalcService.divide(num1, num2);
                } catch (IllegalArgumentException e) {
                    model.addAttribute("error", e.getMessage());
                    return "calculator";
                }
                break;
        }

        model.addAttribute("result", result);
        return "calculator";
    }
}

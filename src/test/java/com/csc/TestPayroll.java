package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPayroll {

    Payroll payroll;

    @BeforeEach
    void setUp() {
        payroll = new Payroll();
    }

    @Test
    void testCalculateGrossPay_NoOvertime() {
        double hoursWorked = 40;
        double expectedGrossPay = 40 * 16.78;
        assertEquals(expectedGrossPay, payroll.calculateGrossPay(hoursWorked), 0.01);
    }

    @Test
    void testCalculateGrossPay_WithOvertime() {
        double hoursWorked = 45;
        double expectedGrossPay = (40 * 16.78) + (5 * 16.78 * 1.5);
        assertEquals(expectedGrossPay, payroll.calculateGrossPay(hoursWorked), 0.01);
    }

    @Test
    void testCalculateSocialSecurityTax() {
        double grossPay = 800.00;
        double expectedSocialSecurity = grossPay * 0.06;
        assertEquals(expectedSocialSecurity, payroll.calculateSocialSecurityTax(grossPay), 0.01);
    }

    @Test
    void testCalculateFederalTax() {
        double grossPay = 800.00;
        double expectedFederalTax = grossPay * 0.14;
        assertEquals(expectedFederalTax, payroll.calculateFederalTax(grossPay), 0.01);
    }

    @Test
    void testCalculateStateTax() {
        double grossPay = 800.00;
        double expectedStateTax = grossPay * 0.05;
        assertEquals(expectedStateTax, payroll.calculateStateTax(grossPay), 0.01);
    }

    @Test
    void testCalculateInsuranceCost_Under3Dependents() {
        int dependents = 2;
        double expectedInsuranceCost = 15.00;
        assertEquals(expectedInsuranceCost, payroll.calculateInsuranceCost(dependents), 0.01);
    }

    @Test
    void testCalculateInsuranceCost_3OrMoreDependents() {
        int dependents = 3;
        double expectedInsuranceCost = 35.00;
        assertEquals(expectedInsuranceCost, payroll.calculateInsuranceCost(dependents), 0.01);
    }

    @Test
    void testCalculateNetPay() {
        double grossPay = 800.00;
        double socialSecurity = payroll.calculateSocialSecurityTax(grossPay);
        double federalTax = payroll.calculateFederalTax(grossPay);
        double stateTax = payroll.calculateStateTax(grossPay);
        double insurance = payroll.calculateInsuranceCost(2);

        double expectedNetPay = grossPay - (socialSecurity + federalTax + stateTax + 10.00 + insurance);
        assertEquals(expectedNetPay, payroll.calculateNetPay(grossPay, socialSecurity, federalTax, stateTax, insurance), 0.01);
    }
}


package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestPayroll {

    Payroll payroll;

    @Test
    void testCalculateGrossPay() {
        // Test with regular pay rate
        assertEquals(671.20, Payroll.calculateGrossPay(40, 16.78), 0.01);  // Regular hours
        assertEquals(797.05, Payroll.calculateGrossPay(45, 16.78), 0.01);  // Overtime
    }

    @Test
    void testCalculateSocialSecurityTax() {
        assertEquals(40.27, Payroll.calculateSocialSecurityTax(671.2), 0.01);
    }

    @Test
    void testCalculateFederalTax() {
        assertEquals(93.97, Payroll.calculateFederalTax(671.2), 0.01);
    }

    @Test
    void testCalculateStateTax() {
        assertEquals(33.56, Payroll.calculateStateTax(671.2), 0.01);
    }

    @Test
    void testCalculateInsuranceCost() {
        assertEquals(35, Payroll.calculateInsuranceCost(3));  // 3 or more dependents
        assertEquals(15, Payroll.calculateInsuranceCost(2));  // Less than 3 dependents
    }

    @Test
    void testCalculateNetPay() {
        double grossPay = 671.2;
        double socialSecurity = Payroll.calculateSocialSecurityTax(grossPay);
        double federalTax = Payroll.calculateFederalTax(grossPay);
        double stateTax = Payroll.calculateStateTax(grossPay);
        double insurance = Payroll.calculateInsuranceCost(2);
    
        assertEquals(478.40, Payroll.calculateNetPay(grossPay, socialSecurity, federalTax, stateTax, insurance, 0.00), 0.01); // Adjusted expected value without life insurance
    }

    // Test net pay with life insurance plan
    @Test
    void testCalculateNetPayWithLifeInsurance() {
        double grossPay = 671.2;
        double socialSecurity = Payroll.calculateSocialSecurityTax(grossPay);
        double federalTax = Payroll.calculateFederalTax(grossPay);
        double stateTax = Payroll.calculateStateTax(grossPay);
        double insurance = Payroll.calculateInsuranceCost(2);
        double lifeInsurance = 5.00; // Assuming single plan

        assertEquals(473.40, Payroll.calculateNetPay(grossPay, socialSecurity, federalTax, stateTax, insurance, lifeInsurance), 0.01); // Expected value with life insurance deduction
    }

    public Payroll getPayroll() {
        return payroll;
    }
}

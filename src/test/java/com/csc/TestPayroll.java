package com.csc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPayroll {

    Payroll payroll;

    @BeforeEach
    void setUp() {
        payroll = new Payroll();
    }

    @Test
    void testCalculateGrossPay() {
        assertEquals(671.2, Payroll.calculateGrossPay(40), 0.01);  // Regular hours
        assertEquals(755.01, Payroll.calculateGrossPay(45), 0.01); // Overtime
    }

    @Test
    void testCalculateSocialSecurityTax() {
        assertEquals(40.27, Payroll.calculateSocialSecurityTax(671.2), 0.01);
    }

    @Test
    void testCalculateFederalTax() {
        assertEquals(94.4, Payroll.calculateFederalTax(671.2), 0.01);
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

        assertEquals(453.27, Payroll.calculateNetPay(grossPay, socialSecurity, federalTax, stateTax, insurance), 0.01);
    }
}

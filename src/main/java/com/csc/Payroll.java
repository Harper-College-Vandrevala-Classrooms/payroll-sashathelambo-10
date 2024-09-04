package com.csc;

import java.util.Scanner;

public class Payroll {

    // Constants for calculations
    private static final double HOURLY_RATE = 16.78;
    private static final double OVERTIME_RATE = HOURLY_RATE * 1.5;
    private static final int REGULAR_HOURS = 40;

    private static final double SOCIAL_SECURITY_TAX_RATE = 0.06;
    private static final double FEDERAL_INCOME_TAX_RATE = 0.14;
    private static final double STATE_INCOME_TAX_RATE = 0.05;

    private static final double INSURANCE_UNDER_3_DEP = 15.00;
    private static final double INSURANCE_3_OR_MORE_DEP = 35.00;
    private static final double UNION_DUES = 10.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payroll Program!");

        System.out.print("How many hours did you work this week? ");
        double hoursWorked = scanner.nextDouble();

        System.out.print("How many children do you have? ");
        int dependents = scanner.nextInt();

        // Calculate gross pay
        double grossPay = calculateGrossPay(hoursWorked);

        // Calculate taxes and deductions
        double socialSecurityTax = calculateSocialSecurityTax(grossPay);
        double federalIncomeTax = calculateFederalTax(grossPay);
        double stateIncomeTax = calculateStateTax(grossPay);

        double insuranceCost = calculateInsuranceCost(dependents);

        double netPay = calculateNetPay(grossPay, socialSecurityTax, federalIncomeTax, stateIncomeTax, insuranceCost);

        // Output results
        System.out.println("\nPayroll Stub:");
        System.out.printf("%10s: %6.2f\n", "Hours", hoursWorked);
        System.out.printf("%10s: %6.2f $/hr\n", "Rate", HOURLY_RATE);
        System.out.printf("%10s: $ %6.2f\n", "Gross", grossPay);
        System.out.printf("%10s: $ %6.2f\n", "SocSec", socialSecurityTax);
        System.out.printf("%10s: $ %6.2f\n", "FedTax", federalIncomeTax);
        System.out.printf("%10s: $ %6.2f\n", "StTax", stateIncomeTax);
        System.out.printf("%10s: $ %6.2f\n", "Union", UNION_DUES);
        System.out.printf("%10s: $ %6.2f\n", "Ins", insuranceCost);
        System.out.printf("%10s: $ %6.2f\n", "Net", netPay);

        System.out.println("\nThank you for using the Payroll Program!");
        scanner.close();
    }

    // Method to calculate gross pay
    public static double calculateGrossPay(double hoursWorked) {
        double hourlyRate = 16.78;
        double overtimeRate = hourlyRate * 1.5;
        int regularHours = 40;
    
        if (hoursWorked <= regularHours) {
            return hoursWorked * hourlyRate;
        } else {
            double overtimeHours = hoursWorked - regularHours;
            return (regularHours * hourlyRate) + (overtimeHours * overtimeRate);
        }
    }

    // Method to calculate social security tax
    public static double calculateSocialSecurityTax(double grossPay) {
        return grossPay * SOCIAL_SECURITY_TAX_RATE;
    }

    // Method to calculate federal income tax
    public static double calculateFederalTax(double grossPay) {
        return grossPay * FEDERAL_INCOME_TAX_RATE;
    }

    // Method to calculate state income tax
    public static double calculateStateTax(double grossPay) {
        return grossPay * STATE_INCOME_TAX_RATE;
    }

    // Method to calculate insurance cost
    public static double calculateInsuranceCost(int dependents) {
        if (dependents >= 3) {
            return INSURANCE_3_OR_MORE_DEP;
        } else {
            return INSURANCE_UNDER_3_DEP;
        }
    }

    // Method to calculate net pay
    public static double calculateNetPay(double grossPay, double socialSecurity, double federalTax, double stateTax, double insurance) {
        return grossPay - (socialSecurity + federalTax + stateTax + UNION_DUES + insurance);
    }
}

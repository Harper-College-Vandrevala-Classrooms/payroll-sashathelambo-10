package com.csc;

import java.util.Scanner;

public class Payroll {

    public static void main(String[] args) {
        double hourlyRate = 16.78;
        double overtimeRate = hourlyRate * 1.5; // Used in calculation
        int regularHours = 40; // Used in calculation

        double socialSecurityTaxRate = 0.06; // Used in calculation
        double federalIncomeTaxRate = 0.14; // Used in calculation
        double stateIncomeTaxRate = 0.05; // Used in calculation
        double unionDues = 10.00; // Used in calculation

        double insuranceUnder3Dep = 15.00; // Used in calculation
        double insurance3OrMoreDep = 35.00; // Used in calculation

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payroll Program!");

        System.out.print("How many hours did you work this week? ");
        double hoursWorked = scanner.nextDouble();

        System.out.print("How many children do you have? ");
        int dependents = scanner.nextInt();

        // Calculate gross pay
        double grossPay;
        if (hoursWorked <= regularHours) {
            grossPay = hoursWorked * hourlyRate;
        } else {
            double overtimeHours = hoursWorked - regularHours;
            grossPay = (regularHours * hourlyRate) + (overtimeHours * overtimeRate);
        }

        // Calculate taxes and deductions
        double socialSecurityTax = grossPay * socialSecurityTaxRate;
        double federalIncomeTax = grossPay * federalIncomeTaxRate;
        double stateIncomeTax = grossPay * stateIncomeTaxRate;

        double insuranceCost;
        if (dependents >= 3) {
            insuranceCost = insurance3OrMoreDep;
        } else {
            insuranceCost = insuranceUnder3Dep;
        }

        double totalDeductions = socialSecurityTax + federalIncomeTax + stateIncomeTax + unionDues + insuranceCost;
        double netPay = grossPay - totalDeductions;

        // Output results
        System.out.println("\nPayroll Stub:");
        System.out.printf("%10s: %6.2f\n", "Hours", hoursWorked);
        System.out.printf("%10s: %6.2f $/hr\n", "Rate", hourlyRate);
        System.out.printf("%10s: $ %6.2f\n", "Gross", grossPay);
        System.out.printf("%10s: $ %6.2f\n", "SocSec", socialSecurityTax);
        System.out.printf("%10s: $ %6.2f\n", "FedTax", federalIncomeTax);
        System.out.printf("%10s: $ %6.2f\n", "StTax", stateIncomeTax);
        System.out.printf("%10s: $ %6.2f\n", "Union", unionDues);
        System.out.printf("%10s: $ %6.2f\n", "Ins", insuranceCost);
        System.out.printf("%10s: $ %6.2f\n", "Net", netPay);

        System.out.println("\nThank you for using the Payroll Program!");
        scanner.close();
    }


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

    public static double calculateSocialSecurityTax(double grossPay) {
        double socialSecurityTaxRate = 0.06;
        return grossPay * socialSecurityTaxRate;
    }

    public static double calculateFederalTax(double grossPay) {
        double federalIncomeTaxRate = 0.14;
        return grossPay * federalIncomeTaxRate;
    }

    public static double calculateStateTax(double grossPay) {
        double stateIncomeTaxRate = 0.05;
        return grossPay * stateIncomeTaxRate;
    }

    public static double calculateInsuranceCost(int dependents) {
        double insuranceUnder3Dep = 15.00;
        double insurance3OrMoreDep = 35.00;

        if (dependents >= 3) {
            return insurance3OrMoreDep;
        } else {
            return insuranceUnder3Dep;
        }
    }

    public static double calculateNetPay(double grossPay, double socialSecurity, double federalTax, double stateTax, double insurance) {
        double unionDues = 10.00;
        return grossPay - (socialSecurity + federalTax + stateTax + unionDues + insurance);
    }
}

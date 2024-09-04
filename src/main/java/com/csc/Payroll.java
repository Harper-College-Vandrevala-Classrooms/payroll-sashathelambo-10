package com.csc;

import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {
        double hourlyRate = 16.78;
        double overtimeRate = hourlyRate * 1.5;
        int regularHours = 40;

        double socialSecurityTaxRate = 0.06;
        double federalIncomeTaxRate = 0.14;
        double stateIncomeTaxRate = 0.05;
        double unionDues = 10.00;

        double insuranceUnder3Dep = 15.00;
        double insurance3OrMoreDep = 35.00;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payroll Program!");

        System.out.print("How many hours did you work this week? ");
        double hoursWorked = scanner.nextDouble();

        System.out.print("How many children do you have? ");
        int dependents = scanner.nextInt();

        double grossPay;
        if (hoursWorked <= regularHours) {
            grossPay = hoursWorked * hourlyRate;
        } else {
            double overtimeHours = hoursWorked - regularHours;
            grossPay = (regularHours * hourlyRate) + (overtimeHours * overtimeRate);
        }

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
}

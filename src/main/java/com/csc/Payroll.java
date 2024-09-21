package com.csc;

import java.util.Scanner;

public class Payroll {

    private static final double SOCIAL_SECURITY_TAX_RATE = 0.06;
    private static final double FEDERAL_INCOME_TAX_RATE = 0.14;
    private static final double STATE_INCOME_TAX_RATE = 0.05;

    private static final double INSURANCE_UNDER_3_DEP = 15.00;
    private static final double INSURANCE_3_OR_MORE_DEP = 35.00;
    private static final double UNION_DUES = 10.00;

    private static final double NO_PLAN_COST = 0.00;
    private static final double SINGLE_PLAN_COST = 5.00;
    private static final double MARRIED_PLAN_COST = 10.00;
    private static final double MARRIED_WITH_CHILDREN_PLAN_COST = 15.00;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Payroll Program!");

            System.out.print("How many hours did you work this week? ");
            double hoursWorked = scanner.nextDouble();

            double payRate;
            do {
                System.out.print("What is your hourly pay rate? ");
                payRate = scanner.nextDouble();
                if (payRate < 0) {
                    System.out.println("Pay rate cannot be negative. Please re-enter.");
                }
            } while (payRate < 0);

            System.out.print("How many children do you have? ");
            int dependents = scanner.nextInt();
            if (dependents < 0) {
                dependents = 0;
            }

            int lifeInsurancePlan;
            double lifeInsuranceCost = 0.00;
            do {
                System.out.println("Which life insurance plan do you want to select?");
                System.out.println("  (1) no plan");
                System.out.println("  (2) single plan");
                System.out.println("  (3) married plan");
                System.out.println("  (4) married with children plan");
                lifeInsurancePlan = scanner.nextInt();

                switch (lifeInsurancePlan) {
                    case 1:
                        lifeInsuranceCost = NO_PLAN_COST;
                        break;
                    case 2:
                        lifeInsuranceCost = SINGLE_PLAN_COST;
                        break;
                    case 3:
                        lifeInsuranceCost = MARRIED_PLAN_COST;
                        break;
                    case 4:
                        if (dependents > 0) {
                            lifeInsuranceCost = MARRIED_WITH_CHILDREN_PLAN_COST;
                        } else {
                            System.out.println("Sorry! You need at least one child to select that plan.");
                            lifeInsurancePlan = -1; // Invalid selection
                        }
                        break;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        lifeInsurancePlan = -1; // Invalid selection
                        break;
                }
            } while (lifeInsurancePlan == -1);

            // Calculate gross pay
            double grossPay = calculateGrossPay(hoursWorked, payRate);

            // Calculate taxes and deductions
            double socialSecurityTax = calculateSocialSecurityTax(grossPay);
            double federalIncomeTax = calculateFederalTax(grossPay);
            double stateIncomeTax = calculateStateTax(grossPay);
            double insuranceCost = calculateInsuranceCost(dependents);

            double netPay = calculateNetPay(grossPay, socialSecurityTax, federalIncomeTax, stateIncomeTax, insuranceCost, lifeInsuranceCost);

            // Output results
            System.out.println("\nPayroll Stub:");
            System.out.printf("%10s: %6.2f\n", "Hours", hoursWorked);
            System.out.printf("%10s: %6.2f $/hr\n", "Rate", payRate);
            System.out.printf("%10s: $ %6.2f\n", "Gross", grossPay);
            System.out.printf("%10s: $ %6.2f\n", "SocSec", socialSecurityTax);
            System.out.printf("%10s: $ %6.2f\n", "FedTax", federalIncomeTax);
            System.out.printf("%10s: $ %6.2f\n", "StTax", stateIncomeTax);

            if (netPay >= 0) {
                System.out.printf("%10s: $ %6.2f\n", "Union", UNION_DUES);
                System.out.printf("%10s: $ %6.2f\n", "Ins", insuranceCost);
                System.out.printf("%10s: $ %6.2f\n", "LifeIns", lifeInsuranceCost);
                System.out.printf("%10s: $ %6.2f\n", "Net", netPay);
            } else {
                System.out.printf("%10s: $ %6.2f\n", "Net", grossPay - (socialSecurityTax + federalIncomeTax + stateIncomeTax));
                System.out.println("\nThe employee still owes:");
                System.out.printf("%10s: $ %6.2f\n", "Union", UNION_DUES);
                System.out.printf("%10s: $ %6.2f\n", "Ins", insuranceCost);
                System.out.printf("%10s: $ %6.2f\n", "LifeIns", lifeInsuranceCost);
            }

            System.out.println("\nThank you for using the Payroll Program!");
        }
    }

    // Method to calculate gross pay
    public static double calculateGrossPay(double hoursWorked, double hourlyRate) {
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
    public static double calculateNetPay(double grossPay, double socialSecurity, double federalTax, double stateTax, double insurance, double lifeInsurance) {
        return grossPay - (socialSecurity + federalTax + stateTax + UNION_DUES + insurance + lifeInsurance);
    }
}

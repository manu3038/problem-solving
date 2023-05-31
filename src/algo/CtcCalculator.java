package algo;

import java.math.*;
import java.util.Scanner;

public class CtcCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the fixed CTC in lakhs");
        String fixedCtcStr = sc.next();
        System.out.println("Enter the basic component in fixed CTC in lakhs");
        String basicInFixedStr = sc.next();

        System.out.println("Enter the investment deduction in lakhs");
        String investedInFixedStr = sc.next();

        BigDecimal fixedCtc = new BigDecimal(fixedCtcStr);
        BigDecimal basicInFixed = new BigDecimal(basicInFixedStr);
        BigDecimal investmentInFixed = new BigDecimal(investedInFixedStr);

        BigDecimal basicInFixedWOPf = basicInFixed.subtract(percentage(basicInFixed, new BigDecimal(24)));// removing Employee's and Employers Contribution
        BigDecimal xtraBonus = fixedCtc.subtract(basicInFixed);


        BigDecimal taxableIncome = basicInFixedWOPf.add(xtraBonus).subtract(new BigDecimal(0.5).add(investmentInFixed)); // removing standard deduction for tax calc
        BigDecimal TWO_POINT_FIVE = new BigDecimal(2.5);
        BigDecimal FIVE = new BigDecimal(5);

        // Income tax calculation for old regime
        BigDecimal firstTaxSlab = percentage(TWO_POINT_FIVE, FIVE);
        BigDecimal reduceTaxFromIncome = taxableIncome.subtract(FIVE); // 2.5 - 5L => 5%
        BigDecimal secondSlabVal = reduceTaxFromIncome.floatValue() >= FIVE.floatValue() ? FIVE : FIVE.subtract(reduceTaxFromIncome);
        BigDecimal secondTaxSlab =  percentage(secondSlabVal, new BigDecimal(20)); // 5-10 => 20%
        reduceTaxFromIncome = secondSlabVal.equals(FIVE) ? reduceTaxFromIncome.subtract(secondSlabVal): BigDecimal.ZERO;
        BigDecimal thirdTaxSlab = percentage(reduceTaxFromIncome, new BigDecimal(30)); // >10 => 30%

        System.out.println(firstTaxSlab + "--" + secondTaxSlab + "--" + thirdTaxSlab);
        BigDecimal totalTaxDeduction = firstTaxSlab.add(secondTaxSlab).add(thirdTaxSlab); // total tax deduction for taxable income
        System.out.println("total tax deduction without investment is " + totalTaxDeduction + " for taxable income of " + taxableIncome);

        BigDecimal monthlyTakeHome = taxableIncome.add(investmentInFixed).subtract(totalTaxDeduction).divide(new BigDecimal(12), 5, RoundingMode.DOWN); // approx. montly take home after PF and IT deductions

        System.out.println("approximate monthly take home would be " + monthlyTakeHome);


    }

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct).divide(new BigDecimal(100));
    }
}

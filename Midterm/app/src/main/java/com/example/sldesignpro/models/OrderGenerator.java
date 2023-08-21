package com.example.sldesignpro.models;

public class OrderGenerator {
    private final String customer_name;
    private final String email_address;
    private final String plan;
    private final String planType;
    private final boolean isDesignAdditional;
    private final boolean isUpdateAdditional;
    private final String designType;
    private final String subscription_date;

    public OrderGenerator(String customer_name, String email_address, String plan, String planType, boolean isDesignAdditional, boolean isUpdateAdditional, String designType, String subscription_date) {
        this.customer_name = customer_name;
        this.email_address = email_address;
        this.plan = plan;
        this.planType = planType;
        this.isDesignAdditional = isDesignAdditional;
        this.isUpdateAdditional = isUpdateAdditional;
        this.designType = designType;
        this.subscription_date = subscription_date;
    }

    public String Generate() {
        String print_string = "";
        print_string += "Custom Name: " + customer_name + "\n";
        print_string += "Email Address: " + email_address + "\n";
        print_string += "Subscription Plan: " + plan + "\n";
        print_string += "Plan Type: " + planType + "\n";
        print_string += "Have Unlimited Design? : " + (isDesignAdditional ? "Yes" : "No") + "\n";
        print_string += "Have Updates? : " + (isUpdateAdditional ? "Yes" : "No") + "\n";
        print_string += "Type of Design: " + designType + "\n";
        print_string += "Subscription Date: " + subscription_date + "\n";
        print_string += "Total Cost (Per Year): " + PriceCalculate() + "\n";
        return print_string;
    }

    private String PriceCalculate() {
        Double plan_type_price;
        Double additional_design_price;
        Double additional_update_price;
        Double type_of_design_price;
        Double yearly_price;
        // plan type price
        if (plan.equals("Monthly")) {
            if (planType.equals("Custom Printing")) {
                plan_type_price = 10.0;
            } else {
                plan_type_price = 15.0;
            }
            additional_design_price = isDesignAdditional ? 3.0 : 0.0;
            additional_update_price = isUpdateAdditional ? 2.0 : 0.0;
            if (designType.equals("Labels")) {
                type_of_design_price = 0.0;
            } else if (designType.equals("Cards")) {
                type_of_design_price = 2.0;
            } else {
                type_of_design_price = 4.0;
            }
            yearly_price = 12 * (plan_type_price + additional_design_price + additional_update_price + type_of_design_price);
        } else {
            if (planType.equals("Yearly")) {
                plan_type_price = 110.0;
            } else {
                plan_type_price = 160.0;
            }
            additional_design_price = isDesignAdditional ? 30.0 : 0.0;
            additional_update_price = isUpdateAdditional ? 20.0 : 0.0;
            if (designType.equals("Labels")) {
                type_of_design_price = 0.0;
            } else if (designType.equals("Cards")) {
                type_of_design_price = 22.0;
            } else {
                type_of_design_price = 42.0;
            }
            yearly_price = plan_type_price + additional_design_price + additional_update_price + type_of_design_price;
        }
        return String.valueOf(yearly_price);
    }
}

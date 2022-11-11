package com.budgetmanager.services;

import com.budgetmanager.core.exceptions.InvalidSupplierException;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.domain.supplier.SupplierData;
import com.budgetmanager.utils.StringToCheck;
import com.budgetmanager.utils.StringToCheckPattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierFactory {

    public Supplier makeSupplier(SupplierData supplierData) throws InvalidSupplierException {
        Supplier newSupplier = new Supplier();
        
        String companyName = supplierData.getCompanyName();
        String tradingName = supplierData.getTradingName();
        String employersIdentificationNumber;
        employersIdentificationNumber = supplierData.getEmployersIdentificationNumber();
        String email = supplierData.getEmail();
        String zipCode = supplierData.getZipCode();
        String street = supplierData.getStreet();
        int buildingNumber = supplierData.getBuildingNumber();
        String city = supplierData.getCity();
        String country = supplierData.getCountry();
        
        checkCompanyName(companyName);
        checkEin(employersIdentificationNumber);
        checkEmail(email);
        checkZipCode(zipCode);
        checkStreet(street);
        checkIfIsPositive(buildingNumber);
        checkCity(city);
        checkCountry(country);
        
        newSupplier.setCompanyName(companyName);
        newSupplier.setTradingName(tradingName);
        newSupplier.setEmployersIdentificationNumber(
                employersIdentificationNumber
        );
        newSupplier.setEmail(email);
        newSupplier.setZipCode(zipCode);
        newSupplier.setStreet(street);
        newSupplier.setBuildingNumber(buildingNumber);
        newSupplier.setCity(city);
        newSupplier.setCountry(country);

        return newSupplier;
    }

    private void checkCompanyName(String companyName) throws InvalidSupplierException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("company name");
        toCheck.setValue(companyName);
        checkIfStringIsBlank(toCheck);
    }

    private void checkEin(String employersIdentificationNumber) throws InvalidSupplierException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("EIN");
        toCheckPattern.setValue(employersIdentificationNumber);
        toCheckPattern.setPattern(
                "^[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0]{3}[1][-][0-9]{2}$"
        );
        checkIfStringMatchPattern(toCheckPattern);
    }

    private void checkEmail(String email) throws InvalidSupplierException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("e-mail");
        toCheckPattern.setValue(email);
        toCheckPattern.setPattern(
                "^.+[@].+[.].+$"
        );
        checkIfStringMatchPattern(toCheckPattern);
    }

    private void checkZipCode(String zipCode) throws InvalidSupplierException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("ZIP code");
        toCheckPattern.setValue(zipCode);
        toCheckPattern.setPattern(
                "^[0-9]{5}[-][0-9]{3}$"
        );
        checkIfStringMatchPattern(toCheckPattern);
    }

    private void checkStreet(String street) throws InvalidSupplierException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("street");
        toCheck.setValue(street);
        checkIfStringIsBlank(toCheck);
    }

    private void checkCity(String city) throws InvalidSupplierException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("city");
        toCheck.setValue(city);
        checkIfStringIsBlank(toCheck);
    }

    private void checkCountry(String country) throws InvalidSupplierException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("country");
        toCheck.setValue(country);
        checkIfStringIsBlank(toCheck);
    }private void checkIfStringIsBlank(StringToCheck toCheck) throws InvalidSupplierException {
        String label = toCheck.getLabel();
        String value = toCheck.getValue();

        if (value.isBlank()) {
            throw new InvalidSupplierException("A " + label + " must be set");
        }
    }

    private void checkIfStringMatchPattern(StringToCheckPattern toCheckPattern) throws InvalidSupplierException {
        String label = toCheckPattern.getLabel();
        String value = toCheckPattern.getValue();
        String expectedPatternString = toCheckPattern.getPattern();
        Pattern expectedPattern = Pattern.compile(expectedPatternString);
        Matcher valueMatcher = expectedPattern.matcher(value);
        boolean doesValueMatchPattern = valueMatcher.matches();

        if (!doesValueMatchPattern) {
            throw new InvalidSupplierException(
                    value + " is not a valid " + label
            );
        }
    }

    private void checkIfIsPositive(int buildingNumber) throws InvalidSupplierException {
        boolean isPositive = buildingNumber > 0;
        
        if(!isPositive) {
            throw new InvalidSupplierException(
                    buildingNumber + " is not a valid building number"
            );
        }
    }
}

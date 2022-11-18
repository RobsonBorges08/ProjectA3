package com.budgetmanager.services;

import com.budgetmanager.core.exceptions.InvalidSupplierException;
import com.budgetmanager.domain.Supplier;
import com.budgetmanager.domain.supplier.SupplierData;
import com.budgetmanager.utils.StringToCheck;
import com.budgetmanager.utils.StringToCheckPattern;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.logging.Level.INFO;

public class SupplierFactory {

    private final Logger logger;

    public SupplierFactory() {
        Class currentClass = getClass();
        this.logger = Logger.getLogger(currentClass.getName());
    }

    public Supplier makeSupplier(SupplierData supplierData) throws
            InvalidSupplierException {
        Supplier newSupplier = new Supplier();

        String companyName = supplierData.getCompanyName();
        checkCompanyName(companyName);
        newSupplier.setCompanyName(companyName);
        logFieldWasSet("companyName", companyName);

        String tradingName = supplierData.getTradingName();
        newSupplier.setTradingName(tradingName);
        logFieldWasSet("tradingName", tradingName);

        String employersIdentificationNumber;
        employersIdentificationNumber = supplierData
                .getEmployersIdentificationNumber();
        checkEin(employersIdentificationNumber);
        newSupplier.setEmployersIdentificationNumber(
                employersIdentificationNumber
        );
        logFieldWasSet("employersIdentificationNumber",
                employersIdentificationNumber);

        String email = supplierData.getEmail();
        checkEmail(email);
        newSupplier.setEmail(email);
        logFieldWasSet("email", email);

        String zipCode = supplierData.getZipCode();
        checkZipCode(zipCode);
        newSupplier.setZipCode(zipCode);
        logFieldWasSet("zipCode", zipCode);

        String street = supplierData.getStreet();
        checkStreet(street);
        newSupplier.setStreet(street);
        logFieldWasSet("street", street);

        int buildingNumber = supplierData.getBuildingNumber();
        checkIfIsPositive(buildingNumber);
        newSupplier.setBuildingNumber(buildingNumber);
        String buildingNumberAsString = String.valueOf(buildingNumber);
        logFieldWasSet("buildingNumber", buildingNumberAsString);

        String city = supplierData.getCity();
        checkCity(city);
        newSupplier.setCity(city);
        logFieldWasSet("city", city);

        String country = supplierData.getCountry();
        checkCountry(country);
        newSupplier.setCountry(country);
        logFieldWasSet("country", country);

        return newSupplier;
    }

    private void logFieldWasSet(String fieldName, String value) {
        String logMessage = String.format("%s was set to the field %s", value,
                fieldName);
        logger.log(INFO, logMessage);
    }

    private void checkCompanyName(String companyName) throws
            InvalidSupplierException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("company name");
        toCheck.setValue(companyName);
        checkIfStringIsBlank(toCheck);
    }

    private void checkEin(String employersIdentificationNumber) throws
            InvalidSupplierException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("EIN");
        toCheckPattern.setValue(employersIdentificationNumber);
        toCheckPattern.setPattern(
                "^[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0]{3}[0-9][-][0-9]{2}$"
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
    }

    private void checkIfStringIsBlank(StringToCheck toCheck) throws
            InvalidSupplierException {
        String label = toCheck.getLabel();
        String value = toCheck.getValue();

        if (value.isBlank()) {
            throw new InvalidSupplierException("A " + label + " must be set");
        }
    }

    private void checkIfStringMatchPattern(StringToCheckPattern toCheckPattern)
            throws InvalidSupplierException {
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

    private void checkIfIsPositive(int buildingNumber) throws
            InvalidSupplierException {
        boolean isPositive = buildingNumber > 0;

        if (!isPositive) {
            throw new InvalidSupplierException(
                    buildingNumber + " is not a valid building number"
            );
        }
    }
}

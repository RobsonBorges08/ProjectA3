package com.budgetmanager.services;

import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.domain.Settings;
import com.budgetmanager.utils.StringToCheck;
import com.budgetmanager.utils.StringToCheckPattern;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.logging.Level.INFO;

public class SettingsService implements Closeable {

    private final String CONFIG_FILE_NAME = "budgetmanager.properties";
    private final Logger logger;

    public SettingsService() throws URISyntaxException, FileNotFoundException,
            IOException {
        this.logger = Logger.getLogger(SettingsService.class.getName());
    }

    @Override
    public void close() throws IOException {
    }

    public Settings getSettings() throws IOException, InvalidSettingsException,
            NumberFormatException {
        Properties properties = getProperties();

        String fullName = properties.getProperty("fullName");
        logPropertyWasLoaded("fullName", fullName);

        String occupation = properties.getProperty("occupation");
        logPropertyWasLoaded("occupation", occupation);

        String email = properties.getProperty("email");
        logPropertyWasLoaded("email", email);

        String companyName = properties.getProperty("companyName");
        logPropertyWasLoaded("companyName", companyName);

        String companyPhone = properties.getProperty("companyPhone");
        logPropertyWasLoaded("companyPhone", companyPhone);

        String companyStreet = properties.getProperty("companyStreet");
        logPropertyWasLoaded("companyStreet", companyStreet);

        String companyBuildingNumberAsString;
        companyBuildingNumberAsString = properties.getProperty(
                "companyBuildingNumber");
        logPropertyWasLoaded("companyBuildingNumber",
                companyBuildingNumberAsString);

        int companyBuildingNumber = Integer.parseInt(
                companyBuildingNumberAsString);
        String companyCity = properties.getProperty("companyCity");
        logPropertyWasLoaded("companyCity", companyCity);

        String companyCountry = properties.getProperty("companyCountry");
        logPropertyWasLoaded("companyCountry", companyCountry);

        Settings settings = new Settings();
        settings.setFullName(fullName);
        settings.setOccupation(occupation);
        settings.setEmail(email);
        settings.setCompanyName(companyName);
        settings.setCompanyPhone(companyPhone);
        settings.setCompanyStreet(companyStreet);
        settings.setCompanyBuildingNumber(companyBuildingNumber);
        settings.setCompanyCity(companyCity);
        settings.setCompanyCountry(companyCountry);

        if (settings.isConfigured()) {
            return settings;
        }

        throw new InvalidSettingsException("Application not configured");
    }

    private void logPropertyWasLoaded(String propertyName, String value) {
        String loggingMessage;
        loggingMessage = String.format(
                "Value %s was loaded from the property %s",
                value, propertyName);
        logger.log(INFO, loggingMessage);
    }

    public boolean isApplicationConfigured() throws IOException,
            NumberFormatException {
        try {
            getSettings();
        } catch (InvalidSettingsException ex) {
            return false;
        }

        return true;
    }

    public void updateSettings(Settings newSettings) throws
            InvalidSettingsException, IOException, URISyntaxException {
        String fullName = newSettings.getFullName();
        String occupation = newSettings.getOccupation();
        String email = newSettings.getEmail();
        String companyName = newSettings.getCompanyName();
        String companyPhone = newSettings.getCompanyPhone();
        String companyStreet = newSettings.getCompanyStreet();
        int companyBuildingNumber = newSettings.getCompanyBuildingNumber();
        String companyCity = newSettings.getCompanyCity();
        String companyCountry = newSettings.getCompanyCountry();

        checkFullName(fullName);
        checkOccupation(occupation);
        checkEmail(email);
        checkCompanyName(companyName);
        checkPhone(companyPhone);
        checkStreet(companyStreet);
        checkIfIsPositive(companyBuildingNumber);
        checkCity(companyCity);
        checkCountry(companyCountry);

        storeSettings(newSettings);
    }

    private void checkFullName(String fullName) throws InvalidSettingsException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("full name");
        toCheckPattern.setValue(fullName);
        toCheckPattern.setPattern(
                "^[a-zA-Z\\s]+$"
        );
        checkIfStringMatchPattern(toCheckPattern);
    }

    private void checkOccupation(String occupation) throws
            InvalidSettingsException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("occupation");
        toCheck.setValue(occupation);
        checkIfStringIsBlank(toCheck);
    }

    private void checkCompanyName(String companyName) throws
            InvalidSettingsException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("company name");
        toCheck.setValue(companyName);
        checkIfStringIsBlank(toCheck);
    }

    private void checkPhone(String phone) throws InvalidSettingsException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("phone number");
        toCheckPattern.setValue(phone);
        // (XX)?XXXX-XXXX
        toCheckPattern.setPattern(
                "^[(][0-9]{2}[)][9]?[0-9]{4}[-][0-9]{4}$"
        );
        checkIfStringMatchPattern(toCheckPattern);
    }

    private void checkEmail(String email) throws InvalidSettingsException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("e-mail");
        toCheckPattern.setValue(email);
        toCheckPattern.setPattern(
                "^.+[@].+[.].+$"
        );
        checkIfStringMatchPattern(toCheckPattern);
    }

    private void checkStreet(String street) throws InvalidSettingsException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("street");
        toCheck.setValue(street);
        checkIfStringIsBlank(toCheck);
    }

    private void checkCity(String city) throws InvalidSettingsException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("city");
        toCheck.setValue(city);
        checkIfStringIsBlank(toCheck);
    }

    private void checkCountry(String country) throws InvalidSettingsException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("country");
        toCheck.setValue(country);
        checkIfStringIsBlank(toCheck);
    }

    private void checkIfStringIsBlank(StringToCheck toCheck) throws
            InvalidSettingsException {
        String label = toCheck.getLabel();
        String value = toCheck.getValue();

        if (value.isBlank()) {
            throw new InvalidSettingsException("A " + label + " must be set");
        }
    }

    private void checkIfStringMatchPattern(StringToCheckPattern toCheckPattern)
            throws InvalidSettingsException {
        String label = toCheckPattern.getLabel();
        String value = toCheckPattern.getValue();
        String expectedPatternString = toCheckPattern.getPattern();
        Pattern expectedPattern = Pattern.compile(expectedPatternString);
        Matcher valueMatcher = expectedPattern.matcher(value);
        boolean doesValueMatchPattern = valueMatcher.matches();

        if (!doesValueMatchPattern) {
            throw new InvalidSettingsException(
                    value + " is not a valid " + label
            );
        }
    }

    private void checkIfIsPositive(int buildingNumber) throws
            InvalidSettingsException {
        boolean isPositive = buildingNumber > 0;

        if (!isPositive) {
            throw new InvalidSettingsException(
                    buildingNumber + " is not a valid building number"
            );
        }
    }

    private void storeSettings(Settings newSettings) throws IOException,
            URISyntaxException {
        String fullName = newSettings.getFullName();
        String occupation = newSettings.getOccupation();
        String email = newSettings.getEmail();
        String companyName = newSettings.getCompanyName();
        String companyPhone = newSettings.getCompanyPhone();
        String companyStreet = newSettings.getCompanyStreet();
        int companyBuildingNumber = newSettings.getCompanyBuildingNumber();
        String companyBuildingNumberAsString;
        companyBuildingNumberAsString = String.valueOf(companyBuildingNumber);
        String companyCity = newSettings.getCompanyCity();
        String companyCountry = newSettings.getCompanyCountry();

        Properties properties = getProperties();
        properties.setProperty("fullName", fullName);
        logPropertyWasSet("fullName", fullName);

        properties.setProperty("occupation", occupation);
        logPropertyWasSet("occupation", occupation);

        properties.setProperty("email", email);
        logPropertyWasSet("email", email);

        properties.setProperty("companyName", companyName);
        logPropertyWasSet("companyName", companyName);

        properties.setProperty("companyPhone", companyPhone);
        logPropertyWasSet("companyPhone", companyPhone);

        properties.setProperty("companyStreet", companyStreet);
        logPropertyWasSet("companyStreet", companyStreet);

        properties.setProperty("companyBuildingNumber",
                companyBuildingNumberAsString);
        logPropertyWasSet("companyBuildingNumber", companyBuildingNumber);

        properties.setProperty("companyCity", companyCity);
        logPropertyWasSet("companyCity", companyCity);

        properties.setProperty("companyCountry", companyCountry);
        logPropertyWasSet("companyCountry", companyCountry);

        File configFile = new File(CONFIG_FILE_NAME);

        try ( OutputStream outputStream = new FileOutputStream(configFile)) {
            properties.store(outputStream, "Budget Manager Settings");
        }
    }

    private void logPropertyWasSet(String propertyName, int value) {
        String valueAsString = String.format("%d", value);
        logPropertyWasSet(propertyName, valueAsString);
    }

    private void logPropertyWasSet(String propertyName, String value) {
        String loggingMessage;
        loggingMessage = String.format(
                "Property %s was set to %s",
                propertyName,
                value);
        logger.log(INFO, loggingMessage);
    }

    private Properties getProperties() throws IOException {
        File configFile = new File(CONFIG_FILE_NAME);
        
        try ( InputStream inputStream = new FileInputStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            
            return properties;
        }
    }
}

package com.budgetmanager.services;

import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.domain.Settings;
import com.budgetmanager.utils.StringToCheck;
import com.budgetmanager.utils.StringToCheckPattern;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsService implements Closeable {

    private final String CONFIG_FILE_NAME = "budgetmanager.properties";
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public SettingsService() throws URISyntaxException, FileNotFoundException {
        File settingsFile = getSettingsFile();
        this.inputStream = new FileInputStream(settingsFile);
        this.outputStream = new FileOutputStream(settingsFile);
    }

    private File getSettingsFile() throws URISyntaxException {
        Class serviceClass = getClass();
        URL fileURL = serviceClass.getResource(CONFIG_FILE_NAME);
        URI fileURI = fileURL.toURI();
        
        return new File(fileURI);
    }
    
    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
    
    public Settings getSettings() throws IOException, InvalidSettingsException {
        if (!isApplicationConfigured()) {
            throw new InvalidSettingsException(
                    "Application not configured"
            );
        }
        
        Properties properties = getProperties();
        
        String fullName = properties.getProperty("fullName");
        String occupation = properties.getProperty("occupation");
        String email = properties.getProperty("email");
        String companyName = properties.getProperty("companyName");
        String companyPhone = properties.getProperty("companyPhone");
        String companyZipCode = properties.getProperty("companyZipCode");
        String companyStreet = properties.getProperty("companyStreet");
        String companyBuildingNumberAsString;
        companyBuildingNumberAsString = properties.getProperty("companyBuildingNumber");
        int companyBuildingNumber = Integer.parseInt(companyBuildingNumberAsString);
        String companyCity = properties.getProperty("companyCity");
        String companyCountry = properties.getProperty("companyCountry");
        
        Settings settings = new Settings();
        settings.setFullName(fullName);
        settings.setOccupation(occupation);
        settings.setEmail(email);
        settings.setCompanyName(companyName);
        settings.setCompanyPhone(companyPhone);
        settings.setCompanyZipCode(companyZipCode);
        settings.setCompanyStreet(companyStreet);
        settings.setCompanyBuildingNumber(companyBuildingNumber);
        settings.setCompanyCity(companyCity);
        settings.setCompanyCountry(companyCountry);
        
        return settings;
    }
    
    public boolean isApplicationConfigured() throws IOException {
        Properties properties = getProperties();
        Set<String> propertiesSet = properties.stringPropertyNames();
        String properyValue;
        
        for(String property : propertiesSet) {
            properyValue = properties.getProperty(property, "");
            
            if (properyValue.isBlank()) {
                return false;
            }
        }
        
        return true;
    }

    public void updateSettings(Settings newSettings) throws InvalidSettingsException, IOException {
        String fullName = newSettings.getFullName();
        String occupation = newSettings.getOccupation();
        String email = newSettings.getEmail();
        String companyName = newSettings.getCompanyName();
        String companyPhone = newSettings.getCompanyPhone();
        String companyZipCode = newSettings.getCompanyZipCode();
        String companyStreet = newSettings.getCompanyStreet();
        int companyBuildingNumber = newSettings.getCompanyBuildingNumber();
        String companyCity = newSettings.getCompanyCity();
        String companyCountry = newSettings.getCompanyCountry();

        checkFullName(fullName);
        checkOccupation(occupation);
        checkEmail(email);
        checkCompanyName(companyName);
        checkPhone(companyPhone);
        checkZipCode(companyZipCode);
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

    private void checkOccupation(String occupation) throws InvalidSettingsException {
        StringToCheck toCheck = new StringToCheck();
        toCheck.setLabel("occupation");
        toCheck.setValue(occupation);
        checkIfStringIsBlank(toCheck);
    }

    private void checkCompanyName(String companyName) throws InvalidSettingsException {
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

    private void checkZipCode(String zipCode) throws InvalidSettingsException {
        StringToCheckPattern toCheckPattern = new StringToCheckPattern();
        toCheckPattern.setLabel("ZIP code");
        toCheckPattern.setValue(zipCode);
        toCheckPattern.setPattern(
                "^[0-9]{5}[-][0-9]{3}$"
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

    private void checkIfStringIsBlank(StringToCheck toCheck) throws InvalidSettingsException {
        String label = toCheck.getLabel();
        String value = toCheck.getValue();

        if (value.isBlank()) {
            throw new InvalidSettingsException("A " + label + " must be set");
        }
    }

    private void checkIfStringMatchPattern(StringToCheckPattern toCheckPattern) throws InvalidSettingsException {
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

    private void checkIfIsPositive(int buildingNumber) throws InvalidSettingsException {
        boolean isPositive = buildingNumber > 0;

        if (!isPositive) {
            throw new InvalidSettingsException(
                    buildingNumber + " is not a valid building number"
            );
        }
    }

    private void storeSettings(Settings newSettings) throws IOException {
        String fullName = newSettings.getFullName();
        String occupation = newSettings.getOccupation();
        String email = newSettings.getEmail();
        String companyName = newSettings.getCompanyName();
        String companyPhone = newSettings.getCompanyPhone();
        String companyZipCode = newSettings.getCompanyZipCode();
        String companyStreet = newSettings.getCompanyStreet();
        int companyBuildingNumber = newSettings.getCompanyBuildingNumber();
        String companyBuildingNumberAsString;
        companyBuildingNumberAsString = String.valueOf(companyBuildingNumber);
        String companyCity = newSettings.getCompanyCity();
        String companyCountry = newSettings.getCompanyCountry();

        Properties properties = getProperties();
        properties.setProperty("fullName", fullName);
        properties.setProperty("occupation", occupation);
        properties.setProperty("email", email);
        properties.setProperty("companyName", companyName);
        properties.setProperty("companyPhone", companyPhone);
        properties.setProperty("companyZipCode", companyZipCode);
        properties.setProperty("companyStreet", companyStreet);
        properties.setProperty("companyBuildingNumber", companyBuildingNumberAsString);
        properties.setProperty("companyCity", companyCity);
        properties.setProperty("companyCountry", companyCountry);
        
        properties.store(outputStream, "Budget Manager Settings");
    }

    private Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        return properties;
    }
}

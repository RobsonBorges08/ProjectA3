/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.budgetmanager.handlers;

import com.budgetmanager.core.exceptions.InvalidSettingsException;
import com.budgetmanager.domain.Settings;
import com.budgetmanager.services.SettingsService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Lucas
 */
public class UpdateSettingsHandler implements EventHandler<ActionEvent> {

    private Settings newSettings;

    @Override
    public void handle(ActionEvent t) {
        try {
            SettingsService service = new SettingsService();
            service.updateSettings(newSettings);

        } catch (URISyntaxException | IOException | InvalidSettingsException ex) {
            Logger.getLogger(UpdateSettingsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

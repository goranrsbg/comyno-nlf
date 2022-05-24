/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.app.nlf.utils;

import xyz.app.nlf.controllers.PrimaryController;

/**
 * Shared data for all active controllers.
 * 
 * @author Lap
 */
public class SharedData {
    
    private static final SharedData INSTANCE = new SharedData();
    
    private PrimaryController primaryController;
    
    private Settable settableController;
    
    private SharedData(){}
    
    public static SharedData get() {
        return INSTANCE;
    }

    public PrimaryController getPrimaryController() {
        return primaryController;
    }

    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    public Settable getSettableController() {
        return settableController;
    }

    public void setSettableController(Settable settableController) {
        this.settableController = settableController;
    }
    
    /**
     * Show message on primary view.
     * @param message Text to be shown.
     */
    public void writeMessage(String message) {
        primaryController.setMessageText(message);
    }
}

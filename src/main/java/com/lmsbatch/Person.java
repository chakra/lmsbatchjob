package com.lmsbatch;

public class Person {
    private String shrStaffID;
    private String preferredLastName;
    private String preferredFirstName;
    private String conflictGroup;
    private String employeeCode;
    private String businessUnit;
    private String state;
    private String team;
    private String managerReference;
    private String secondLevelManager;
    private String roleLocation;
    private String emailAddressWork;
    private Boolean active;



    public Person() {

    }

    public String getShrStaffID() {
        return shrStaffID;
    }

    public String getPreferredLastName() {
        return preferredLastName;
    }

    public String getPreferredFirstName() {
        return preferredFirstName;
    }

    public String getConflictGroup() {
        return conflictGroup;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public String getState() {
        return state;
    }

    public String getTeam() {
        return team;
    }

    public String getManagerReference() {
        return managerReference;
    }

    public String getSecondLevelManager() {
        return secondLevelManager;
    }

    public String getRoleLocation() {
        return roleLocation;
    }

    public String getEmailAddressWork() {
        return emailAddressWork;
    }

    public Boolean getActive() {
        return active;
    }

    public void setShrStaffID(String shrStaffID) {
        this.shrStaffID = shrStaffID;
    }

    public void setPreferredLastName(String preferredLastName) {
        this.preferredLastName = preferredLastName;
    }

    public void setPreferredFirstName(String preferredFirstName) {
        this.preferredFirstName = preferredFirstName;
    }

    public void setConflictGroup(String conflictGroup) {
        this.conflictGroup = conflictGroup;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
    }

    public void setSecondLevelManager(String secondLevelManager) {
        this.secondLevelManager = secondLevelManager;
    }

    public void setRoleLocation(String roleLocation) {
        this.roleLocation = roleLocation;
    }

    public void setEmailAddressWork(String emailAddressWork) {
        this.emailAddressWork = emailAddressWork;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Person(String shrStaffID, String preferredLastName, String preferredFirstName, String conflictGroup, String employeeCode, String businessUnit, String state, String team, String managerReference, String secondLevelManager, String roleLocation, String emailAddressWork, Boolean active) {
        this.shrStaffID = shrStaffID;
        this.preferredLastName = preferredLastName;
        this.preferredFirstName = preferredFirstName;
        this.conflictGroup = conflictGroup;
        this.employeeCode = employeeCode;
        this.businessUnit = businessUnit;
        this.state = state;
        this.team = team;
        this.managerReference = managerReference;
        this.secondLevelManager = secondLevelManager;
        this.roleLocation = roleLocation;
        this.emailAddressWork = emailAddressWork;
        this.active = active;
    }

    @Override
    public String toString() {
        return "SHRStaffID : " + shrStaffID + ", Preferred First Name : " + preferredFirstName + ",  Preferred Last Name : " + preferredLastName
                + ",  Conflict Group : " + conflictGroup  + ",  Employee Code : " + employeeCode  + ",  Business Unit : " + businessUnit
                + ",  State : " + state  + ",  Team : " + team  + ",  Manager Reference : " + managerReference  + ",  Second Level Manager : " + secondLevelManager
                + ",  Role Location : " + roleLocation  + ",  Email Address Work : " + emailAddressWork  + ",  Active : " + active;
    }

}


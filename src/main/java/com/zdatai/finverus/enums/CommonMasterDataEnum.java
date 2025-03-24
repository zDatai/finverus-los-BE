package com.zdatai.finverus.enums;

import lombok.Getter;

/**
 * Note: After adding a new record, please update the Confluence documentation.
 *
 * @see <a href="https://zdatai.atlassian.net/wiki/spaces/FinVerus/pages/56262697/Common+Master+Data#Master-Data-Reference-Codes-Grid">Confluence Documentation</a>
 * <p>
 * author: Udara Wikum
 * date: 27-02-2025 09.51 PM
 * purpose: Manage System Required Common Data
 * version: 1.0.0
 */

@Getter
public enum CommonMasterDataEnum {
    COMPANY_ORGANIZATION("Company/Organization", "COMPANY_ORGANIZATION"),
    INCOME_TYPE("Income Type", "INCOME_TYPE"),
    INCOME_SUBTYPE("Income Sub Type", "INCOME_SUBTYPE"),
    BUSINESS_LOCATION("Business Location", "BUSINESS_LOCATION"),
    EMPLOYEE_RANGE("Employee Range", "EMPLOYEE_RANGE"),
    OWNERSHIP_BUSINESS("Ownership Of Business", "OWNERSHIP_BUSINESS"),
    EMPLOYMENT_GRADE("Employee Grade", "EMPLOYMENT_GRADE"),
    BUSINESS_LOCATION_OWNERSHIP("Business Location Ownership", "B_LOCATION_OWNERSHIP"),
    EMPLOYMENT_STATUS("Employment Status", "EMPLOYMENT_STATUS"),
    POSITION_TITLE("Position/Title", "POSITION-TITLE"),
    BUSINESS_TYPE("Business Type", "BUSINESS_TYPE"),
    CORPORATE_GRADE("Corporate Grade", "CORPORATE_GRADE"),
    COMMUNICATION_TYPE("Communication Type", "COMMUNICATION_TYPE"),
    RESPONSIBILITY_TYPE("Responsibility Type", "RESPONSIBILITY_TYPE"),
    OWNERSHIP_OF_WEALTH("Ownership Of Wealth", "OWNERSHIP_WEALTH"),
    SOURCE_OF_FUNDS("Source Of Funds", "SOURCE_OF_FUNDS"),
    SOURCE_WEALTH("Source Of Wealth", "SOURCE_WEALTH"),
    AVERAGE_MONTHLY_INCOME("Average Monthly income", "AVERAGE_MONTHLY_INCOME"),
    ANTICIPATED_VALUE("Anticipated Value", "ANTICIPATED_VALUE"),
    EMPLOYMENT_CATEGORY("Employee Category", "EMPLOYMENT_CATEGORY"),
    ORGANIZATION_TYPE("Organization Type", "ORGANIZATION_TYPE"),
    OWNERSHIP_TYPE("Ownership Type", "OWNERSHIP_TYPE"),
    RELATIONSHIP("Relationship", "RELATIONSHIP"),
    TRAVELING_METHOD("Traveling Method", "TRAVELING_METHOD"),
    WHT_LIABILITY("WHT Liability", "WHT_LIABILITY"),
    ACCOUNT_TYPE("Account Type", "ACCOUNT_TYPE"),
    ACCOUNT_OPERATION("Account Operation", "ACCOUNT_OPERATION"),
    EDU_LEVEL("Education Level", "EDU_LEVEL"),
    MONTH("Month", "MONTH"),
    SPECIALIZED_ON("Specialized On", "SPECIALIZED_ON"),
    ASSET_TYPE("Assets Type", "ASSET_TYPE"),
    SOCIAL_MEDIA_TYPE("Social Media Type", "SOCIAL_MEDIA_TYPE"),
    GENDER("Gender", "SOCIAL_MEDIA_TYPE"),
    CITIZENSHIP("Citizenship","CITIZENSHIP"),
    CUSTOMER_TYPE("Customer Type","CUSTOMER_TYPE"),
    VISA_TYPE("Visa Type","VISA_TYPE"),
    PRIORITY("Priority","PRIORITY"),
    NATIONALITY("Nationality","NATIONALITY"),
    RACE("Race","RACE"),
    RELIGION("Religion","RELIGION"),
    TITLE("title","TITLE"),
    CIVIL_STATUS("Civil Status","CIVIL_STATUS"),
    ADDRESS_TYPE("Address Type","ADDRESS_TYPE"),
    CONTACT_NUMBER_TYPE("Contact Number Type","CONTACT_NUMBER_TYPE"),
    CONTACT_N_OWNERSHIP_TYPE("Contact Number Ownership Type","CONTACT_N_OWNERSHIP_TYPE"),
    ID_TYPE("Id type","ID_TYPE"),
    ONBOARDING("Onboarding","ONBOARDING"),
    REGISTRATION_TYPE("Registration Type","REGISTRATION_TYPE"),
    EXPECTED_M_TRANSACTION("Expected Monthly Transaction", "EXPECTED_M_TRANSACTION");

    private String masterDataName;
    private String referenceCode;

    CommonMasterDataEnum(String masterDataName, String referenceCode) {
        this.masterDataName = masterDataName;
        this.referenceCode = referenceCode;
    }

}

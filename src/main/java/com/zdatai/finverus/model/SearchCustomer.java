package com.zdatai.finverus.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@Table(name = "search_customer")
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class SearchCustomer {
    
    @Id
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_code")
    private String clientCode;

    @Column(name = "client_category")
    private String clientCategory;

    @Column(name = "client_identification")
    private String clientIdentification;

    @Column(name = "business_name_or_last_name")
    private String businessNameOrLastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "is_citizen")
    private String isCitizen;

    @Column(name = "is_dual_citizenship")
    private String isDualCitizenship;

    @Column(name = "is_related_company")
    private String isRelatedCompany;

    @Column(name = "is_related_company_description")
    private String isRelatedCompanyDescription;

    @Column(name = "passport_expiry_date")
    private LocalDate passportExpiryDate;

    @Column(name = "passport_issue_date")
    private LocalDate passportIssueDate;

    @Column(name = "passport_no")
    private LocalDate passportNo;

    @Column(name = "visa_expired_date")
    private LocalDate visaExpiredDate;

    @Column(name = "civil_status_id")
    private Long civilStatusId;

    @Column(name = "civil_status_name")
    private String civilStatusName;

    @Column(name = "customer_type_id")
    private Long customerTypeId;

    @Column(name = "customer_type")
    private String customertype;

    @Column(name = "nationality_id")
    private Long nationalityId;

    @Column(name ="nationality_name")
    private String nationalityName;

    @Column(name = "priority_id")
    private Long priorityId;

    @Column(name = "priority_name")
    private String priorityName;

    @Column(name = "race_id")
    private Long raceId;

    @Column(name = "race_name")
    private String raceName;

    @Column(name = "religion_id")
    private Long religionId;

    @Column(name = "religion_name")
    private String religionName;

    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "visa_type_id")
    private Long visaTypeid;

    @Column(name = "visa_type_name")
    private String visaTypeName;

    @Column(name = "onboarding_id")
    private Long onboardingId;

    @Column(name = "onboarding_name")
    private String onboardingName;

    @Column(name = "name_with_initials")
    private String nameWithInitials;

    @Column(name = "title")
    private String title;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "contact_number_type")
    private String contactNumberType;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "address_ownership")
    private String addressOwnership;

    @Column(name = "city")
    private String city;

    @Column(name = "city_id")
    private Long cityid;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "citizenship_id")
    private Long citizenshipId;

    @Column(name = "citizenship_name")
    private String citizenshipName;

    @Column(name = "postal_area_id")
    private Long postalAreaId;

    @Column(name = "postal_area_name")
    private String postalAreaName;

    @Column(name = "postal_area_code")
    private String postalAreaCode;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "secretarial")
    private String secretarial;

    @Column(name = "secretarial_id")
    private Long secretarialId;
    
    
}

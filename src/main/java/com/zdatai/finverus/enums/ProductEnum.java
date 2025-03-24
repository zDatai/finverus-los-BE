package com.zdatai.finverus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductEnum {
    CUSTOMER_ONBOARD(1, "CO", "Customer Onboard", true);

    private int id;
    private String code;
    private String description;
    private boolean active;

    public void setId(int id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public static ProductEnum getEnumById(int id){
        Class cls = ProductEnum.class;
        for(Object obj : cls.getEnumConstants()){
            ProductEnum myEnum = (ProductEnum) obj;
            if(myEnum.getId() == id){
                return myEnum;
            }
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

/**
 * Contact information ile ilgili sabitler.
 * @author Hakan Uygun
 */
public class ContactInformationConsts {
    
    public class Roles{
        public static final String PRIMARY = "PRIMARY";
        public static final String BUSINESS = "BUSINESS";
        public static final String HOME = "HOME";
        public static final String PERSONAL = "PERSONAL";
    }
    
    public class PhoneSubTypes{
        public static final String MOBILE = "MOBILE";
        public static final String LAND = "LAND";
        public static final String FAX = "FAX";
    }
    
    
    public class AddressSubTypes{
        public static final String INVOICE = "INVOICE";
        public static final String SHIPPING = "SHIPPING";
    }
    
}

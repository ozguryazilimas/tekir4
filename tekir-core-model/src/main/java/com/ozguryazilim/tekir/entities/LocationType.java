/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

/**
 * Location Types
 * 
 * FIXME: Burada tipler netleştirilecek.
 * 
 * @author Hakan Uygun
 */



public enum LocationType {    
    LOCATION,
    COUNTRY,
    TERRITORY,
    CITY,
    TOWN,
    DISTRICT;       
    
    private static EnumMap<LocationType, List<LocationType>> locationMap;
    
    public static int a = 5;
    
    public static List<LocationType> getSubTypes(LocationType lt) {
        if (locationMap == null) {
            initializeMapping();
        }  
        
        return locationMap.get(lt);
    }

    private static void initializeMapping() {
        locationMap = new EnumMap<>(LocationType.class);
        
        /* Location */
        locationMap.put(LOCATION, Arrays.asList(LocationType.values()));
        
        /* Country */
        locationMap.put(COUNTRY, Arrays.asList(LOCATION,TERRITORY,CITY,TOWN,DISTRICT));
        
        /* Territory */
        locationMap.put(TERRITORY, Arrays.asList(LOCATION,CITY,TOWN,DISTRICT));
        
        /* City */
        locationMap.put(CITY, Arrays.asList(LOCATION,TOWN,DISTRICT));
        
        /* Town */
        locationMap.put(TOWN, Arrays.asList(LOCATION,DISTRICT));
        
         /* District */
        locationMap.put(DISTRICT, Arrays.asList(LOCATION));
        
    }
}

 

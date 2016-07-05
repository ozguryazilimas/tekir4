/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Sistemde kullanılabilecek olan ContactRole listesini tutar.
 * 
 * Bir enum yapamıyoruz çünkü alt modüller kendi rollerini ve bunlarla ilgili kuralları register edebilir.
 * 
 * 
 * FIXME: Aslında bu rollerin kullanıcı tarafından ne zaman seçilebileceğine dair de bir rule tanımlaması gerek sanırım
 * Örneğin : Account rolüne sahip olmayanın Customer ya da Vendor rolüne sahip olmasını bekleyemeyiz. Ayrıca Account rolüne de kullanıcı karar veremez.
 * 
 * @author Hakan Uygun
 */
public class ContactRoleRegistery {
    
    
    private static final List<String> contactRoles = new ArrayList<>();
    
    
    public static void register(String role) {
        contactRoles.add(role);
    }

    public static List<String> getContactRoles() {
        return contactRoles;
    }
    
}

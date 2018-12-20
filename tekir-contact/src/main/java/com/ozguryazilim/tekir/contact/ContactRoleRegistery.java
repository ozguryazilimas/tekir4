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
    
    /**
     * Tüm Roller
     */
    private static final List<String> contactRoles = new ArrayList<>();
    
    /**
     * Kullanıcı tarafından seçilebilir olanlar.
     */
    private static final List<String> selectableContactRoles = new ArrayList<>();

    /**
     * Filtre icin kullanilacak olan roller
     */
    private static final List<String> filterableContactRoles = new ArrayList<>();

    /**
     * Verilen bağlantı rolünü sisteme register eder.
     * @param role
     * @param forUser bu rolün kullanıcı tarafından seçilip seçilemeyeceği
     */
    public static void register(String role, boolean forUser, boolean forFilter) {
        contactRoles.add(role);
        if( forUser ){
            selectableContactRoles.add(role);
        }
        if (forFilter) {
            filterableContactRoles.add(role);
        }
    }

    public static List<String> getContactRoles() {
        return contactRoles;
    }
    
    public static List<String> getSelectableContactRoles() {
        return selectableContactRoles;
    }

    public static List<String> getFilterableContactRoles() {
        return filterableContactRoles;
    }
}

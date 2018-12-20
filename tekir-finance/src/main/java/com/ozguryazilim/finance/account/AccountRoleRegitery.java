package com.ozguryazilim.finance.account;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oyas
 */
public class AccountRoleRegitery {
 
    /**
     * Tüm Roller
     */
    private static final List<String> accountRoles = new ArrayList<>();
    
    /**
     * Kullanıcı tarafından seçilebilir olanlar.
     */
    private static final List<String> selectableAccountRoles = new ArrayList<>();
    
    
    /**
     * Verilen bağlantı rolünü sisteme register eder.
     * @param role
     * @param forUser bu rolün kullanıcı tarafından seçilip seçilemeyeceği
     */
    public static void register(String role, boolean forUser) {
        accountRoles.add(role);
        if( forUser ){
            selectableAccountRoles.add(role);
        }
    }

    public static List<String> getAccountRoles() {
        return accountRoles;
    }
    
    public static List<String> getSelectableAccountRoles() {
        return selectableAccountRoles;
    }

}

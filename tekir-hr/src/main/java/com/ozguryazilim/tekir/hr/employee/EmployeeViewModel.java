/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee;

import com.ozguryazilim.tekir.contact.ContactViewModel;
import com.ozguryazilim.tekir.entities.Contact;

/**
 * Employee View Model.
 * 
 * TODO: Empoyee'ye özgü alanlar eklenecek.
 * 
 * @author Hakan Uygun
 */
public class EmployeeViewModel extends ContactViewModel{
    
    public EmployeeViewModel(Long id, String code, String name, String info, Boolean active, Class<? extends Contact> type, Long pmMobileId, String pmMobile, Long pmPhoneId, String pmPhone, Long pmEmailId, String pmEmail) {
        super(id, code, name, info, active, type, pmMobileId, pmMobile, pmPhoneId, pmPhone, pmEmailId, pmEmail);
    }
    
}

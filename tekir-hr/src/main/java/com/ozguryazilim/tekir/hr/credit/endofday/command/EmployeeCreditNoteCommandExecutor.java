package com.ozguryazilim.tekir.hr.credit.endofday.command;

import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.hr.employee.EmployeeRepository;
import com.ozguryazilim.tekir.hr.credit.EmployeeCreditNoteRepository;
import com.ozguryazilim.tekir.voucher.number.VoucherSerialService;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import java.util.List;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erdem Uslu
 */
@CommandExecutor(command = EmployeeCreditNoteCommand.class)
public class EmployeeCreditNoteCommandExecutor extends AbstractCommandExecuter<EmployeeCreditNoteCommand> {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeCreditNoteCommandExecutor.class);
    
    @Inject
    private EmployeeRepository employeeRepository;
    
    @Inject
    private EmployeeCreditNoteRepository employeeCreditNoteRepository;
    
    @Inject
    private VoucherSerialService voucherSerialService;
    
    @Transactional
    @Override
    public void execute(EmployeeCreditNoteCommand command) {
        
        List<Employee> ls = employeeRepository.findAll();
        
        for (Employee employee: ls) {
            
            //Normalde employeeCreditNoteRepository.createNew() ile oluşturuyordum.
            EmployeeCreditNote e = new EmployeeCreditNote();
            e.setVoucherNo(voucherSerialService.getNewSerialNumber(FeatureRegistery.getHandler(e.getClass())));
            e.setDate(command.getDate().getCalculatedValue());
            e.setEmployee(employee);
            e.setFinanceAccount(command.getFinanceAccount());
            e.setAmount(command.getAmount());
            e.setCurrency(command.getCurrency());
            e.setTopic(command.getTopic());
            e.setInfo(command.getInfo());
            e.setPaymentDate(command.getPaymentDate().getCalculatedValue());
            e.setGroup(command.getGroup());
            e.setState(VoucherState.CLOSE);
            employeeCreditNoteRepository.saveAndFlush(e);
            
        }
    }
    
}

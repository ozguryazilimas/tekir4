package com.ozguryazilim.tekir.core.exchange.tcmb.command;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.core.currency.exchange.ExchangeRateRepository;
import com.ozguryazilim.tekir.core.currency.exchange.TCMBRateParser;
import com.ozguryazilim.tekir.entities.ExchangeRate;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;

/**
 *
 * @author oyas
 */
@CommandExecutor(command = TcmbExchangeRatesCommand.class)
public class TcmbExchangeRatesCommandExecutor extends AbstractCommandExecuter<TcmbExchangeRatesCommand>{

    private static final Logger LOG = LoggerFactory.getLogger(TcmbExchangeRatesCommandExecutor.class);
    
	@Inject
	private ExchangeRateRepository exchangeRateRepository;	

	@Inject
	private TCMBRateParser tcmbRateParser;
    
	@Transactional
    @Override
    public void execute(TcmbExchangeRatesCommand command) {
        Date date = new Date();
    	try {
			List<ExchangeRate> resultList = tcmbRateParser.getExchangeRatesByDate(date);
			exchangeRateRepository.deleteByDate(date);
			resultList.stream().forEach(xcr -> exchangeRateRepository.save(xcr));
	
		} catch (IOException | DocumentException e) {

			LOG.error("Hata", e);		
		}
        
        LOG.info("Exchange rates for the date {} will be fetched from CBRT.", date );    
        
    }
    
}

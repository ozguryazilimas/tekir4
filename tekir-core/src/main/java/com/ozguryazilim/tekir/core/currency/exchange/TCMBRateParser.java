package com.ozguryazilim.tekir.core.currency.exchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.ExchangeRate;

/**
 * @author Ceyhun Onur
 * Türkiye Cumhuriyeti Merkez Bankasına bağlanıp verilen tarihe göre tekir tarafından belirtilmiş kurları çeker.
 */
@Dependent
public class TCMBRateParser implements Serializable {	
	private final static String EXCHANGE_PROVIDER_URL = "http://www.tcmb.gov.tr/kurlar/";
	
    @Inject
    private CurrencyService currencyService;
    
    @Inject
    private ExchangeRateRepository exchangeRateRepository;
	
	/**
	 * TCMB sayfasına bir bağlantı açar, bağlantı 404 veriyorsa TCMB'nin eski sayfalarına bakar.
	 * @param date
	 * @return List<ExchangeRate> kurlar listesi
	 * @throws IOException
	 * @throws DocumentException
	 */
	public List<ExchangeRate> getExchangeRatesByDate(Date date) throws IOException, DocumentException{
		//Verilen tarih için bir bağlantı alır.
		HttpURLConnection connection = getConnectionByDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		//404 veriyorsa bir gün öncesinin sayfasına bakar.
		while(connection.getResponseCode() == 404){			
			calendar.add(Calendar.DATE, -1);
			//Connection açık kalmasın.
			connection.disconnect();
			connection = getConnectionByDate(calendar.getTime());			
		}		
		//Connection'ı parse methoduna yollar
		return parse(date,connection.getInputStream());		
		
	}
	
	/**
	 * Bir inputStream alarak içersindeki .xml'i ayrıştırır, ve ExhangeRate listesi döner.
	 * @param date
	 * @param inputStream
	 * @return List<ExchangeRate> kurlar listesi
	 * @throws DocumentException
	 * @throws IOException
	 */
	public List<ExchangeRate> parse(Date date, InputStream inputStream) throws DocumentException, IOException{
		List<ExchangeRate> resultList = new ArrayList<ExchangeRate>();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(inputStream);		
		
		for (Currency currency : currencyService.getAvailableCurrencies()){							
			String xPathQuery = "//*[@Kod=\""+currency.getCurrencyCode()+"\"]";
			if(doc.valueOf(xPathQuery) != null && !doc.valueOf(xPathQuery).isEmpty()){
			String buyRateStr = doc.valueOf("//*[@Kod=\""+currency.getCurrencyCode()+"\"]/BanknoteBuying");
			String sellRateStr = doc.valueOf("//*[@Kod=\""+currency.getCurrencyCode()+"\"]/BanknoteSelling");	
			String crossRateUsdStr = doc.valueOf("//*[@Kod=\""+currency.getCurrencyCode()+"\"]/CrossRateUSD");
			String crossRateOtherStr = doc.valueOf("//*[@Kod=\""+currency.getCurrencyCode()+"\"]/CrossRateOther");

			ExchangeRate er = new ExchangeRate();
			er.setDate(date);
			er.setBaseCurrency(currency);
			er.setTermCurrency(Currency.getInstance("TRY"));
			
			if(buyRateStr != null && !buyRateStr.isEmpty()){
			er.setBuyRate(new BigDecimal(buyRateStr));
			}
			else{
				String forexBuyRateStr = doc.valueOf("//*[@Kod=\""+currency.getCurrencyCode()+"\"]/ForexBuying");
				if(forexBuyRateStr != null && !forexBuyRateStr.isEmpty()){
				er.setBuyRate(new BigDecimal(forexBuyRateStr));
				}
			}
			
			if(sellRateStr != null && !sellRateStr.isEmpty()){
			er.setSellRate(new BigDecimal(sellRateStr));
			}
			else{
				String forexSellRateStr = doc.valueOf("//*[@Kod=\""+currency.getCurrencyCode()+"\"]/ForexSelling");
				if(forexSellRateStr != null && !forexSellRateStr.isEmpty()){
				er.setSellRate(new BigDecimal(forexSellRateStr));
				}
			}
			//Çapraz kurlar
			if(crossRateUsdStr != null && !crossRateUsdStr.isEmpty()){
				//TCMB çapraz kurları kur ve USD arasında yapıyor.
				ExchangeRate erc = new ExchangeRate();
				erc.setDate(date);
				erc.setBaseCurrency(Currency.getInstance("USD"));
				erc.setTermCurrency(currency);
				erc.setBuyRate(new BigDecimal(crossRateUsdStr));
				erc.setSellRate(new BigDecimal(crossRateUsdStr));
				resultList.add(erc);
			}
			else if(crossRateOtherStr != null && !crossRateOtherStr.isEmpty()){
				//TCMB CrossRateOther bilgisini USD/Kur çapraz dönüşümü için kullanıyor.
				ExchangeRate erc = new ExchangeRate();
				erc.setDate(date);
				erc.setBaseCurrency(currency);
				erc.setTermCurrency(Currency.getInstance("USD"));
				erc.setBuyRate(new BigDecimal(crossRateOtherStr));
				erc.setSellRate(new BigDecimal(crossRateOtherStr));
				resultList.add(erc);
			}
			
			resultList.add(er);
			}
		}
		inputStream.close();
		return resultList;
	}
	
	/**
	 * Verilen tarihe göre URL oluşturur ve bu URL'e connection açar.
	 * @param date
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection getConnectionByDate(Date date) throws IOException{
		URL url;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(DateUtils.isSameDay(date, new Date())){
			url = new URL(EXCHANGE_PROVIDER_URL + "today.xml");
		}
		
		else{			
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			String monthStr = String.format("%02d", month);  
			
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			String dayStr = String.format("%02d", day);  

			url = new URL(EXCHANGE_PROVIDER_URL + year + monthStr + "/" + dayStr + monthStr + year + ".xml");;
		}		
		
		
		HttpURLConnection connection =
				(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");	
		
		return connection;
	}
	

}

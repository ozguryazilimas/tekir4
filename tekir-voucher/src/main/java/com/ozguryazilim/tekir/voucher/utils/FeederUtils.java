package com.ozguryazilim.tekir.voucher.utils;

import org.primefaces.extensions.util.MessageUtils;

import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;

public class FeederUtils {

	private FeederUtils() {
	}

	public static String getEventMessage(VoucherOwnerChange event) {

        return "feeder.messages.OwnerChanged$%&" + event.getPayload().getVoucherNo() + "$%&" +
            event.getFrom() + "$%&" + event.getTo();
	}

}

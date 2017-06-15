package com.ozguryazilim.tekir.core.duration;

import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.google.common.base.Strings;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.messages.MessagesUtils;

@FacesConverter("durationConverter")
public class DurationConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (Strings.isNullOrEmpty(value)) {
			return null;
		}

		String hourAcr = MessagesUtils.getMessage("general.acronym.Hour");
		String minuteAcr = MessagesUtils.getMessage("general.acronym.Minute");

		if (!Pattern.compile("([0-9]+?" + hourAcr + "){0,1}([0-9]+?" + minuteAcr + "){0,1}").matcher(value).matches()) {
			FacesMessages.error(MessagesUtils.getMessage("general.message.InvalidDuration"));
			throw new ConverterException();
		}

		String[] times = value.split("(?<=" + hourAcr + "|" + minuteAcr + ")|(?=" + hourAcr + "|" + minuteAcr + ")");

		Long hours = 0l, minutes = 0l;

		if (times.length == 2) {
			if (times[1].equals(hourAcr)) {
				hours = Long.valueOf(times[0]);
			}
			else if (times[1].equals(minuteAcr)) {
				minutes = Long.valueOf(times[0]);
			}
		} else {
			hours = Long.valueOf(times[0]);
			minutes = Long.valueOf(times[2]);
		}

		return hours * 60 + minutes;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Long) {

			String hourAcr = MessagesUtils.getMessage("general.acronym.Hour");
			String minuteAcr = MessagesUtils.getMessage("general.acronym.Minute");

			Long duration = (Long) value;

			long hours = duration / 60, minutes = duration % 60;

			String durationString = hours + hourAcr + minutes + minuteAcr;

			return durationString;
		}

		return null;
	}

}

package com.ozguryazilim.tekir.core.duration;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ozguryazilim.telve.messages.MessagesUtils;

@FacesConverter("durationOutputConverter")
public class DurationOutputConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Long) {

			String hourExp = MessagesUtils.getMessage("general.expansion.Hour");
			String minuteExp = MessagesUtils.getMessage("general.expansion.Minute");

			Long duration = (Long) value;

			long hours = duration / 60, minutes = duration % 60;

			String durationString = hours + " " + hourExp + " " + minutes + " " + minuteExp;

			return durationString;
		}

		return null;
	}

}

package com.ozguryazilim.tekir.core.duration;

import com.ozguryazilim.telve.messages.Messages;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("durationOutputConverter")
public class DurationOutputConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value instanceof Long) {

			String hourExp = Messages.getMessage("general.expansion.Hour");
			String minuteExp = Messages.getMessage("general.expansion.Minute");

			Long duration = (Long) value;

			long hours = duration / 60, minutes = duration % 60;

			String durationString = hours + " " + hourExp + " " + minutes + " " + minuteExp;

			return durationString;
		}

		return null;
	}

}

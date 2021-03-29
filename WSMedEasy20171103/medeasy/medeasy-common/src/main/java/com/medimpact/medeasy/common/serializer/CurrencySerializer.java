package com.medimpact.medeasy.common.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CurrencySerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {		 
	    arg1.writeString(new DecimalFormat(",##0.00").format(arg0));
	}

}

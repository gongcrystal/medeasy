package com.medimpact.medeasy.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.medimpact.medeasy.common.utils.DateTimeUtil;

public class DatetimeSerializer4Long extends JsonSerializer<Long> {

	@Override
	public void serialize(Long arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {		 
	    arg1.writeString(DateTimeUtil.formatDateTime(arg0));
	}

}

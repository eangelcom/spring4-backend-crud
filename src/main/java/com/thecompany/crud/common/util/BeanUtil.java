package com.thecompany.crud.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public Object copyProperties(final Object dest, final Object orig) {
		try {
			
			BeanUtils.copyProperties(dest, orig);
			
		} catch (Exception e) {
			logger.warn("BeanUtil.copyProperties {}", e.getMessage());
		}
		
		return dest;
	}

	public List<String> csvToList(String csv) {
		if(null == csv || csv.isBlank()) {
			return new ArrayList<>();			
		} else {
			return Arrays.asList(csv.split(",", -1));
		}
	}

	public String listToCsv(List<String> list) {
		if(null == list || list.isEmpty()) {
			return "";			
		} else {
			return list.stream().collect(Collectors.joining(","));
		}
	}

}

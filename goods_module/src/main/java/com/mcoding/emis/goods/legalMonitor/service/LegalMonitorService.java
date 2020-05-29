package com.mcoding.emis.goods.legalMonitor.service;

import javax.servlet.http.HttpServletRequest;

public interface LegalMonitorService {
	boolean isOutsideInvoking(HttpServletRequest request);
	boolean isOverFrequency(int frequency, HttpServletRequest request);

}

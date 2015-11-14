package org.Hangaram.Hangaroid.AndroMoney;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;


public class SMSParser {

	public String[] DevideSMS(String strSMS)
	{
		String[] strResult = strSMS.split("\r\n");
		return strResult;
	}
	
	private ArrayList<typeParsingRule> GetParsingRule()
	{
		/*
		 * [일시불.승인]
		 * 3,500원
		 * 신한BC(0*9*)김대현님
		 * 03/21 21:54
		 * GS25대전만년점
		 * 
		 */
		ArrayList<typeParsingRule> ruleList = new ArrayList<typeParsingRule>();
		//typeParsingRule costline = new typeParsingRule(1, "\\p");
		//typeParsingRule purposeline = new typeParsingRule(5, "");
		//typeParsingRule dateline = new typeParsingRule();
		
		return ruleList;
		
	}
	
	public typeTransaction GetParsingResult(String phoneNo, String sms)
	{
		typeTransaction result = new typeTransaction();
		String[] strDevide = this.DevideSMS(sms);
		if(strDevide.length < 3)
		{
			return null;
		}
		// 분리후에 파싱룰 적용해야..
		GetParsingRule();
		
		// 날짜 03/21 21:54
		String originDate = strDevide[3];
		Pattern pattern = Pattern.compile("[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}");  
		Matcher matches = pattern.matcher(originDate);
		Date billingDate = new Date();
		billingDate.setMonth(Integer.valueOf(matches.replaceAll("$1")));
		billingDate.setDate(Integer.valueOf(matches.replaceAll("$2")));
		billingDate.setHours(Integer.valueOf(matches.replaceAll("$3")));
		billingDate.setMinutes(Integer.valueOf(matches.replaceAll("$2")));
		result.setBillingTime(billingDate);
		
		// 금액
		String originCost = strDevide[1].replace("원", "");
		originCost = originCost.replace(",", "");
		result.setCost(Long.valueOf(originCost));
		
		// 용도
		result.setContent(strDevide[4]);
		
		return result;
	}
	
	
}

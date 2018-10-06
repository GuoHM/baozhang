package service;

import java.util.List;

import bean.VwAllConditions;

public interface IAllConditionsService {
	
	List getCityConditionsByDate(String date);

	List getDistrictConditionsByDate(String date,String districtCompany);
	
	List getAllMonthConditionsByDate(String date);
}

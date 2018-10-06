package service;

import java.util.List;

import bean.VwAllConditionsYear;

public interface IAllConditionsYearService {

	List getCityConditionsByDate(String date);
	
	List getDistrictConditionsByDate(String date,String districtCompany);

}

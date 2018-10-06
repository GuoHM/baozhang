package dao;

import java.util.List;

import bean.VwAllConditions;

public interface IAllConditionsDao {

	List getCityConditionsByDate(String date);

	List getDistrictConditionsByDate(String date, String districtCompany);
	
	List getAllMonthConditionsByDate(String date);

}

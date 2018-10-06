package dao;

import java.util.List;

import bean.VwAllConditionsYear;

public interface IAllConditionsYearDao {

	List getCityConditionsByDate(String date);

	List getDistrictConditionsByDate(String date, String districtCompany);

}

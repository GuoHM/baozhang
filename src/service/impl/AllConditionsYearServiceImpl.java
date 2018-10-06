package service.impl;

import java.util.List;
import java.util.Map;

import bean.VwAllConditionsYear;
import bean.VwAllConditionsYearId;
import dao.IAllConditionsYearDao;

import service.IAllConditionsYearService;

public class AllConditionsYearServiceImpl implements IAllConditionsYearService {

	IAllConditionsYearDao allConditionsYearDao;

	@Override
	public List getCityConditionsByDate(String date) {
		// TODO Auto-generated method stub
		List temp = allConditionsYearDao.getCityConditionsByDate(date);
		return temp;
	}

	public IAllConditionsYearDao getAllConditionsYearDao() {
		return allConditionsYearDao;
	}

	public void setAllConditionsYearDao(IAllConditionsYearDao allConditionsYearDao) {
		this.allConditionsYearDao = allConditionsYearDao;
	}

	@Override
	public List getDistrictConditionsByDate(String date, String districtCompany) {
		// TODO Auto-generated method stub
		return allConditionsYearDao.getDistrictConditionsByDate(date, districtCompany);
	}

}

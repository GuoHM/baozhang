package service.impl;

import java.util.List;

import bean.VwAllConditions;
import dao.IAllConditionsDao;
import service.IAllConditionsService;

public class AllConditionsServiceImpl implements IAllConditionsService {

	IAllConditionsDao allConditionsDao;

	@Override
	public List<VwAllConditions> getCityConditionsByDate(String date) {
		// TODO Auto-generated method stub
		return allConditionsDao.getCityConditionsByDate(date);
	}

	public IAllConditionsDao getAllConditionsDao() {
		return allConditionsDao;
	}

	public void setAllConditionsDao(IAllConditionsDao allConditionsDao) {
		this.allConditionsDao = allConditionsDao;
	}

	public IAllConditionsDao getAllConditionsServiceDao() {
		return allConditionsDao;
	}

	public void setAllConditionsServiceDao(IAllConditionsDao allConditionsServiceDao) {
		this.allConditionsDao = allConditionsServiceDao;
	}

	@Override
	public List getDistrictConditionsByDate(String date, String districtCompany) {
		// TODO Auto-generated method stub
		return allConditionsDao.getDistrictConditionsByDate(date, districtCompany);
	}

	@Override
	public List getAllMonthConditionsByDate(String date) {
		// TODO Auto-generated method stub
		return allConditionsDao.getAllMonthConditionsByDate(date);
	}

}

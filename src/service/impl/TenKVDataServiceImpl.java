package service.impl;

import java.util.List;

import dao.ITenKVDataDao;
import service.ITenKVDataService;

public class TenKVDataServiceImpl implements ITenKVDataService {
	private ITenKVDataDao tenKVDataDao;

	@Override
	public List getDataByDate(String date) {
		// TODO Auto-generated method stub
		return tenKVDataDao.getDataByDate(date);
	}

	public ITenKVDataDao getTenKVDataDao() {
		return tenKVDataDao;
	}

	public void setTenKVDataDao(ITenKVDataDao tenKVDataDao) {
		this.tenKVDataDao = tenKVDataDao;
	}

	@Override
	public List getEntityDataByDateAndName(String date, String entityName) {
		// TODO Auto-generated method stub
		return tenKVDataDao.getEntityDataByDateAndName(date, entityName);
	}


}

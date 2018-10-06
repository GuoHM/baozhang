package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import bean.TblBaozhang;

import dao.IBaozhangDao;
import service.IBaozhangService;

public class BaozhangServiceImpl implements IBaozhangService {
	private IBaozhangDao baozhangDao;

	@Override
	public List<TblBaozhang> getDataByConditions(Map<String, String> conditions, int startSize, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		
			return baozhangDao.getDataByConditions(conditions, startSize, pageSize);
		
	}

	public IBaozhangDao getBaozhangDao() {
		return baozhangDao;
	}

	public void setBaozhangDao(IBaozhangDao baozhangDao) {
		this.baozhangDao = baozhangDao;
	}

	@Override
	public List<String> getAllDistrictCompany() throws Exception {
		// TODO Auto-generated method stub
		return baozhangDao.getAllDistrictCompany();
	}

	@Override
	public List<String> getAllAccountDate() throws Exception {
		// TODO Auto-generated method stub
		return baozhangDao.getAllAccountDate();
	}

	@Override
	public List<String> getAllAccountMethod() throws Exception {
		// TODO Auto-generated method stub
		return baozhangDao.getAllAccountMethod();
	}

	@Override
	public List<String> getAllElectricType() throws Exception {
		// TODO Auto-generated method stub
		return baozhangDao.getAllElectricType();
	}

	@Override
	public int getAll(Map<String, String> conditions) {
		// TODO Auto-generated method stub
		
			return baozhangDao.getAll(conditions);
		
	}

	@Override
	public Set<String> getAllYear() throws Exception {
		// TODO Auto-generated method stub
		List<String> list = baozhangDao.getYear();
		Set<String> result = new TreeSet<String>();
		for (String i : list) {
			result.add(i.substring(0,4));
		}
		return result;
	}

}

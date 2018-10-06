package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.TblBaozhang;
;

public interface IBaozhangService {

	List<TblBaozhang> getDataByConditions(Map<String, String> conditions, int startSize, int pageSize) throws Exception;

	int getAll(Map<String, String> conditions);

	List<String> getAllDistrictCompany() throws Exception;

	List<String> getAllAccountDate() throws Exception;

	List<String> getAllAccountMethod() throws Exception;

	List<String> getAllElectricType() throws Exception;
	
	Set<String> getAllYear() throws Exception;

}

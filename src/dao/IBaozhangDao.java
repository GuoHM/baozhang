package dao;

import java.util.List;
import java.util.Map;

import bean.*;

public interface IBaozhangDao {

	List<TblBaozhang> getDataByConditions(Map<String, String> conditions, int startSize, int pageSize) throws Exception;
	
	int getAll(Map<String, String> conditions);

	List<String> getAllDistrictCompany() throws Exception;

	List<String> getAllAccountDate() throws Exception;

	List<String> getAllAccountMethod() throws Exception;

	List<String> getAllElectricType() throws Exception;
	
	List<String> getYear() throws Exception;
	

}

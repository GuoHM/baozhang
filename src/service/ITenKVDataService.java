package service;

import java.util.List;

public interface ITenKVDataService {

	List getDataByDate(String date);
	
	List getEntityDataByDateAndName(String date, String entityName);

}

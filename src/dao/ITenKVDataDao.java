package dao;

import java.util.List;

public interface ITenKVDataDao {

	List getDataByDate(String date);

	List getEntityDataByDateAndName(String date, String entityName);

}

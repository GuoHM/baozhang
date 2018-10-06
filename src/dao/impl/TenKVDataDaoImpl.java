package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ITenKVDataDao;

public class TenKVDataDaoImpl extends HibernateDaoSupport implements ITenKVDataDao {

	@Override
	public List getDataByDate(String date) {
		// TODO Auto-generated method stub
		String hql = "select SUM(t.id.totalElectricity) as totalElectricity,sum(t.id.totalAmount) as totalAmount, t.id.accountDate as accountDate"
				+ " from  Vw10kvdata t"
				+ " where  t.id.accountDate like ?  group by t.id.accountDate order by CONVERT(DATE,t.id.accountDate+'-01') asc";
		Session session = null;
		List temp = new ArrayList();
		try {
			session = getSession();
			temp = (session.createQuery(hql)).setParameter(0, date)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		} finally {
			releaseSession(session);
		}
		return temp;
	}

	@Override
	public List getEntityDataByDateAndName(String date, String entityName) {
		// TODO Auto-generated method stub
		String hql = "select SUM(t.id.totalElectricity) as totalElectricity,sum(t.id.totalAmount) as totalAmount, t.id.accountDate as accountDate"
				+ " from  Vw10kvdata t"
				+ " where  t.id.accountDate like ?  and t.id.entityName=? group by t.id.accountDate order by CONVERT(DATE,t.id.accountDate+'-01') asc";
		Session session = null;
		List temp = new ArrayList();
		try {
			session = getSession();
			temp = (session.createQuery(hql)).setParameter(0, date).setParameter(1, entityName)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		} finally {
			releaseSession(session);
		}
		return temp;
	}

}

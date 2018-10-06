package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.VwAllConditions;
import dao.IAllConditionsDao;

public class AllConditionsDaoImpl extends HibernateDaoSupport implements IAllConditionsDao {

	@Override
	public List getCityConditionsByDate(String date) {
		// TODO Auto-generated method stub
		String hql = "select SUM(t.id.totalElectricity) as totalElectricity,sum(t.id.totalAmount) as totalAmount,t.id.electricType as electricType "
				+ " from  VwAllConditions t" + " where  t.id.accountDate=? group by t.id.electricType";
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
	public List getDistrictConditionsByDate(String date, String districtCompany) {
		// TODO Auto-generated method stub
		String hql = "select SUM(t.id.totalElectricity) as totalElectricity,sum(t.id.totalAmount) as totalAmount,t.id.electricType as electricType "
				+ " from  VwAllConditions t"
				+ " where  t.id.accountDate=? and t.id.districtCompany like ? group by t.id.electricType";
		Session session = null;
		List temp = new ArrayList();
		try {
			session = getSession();
			temp = (session.createQuery(hql)).setParameter(0, date).setParameter(1, districtCompany)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		} finally {
			releaseSession(session);
		}
		return temp;
	}

	@Override
	public List getAllMonthConditionsByDate(String date) {
		// TODO Auto-generated method stub
		String hql = "select SUM(t.id.totalElectricity) as totalElectricity,sum(t.id.totalAmount) as totalAmount, t.id.accountDate as accountDate"
				+ " from  VwAllConditions t" + " where  t.id.accountDate like ?  group by t.id.accountDate";
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

}

package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.VwAllConditionsYear;
import dao.IAllConditionsYearDao;

public class AllConditionsYearDaoImpl extends HibernateDaoSupport implements IAllConditionsYearDao {

	@Override
	public List getCityConditionsByDate(String date) {
		// TODO Auto-generated method stub
		String hql = "select SUM(t.id.totalElectricity) as totalElectricity,sum(t.id.totalAmount) as totalAmount,t.id.electricType as electricType "
				+ " from  VwAllConditionsYear t" + " where  t.id.dateYear like ? group by t.id.electricType";
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
				+ " from  VwAllConditionsYear t"
				+ " where  t.id.dateYear like ? and t.id.districtCompany like ? group by t.id.electricType";
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

}

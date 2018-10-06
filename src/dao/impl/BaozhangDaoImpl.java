package dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bean.TblBaozhang;
import dao.IBaozhangDao;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaozhangDaoImpl extends HibernateDaoSupport implements IBaozhangDao {

	@Override
	public List<TblBaozhang> getDataByConditions(Map<String, String> conditions, int startSize, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		String hql = "from TblBaozhang t where";
		boolean endsWithAnd = false;
		Iterator<Entry<String, String>> iter = conditions.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			if (!entry.getValue().equals("")) {
				if (entry.getKey().equals("accountDate")||entry.getKey().equals("districtCompany")) {
					//仅对日期和区公司名称模糊查询	
					hql += " t.id." + entry.getKey() + " like '%" + entry.getValue() + "' and ";
				} else {
					hql += " t.id." + entry.getKey() + "='" + entry.getValue() + "' and ";
				}
				endsWithAnd = true;
			}
		}
		if (endsWithAnd) {
			hql = hql.substring(0, hql.length() - 4);
		} else {
			hql = hql.substring(0, hql.length() - 5);
		}
		Session session = null;
		List<TblBaozhang> list = new ArrayList<TblBaozhang>();
		try {
			session = getSession();
			if(startSize!=0){
				//startSize不为0表示取部分条数
				list = (List<TblBaozhang>) session.createQuery(hql).setFirstResult(startSize).setMaxResults(pageSize)
						.list();
			} else {
				//startSize为0表示取全部数据
				list = (List<TblBaozhang>) session.createQuery(hql).list();
			}
		} finally {
			releaseSession(session);
		}
		return list;

	}

	@Override
	public List<String> getAllDistrictCompany() throws Exception {
		// TODO Auto-generated method stub
		String hql = "select distinct t.id.districtCompany from TblBaozhang t";
		Session session = null;
		List<String> list = new ArrayList<String>();
		try {
			session = getSession();
			list = (List<String>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public List<String> getAllAccountDate() throws Exception {
		// TODO Auto-generated method stub
		String hql = "select distinct t.id.accountDate from TblBaozhang t";
		Session session = null;
		List<String> list = new ArrayList<String>();
		try {
			session = getSession();
			list = (List<String>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public List<String> getAllAccountMethod() throws Exception {
		// TODO Auto-generated method stub
		String hql = "select distinct t.id.accountMethod from TblBaozhang t";
		Session session = null;
		List<String> list = new ArrayList<String>();
		try {
			session = getSession();
			list = (List<String>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public List<String> getAllElectricType() throws Exception {
		// TODO Auto-generated method stub
		String hql = "select distinct t.id.electricType from TblBaozhang t";
		Session session = null;
		List<String> list = new ArrayList<String>();
		try {
			session = getSession();
			list = (List<String>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

	@Override
	public int getAll(Map<String, String> conditions) {
		// TODO Auto-generated method stub
		String hql = "from TblBaozhang t where";
		boolean endsWithAnd = false;
		Iterator<Entry<String, String>> iter = conditions.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			if (!entry.getValue().equals("")) {
				if (entry.getKey().equals("accountDate")) {
					//仅对日期模糊查询	
					hql += " t.id." + entry.getKey() + " like '" + entry.getValue() + "' and ";

				} else {
					hql += " t.id." + entry.getKey() + "='" + entry.getValue() + "' and ";
				}
				endsWithAnd = true;
			}
		}
		if (endsWithAnd) {
			hql = hql.substring(0, hql.length() - 4);
		} else {
			hql = hql.substring(0, hql.length() - 5);
		}
		Session session = null;
		List<TblBaozhang> list = new ArrayList<TblBaozhang>();
		try {
			session = getSession();
			list = (List<TblBaozhang>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}

		return list.size();
	}

	@Override
	public List<String> getYear() throws Exception {
		// TODO Auto-generated method stub
		String hql = "select distinct t.id.accountDate from TblBaozhang t";
		Session session = null;
		List<String> list = new ArrayList<String>();
		try {
			session = getSession();
			list = (List<String>) session.createQuery(hql).list();
		} finally {
			releaseSession(session);
		}
		return list;
	}

}

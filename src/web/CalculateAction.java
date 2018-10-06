package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.TblBaozhang;
import service.IAllConditionsService;
import service.IAllConditionsYearService;
import service.IBaozhangService;

public class CalculateAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4711404888359718304L;
	private String accountDate;
	private String districtCompany;
	private int pageSize;
	private IAllConditionsService allConditionsService;
	private IAllConditionsYearService allConditionsYearService;
	private IBaozhangService baozhangService;
	ActionContext context = ActionContext.getContext();
	private HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

	/**
	 * @Method: calculate @Description: Return the result of statistics by
	 * conditions @param @throws Exception @return void @throws
	 */
	public void calculate() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> para = setParameters();
		List<TblBaozhang> list = baozhangService.getDataByConditions(para, 0, pageSize);
		// startSize=0表示取全部数据
		double result = 0, amountResult = 0;
		for (TblBaozhang i : list) {
			String accountMethod = i.getId().getAccountMethod();
			double money = i.getId().getUtilitiesFee().doubleValue();
			double amount = i.getId().getElectricAmount().doubleValue();
			if (accountMethod.equals("收回代垫外单位或员工费用并冲销已列成本") || accountMethod.equals("冲销预列")
					|| accountMethod.equals("（调账）冲销成本")) {
				result -= money;
				amountResult -= amount;
			} else {
				result += money;
				amountResult += amount;
			}
		}
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("result", result);
		map.put("amountResult", amountResult);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		List<String> jsonlist = new ArrayList<String>();
		jsonlist.add(json);
		response.getWriter().print(jsonlist);
	}

	public void initYear() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		Set<String> yearList = new TreeSet<String>();
		yearList = baozhangService.getAllYear();
		map.put("yearList", yearList);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		List<String> jsonlist= new ArrayList<String>();
		jsonlist.add(json);
		response.getWriter().print(jsonlist);
	}

	/**
	 * @Method: getCityData @Description: Get the statistics data group by
	 * different electricity types in the whole city @param @throws
	 * IOException @return void @throws
	 */
	public void getCityData() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		List list = new ArrayList();
		if (accountDate.contains("%")) {
			// 只选了年,%表示全部区
			list = allConditionsYearService.getCityConditionsByDate(accountDate);
		} else {
			// 选了固定的某个月
			list = allConditionsService.getCityConditionsByDate(accountDate);
		}
		List<String> lists = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("id", i + 1);
				jsonMap.put("electricType", map.get("electricType"));
				jsonMap.put("totalAmount", map.get("totalAmount"));
				jsonMap.put("totalElectricity", map.get("totalElectricity"));
				lists.add(mapper.writeValueAsString(jsonMap));
			}
		}
		response.getWriter().print(lists);
	}

	/**
	 * @Method: getDistrictConditions @Description: Get the statistics data
	 * group by different electricity types in the selected district
	 * company @param @throws IOException @return void @throws
	 */
	public void getDistrictConditions() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		List list = new ArrayList();
		if (accountDate.contains("%")) {
			// 只选了年,%表示全部区
			list = allConditionsYearService.getDistrictConditionsByDate(accountDate, districtCompany);
		} else {
			// 选了固定的某个月
			list = allConditionsService.getDistrictConditionsByDate(accountDate, districtCompany);
		}
		List<String> lists = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("id", i + 1);
				jsonMap.put("electricType", map.get("electricType"));
				jsonMap.put("totalAmount", map.get("totalAmount"));
				jsonMap.put("totalElectricity", map.get("totalElectricity"));
				lists.add(mapper.writeValueAsString(jsonMap));
			}
		}
		response.getWriter().print(lists);
	}

	private Map<String, String> setParameters() {
		Map<String, String> para = new HashMap<String, String>();
		if (accountDate != null) {
			para.put("accountDate", accountDate);
		}
		if (districtCompany != null) {
			para.put("districtCompany", districtCompany);
		}
		return para;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IAllConditionsService getAllConditionsService() {
		return allConditionsService;
	}

	public void setAllConditionsService(IAllConditionsService allConditionsService) {
		this.allConditionsService = allConditionsService;
	}

	public ActionContext getContext() {
		return context;
	}

	public void setContext(ActionContext context) {
		this.context = context;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public IAllConditionsYearService getAllConditionsYearService() {
		return allConditionsYearService;
	}

	public void setAllConditionsYearService(IAllConditionsYearService allConditionsYearService) {
		this.allConditionsYearService = allConditionsYearService;
	}

	public IBaozhangService getBaozhangService() {
		return baozhangService;
	}

	public void setBaozhangService(IBaozhangService baozhangService) {
		this.baozhangService = baozhangService;
	}

	public String getDistrictCompany() {
		return districtCompany;
	}

	public void setDistrictCompany(String districtCompany) {
		this.districtCompany = districtCompany;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

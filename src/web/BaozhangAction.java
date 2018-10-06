
package web;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.TblBaozhang;

import net.sf.json.JSONObject;
import service.IBaozhangService;

public class BaozhangAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1312901920530023534L;
	private String districtCompany;
	private String accountDate;
	private String businessType;
	private String accountMethod;
	private String electricType;
	private int pageNumber;
	private int pageSize;
	private IBaozhangService baozhangService;
	ActionContext context = ActionContext.getContext();
	private HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);;

	/**
	 * @Method: initData
	 * @Description: Get the initial items in the select bar
	 * @param @throws
	 *            Exception
	 * @return void
	 */
	public void initData() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> districtCompanyList = new ArrayList<String>(), accountMethodList = new ArrayList<String>(),
				electricTypeList = new ArrayList<String>(), yearList = new ArrayList<String>();
		districtCompanyList = baozhangService.getAllDistrictCompany();
		Set<String> yearList1 = new TreeSet<String>();
		yearList1 = baozhangService.getAllYear();
		yearList.addAll(yearList1);
		accountMethodList = baozhangService.getAllAccountMethod();
		electricTypeList = baozhangService.getAllElectricType();
		map.put("districtCompanyList", districtCompanyList);
		map.put("yearList", yearList);
		map.put("accountMethodList", accountMethodList);
		map.put("electricTypeList", electricTypeList);
		List<Map<String, List<String>>> jsonlist = new ArrayList<Map<String, List<String>>>();
		jsonlist.add(map);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(jsonlist);
		System.out.println(json);
		response.getWriter().print(json);
		System.out.println("h111");
	}

	/**
	 * @Method: getData
	 * @Description: Get data by conditions
	 * @param @throws
	 *            Exception
	 * @return void
	 */
	public void getData() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		int startSize = (pageNumber - 1) * pageSize;
		Map<String, String> para = setParameters();
		List<TblBaozhang> list = baozhangService.getDataByConditions(para, startSize, pageSize);
		int TotalCount = baozhangService.getAll(para);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", setTableData(list));// JSONArray
		jsonObject.put("total", TotalCount);// ×Ü¼ÇÂ¼Êý
		response.getWriter().print(jsonObject);
	}

	/**
	 * @Method: setParameters
	 * @Description: Get the items from select bar and determine whether is null
	 * @return Map<String,String>
	 */
	private Map<String, String> setParameters() {
		Map<String, String> para = new HashMap<String, String>();
		if (districtCompany != null) {
			para.put("districtCompany", districtCompany);
		}
		para.put("accountDate", accountDate);
		if (businessType != null) {
			para.put("businessType", businessType);
		}
		if (accountMethod != null) {
			para.put("accountMethod", accountMethod);
		}
		if (electricType != null) {
			para.put("electricType", electricType);
		}
		return para;
	}

	/**
	 * @Method: setTableData
	 * @Description: Convert the bean list into the json data for the bootstrap
	 *               table formatter
	 * @param The
	 *            bean list
	 * @return JSONArray
	 * @throws JsonProcessingException
	 */
	private String setTableData(List<TblBaozhang> list) throws JsonProcessingException {
		List<Map<String, Object>> JsonList = new ArrayList<Map<String, Object>>();
		if (list.size() != 0) {
			for (int i = 0; i < pageSize && i < list.size(); i++) {
				Map<String, Object> jo = new HashMap<String, Object>();
				jo.put("id", i + 1);
				TblBaozhang e = list.get(i);
				jo.put("status", e.getId().getStatus());
				jo.put("branchCompany", e.getId().getBranchCompany());
				jo.put("districtCompany", e.getId().getDistrictCompany());
				jo.put("costObjectId", e.getId().getCostObjectId());
				jo.put("costObjectName", e.getId().getCostObjectName());
				jo.put("entityId", e.getId().getEntityId());
				jo.put("entityName", e.getId().getEntityName());
				jo.put("costType", e.getId().getCostType());
				jo.put("accountDate", e.getId().getAccountDate());
				jo.put("accountPerson", e.getId().getAccountPerson());
				jo.put("startedDate", e.getId().getStartedDate());
				jo.put("endedDate", e.getId().getEndedDate());
				jo.put("accountType", e.getId().getAccountType());
				jo.put("businessType", e.getId().getBusinessType());
				jo.put("accountMethod", e.getId().getAccountMethod());
				jo.put("electricType", e.getId().getElectricType());
				jo.put("electricAmount", e.getId().getElectricAmount());
				jo.put("electricPrice", e.getId().getElectricPrice());
				jo.put("accountAmount", e.getId().getAccountAmount());
				jo.put("tax", e.getId().getTax());
				jo.put("payMethod", e.getId().getPayMethod());
				jo.put("utilitiesFee", e.getId().getUtilitiesFee());
				jo.put("sewageFee", e.getId().getSewageFee());
				jo.put("garbageFee", e.getId().getGarbageFee());
				jo.put("accountCenterName", e.getId().getAccountCenterName());
				jo.put("accountCenterId", e.getId().getAccountCenterId());
				jo.put("userId", e.getId().getUserId());
				jo.put("advancePayment", e.getId().getAdvancePayment());
				jo.put("billType", e.getId().getBillType());
				jo.put("is10kv", e.getId().getIs10kv());
				JsonList.add(jo);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(JsonList);
		return json;
	}

	public String getDistrictCompany() {
		return districtCompany;
	}

	public void setDistrictCompany(String districtCompany) {
		this.districtCompany = districtCompany;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getAccountMethod() {
		return accountMethod;
	}

	public void setAccountMethod(String accountMethod) {
		this.accountMethod = accountMethod;
	}

	public String getElectricType() {
		return electricType;
	}

	public void setElectricType(String electricType) {
		this.electricType = electricType;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public IBaozhangService getBaozhangService() {
		return baozhangService;
	}

	public void setBaozhangService(IBaozhangService baozhangService) {
		this.baozhangService = baozhangService;
	}

	public ActionContext getContext() {
		return context;
	}

	public void setContext(ActionContext context) {
		this.context = context;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

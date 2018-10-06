package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import service.ITenKVDataService;
import util.ErrorFix;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AnalysisAction extends ActionSupport implements ServletRequestAware {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8362432242565768419L;
	private String accountDate;
	private String entityName;
	private ITenKVDataService tenKVDataService;
	private int predictNumber;
	private double offset;
	private int trendNumber;
	ActionContext context = ActionContext.getContext();
	private HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
	private String modelName;
	
	public void setChart() throws IOException {
		List list = tenKVDataService.getDataByDate(accountDate);
		outputEChartJson(list);
	}

	public void setEntityChart() throws IOException {
		List list = tenKVDataService.getEntityDataByDateAndName(accountDate, entityName);
		outputEChartJson(list);
	}

	public void setPredictionChart() throws IOException, REngineException, REXPMismatchException {
		ArrayList<Double> orign = (ArrayList<Double>) context.getSession().get("data");
		ArrayList<String> month = new ArrayList<String>();
		ArrayList<String> predict = new ArrayList<String>();
		double[] data = new double[orign.size()];
		for (int i = 0; i < data.length; i++) {
			data[i] = orign.get(i);
		}
		String forecastCode = null;
		switch (modelName) {
		case "ARIMA":
			forecastCode = "arima(data1,order=c(0,1,0),seasonal=list(order=c(1,0,0),period=12))";
			break;
		case "HoltWinters":
			forecastCode = "HoltWinters(data1)";
			break;
		}
		RConnection c = new RConnection("127.0.0.1");
		c.eval("library(tseries)");
		c.eval("library(forecast)");
		c.assign("data", data);
		c.eval("data1<-ts(data,frequency=12,start=2014)");
		c.eval("fit<-" + forecastCode);
		c.eval("f.p1<-forecast(fit,h=" + predictNumber + ",level=c(99.5))");
		double[] prediction = c.eval("f.p1$mean").asDoubles();
		double[] fitted = c.eval("f.p1$fitted").asDoubles();
		String modelname = c.eval("f.p1$method").asString();
//		int aic = c.eval("fit$aic").asInteger();
//		double MAPE = c.eval("accuracy(fit)[5]").asDouble();
//		double RMSE = c.eval("accuracy(fit)[2]").asDouble();
		c.close();
		for (int i = 0; i < data.length + predictNumber; i++) {
			month.add("" + (2014 + i / 12) + "-" + ((i % 12) + 1));
			if (i < data.length) {
				predict.add("" + fitted[i]);
			} else {
				predict.add("" + prediction[i - data.length]);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("amount", orign);
		jsonMap.put("categories", month);
		jsonMap.put("predict", predict);
		ObjectMapper mapper = new ObjectMapper();
		jsonMap.put("modelname", modelname);
//		jsonMap.put("aic", aic);
//		jsonMap.put("MAPE", MAPE);
//		jsonMap.put("RMSE", RMSE);
		String json = mapper.writeValueAsString(jsonMap);
		System.out.println(json);
		response.getWriter().print(json);
	}

	public void fixData() throws IOException {
		context.getSession().remove("data");
		ArrayList<Double> data = getOrignalElectricityData();
		ErrorFix fix = new ErrorFix(offset, trendNumber, data);
		data = fix.getFixData();
		context.getSession().put("data", data);
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ArrayList<String> month = new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			month.add("" + (2014 + i / 12) + "-" + ((i % 12) + 1));
		}
		jsonMap.put("categories", month);
		jsonMap.put("amount", data);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(jsonMap);
		response.getWriter().print(json);
	}

	/**
	 * @Method: getEChartJson
	 * @Description: Get the chart json with xAxis is month and yAxis is amount
	 *               and money,without prediction number
	 * @param datalist
	 * @throws IOException
	 *             void
	 */
	private void outputEChartJson(List datalist) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ArrayList<Object> month = new ArrayList<Object>();
		ArrayList<Object> amount = new ArrayList<Object>();
		ArrayList<Object> money = new ArrayList<Object>();
		for (int i = 0; i < datalist.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) datalist.get(i);
			month.add(map.get("accountDate"));
			amount.add(map.get("totalElectricity"));
			money.add(map.get("totalAmount"));
		}
		jsonMap.put("categories", month);
		jsonMap.put("amount", amount);
		jsonMap.put("money", money);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(jsonMap);
		response.getWriter().print(json);
	}

	private ArrayList<Double> getOrignalElectricityData() {
		ArrayList<Double> temp = new ArrayList<Double>();
		List list = tenKVDataService.getDataByDate("%");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) list.get(i);
			temp.add(Double.parseDouble(map.get("totalElectricity").toString()));
		}
		return temp;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(String accountDate) {
		this.accountDate = accountDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

	public ITenKVDataService getTenKVDataService() {
		return tenKVDataService;
	}

	public void setTenKVDataService(ITenKVDataService tenKVDataService) {
		this.tenKVDataService = tenKVDataService;
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

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public int getPredictNumber() {
		return predictNumber;
	}

	public void setPredictNumber(int predictNumber) {
		this.predictNumber = predictNumber;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public int getTrendNumber() {
		return trendNumber;
	}

	public void setTrendNumber(int trendNumber) {
		this.trendNumber = trendNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	

}

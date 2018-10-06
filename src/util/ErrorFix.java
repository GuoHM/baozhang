package util;

import java.util.ArrayList;

public class ErrorFix {

	private double offset;
	private int trendNumber;
	private ArrayList<Double> data;

	public ErrorFix(double offset, int trendNumber, ArrayList<Double> data) {
		super();
		this.offset = offset;
		this.trendNumber = trendNumber;
		this.data = data;
	}

	public ArrayList<Double> getFixData() {
		double x1, x2, gradient=0;
		if (offset != 0) {
			for (int i = 0; i + 3 < data.size(); i++) {
				
				if (isError(data.get(i), data.get(i + 1), data.get(i + 2), data.get(i + 3))) {
					System.out.println(i);
					double sum = data.get(i + 1) + data.get(i + 2);
					
					if (trendNumber != 0 && i + 3 + trendNumber <= data.size() - 1 && i - trendNumber > 0) {
						gradient = (data.get(i + 3 + trendNumber) - data.get(i - trendNumber)) / (3 + 2 * trendNumber);
					} else if (i + 3 == data.size() - 1) {
						// last 4 point
						gradient = (data.get(i + 3) - data.get(i)) / 3;
					}
					System.out.println(gradient);
					if (gradient > 0) {
						x2 = (sum + gradient) / 2;
						x1 = x2 - gradient;
					} else {
						x1 = (sum - gradient) / 2;
						x2 = x1 + gradient;
					}
					this.data.set(i + 1, x1);
					this.data.set(i + 2, x2);
				}
			}
		}
		return this.data;
	}

	private boolean isError(double x1, double x2, double x3, double x4) {
		double min = 1 - offset;
		double max = 1 + offset;
		return (x1 * min >= x2 && x2 * max <= x3 && x3 * min >= x4)
				|| (x1 * max <= x2 && x2 * min >= x3 && x3 * max <= x4);
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

	public ArrayList<Double> getData() {
		return data;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}

}

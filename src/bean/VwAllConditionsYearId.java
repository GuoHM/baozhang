package bean;
// Generated 2017-12-22 15:37:05 by Hibernate Tools 3.5.0.Final

import java.math.BigDecimal;

/**
 * VwAllConditionsYearId generated by hbm2java
 */
public class VwAllConditionsYearId implements java.io.Serializable {

	private BigDecimal totalElectricity;
	private BigDecimal totalAmount;
	private String electricType;
	private String dateYear;
	private String districtCompany;

	public VwAllConditionsYearId() {
	}

	public VwAllConditionsYearId(String districtCompany) {
		this.districtCompany = districtCompany;
	}

	public VwAllConditionsYearId(BigDecimal totalElectricity, BigDecimal totalAmount, String electricType,
			String dateYear, String districtCompany) {
		this.totalElectricity = totalElectricity;
		this.totalAmount = totalAmount;
		this.electricType = electricType;
		this.dateYear = dateYear;
		this.districtCompany = districtCompany;
	}

	public BigDecimal getTotalElectricity() {
		return this.totalElectricity;
	}

	public void setTotalElectricity(BigDecimal totalElectricity) {
		this.totalElectricity = totalElectricity;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getElectricType() {
		return this.electricType;
	}

	public void setElectricType(String electricType) {
		this.electricType = electricType;
	}

	public String getDateYear() {
		return this.dateYear;
	}

	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}

	public String getDistrictCompany() {
		return this.districtCompany;
	}

	public void setDistrictCompany(String districtCompany) {
		this.districtCompany = districtCompany;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VwAllConditionsYearId))
			return false;
		VwAllConditionsYearId castOther = (VwAllConditionsYearId) other;

		return ((this.getTotalElectricity() == castOther.getTotalElectricity())
				|| (this.getTotalElectricity() != null && castOther.getTotalElectricity() != null
						&& this.getTotalElectricity().equals(castOther.getTotalElectricity())))
				&& ((this.getTotalAmount() == castOther.getTotalAmount())
						|| (this.getTotalAmount() != null && castOther.getTotalAmount() != null
								&& this.getTotalAmount().equals(castOther.getTotalAmount())))
				&& ((this.getElectricType() == castOther.getElectricType())
						|| (this.getElectricType() != null && castOther.getElectricType() != null
								&& this.getElectricType().equals(castOther.getElectricType())))
				&& ((this.getDateYear() == castOther.getDateYear()) || (this.getDateYear() != null
						&& castOther.getDateYear() != null && this.getDateYear().equals(castOther.getDateYear())))
				&& ((this.getDistrictCompany() == castOther.getDistrictCompany())
						|| (this.getDistrictCompany() != null && castOther.getDistrictCompany() != null
								&& this.getDistrictCompany().equals(castOther.getDistrictCompany())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTotalElectricity() == null ? 0 : this.getTotalElectricity().hashCode());
		result = 37 * result + (getTotalAmount() == null ? 0 : this.getTotalAmount().hashCode());
		result = 37 * result + (getElectricType() == null ? 0 : this.getElectricType().hashCode());
		result = 37 * result + (getDateYear() == null ? 0 : this.getDateYear().hashCode());
		result = 37 * result + (getDistrictCompany() == null ? 0 : this.getDistrictCompany().hashCode());
		return result;
	}

}
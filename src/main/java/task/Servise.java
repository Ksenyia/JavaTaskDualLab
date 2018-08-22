package task;

import java.time.LocalTime;

public class Servise implements Comparable<Servise>  {
	
	
	public Servise(String servise) {
		super();
		String seperate = "\\s";
		String[] s = servise.split(seperate);
		this.company = s[0];
		this.departureTime = LocalTime.parse(s[1]);
		this.arrivalTime = LocalTime.parse(s[2]);
		this.efficient = true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + (efficient ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servise other = (Servise) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (efficient != other.efficient)
			return false;
		return true;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	@Override
	public String toString() {
		return company+" " + departureTime+" "
				+ arrivalTime;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isEfficient() {
		return efficient;
	}
	public void setEfficient(boolean efficient) {
		this.efficient = efficient;
	}
	public int compareTo(Servise anotherServise) {
		LocalTime anotherTimeValue = anotherServise.getDepartureTime();
		LocalTime thisTimeValue = this.getDepartureTime();
		return thisTimeValue.compareTo(anotherTimeValue);
	}
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private boolean efficient;
	private String company;
}

/**
 * 
 */
package com.md.dm.dw.lastfm.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * <a href=
 * "http://download.oracle.com/javase/1.5.0/docs/api/java/util/GregorianCalendar.html"
 * > Gregorian Calendar</a>
 * 
 * <a
 * href="http://mondrian.pentaho.com/documentation/schema.php#Time_dimensions">
 * Pentaho Time Dimensions</a>
 * 
 * @author diego
 * 
 */
@Entity
@Table(name = "D_DATE_HIERARCHY")
@NamedQuery(name = "DateHierarchyBean.all", query = "SELECT A FROM DateHierarchyBean A")
public class DateHierarchyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date time;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAndTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar calendar;
	private int year;
	private int month;
	private int quarter;
	private int weekOfMonth;
	private int dayOfMonth;
	@Column(name = "month_name")
	private String monthName;
	@Column(name = "day_name")
	private String dayName;
	@Column(name = "quarter_name")
	private String quarterName;
	

	DateHierarchyBean() {
		// ORM :(
	}

	/**
	 * Creates an instance
	 * 
	 * @param milliseconds
	 */
	public DateHierarchyBean(long millisecs) {
		String[] monthName = {"January", "February",
				  "March", "April", "May", "June", "July",
				  "August", "September", "October", "November",
				  "December"
				  };
		this.id = new Long(millisecs);
		this.date = new Date(millisecs);
		this.time = new Date(millisecs);
		this.dateAndTime = new Date(millisecs);
		this.calendar = new GregorianCalendar();
		this.calendar.setTimeInMillis(millisecs);
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.quarter = (this.month / 3) + 1;
		this.weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		this.monthName = monthName[calendar.get(Calendar.MONTH)];
		this.quarterName = "Q" + this.quarter;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Date getTime() {
		return time;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getQuarter() {
		return quarter;
	}

	public int getWeekOfMonth() {
		return weekOfMonth;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public String getQuarterName() {
		return quarterName;
	}

	public String getMonthName() {
		return monthName;
	}

	public String getDayName() {
		return dayName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DateHierarchyBean other = (DateHierarchyBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

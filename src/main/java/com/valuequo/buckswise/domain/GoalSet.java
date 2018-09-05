	package com.valuequo.buckswise.domain;
	import java.io.Serializable;
	import java.sql.Date;
	import java.util.Objects;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import org.hibernate.annotations.Cache;
	import org.hibernate.annotations.CacheConcurrencyStrategy;

	@Entity
	@Table(name = "goalset")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public class GoalSet implements Serializable {
		
		private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "uid")
	    private Long uid;
	    
		@Column(name = "goaltype")
	    private String goaltype;
	    
		@Column(name = "goalname")
	    private String goalname;
		
		@Column(name = "goalpriority")
	    private String goalpriority;

	    @Column(name = "yeartogoal")
	    private String yeartogoal;
	    
	    @Column(name = "presentcost")
	    private String presentcost;

	    @Column(name = "futurecost")
	    private String futurecost;

	    
		@Column(name = "requiremonthinvest")
	    private String requiremonthinvest;
	    
	    @Column(name = "fundshortage")
	    private String fundshortage;
	    
	    @Column(name = "creationdate")
	    private Date crationdate ;
	    
	    @Column(name = "goalnotes")
	    private String goalnotes;
	    
	    @Column(name = "linkassets")
	    private String linkassets;


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getUid() {
			return uid;
		}

		public void setUid(Long uid) {
			this.uid = uid;
		}
		public GoalSet goalset(Long uid) {
	        this.uid = uid;
	        return this;
	    }

		public String getGoaltype() {
			return goaltype;
		}

		public void setGoaltype(String goaltype) {
			this.goaltype = goaltype;
		}
		public GoalSet goaltype(String goaltype) {
	        this.goaltype = goaltype;
	        return this;
	    }

		public String getGoalname() {
			return goalname;
		}

		public void setGoalname(String goalname) {
			this.goalname = goalname;
		}
		public GoalSet goalname(String goalname) {
	        this.goalname = goalname;
	        return this;
	    }

		public String getGoalpriority() {
			return goalpriority;
		}

		public void setGoalpriority(String goalpriority) {
			this.goalpriority = goalpriority;
		}
		public GoalSet goalpriority(String goalpriority) {
	        this.goalpriority = goalpriority;
	        return this;
	    }

		public String getYeartogoal() {
			return yeartogoal;
		}

		public void setYeartogoal(String yeartogoal) {
			this.yeartogoal = yeartogoal;
		}
		public GoalSet yeartogoal(String yeartogoal) {
	        this.yeartogoal = yeartogoal;
	        return this;
	    }

		public String getPresentcost() {
			return presentcost;
		}

		public void setPresentcost(String presentcost) {
			this.presentcost = presentcost;
		}
		public GoalSet presentcost(String presentcost) {
	        this.presentcost = presentcost;
	        return this;
	    }

		public String getFuturecost() {
			return futurecost;
		}

		public void setFuturecost(String futurecost) {
			this.futurecost = futurecost;
		}
		public GoalSet futurecost(String futurecost) {
	        this.futurecost = futurecost;
	        return this;
	    }

		public String getRequiremonthinvest() {
			return requiremonthinvest;
		}

		public void setRequiremonthinvest(String requiremonthinvest) {
			this.requiremonthinvest = requiremonthinvest;
		}
		public GoalSet requiremonthinvest(String requiremonthinvest) {
	        this.requiremonthinvest = requiremonthinvest;
	        return this;
	    }

		public String getFundshortage() {
			return fundshortage;
		}

		public void setFundshortage(String fundshortage) {
			this.fundshortage = fundshortage;
		}
		public GoalSet fundshortage(String fundshortage) {
	        this.fundshortage = fundshortage;
	        return this;
	    }

		public Date getCrationdate() {
			return crationdate;
		}

		public void setCrationdate(Date crationdate) {
			this.crationdate = crationdate;
		}
		public GoalSet crationdate(Date crationdate) {
	        this.crationdate = crationdate;
	        return this;
	    }

		public String getGoalNotes() {
			return goalnotes;
		}

		public void setGoalNotes(String goalnotes) {
			this.goalnotes = goalnotes;
		}
		public GoalSet goalnotes(String goalnotes) {
	        this.goalnotes = goalnotes;
	        return this;
	    }
		public String getLinkassets() {
			return linkassets;
		}

		public void setLinkassets(String linkassets) {
			this.linkassets = linkassets;
		}
		public GoalSet linkassets(String linkassets) {
	        this.linkassets = linkassets;
	        return this;
	    }
		@Override
		public String toString() {
			return "GoalSet [id=" + id + ", uid=" + uid + ", goaltype=" + goaltype + ", goalname=" + goalname
					+ ", goalpriority=" + goalpriority + ", yeartogoal=" + yeartogoal + ", presentcost=" + presentcost
					+ ", futurecost=" + futurecost + ", requiremonthinvest=" + requiremonthinvest + ", fundshortage="
					+ fundshortage + ", crationdate=" + crationdate + ", goalnotes=" + goalnotes + ", linkassets="
					+ linkassets + "]";
		}
}

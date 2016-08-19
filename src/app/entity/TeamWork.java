package app.entity;
// default package



/**
 * TeamWork entity. 
 * 车间工人组
 * @author MyEclipse Persistence Tools
 */

public class TeamWork  implements java.io.Serializable {


    // Fields    
     private Integer id;
     private Tempolyee leader=new Tempolyee();
     private String teamName;

    // Constructors

    public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/** default constructor */
    public TeamWork() {
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

	public Tempolyee getLeader() {
		return leader;
	}

	public void setLeader(Tempolyee leader) {
		this.leader = leader;
	}


}
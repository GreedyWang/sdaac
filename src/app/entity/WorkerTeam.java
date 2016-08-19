package app.entity;
// default package



/**
 * WorkerTeam entity. @author MyEclipse Persistence Tools
 */

public class WorkerTeam  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Tempolyee worker=new Tempolyee();;
     private TeamWork team=new TeamWork();


    // Constructors

    /** default constructor */
    public WorkerTeam() {
    }
    
    public WorkerTeam(int teamID,String workerId) {
    	this.team.setId(teamID);
    	worker.setUid(workerId);
    }
    
   
    // Property accessors

    public WorkerTeam(Tempolyee worker, TeamWork team) {
		super();
		this.worker = worker;
		this.team = team;
	}

	public Tempolyee getWorker() {
		return worker;
	}

	public void setWorker(Tempolyee worker) {
		this.worker = worker;
	}

	public TeamWork getTeam() {
		return team;
	}

	public void setTeam(TeamWork team) {
		this.team = team;
	}

	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

 








}
package common.util;
public abstract class TxtWrite {
	
	protected String fileName;
	
	
	public TxtWrite( String fileName) {
		super();
	
		this.fileName = fileName;
	}


	public abstract void write();

}

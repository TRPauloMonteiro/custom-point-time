package tr.customteam.point_time.viewmanager;

public class LoadViewException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4162137767068857065L;

	public LoadViewException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}

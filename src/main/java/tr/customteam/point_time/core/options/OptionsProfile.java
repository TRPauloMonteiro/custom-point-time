package tr.customteam.point_time.core.options;

public class OptionsProfile implements IOptions {
	private String folhaCertaUsername;
	private String folhaCertaPassWord;
	private boolean folhaCertaActiveReminder;
	private String folhaCertaStartTime;
	private String folhaCertaLunchtTime;
	private String folhaCertaLeaveTime;
	private String folhaCertaMinutesToReminder;
	private boolean folhaCertaActiveSummary;
	
	private String jiraUsername;
	private String jiraPassWord;
	private boolean jiraActiveReminder;
	private String jiraReminderType;
	private boolean jiraActiveSummary;
	
	private String summaryEmail;
	private String summaryEmailPass;
	private boolean jiraModule;
	private boolean folhaCertaModule;
	
	public String getFolhaCertaUsername() {
		return folhaCertaUsername;
	}
	public void setFolhaCertaUsername(String folhaCertaUsername) {
		this.folhaCertaUsername = folhaCertaUsername;
	}
	public String getFolhaCertaPassWord() {
		return folhaCertaPassWord;
	}
	public void setFolhaCertaPassWord(String folhaCertaPassWord) {
		this.folhaCertaPassWord = folhaCertaPassWord;
	}
	public boolean isFolhaCertaActiveReminder() {
		return folhaCertaActiveReminder;
	}
	public void setFolhaCertaActiveReminder(boolean folhaCertaActiveReminder) {
		this.folhaCertaActiveReminder = folhaCertaActiveReminder;
	}
	public String getFolhaCertaStartTime() {
		return folhaCertaStartTime;
	}
	public void setFolhaCertaStartTime(String folhaCertaStartTime) {
		this.folhaCertaStartTime = folhaCertaStartTime;
	}
	public String getFolhaCertaLunchtTime() {
		return folhaCertaLunchtTime;
	}
	public void setFolhaCertaLunchtTime(String folhaCertaLunchtTime) {
		this.folhaCertaLunchtTime = folhaCertaLunchtTime;
	}
	public String getFolhaCertaLeaveTime() {
		return folhaCertaLeaveTime;
	}
	public void setFolhaCertaLeaveTime(String folhaCertaLeaveTime) {
		this.folhaCertaLeaveTime = folhaCertaLeaveTime;
	}
	public String getFolhaCertaMinutesToReminder() {
		return folhaCertaMinutesToReminder;
	}
	public void setFolhaCertaMinutesToReminder(String folhaCertaMinutesToReminder) {
		this.folhaCertaMinutesToReminder = folhaCertaMinutesToReminder;
	}
	public boolean isFolhaCertaActiveSummary() {
		return folhaCertaActiveSummary;
	}
	public void setFolhaCertaActiveSummary(boolean folhaCertaActiveSummary) {
		this.folhaCertaActiveSummary = folhaCertaActiveSummary;
	}
	public String getJiraUsername() {
		return jiraUsername;
	}
	public void setJiraUsername(String jiraUsername) {
		this.jiraUsername = jiraUsername;
	}
	public String getJiraPassWord() {
		return jiraPassWord;
	}
	public void setJiraPassWord(String jiraPassWord) {
		this.jiraPassWord = jiraPassWord;
	}
	public boolean isJiraActiveReminder() {
		return jiraActiveReminder;
	}
	public void setJiraActiveReminder(boolean jiraActiveReminder) {
		this.jiraActiveReminder = jiraActiveReminder;
	}
	public String getJiraReminderType() {
		return jiraReminderType;
	}
	public void setJiraReminderType(String jiraReminderType) {
		this.jiraReminderType = jiraReminderType;
	}
	public boolean isJiraActiveSummary() {
		return jiraActiveSummary;
	}
	public void setJiraActiveSummary(boolean jiraActiveSummary) {
		this.jiraActiveSummary = jiraActiveSummary;
	}
	public String getSummaryEmail() {
		return summaryEmail;
	}
	public void setSummaryEmail(String summaryEmail) {
		this.summaryEmail = summaryEmail;
	}
	public String getSummaryEmailPass() {
		return summaryEmailPass;
	}
	public void setSummaryEmailPass(String summaryEmailPass) {
		this.summaryEmailPass = summaryEmailPass;
	}
	public boolean isJiraModule() {
		return jiraModule;
	}
	public void setJiraModule(boolean jiraModule) {
		this.jiraModule = jiraModule;
	}
	public boolean isFolhaCertaModule() {
		return folhaCertaModule;
	}
	public void setFolhaCertaModule(boolean folhaCertaModule) {
		this.folhaCertaModule = folhaCertaModule;
	}
	
}

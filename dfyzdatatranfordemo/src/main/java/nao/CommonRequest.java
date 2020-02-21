package nao;

import java.time.LocalDateTime;

public class CommonRequest {
    private String plateNumber;
    private String terminalPhone;
    private int warningFlagField;
    private int statusField;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public CommonRequest(){
        super();
    }
    public CommonRequest(String plateNumber, String terminalPhone, int warningFlagField, int statusField, LocalDateTime startTime, LocalDateTime endTime) {
        this.plateNumber = plateNumber;
        this.terminalPhone = terminalPhone;
        this.warningFlagField = warningFlagField;
        this.statusField = statusField;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getTerminalPhone() {
        return terminalPhone;
    }

    public void setTerminalPhone(String terminalPhone) {
        this.terminalPhone = terminalPhone;
    }

    public int getWarningFlagField() {
        return warningFlagField;
    }

    public void setWarningFlagField(int warningFlagField) {
        this.warningFlagField = warningFlagField;
    }

    public int getStatusField() {
        return statusField;
    }

    public void setStatusField(int statusField) {
        this.statusField = statusField;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "CommonRequest{" +
                "plateNumber='" + plateNumber + '\'' +
                ", terminalPhone='" + terminalPhone + '\'' +
                ", warningFlagField=" + warningFlagField +
                ", statusField=" + statusField +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

package nao;


import java.io.Serializable;
import java.util.Date;

public class LocationInfoMsg implements Serializable {
	private static final long serialVersionUID = 1L;

    // 车牌号
	private String PlateNumber;

    // 终端手机号
    private String TerminalPhone;
    // 流水号
    private int FlowId;

//    public String getTerminalPhone() {
//        return terminalPhone;
//    }
//    public void setTerminalPhone(String terminalPhone) {
//        this.terminalPhone = terminalPhone;
//    }
//    public int getFlowId() {
//        return flowId;
//    }
//    public void setFlowId(int flowId) {
//        this.flowId = flowId;
//    }
//
//    public String getPlateNumber() {
//        return plateNumber;
//    }
//
//    public void setPlateNumber(String plateNumber) {
//        this.plateNumber = plateNumber;
//    }

    // 告警信息
    // byte[0-3]
    private int WarningFlagField;
    // byte[4-7] 状态(DWORD(32))
    private int StatusField;
    // byte[8-11] 纬度(DWORD(32))
    private float Latitude;//22.587267
    // byte[12-15] 经度(DWORD(32))
    private float Longitude;//113.963936
    // byte[16-17] 高程(WORD(16)) 海拔高度，单位为米（ m）
    // TODO ==>int?海拔
    private int Elevation;
    // byte[18-19] 速度(WORD) 1/10km/h
    // TODO ==>float?速度
    private float Speed;
    // byte[20-21] 方向(WORD) 0-359，正北为 0，顺时针
    private int Direction;
    // byte[22-x] 时间(BCD[6]) YY-MM-DD-hh-mm-ss
    // GMT+8 时间，本标准中之后涉及的时间均采用此时区
    private Date Time;
    //byte 附加信息ID
    private int AttachId;
    //byte 附加信息长度	BYTE
    private int AttachLength;

    //上传内容：设备注册是为公钥，否则为业务数据
    private String Content;

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public float getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(float latitude) {
//        this.latitude = latitude;
//    }
//
//    public float getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(float longitude) {
//        this.longitude = longitude;
//    }
//
//    public int getElevation() {
//        return elevation;
//    }
//
//    public void setElevation(int elevation) {
//        this.elevation = elevation;
//    }
//
//    public float getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(float speed) {
//        this.speed = speed;
//    }
//
//    public int getDirection() {
//        return direction;
//    }
//
//    public void setDirection(int direction) {
//        this.direction = direction;
//    }
//
//    public Date getTime() {
//        return time;
//    }
//
//    public void setTime(Date time) {
//        this.time = time;
//    }
//
//    public int getWarningFlagField() {
//        return warningFlagField;
//    }
//
//    public void setWarningFlagField(int warningFlagField) {
//        this.warningFlagField = warningFlagField;
//    }
//
//    public int getStatusField() {
//        return statusField;
//    }
//
//    public void setStatusField(int statusField) {
//        this.statusField = statusField;
//    }
//
//    public int getAttachId() {
//        return attachId;
//    }
//
//    public void setAttachId(int attachId) {
//        this.attachId = attachId;
//    }
//
//    public int getAttachLength() {
//        return attachLength;
//    }
//
//    public void setAttachLength(int attachLength) {
//        this.attachLength = attachLength;
//    }
//
//    @Override
//    public String toString() {
//        return "LocationInfoUploadMsg [warningFlagField=" + warningFlagField + ", statusField=" + statusField
//                + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + ", speed="
//                + speed + ", direction=" + direction + ", time=" + time +  ", attachId=" + attachId +", attachLength=" + attachLength+ "]";
//    }


    @Override
    public String toString() {
        return "LocationInfoMsg{" +
                "PlateNumber='" + PlateNumber + '\'' +
                ", TerminalPhone='" + TerminalPhone + '\'' +
                ", FlowId=" + FlowId +
                ", WarningFlagField=" + WarningFlagField +
                ", StatusField=" + StatusField +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Elevation=" + Elevation +
                ", Speed=" + Speed +
                ", Direction=" + Direction +
                ", Time=" + Time +
                ", AttachId=" + AttachId +
                ", AttachLength=" + AttachLength +
                ", Content='" + Content + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
    }

    public String getTerminalPhone() {
        return TerminalPhone;
    }

    public void setTerminalPhone(String terminalPhone) {
        TerminalPhone = terminalPhone;
    }

    public int getFlowId() {
        return FlowId;
    }

    public void setFlowId(int flowId) {
        FlowId = flowId;
    }

    public int getWarningFlagField() {
        return WarningFlagField;
    }

    public void setWarningFlagField(int warningFlagField) {
        WarningFlagField = warningFlagField;
    }

    public int getStatusField() {
        return StatusField;
    }

    public void setStatusField(int statusField) {
        StatusField = statusField;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public int getElevation() {
        return Elevation;
    }

    public void setElevation(int elevation) {
        Elevation = elevation;
    }

    public float getSpeed() {
        return Speed;
    }

    public void setSpeed(float speed) {
        Speed = speed;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int direction) {
        Direction = direction;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public int getAttachId() {
        return AttachId;
    }

    public void setAttachId(int attachId) {
        AttachId = attachId;
    }

    public int getAttachLength() {
        return AttachLength;
    }

    public void setAttachLength(int attachLength) {
        AttachLength = attachLength;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

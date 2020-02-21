package com.dfyz.provied.yiliutochain.po;


import java.io.Serializable;
import java.util.Date;

public class LocationInfoMsg implements Serializable {
	private static final long serialVersionUID = 1L;

    // 车牌号
	private String plateNumber;

    // 终端手机号
    private String terminalPhone;
    // 流水号
    private int flowId;

    public String getTerminalPhone() {
        return terminalPhone;
    }
    public void setTerminalPhone(String terminalPhone) {
        this.terminalPhone = terminalPhone;
    }
    public int getFlowId() {
        return flowId;
    }
    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    // 告警信息
    // byte[0-3]
    private int warningFlagField;
    // byte[4-7] 状态(DWORD(32))
    private int statusField;
    // byte[8-11] 纬度(DWORD(32))
    private float latitude;//22.587267
    // byte[12-15] 经度(DWORD(32))
    private float longitude;//113.963936
    // byte[16-17] 高程(WORD(16)) 海拔高度，单位为米（ m）
    // TODO ==>int?海拔
    private int elevation;
    // byte[18-19] 速度(WORD) 1/10km/h
    // TODO ==>float?速度
    private float speed;
    // byte[20-21] 方向(WORD) 0-359，正北为 0，顺时针
    private int direction;
    // byte[22-x] 时间(BCD[6]) YY-MM-DD-hh-mm-ss
    // GMT+8 时间，本标准中之后涉及的时间均采用此时区
    private Date time;
    //byte 附加信息ID
    private int attachId;
    //byte 附加信息长度	BYTE
    private int attachLength;

    //上传内容：设备注册是为公钥，否则为业务数据
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public int getAttachId() {
        return attachId;
    }

    public void setAttachId(int attachId) {
        this.attachId = attachId;
    }

    public int getAttachLength() {
        return attachLength;
    }

    public void setAttachLength(int attachLength) {
        this.attachLength = attachLength;
    }

    @Override
    public String toString() {
        return "LocationInfoUploadMsg [warningFlagField=" + warningFlagField + ", statusField=" + statusField
                + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + ", speed="
                + speed + ", direction=" + direction + ", time=" + time +  ", attachId=" + attachId +", attachLength=" + attachLength+ "]";
    }


}

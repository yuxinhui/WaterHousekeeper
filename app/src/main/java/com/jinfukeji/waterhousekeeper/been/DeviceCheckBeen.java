package com.jinfukeji.waterhousekeeper.been;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"15:06"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:设备检测接口实例
 */

public class DeviceCheckBeen {
    /**
     * message : {"id":"e2cea4d1-b1a0-44fe-bf95-38f1fe8b644d","serialNumber":"589630","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494914744000}
     * iTotalDisplayRecords : 7
     * status : ok
     * iTotalRecords : 7
     * aaData : [{"id":"21d6cb27-1e2c-49d1-9eac-49fe11027446","serialNumber":"520268","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494816115000},{"id":"3341c6c5-2e6d-479e-8b50-23ad865507eb","serialNumber":"21341","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494816939000},{"id":"781aead2-61a6-4d3e-a291-8bd677a1b278","serialNumber":"7809","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494812922000},{"id":"9ea92dec-162f-4d00-bd17-02cb47a0a02d","serialNumber":"89987","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494812949000},{"id":"c0a791c0-d83b-4d47-99e9-4166780b8620","serialNumber":"12365","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494820592000},{"id":"c2cdf7c3-5916-4c40-9444-51cbd7b63994","serialNumber":"1111","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494815309000},{"id":"c4b34252-0470-4215-abf3-4fb13667f330","serialNumber":"123456","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1494819714000}]
     * sEcho : 1
     */

    private MessageBean message;
    private int iTotalDisplayRecords;
    private String status;
    private int iTotalRecords;
    private String sEcho;
    private List<AaDataBean> aaData;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getSEcho() {
        return sEcho;
    }

    public void setSEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class MessageBean {
        /**
         * id : e2cea4d1-b1a0-44fe-bf95-38f1fe8b644d
         * serialNumber : 589630
         * deviceStatus : 3
         * filterStatus1 : 100%
         * filterStatus2 : 100%
         * filterStatus3 : 100%
         * filterStatus4 : 100%
         * filterStatus5 : 100%
         * checkDate : 1494914744000
         */

        private String id;
        private String serialNumber;
        private String deviceStatus;
        private String filterStatus1;
        private String filterStatus2;
        private String filterStatus3;
        private String filterStatus4;
        private String filterStatus5;
        private long checkDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(String deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public String getFilterStatus1() {
            return filterStatus1;
        }

        public void setFilterStatus1(String filterStatus1) {
            this.filterStatus1 = filterStatus1;
        }

        public String getFilterStatus2() {
            return filterStatus2;
        }

        public void setFilterStatus2(String filterStatus2) {
            this.filterStatus2 = filterStatus2;
        }

        public String getFilterStatus3() {
            return filterStatus3;
        }

        public void setFilterStatus3(String filterStatus3) {
            this.filterStatus3 = filterStatus3;
        }

        public String getFilterStatus4() {
            return filterStatus4;
        }

        public void setFilterStatus4(String filterStatus4) {
            this.filterStatus4 = filterStatus4;
        }

        public String getFilterStatus5() {
            return filterStatus5;
        }

        public void setFilterStatus5(String filterStatus5) {
            this.filterStatus5 = filterStatus5;
        }

        public long getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(long checkDate) {
            this.checkDate = checkDate;
        }
    }

    public static class AaDataBean {
        /**
         * id : 21d6cb27-1e2c-49d1-9eac-49fe11027446
         * serialNumber : 520268
         * deviceStatus : 3
         * filterStatus1 : 100%
         * filterStatus2 : 100%
         * filterStatus3 : 100%
         * filterStatus4 : 100%
         * filterStatus5 : 100%
         * checkDate : 1494816115000
         */

        private String id;
        private String serialNumber;
        private String deviceStatus;
        private String filterStatus1;
        private String filterStatus2;
        private String filterStatus3;
        private String filterStatus4;
        private String filterStatus5;
        private long checkDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public String getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(String deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public String getFilterStatus1() {
            return filterStatus1;
        }

        public void setFilterStatus1(String filterStatus1) {
            this.filterStatus1 = filterStatus1;
        }

        public String getFilterStatus2() {
            return filterStatus2;
        }

        public void setFilterStatus2(String filterStatus2) {
            this.filterStatus2 = filterStatus2;
        }

        public String getFilterStatus3() {
            return filterStatus3;
        }

        public void setFilterStatus3(String filterStatus3) {
            this.filterStatus3 = filterStatus3;
        }

        public String getFilterStatus4() {
            return filterStatus4;
        }

        public void setFilterStatus4(String filterStatus4) {
            this.filterStatus4 = filterStatus4;
        }

        public String getFilterStatus5() {
            return filterStatus5;
        }

        public void setFilterStatus5(String filterStatus5) {
            this.filterStatus5 = filterStatus5;
        }

        public long getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(long checkDate) {
            this.checkDate = checkDate;
        }
    }
}

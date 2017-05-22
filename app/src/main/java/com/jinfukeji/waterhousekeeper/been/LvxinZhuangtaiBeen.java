package com.jinfukeji.waterhousekeeper.been;

/**
 * Created by "于志渊"
 * 时间:"10:14"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:滤芯状态实例
 */

public class LvxinZhuangtaiBeen {
    /**
     * message : {"id":"9aefda0c-bb6f-4069-9e12-618a7fd379e0","serialNumber":"4444","deviceStatus":"3","filterStatus1":"100%","filterStatus2":"100%","filterStatus3":"100%","filterStatus4":"100%","filterStatus5":"100%","checkDate":1495163679000}
     * status : ok
     */

    private MessageBean message;
    private String status;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MessageBean {
        /**
         * id : 9aefda0c-bb6f-4069-9e12-618a7fd379e0
         * serialNumber : 4444
         * deviceStatus : 3
         * filterStatus1 : 100%
         * filterStatus2 : 100%
         * filterStatus3 : 100%
         * filterStatus4 : 100%
         * filterStatus5 : 100%
         * checkDate : 1495163679000
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

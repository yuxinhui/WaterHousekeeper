package com.jinfukeji.waterhousekeeper.been;

/**
 * Created by "于志渊"
 * 时间:"9:26"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:账单记录实例
 */

public class ZhangdanJiluBeen {
    /**
     * message : {"id":"7a6971d6-8abf-4711-a4f5-8d73b986a5c7","serialNumber":"123","orderNo":"TNO2017041814520695600000001","money":1,"rechargeDate":1492498218000,"payway":"0"}
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
         * id : 7a6971d6-8abf-4711-a4f5-8d73b986a5c7
         * serialNumber : 123
         * orderNo : TNO2017041814520695600000001
         * money : 1
         * rechargeDate : 1492498218000
         * payway : 0
         */

        private String id;
        private String serialNumber;
        private String orderNo;
        private int money;
        private long rechargeDate;
        private String payway;

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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public long getRechargeDate() {
            return rechargeDate;
        }

        public void setRechargeDate(long rechargeDate) {
            this.rechargeDate = rechargeDate;
        }

        public String getPayway() {
            return payway;
        }

        public void setPayway(String payway) {
            this.payway = payway;
        }
    }

    /**
     * message : null
     * status : fail
     */

    /*private Object message;
    private String status;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

}

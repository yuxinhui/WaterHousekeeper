package com.jinfukeji.waterhousekeeper.been;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"9:26"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:账单记录实例
 */

public class ZhangdanJiluBeen {
    /**
     * message : [{"id":"28653dad-7836-47be-9f43-bb66b73c5459","serialNumber":"25860","orderNo":"TNO2017051910185829500000001","money":0.01,"rechargeDate":1495161602000,"payway":"0"},{"id":"75730f77-be0e-4a78-a68c-e44584566458","serialNumber":"25860","orderNo":"TNO2017051910191431000000001","money":0.01,"rechargeDate":1495161602000,"payway":"0"},{"id":"c212e3bf-433f-4674-b0ff-4460c2757bdb","serialNumber":"25860","orderNo":"TNO2017051910400218500000001","money":0.01,"rechargeDate":1495161602000,"payway":"0"}]
     * status : ok
     */

    private String status;
    private List<MessageBean> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * id : 28653dad-7836-47be-9f43-bb66b73c5459
         * serialNumber : 25860
         * orderNo : TNO2017051910185829500000001
         * money : 0.01
         * rechargeDate : 1495161602000
         * payway : 0
         */

        private String id;
        private String serialNumber;
        private String orderNo;
        private double money;
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

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
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
}

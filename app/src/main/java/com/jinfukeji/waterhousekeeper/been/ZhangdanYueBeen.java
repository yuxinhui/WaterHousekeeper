package com.jinfukeji.waterhousekeeper.been;

/**
 * Created by "于志渊"
 * 时间:"16:31"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:账单余额实例
 */

public class ZhangdanYueBeen {
    /**
     * message : {"id":"69d680be-14dd-42bf-b61d-db211d317428","serialNumber":"4444","password":"123432","balance":0}
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
         * id : 69d680be-14dd-42bf-b61d-db211d317428
         * serialNumber : 4444
         * password : 123432
         * balance : 0
         */

        private String id;
        private String serialNumber;
        private String password;
        private int balance;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}

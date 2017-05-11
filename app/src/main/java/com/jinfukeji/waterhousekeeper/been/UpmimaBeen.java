package com.jinfukeji.waterhousekeeper.been;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:31"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:修改密码实例
 */

public class UpmimaBeen {
    /**
     * iTotalDisplayRecords : 14
     * message : 修改成功成功！
     * status : ok
     * iTotalRecords : 14
     * aaData : [{"id":"00af9bf4-29ad-4e71-8bc8-d03af46d563e","serialNumber":"99","password":"111111","balance":0},{"id":"1bfef04a-07fa-4ec9-bdfa-07815358bf4d","serialNumber":"123","password":"123","balance":0},{"id":"24e07e77-e791-4da4-8082-41461e38b44b","serialNumber":"36","password":"232323","balance":0},{"id":"26b61c34-17a2-4f8b-aad5-27a1e08e3b43","serialNumber":"1314","password":"000000","balance":0},{"id":"47b471d5-2492-4e0a-8889-5d4ad415012e","serialNumber":"6","password":"123456","balance":0},{"id":"5ff93a1b-6f33-44bb-afa7-f445c58e0f57","serialNumber":"66","password":"123456","balance":0},{"id":"6a9d6595-87ce-4540-ac98-a6a0b5899871","serialNumber":"11111","password":"12345","balance":0},{"id":"6c11f5ed-e179-4bca-992d-98fffbf91825","serialNumber":"2625","password":"111111","balance":0},{"id":"7a11f3e8-f60a-46a2-86b0-50d3a79aa9e5","serialNumber":"1234","password":"12","balance":0},{"id":"970afcd3-b4af-4937-be2c-eb35578323d5","serialNumber":"00","password":"000000","balance":0}]
     * sEcho : 1
     */

    private int iTotalDisplayRecords;
    private String message;
    private String status;
    private int iTotalRecords;
    private String sEcho;
    private List<AaDataBean> aaData;

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public static class AaDataBean {
        /**
         * id : 00af9bf4-29ad-4e71-8bc8-d03af46d563e
         * serialNumber : 99
         * password : 111111
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

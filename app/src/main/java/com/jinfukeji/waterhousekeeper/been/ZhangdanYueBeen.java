package com.jinfukeji.waterhousekeeper.been;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"16:31"
 * 包名:com.jinfukeji.waterhousekeeper.been
 * 描述:账单余额实例
 */

public class ZhangdanYueBeen {
    /**
     * iTotalDisplayRecords : 45
     * message : {"id":"797b91f1-6bd6-4772-89d2-f3f366ea25de","serialNumber":"236","password":"25698","balance":0}
     * status : ok
     * iTotalRecords : 45
     * aaData : [{"id":"00af9bf4-29ad-4e71-8bc8-d03af46d563e","serialNumber":"99","password":"111111","balance":0},{"id":"042e9853-9527-4b6d-8f13-3c9c367fc102","serialNumber":"369","password":"235235","balance":0},{"id":"078bd7cd-08ba-4925-b26f-927c21e217c1","serialNumber":"80","password":"66666","balance":0},{"id":"10bb7603-6f26-4f74-a011-b94ea92821f1","serialNumber":"33441","password":"2","balance":0},{"id":"15a15cc3-3b1b-4482-b246-c516fa529013","serialNumber":"9999","password":"88888888","balance":0},{"id":"1bfef04a-07fa-4ec9-bdfa-07815358bf4d","serialNumber":"123","password":"123","balance":0},{"id":"211b806a-472b-4f70-82da-9d8c07110080","serialNumber":"365896","password":"36985","balance":0},{"id":"24e07e77-e791-4da4-8082-41461e38b44b","serialNumber":"36","password":"232323","balance":0},{"id":"26b61c34-17a2-4f8b-aad5-27a1e08e3b43","serialNumber":"1314","password":"000000","balance":0},{"id":"308d05a0-925c-4a6d-9a76-ad2e2761f71d","serialNumber":"26365","password":"369852","balance":0}]
     * sEcho : 1
     */

    private int iTotalDisplayRecords;
    private MessageBean message;
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
         * id : 797b91f1-6bd6-4772-89d2-f3f366ea25de
         * serialNumber : 236
         * password : 25698
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

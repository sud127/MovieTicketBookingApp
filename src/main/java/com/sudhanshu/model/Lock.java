package com.sudhanshu.model;

import java.util.Date;

public class Lock {

        private Seat seat;
        private Show show;
        private int timeoutInSeconds;
        private Date lockTime;
        private String user;

        public Lock(Seat seat, Show show, int timeoutInSeconds, Date lockTime, String user) {
            this.seat = seat;
            this.show = show;
            this.timeoutInSeconds = timeoutInSeconds;
            this.lockTime = lockTime;
            this.user = user;
        }

        public Seat getSeat() {
            return seat;
        }

        public Show getShow() {
            return show;
        }

        public Integer getTimeoutInSeconds() {
            return timeoutInSeconds;
        }

        public Date getLockTime() {
            return lockTime;
        }

        public String getLockedByUser() {
            return user;
        }

    }

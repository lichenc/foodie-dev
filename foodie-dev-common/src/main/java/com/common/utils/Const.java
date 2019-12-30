package com.common.utils;

public class Const {

    public enum Sex{
        MALE(1,"男"),
        FEMALE(0,"女"),
        SECRET(2,"保密");

        private String type;
        private int code;

        Sex(int code,String type){
            this.type = type;
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public enum Show{
        YES(1,"是"),
        NO(0,"否");

        private String type;
        private int code;

        Show(int code,String type){
            this.type = type;
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}

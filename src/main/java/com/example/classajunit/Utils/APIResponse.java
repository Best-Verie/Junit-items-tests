package com.example.classajunit.Utils;

public class APIResponse {
        private boolean status;

        private String message;


        public APIResponse() {
            super();
        }
        public APIResponse(boolean status, String message) {
            super();
            this.status = status;
            this.message = message;
        }

    public APIResponse(String s) {
            this.message = s;
    }


    public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}

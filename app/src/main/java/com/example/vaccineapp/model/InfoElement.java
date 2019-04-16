package com.example.vaccineapp.model;

import com.example.vaccineapp.R;

public class InfoElement {

        private String information;
        private int alertLevel;

        public InfoElement(String information, int alertLevel){
            this.information = information;
            this.alertLevel = alertLevel;
        }

        public String getInformation() {
            return information;
        }

        public int getAlert() {
            return alertLevel;
        }

        public int getAlertResource(){
            if(this.alertLevel == 1){
                return R.drawable.check;
            }else if(this.alertLevel == -1){
                return R.drawable.warning_yellow;
            }else if(this.alertLevel == -2){
                return R.drawable.warning_red;
            }else{
                return R.drawable.warning_yellow;
            }
        }

        public void checkInfo(){
            this.alertLevel = 1;
        }
}

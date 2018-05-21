package com.pi.bidamla.helper;

/**
 * Created by omral on 18.04.2018.
 */

public class Enums {

    public enum MessageType {
        SUCCESS,ERROR,INFO
    }

    public enum BloodGroup {
        AP, AN, BP, BN, ABP, ABN, P0, N0;

        @Override
        public String toString() {
            switch (this) {
                case AP:
                    return "A Rh+";
                case AN:
                    return "A Rh-";
                case BP:
                    return "B Rh+";
                case BN:
                    return "B Rh-";
                case ABP:
                    return "AB Rh+";
                case ABN:
                    return "AB Rh-";
                case P0:
                    return "0 Rh+";
                case N0:
                    return "0 Rh-";
                default:
                    return "";
            }
        }

        public String toEnum() {
            switch (this) {
                case AP:
                    return "A+";
                case AN:
                    return "A-";
                case BP:
                    return "B+";
                case BN:
                    return "B-";
                case ABP:
                    return "AB+";
                case ABN:
                    return "AB-";
                case P0:
                    return "0+";
                case N0:
                    return "0-";
                default:
                    return "";
            }
        }
    }
    
}

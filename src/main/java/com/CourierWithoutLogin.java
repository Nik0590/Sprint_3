package com;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierWithoutLogin {

        private String firstName;
        private String password;



        public CourierWithoutLogin(String firstName, String password){
            this.firstName = firstName;
            this.password = password;
        }
}

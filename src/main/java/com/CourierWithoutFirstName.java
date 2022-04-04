package com;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierWithoutFirstName {

        private String password;
        private String login;



        public CourierWithoutFirstName(String password, String login){
            this.password = password;
            this.login = login;
        }
}

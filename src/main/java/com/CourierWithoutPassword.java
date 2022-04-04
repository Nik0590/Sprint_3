package com;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierWithoutPassword {

        private String firstName;
        private String login;



        public CourierWithoutPassword(String firstName, String login){
            this.firstName = firstName;
            this.login = login;
        }
}

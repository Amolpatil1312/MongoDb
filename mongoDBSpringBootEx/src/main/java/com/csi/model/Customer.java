package com.csi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {

    @Id
    private int custId;

    @Size(min = 4,message = "The Size of the name Must be atleast 4 chars")
    private String custName;

    private String custAddress;

    private double custActBalence;

    @Range(min = 1000000000l,max = 9999999999l,message = "Mobile Number should be Valid")
    private long custContactNumber;

    private long custUID;

    private String custPancard;

    @Email(message = "Email should be Valid")
    private String custEmailId;

    @Size(min = 3,message = "Password Should be Atleast 3 chars")
    private String custPassword;


}

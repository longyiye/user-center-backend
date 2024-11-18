package com.yiye.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReq implements Serializable {
    private static final long serialVersionUID = -6553292982583999789L;
    private String userAccount;
    private String userPassword;
}

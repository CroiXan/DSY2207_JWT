package com.grupo8.jwt.model;

public class UnassignRoleRequest {

    private Long role_id;

    public UnassignRoleRequest(Long role_id) {
        this.role_id = role_id;
    }

    public UnassignRoleRequest() {
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

}

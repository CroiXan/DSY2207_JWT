package com.grupo8.jwt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.jwt.client.RoleAPIClient;
import com.grupo8.jwt.model.Roles;
import com.grupo8.jwt.model.UnassignRoleRequest;
import com.grupo8.jwt.model.UserRoles;
import com.grupo8.jwt.service.AuditService;
import com.grupo8.jwt.util.ClaimsUtil;

@RestController
@RequestMapping("/api/grupo8/role")
public class RoleAPIController {

    private final RoleAPIClient roleAPIClient;
    private final AuditService auditService;
    private ClaimsUtil claimsUtil;

    @Value("${azure.function.role.key}")
    private String roleApiKey;

    public RoleAPIController(RoleAPIClient roleAPIClient, AuditService auditService) {
        this.roleAPIClient = roleAPIClient;
        this.auditService = auditService;
        claimsUtil = new ClaimsUtil();
    }

    @PostMapping("/createRole")
    public String createRole(@RequestBody Roles body){
        this.auditService.rolAuditInsert("CREATE","Crea Rol",0,this.claimsUtil.getClaimUserId());
        return this.roleAPIClient.createRole(body,roleApiKey);
    }

    @GetMapping("/getRole/{id}")
    public String getRole(@PathVariable("id") Long id){
        return this.roleAPIClient.getRole(id,roleApiKey);
    }

    @GetMapping("/getAllRoles")
    public String getAllRoles(){
        return this.roleAPIClient.getAllRoles(roleApiKey);
    }

    @PutMapping("/updateRole/{id}")
    public String updateRole(@PathVariable("id") Long id, @RequestBody Roles body){
        this.auditService.rolAuditInsert("UPDATE","Actualiza Rol",id.intValue(),this.claimsUtil.getClaimUserId());
        return this.roleAPIClient.updateRole(id, body,roleApiKey);
    }

    @DeleteMapping("/deleteRole/{id}")
    String deleteRole(@PathVariable("id") Long id){
        this.auditService.rolAuditInsert("DELETE","Borra Rol",id.intValue(),this.claimsUtil.getClaimUserId());
        return this.roleAPIClient.deleteRole(id,roleApiKey);
    }

    @PostMapping("/createUserRole")
    String createUserRole(@RequestBody UserRoles userRole){
        this.auditService.UserRolAuditInsert("ASSIGN", userRole.getRole_id().intValue(), userRole.getUser_id().intValue(), this.claimsUtil.getClaimUserId());
        return this.roleAPIClient.createUserRole(userRole,roleApiKey);
    }

    @GetMapping("/getUserRole/{id}")
    String getUserRole(@PathVariable("id") Long id){
        return this.roleAPIClient.getUserRole(id,roleApiKey);
    }

    @GetMapping("/getUserRoleByUser/{id}")
    String getUserRoleByUser(@PathVariable("id") Long id){
        return this.roleAPIClient.getUserRoleByUser(id,roleApiKey);
    }

    @GetMapping("/getUserRoleByRoleId/{id}")
    String getUserRoleByRoleId(@PathVariable("id") Long id){
        return this.roleAPIClient.getUserRoleByRoleId(id,roleApiKey);
    }
    
    @GetMapping("/getAllUserRoles")
    String getAllUserRoles(){
        return this.roleAPIClient.getAllUserRoles(roleApiKey);
    }
    
    @DeleteMapping("/deleteUserRole/{id}")
    String deleteUserRole(@PathVariable("id") Long id){
        this.auditService.UserRolAuditInsert("UNASSIGN", id.intValue(), 0, this.claimsUtil.getClaimUserId());
        String response = this.roleAPIClient.deleteUserRole(id,roleApiKey);
        if ("Rol eliminado".equals(response)) {
            this.roleAPIClient.UnassignRoleTrigger(new UnassignRoleRequest(id),roleApiKey);
        }
        return response;
    }

}

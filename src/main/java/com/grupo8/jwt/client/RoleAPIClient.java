package com.grupo8.jwt.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo8.jwt.model.Roles;
import com.grupo8.jwt.model.UnassignRoleRequest;
import com.grupo8.jwt.model.UserRoles;

@FeignClient(name = "userrole-service", url = "https://grupo8rolesfunctions.azurewebsites.net/api")
public interface RoleAPIClient {

    @PostMapping(value = "/createRole", consumes = "application/json")
    String createRole(@RequestBody Roles body, @RequestParam("code") String apiKey);

    @RequestMapping(method = RequestMethod.GET, value = "/getRole/{id}")
    String getRole(@PathVariable("id") Long id, @RequestParam("code") String apiKey);

    @RequestMapping(method = RequestMethod.GET, value = "/getAllRoles")
    String getAllRoles(@RequestParam("code") String apiKey);

    @PutMapping(value = "/updateRole/{id}", consumes = "application/json")
    String updateRole(@PathVariable("id") Long id, @RequestBody Roles body, @RequestParam("code") String apiKey);

    @DeleteMapping(value = "/deleteRole/{id}")
    String deleteRole(@PathVariable("id") Long id, @RequestParam("code") String apiKey);

    @PostMapping(value = "/createUserRole", consumes = "application/json")
    String createUserRole(@RequestBody UserRoles userRole, @RequestParam("code") String apiKey);

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRole/{id}")
    String getUserRole(@PathVariable("id") Long id, @RequestParam("code") String apiKey);

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRoleByUser/{id}")
    String getUserRoleByUser(@PathVariable("id") Long id, @RequestParam("code") String apiKey);

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRoleByRoleId/{id}")
    String getUserRoleByRoleId(@PathVariable("id") Long id, @RequestParam("code") String apiKey);
    
    @RequestMapping(method = RequestMethod.GET, value = "/getAllUserRoles")
    String getAllUserRoles(@RequestParam("code") String apiKey);
    
    @DeleteMapping(value = "/deleteUserRole/{id}")
    String deleteUserRole(@PathVariable("id") Long id, @RequestParam("code") String apiKey);

    @RequestMapping(method = RequestMethod.POST, value = "/unassignRoleTrigger")
    String UnassignRoleTrigger(@RequestBody UnassignRoleRequest unassignRoleRequest, @RequestParam("code") String apiKey);

    @GetMapping(value = "/user/{userId}/roles")
    String getRoleNameByUserID(@PathVariable("userId") Long userId, @RequestParam("code") String apiKey);
    
}

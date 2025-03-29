package com.grupo8.jwt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grupo8.jwt.model.Roles;
import com.grupo8.jwt.model.UserRoles;

@FeignClient(name = "userrole-service", url = "https://grupo8rolesmanagement.azurewebsites.net/api")
public interface RoleAPIClient {

    @PostMapping(value = "/createRole?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==", consumes = "application/json")
    String createRole(@RequestBody Roles body);

    @RequestMapping(method = RequestMethod.GET, value = "/getRole/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String getRole(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/getAllRoles?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String getAllRoles();

    @PutMapping(value = "/updateRole/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==", consumes = "application/json")
    String updateRole(@PathVariable("id") Long id, @RequestBody Roles body);

    @DeleteMapping(value = "/deleteRole/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String deleteRole(@PathVariable("id") Long id);

    @PostMapping(value = "/createUserRole?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==", consumes = "application/json")
    String createUserRole(@RequestBody UserRoles userRole);

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRole/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String getUserRole(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRoleByUser/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String getUserRoleByUser(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRoleByRoleId/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String getUserRoleByRoleId(@PathVariable("id") Long id);
    
    @RequestMapping(method = RequestMethod.GET, value = "/getAllUserRoles?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String getAllUserRoles();
    
    @DeleteMapping(value = "/deleteUserRole/{id}?code=GnveK0TEzZEW85ZQGpb2LOe2iPikoZz819qdE9ljqXjoAzFu2sDGlA==")
    String deleteUserRole(@PathVariable("id") Long id);
    
}

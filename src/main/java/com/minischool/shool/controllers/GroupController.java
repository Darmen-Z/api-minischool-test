package com.minischool.shool.controllers;


import com.minischool.shool.dto.GroupDto;
import com.minischool.shool.exceptions.ValidationExceptionsHandler;
import com.minischool.shool.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class GroupController{

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping("")
    public ResponseEntity<List<GroupDto>> getAllGroups(@RequestParam(name = "isOrder", defaultValue = "false") boolean isOrder) throws ValidationException {
        return new ResponseEntity<>(groupService.getAllGroups(isOrder), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GroupDto> addGroup(@Valid@RequestBody GroupDto groupDto){
        return new ResponseEntity<>(groupService.createGroup(groupDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable(name = "id") int groupId){
        return ResponseEntity.ok(groupService.getGroup(groupId));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable(name = "id") int groupId, @RequestBody GroupDto groupDto){
        return new ResponseEntity<>(groupService.updateGroup(groupId, groupDto), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}/delete")
//    public ResponseEntity<String> deleteGroup(@PathVariable(name = "id") int id){
//        groupService.deleteGroup(id);
//        groupService.getAllGroups(false);
//        return new ResponseEntity<>("Group deleted successfully.", HttpStatus.OK);
//    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<List<GroupDto>> deleteGroup(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(groupService.deleteGroupGetAll(id), HttpStatus.OK);
    }
}

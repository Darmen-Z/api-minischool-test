package com.minischool.shool.service;

import com.minischool.shool.dto.GroupDto;
import com.minischool.shool.models.GroupEntity;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups(boolean isOrder);

    GroupDto getGroup(int id);

    GroupDto createGroup(GroupDto groupDto);

    GroupDto updateGroup(int id, GroupDto groupDto);

    void deleteGroup(int id);

    List<GroupDto> deleteGroupGetAll(int id);

}

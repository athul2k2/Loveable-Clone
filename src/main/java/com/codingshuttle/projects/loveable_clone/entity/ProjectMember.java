package com.codingshuttle.projects.loveable_clone.entity;

import com.codingshuttle.projects.loveable_clone.enums.ProjectRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {
    // this is like a join table for project  and user
    ProjectMemberId id; // this id contain both

    Project project;
    User user;
    ProjectRole projectRole;

    User invitedBy;

    Instant invitedAt;
    Instant acceptedAt;



}

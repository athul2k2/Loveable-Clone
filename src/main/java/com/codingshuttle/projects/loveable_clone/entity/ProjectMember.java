package com.codingshuttle.projects.loveable_clone.entity;

import com.codingshuttle.projects.loveable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "project_members")
public class ProjectMember {
    // this is like a join table for project  and user
    // composite id
    @EmbeddedId
    ProjectMemberId id; // this id contain both

    @ManyToOne
    @MapsId("projectId") // mapping project
    Project project;

    @ManyToOne
    @MapsId("userId") // mapping user
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

//    User invitedBy;

    Instant invitedAt;
    Instant acceptedAt;



}

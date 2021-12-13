package com.codebuffer.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course") // added this because getting this :- could not initialize proxy [com.codebuffer.spring.data.jpa.tutorial.entity.Course#1] - no Session
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

  @OneToOne
    (
          cascade = CascadeType.ALL,
          fetch = FetchType.LAZY
//            optional = false //when want this field or relationship not optional
   )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
package com.galvanize.lessonapi.repository;

import com.galvanize.lessonapi.domain.Lesson;
import org.springframework.data.repository.CrudRepository;
public interface LessonRepository extends CrudRepository<Lesson,Long> {
}

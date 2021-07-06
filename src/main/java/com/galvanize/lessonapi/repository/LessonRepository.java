package com.galvanize.lessonapi.repository;

import com.galvanize.lessonapi.domain.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson,Long> {
    Lesson findByTitle(String title);
    @Query(value = "from Lesson l where deliveredOn BETWEEN :date1 AND :date2")
    List<Lesson> getAllBetweenDates(@Param("date1") Date date1, @Param("date2")Date date2);
}

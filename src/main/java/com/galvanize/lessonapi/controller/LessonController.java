package com.galvanize.lessonapi.controller;

import com.galvanize.lessonapi.domain.Lesson;
import com.galvanize.lessonapi.repository.LessonRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class LessonController {
    @Autowired
    LessonRepository lessonRepository;
    @PostMapping("/lessons")
    public ResponseEntity addLesson(@RequestBody Lesson lesson){
        lessonRepository.save(lesson);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @GetMapping("/lessons")
    public ResponseEntity<Iterable<Lesson>> listAllLessons(){
        Iterable<Lesson> list=lessonRepository.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("lessons/{id}")
    public ResponseEntity<Lesson> findLesson(@PathVariable("id") Long id){
        Optional<Lesson> lesson=lessonRepository.findById(id);
        return lesson.isPresent()
                ? new ResponseEntity(lesson.get(), HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("lessons/{id}")
    public ResponseEntity deleteLesson(@PathVariable("id") Long id){
        Optional<Lesson> lesson=lessonRepository.findById(id);
        if(lesson.isPresent()){
            lessonRepository.delete(lesson.get());
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    @PatchMapping("lessons/{id}")
    public ResponseEntity<Lesson> updateLessonRecord(@RequestBody Lesson lesson,@PathVariable("id" )Long id){
        Optional<Lesson> l=lessonRepository.findById(id);
        if(l.isPresent()){
            l.get().setTitle(lesson.getTitle());
            l.get().setDeliveredOn("2017-04-12");
            return new ResponseEntity(l,HttpStatus.OK);
        }
       else
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }



}

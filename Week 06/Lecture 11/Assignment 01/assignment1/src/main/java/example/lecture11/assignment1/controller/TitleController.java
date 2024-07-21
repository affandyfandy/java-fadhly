package example.lecture11.assignment1.controller;

import org.springframework.web.bind.annotation.*;

import example.lecture11.assignment1.entity.Title;
import example.lecture11.assignment1.entity.TitleId;
import example.lecture11.assignment1.service.TitleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v4/title")
@AllArgsConstructor
public class TitleController {
    private final TitleService titleService;

    @PostMapping
    public ResponseEntity<?> createTitle(@RequestBody Title title) {
        Title ttl = titleService.save(title);
        if(ttl == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate Title");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ttl);
    }

    @PutMapping
    public ResponseEntity<?> updateTitle(@RequestBody Title title) {        
        TitleId id = new TitleId(title.getEmpNo(), title.getTitle(), title.getFromDate());
        Title ttl = titleService.updateTitle(id, title.getToDate());
        if(ttl == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Title Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ttl);
    }
}

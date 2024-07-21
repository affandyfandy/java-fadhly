package example.lecture11.assignment1.service;

import example.lecture11.assignment1.entity.Title;
import example.lecture11.assignment1.entity.TitleId;

import java.util.Date;

public interface TitleService {
    Title findTitleById(TitleId id);
    Title save(Title salary);
    Title updateTitle(TitleId id, Date toDate);
}

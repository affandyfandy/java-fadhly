package example.lecture12.assignment1.service.Impl;

import example.lecture12.assignment1.entity.Title;
import example.lecture12.assignment1.entity.TitleId;
import example.lecture12.assignment1.repository.TitleRepository;
import example.lecture12.assignment1.service.TitleService;

import lombok.AllArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TitleServiceImpl implements TitleService {
    private final TitleRepository titleRepository;

    @Override
    public Title findTitleById(TitleId id) {
        return titleRepository.findById(id).orElse(null);
    }

    @Override
    public Title save(Title title) {
        TitleId id = new TitleId(title.getEmpNo(), title.getTitle(), title.getFromDate());
        Title ttl = findTitleById(id);
        if(ttl == null) {
            return titleRepository.save(title);
        }
        return title;
    }

    @Override
    public Title updateTitle(TitleId id, Date toDate) {
        Title ttl = findTitleById(id);
        if(ttl != null) {
            ttl.setToDate(toDate);

            Title updateTtl = titleRepository.save(ttl);
            return updateTtl;
        }
        return null;
    }
}

package example.lecture11.assignment1.repository;

import example.lecture11.assignment1.entity.Title;
import example.lecture11.assignment1.entity.TitleId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, TitleId> {

}

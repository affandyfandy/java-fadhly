package example.lecture11.assignment1.repository;

import example.lecture11.assignment1.entity.Salary;
import example.lecture11.assignment1.entity.SalaryId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {

}

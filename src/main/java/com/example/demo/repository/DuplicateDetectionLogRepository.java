package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface DuplicateDetectionLogRepository extends JpaRepository<DuplicateDetectionLog,Long>{
    List<DuplicateDetectionLog> findByTicket_Id(long id);
}

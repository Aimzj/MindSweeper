package za.co.bbd.db;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.bbd.model.Board1;

interface BoardRepository extends JpaRepository<Board1, Long> {

}
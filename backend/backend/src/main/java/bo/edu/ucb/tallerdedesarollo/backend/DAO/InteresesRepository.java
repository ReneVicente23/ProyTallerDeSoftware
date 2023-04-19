package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.InteresesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteresesRepository extends JpaRepository<InteresesDTO, Integer> {
    List<InteresesDTO> findAllByUsuarioId(Integer userId);
}



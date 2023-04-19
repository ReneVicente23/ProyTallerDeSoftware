package bo.edu.ucb.tallerdedesarollo.backend.DAO;

import bo.edu.ucb.tallerdedesarollo.backend.DTO.UsuariosInteresesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosInteresesRepository extends JpaRepository<UsuariosInteresesDTO, Integer> {
    List<UsuariosInteresesDTO> findByUsuarioUserId(Integer userId);
}


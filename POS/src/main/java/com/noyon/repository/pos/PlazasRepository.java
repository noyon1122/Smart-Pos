package com.noyon.repository.pos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.noyon.entity.pos.Plazas;
import java.util.Optional;
@Repository
public interface PlazasRepository extends JpaRepository<Plazas, Long> {
  Optional<Plazas> findById(Long id);
}

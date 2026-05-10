package com.vetnova.ms_mascotas.repository;
import com.vetnova.ms_mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
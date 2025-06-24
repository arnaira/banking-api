package com.arnaira.accounts.repository;

import com.arnaira.accounts.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
    @Query("SELECT DISTINCT m from Movimiento m WHERE m.numeroCuenta = :numeroCuenta" +
            " AND m.fecha BETWEEN :desde AND :hasta")
  List<Movimiento> findByNumeroCuentaAndFechaBetween(@Param("numeroCuenta")String numeroCuenta, @Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}

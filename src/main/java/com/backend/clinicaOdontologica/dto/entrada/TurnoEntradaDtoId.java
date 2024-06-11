package com.backend.clinicaOdontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDtoId {


    private Long idPaciente;

    private Long idOdontologo;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha para realizar la reserva de Turno")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHora;

    public TurnoEntradaDtoId() {
    }

    public TurnoEntradaDtoId(Long idPaciente, Long idOdontologo, LocalDateTime fechaHora) {
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.fechaHora = fechaHora;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy") @NotNull(message = "Debe especificarse la fecha para realizar la reserva de Turno") LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(@FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy") @NotNull(message = "Debe especificarse la fecha para realizar la reserva de Turno") LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
//        return "\n Paciente: " + pacienteEntradaDto + " - Odontologo: " + odontologoEntradaDto + " - Fecha y hora: " + fechaHora ;
        return "nullo";
    }
}

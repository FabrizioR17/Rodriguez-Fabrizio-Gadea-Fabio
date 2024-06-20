package com.backend.clinicaOdontologica.dto.entrada;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class TurnoEntradaDto {

    @Positive(message = "El id de Turno no puede ser nulo o ser menor a 0.")
    private Long id;

    @NotNull(message = "El paciente no puede ser nulo.")
    @Valid
    private PacienteEntradaDto pacienteEntradaDto;

    @NotNull(message = "El odontologo no puede ser nulo.")
    @Valid
    private OdontologoEntradaDto odontologoEntradaDto;

    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy.")
    @NotNull(message = "Debe especificarse la fecha para realizar la reserva de Turno.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaHora;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(LocalDateTime fechaHora, OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto) {
        this.fechaHora = fechaHora;
        this.odontologoEntradaDto = odontologoEntradaDto;
        this.pacienteEntradaDto = pacienteEntradaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteEntradaDto getPacienteEntradaDto() {
        return pacienteEntradaDto;
    }

    public void setPacienteEntradaDto(PacienteEntradaDto pacienteEntradaDto) {
        this.pacienteEntradaDto = pacienteEntradaDto;
    }

    public OdontologoEntradaDto getOdontologoEntradaDto() {
        return odontologoEntradaDto;
    }

    public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologoEntradaDto = odontologoEntradaDto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {

        return "\n fechaHora: " + fechaHora + " - ID: " + id + " - odontologoEntradaDto: " + odontologoEntradaDto + " - pacienteEntradaDto: " + pacienteEntradaDto ;
    }

}

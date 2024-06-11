document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const turnoId = urlParams.get('id');

    if (turnoId) {
        fetchTurno(turnoId);
    } else {
        console.error('No se proporcionó un ID de turno.');
    }

    document.getElementById('form-editar-turno').addEventListener('submit', async function (e) {
        e.preventDefault();
        await actualizarTurno(turnoId);
    });
});

async function fetchTurno(id) {
    try {
        const response = await fetch(`http://localhost:8080/turnos/${id}`);
        if (response.ok) {
            const turno = await response.json();
            const paciente = await fetchPaciente(turno.pacienteSalidaDto.id);
            const odontologo = await fetchOdontologo(turno.odontologoSalidaDto.id);

            document.getElementById('id').value = turno.id;
            document.getElementById('paciente').value = `${paciente.nombre} ${paciente.apellido}`;
            document.getElementById('odontologo').value = `${odontologo.nombre} ${odontologo.apellido}`;
            document.getElementById('fechaHora').value = turno.fechaHora; // Ajustar formato para el input

            // Agregar los detalles completos del paciente y odontólogo en campos ocultos
            document.getElementById('pacienteId').value = paciente.id;
            document.getElementById('pacienteNombre').value = paciente.nombre;
            document.getElementById('pacienteApellido').value = paciente.apellido;
            document.getElementById('pacienteDni').value = paciente.dni;
            document.getElementById('pacienteFechaIngreso').value = paciente.fechaIngreso;
            document.getElementById('pacienteDomicilio').value = JSON.stringify(paciente.domicilioSalidaDto);

            document.getElementById('odontologoId').value = odontologo.id;
            document.getElementById('odontologoNombre').value = odontologo.nombre;
            document.getElementById('odontologoApellido').value = odontologo.apellido;
            document.getElementById('odontologoNumeroMatricula').value = odontologo.numeroMatricula;
        } else {
            alert('Error al obtener los detalles del turno');
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
    }
}

async function fetchPaciente(id) {
    try {
        const response = await fetch(`http://localhost:8080/pacientes/${id}`);
        if (response.ok) {
            return await response.json();
        } else {
            alert('Error al obtener los detalles del paciente');
            return {};
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
        return {};
    }
}

async function fetchOdontologo(id) {
    try {
        const response = await fetch(`http://localhost:8080/odontologos/${id}`);
        if (response.ok) {
            return await response.json();
        } else {
            alert('Error al obtener los detalles del odontólogo');
            return {};
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
        return {};
    }
}

async function actualizarTurno(id) {
    let fechaHora = document.getElementById('fechaHora').value;
    const pacienteId = document.getElementById('pacienteId').value;
    const pacienteNombre = document.getElementById('pacienteNombre').value;
    const pacienteApellido = document.getElementById('pacienteApellido').value;
    const pacienteDni = document.getElementById('pacienteDni').value;
    const pacienteFechaIngreso = document.getElementById('pacienteFechaIngreso').value;
    const pacienteDomicilio = JSON.parse(document.getElementById('pacienteDomicilio').value);

    const odontologoId = document.getElementById('odontologoId').value;
    const odontologoNombre = document.getElementById('odontologoNombre').value;
    const odontologoApellido = document.getElementById('odontologoApellido').value;
    const odontologoNumeroMatricula = document.getElementById('odontologoNumeroMatricula').value;

    // Ajustar el formato de fechaHora para el backend
    fechaHora = fechaHora.replace('T', ' '); // Asegurar el formato adecuado para LocalDateTime

    const data = {
        fechaHora: fechaHora,
        pacienteEntradaDto: {
            id: pacienteId,
            nombre: pacienteNombre,
            apellido: pacienteApellido,
            dni: pacienteDni,
            fechaIngreso: pacienteFechaIngreso,
            domicilioEntradaDto: pacienteDomicilio
        },
        odontologoEntradaDto: {
            id: odontologoId,
            nombre: odontologoNombre,
            apellido: odontologoApellido,
            numeroMatricula: odontologoNumeroMatricula
        }
    };

    try {
        const response = await fetch(`http://localhost:8080/turnos/actualizar/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Turno actualizado correctamente');
            window.location.href = 'turno.html';
        } else {
            alert('Error al actualizar el turno');
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
    }
}

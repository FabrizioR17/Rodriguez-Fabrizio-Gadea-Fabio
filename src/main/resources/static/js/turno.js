document.addEventListener("DOMContentLoaded", function() {
    async function fetchPacientes() {
        try {
            const response = await fetch("http://localhost:8080/pacientes/listar");
            if (response.ok) {
                const pacientes = await response.json();
                const pacienteSelect = document.getElementById("paciente");
                pacientes.forEach(paciente => {
                    const option = document.createElement("option");
                    option.value = JSON.stringify(paciente);  // Store entire paciente object
                    option.text = `${paciente.nombre} ${paciente.apellido}`;
                    pacienteSelect.appendChild(option);
                });
            } else {
                console.error("Error fetching pacientes");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    }

    async function fetchOdontologos() {
        try {
            const response = await fetch("http://localhost:8080/odontologos/listar");
            if (response.ok) {
                const odontologos = await response.json();
                const odontologoSelect = document.getElementById("odontologo");
                odontologos.forEach(odontologo => {
                    const option = document.createElement("option");
                    option.value = JSON.stringify(odontologo);  // Store entire odontologo object
                    option.text = `${odontologo.nombre} ${odontologo.apellido} (Matrícula: ${odontologo.numeroMatricula})`;
                    odontologoSelect.appendChild(option);
                });
            } else {
                console.error("Error fetching odontologos");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    }

    function getValidDomicilio(domicilio) {
        return {
            calle: domicilio && domicilio.calle ? domicilio.calle : "Sin calle",
            numero: domicilio && domicilio.numero ? domicilio.numero : 1, // Assuming 1 is a valid default value
            localidad: domicilio && domicilio.localidad ? domicilio.localidad : "Sin localidad",
            provincia: domicilio && domicilio.provincia ? domicilio.provincia : "Sin provincia"
        };
    }

    async function registerTurno(event) {
        event.preventDefault();

        const paciente = JSON.parse(document.getElementById("paciente").value);
        const odontologo = JSON.parse(document.getElementById("odontologo").value);
        let fechaHora = document.getElementById("fecha-hora").value;

        // Replace 'T' with a space to match the expected format
        fechaHora = fechaHora.replace('T', ' ');

        const turno = {
            fechaHora: fechaHora,
            pacienteEntradaDto: {
                nombre: paciente.nombre,
                apellido: paciente.apellido,
                dni: paciente.dni,
                fechaIngreso: paciente.fechaIngreso,
                domicilioEntradaDto: getValidDomicilio(paciente.domicilio)
            },
            odontologoEntradaDto: {
                numeroMatricula: odontologo.numeroMatricula,
                nombre: odontologo.nombre,
                apellido: odontologo.apellido
            }
        };

        try {
            const response = await fetch("http://localhost:8080/turnos/registrar", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(turno)
            });

            if (response.ok) {
                alert("TURNO REGISTRADO CON ÉXITO!");
            } else {
                alert("ERROR AL REGISTRAR EL TURNO.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("ERROR AL REGISTRAR EL TURNO.");
        }
    }

    fetchPacientes();
    fetchOdontologos();

    document.getElementById("turno-form").addEventListener("submit", registerTurno);
});
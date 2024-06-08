async function fetchOptions(url, selectId) {
    const response = await fetch(url);
    const data = await response.json();
    const select = document.getElementById(selectId);

    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.id;
        option.text = `${item.nombre} ${item.apellido}`;
        select.add(option);
    });
}

async function loadOptions() {
    await fetchOptions('http://localhost:8080/pacientes/listar', 'paciente');
    await fetchOptions('http://localhost:8080/odontologos/listar', 'odontologo');

}

document.addEventListener('DOMContentLoaded', loadOptions);

async function registerTurno(event) {
    event.preventDefault();

    const pacienteId = document.getElementById("paciente").value;
    const odontologoId = document.getElementById("odontologo").value;
    let fechaHora = document.getElementById("fecha-hora").value;

    fechaHora = fechaHora.replace("T", " ");
    const messageDiv = document.getElementById("message");
    const submitButton = document.getElementById("submit-button");

    const endpoint = 'http://localhost:8080/turnos/registrar';
    const data = {
        paciente: { id: pacienteId },
        odontologo: { id: odontologoId },
        fechaHora: fechaHora
    };

    try {
        const response = await fetch(endpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Registro de turno exitoso.");
        } else {
            messageDiv.innerHTML = `<p>Error en el registro del turno</p>`;
        }
    } catch (error) {
        messageDiv.innerHTML = `<p>Error: ${error.message}</p>`;
    }
}
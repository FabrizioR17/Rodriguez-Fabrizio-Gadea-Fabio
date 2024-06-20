function toggleFields(tipoPer) {
    var calleField = document.getElementById("calle");
    var calleLabel = document.getElementById("label-calle");
    var numDocLabel = document.querySelector('label[for="num-doc-reg"]');
    var domicilioFields = document.getElementById("domicilio-fields");
    var provinciaFields = document.getElementById("provincia-fields");
    var localidadFields = document.getElementById("localidad-fields");
    var numeroFields = document.getElementById("numero-fields");
    var fechaIngresoField = document.getElementById("fecha-ingreso-field");

    if (tipoPer === "Odontologo") {
        calleField.style.display = "none";
        calleLabel.style.display = "none";
        calleField.removeAttribute("required");
        numDocLabel.textContent = "Número de Matrícula";
        domicilioFields.style.display = "none";
        provinciaFields.style.display = "none";
        localidadFields.style.display = "none";
        numeroFields.style.display = "none";
        fechaIngresoField.style.display = "none";
    } else {
        calleField.style.display = "block";
        calleLabel.style.display = "block";
        calleField.setAttribute("required", "required");
        numDocLabel.textContent = "Número de Documento";
        domicilioFields.style.display = "block";
        provinciaFields.style.display = "block";
        localidadFields.style.display = "block";
        numeroFields.style.display = "block";
        fechaIngresoField.style.display = "block";
    }
}

function validateFields() {
    const tipoPer = document.getElementById("tipo-persona").value;
    const numDoc = document.getElementById("num-doc-reg").value;
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const calle = document.getElementById("calle").value;
    const provincia = document.getElementById("provincia").value;
    const localidad = document.getElementById("localidad").value;
    const fechaIngreso = document.getElementById("fecha-ingreso").value;

    const nameRegex = /^[a-zA-Z\s]+$/;
    const numDocRegex = /^[0-9]+$/;
    const numMatriculaRegex = /^\d{6}$/;
    const streetRegex = /^[a-zA-Z\s]+$/;

    const today = new Date().toISOString().split('T')[0];

    if (!nameRegex.test(nombre) || !nameRegex.test(apellido)) {
        alert("El nombre y el apellido no pueden contener números o caracteres especiales.");
        return false;
    }

    if (!numDocRegex.test(numDoc)) {
        alert("El número de documento o matrícula no puede contener letras.");
        return false;
    }

    if (tipoPer === "Odontologo") {
        if (!numMatriculaRegex.test(numDoc)) {
            alert("El número de matrícula debe tener exactamente 6 dígitos.");
            return false;
        }
    }

    if (tipoPer === "Paciente") {
        if (!streetRegex.test(calle)) {
            alert("La calle solo puede contener letras.");
            return false;
        }

        if (!nameRegex.test(provincia) || !nameRegex.test(localidad)) {
            alert("La provincia y la localidad solo pueden contener letras.");
            return false;
        }

        if (fechaIngreso < today) {
            alert("La fecha de ingreso no puede ser anterior a la fecha actual.");
            return false;
        }
    }

    return true;
}

async function register(event) {
    event.preventDefault();

    if (!validateFields()) {
        return;
    }

    const tipoPer = document.getElementById("tipo-persona").value;
    const numDoc = document.getElementById("num-doc-reg").value;
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const calle = document.getElementById("calle").value;
    const provincia = document.getElementById("provincia").value;
    const localidad = document.getElementById("localidad").value;
    const numero = document.getElementById("numero").value;
    const fechaIngreso = document.getElementById("fecha-ingreso").value;

    const messageDiv = document.getElementById("message");

    let endpoint = '';
    let data = {};

    if (tipoPer === "Odontologo") {
        endpoint = 'http://localhost:8080/odontologos/registrar';
        data = {
            numeroMatricula: numDoc,
            nombre: nombre,
            apellido: apellido
        };
    } else {
        endpoint = 'http://localhost:8080/pacientes/registrar';
        data = {
            dni: numDoc,
            nombre: nombre,
            apellido: apellido,
            domicilioEntradaDto: {
                calle: calle,
                numero: numero,
                localidad: localidad,
                provincia: provincia
            },
            fechaIngreso: fechaIngreso
        };
    }

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
            alert("Registro exitoso: ");
        } else {
            const errorMessage = await response.text();
            messageDiv.innerHTML = `<p>Error en el registro: ${errorMessage}</p>`;
        }
    } catch (error) {
        messageDiv.innerHTML = `<p>Error: ${error.message}</p>`;
    }
}

document.getElementById("form-registro").addEventListener("submit", register);

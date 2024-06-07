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
async function register(event) {
    event.preventDefault();

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
            messageDiv.innerHTML = `<p>Error en el registro</p>`;
        }
    } catch (error) {
        messageDiv.innerHTML = `<p>Error: ${error.message}</p>`;
    }
}
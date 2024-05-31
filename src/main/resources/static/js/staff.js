async function fetchOdontologos() {
    const section = document.getElementById("odontologos-section");

    try {
        const response = await fetch('http://localhost:8080/odontologos/listar');
        if (response.ok) {
            const odontologos = await response.json();
            section.innerHTML = '';
            odontologos.forEach(odontologo => {
                const card = document.createElement("div");
                card.className = "tarjeta";

                const imageDiv = document.createElement("div");
                imageDiv.className = "imagen-odontologo";
                const img = document.createElement("img");
                img.src = "img/doctor.jpeg";
                img.alt = "Foto del odontólogo";
                imageDiv.appendChild(img);

                const infoDiv = document.createElement("div");
                infoDiv.className = "informacion-odontologo";
                const nameH2 = document.createElement("h2");
                nameH2.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
                const matriculaP = document.createElement("p");
                matriculaP.innerHTML = `<strong>Número de Matrícula:</strong> ${odontologo.numeroMatricula}`;

                infoDiv.appendChild(nameH2);
                infoDiv.appendChild(matriculaP);

                card.appendChild(imageDiv);
                card.appendChild(infoDiv);

                section.appendChild(card);
            });
        } else {
            section.innerHTML = '<p>Error al obtener la lista de odontólogos</p>';
        }
    } catch (error) {
        section.innerHTML = `<p>Error: ${error.message}</p>`;
    }
}

// Llamar a la función cuando se carga la página
document.addEventListener('DOMContentLoaded', fetchOdontologos);

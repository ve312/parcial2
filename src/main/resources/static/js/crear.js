console.log("crear.js cargado correctamente");
document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (e) {
        const nombre = document.getElementById("nombre").value.trim();
        const descripcion = document.getElementById("descripcion").value.trim();
        const salon = document.getElementById("salon").value.trim();
        const horaInicio = document.getElementById("horaInicio").value;
        const horaFin = document.getElementById("horaFin").value;

        // Validar nombre alfanumérico
        const regexNombre = /^[a-zA-Z0-9\sáéíóúÁÉÍÓÚñÑ]+$/;
        if (!regexNombre.test(nombre)) {
            alert("El nombre debe ser alfanumérico (sin caracteres especiales).");
            e.preventDefault();
            return;
        }

        // Validar longitud de la descripción (refuerzo)
        if (descripcion.length > 100) {
            alert("La descripción no debe superar los 100 caracteres.");
            e.preventDefault();
            return;
        }

        // Validar que el salón sea un número positivo
        if (!/^\d+$/.test(salon) || parseInt(salon) <= 0) {
            alert("El número de salón debe ser un número positivo.");
            e.preventDefault();
            return;
        }

        // Validar que la hora de inicio sea anterior a la de finalización
        if (horaInicio && horaFin && horaInicio >= horaFin) {
            alert("La hora de inicio debe ser anterior a la hora de finalización.");
            e.preventDefault();
            return;
        }
    });
})
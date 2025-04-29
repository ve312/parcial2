console.log("editarHorario.js cargado correctamente");
document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (e) {
        const horaInicio = document.getElementById("horaInicio").value;
        const horaFin = document.getElementById("horaFin").value;

        if (!horaInicio || !horaFin) {
            alert("Debes completar ambos campos de hora.");
            e.preventDefault();
            return;
        }

        if (horaInicio >= horaFin) {
            alert("La hora de inicio debe ser anterior a la hora de finalización.");
            e.preventDefault();
        }
    });
});


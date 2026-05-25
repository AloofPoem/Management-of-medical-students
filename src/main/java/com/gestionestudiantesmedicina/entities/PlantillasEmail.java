package com.gestionestudiantesmedicina.entities;

public class PlantillasEmail {

    // ALERTA 1: Ingreso sin docente
    public static String alertaIngresoSinDocente(String estudiante, String programa) {
        return "⚠️ ALERTA DE CONTROL: Intento de ingreso no autorizado\n\n"
             + "El estudiante " + estudiante + " (" + programa + ") ha intentado registrar "
             + "ingreso a las prácticas sin un docente registrado en el sistema.\n"
             + "Acción requerida: Verificar asignación de docente supervisor.";
    }

    // ALERTA 2: Fuera de franja horaria
    public static String alertaFueraDeHorario(String estudiante, String horaIntento) {
        return "⚠️ ALERTA: Acceso fuera de horario\n\n"
             + "El estudiante " + estudiante + " intentó registrar ingreso al hospital "
             + "fuera de su franja horaria establecida a las " + horaIntento + ".\n"
             + "Acción requerida: Monitorear cumplimiento de cronogramas.";
    }

    // ALERTA 3: Vencimiento de ARL (Próximos 15 días)
    public static String alertaVencimientoArl(String estudiante, String fechaVencimiento, int diasRestantes) {
        return "⏳ ALERTA PREVENTIVA: Vencimiento de ARL próximo\n\n"
             + "La cobertura de la Administradora de Riesgos Laborales (ARL) del estudiante "
             + estudiante + " vencerá el " + fechaVencimiento + " (en " + diasRestantes + " días).\n"
             + "Acción requerida: Solicitar renovación de documentos para evitar suspensión de prácticas.";
    }

    // ALERTA 4: Capacidad instalada al 100%
    public static String alertaCapacidadMaxima(String servicioHospitalario) {
        return "🚨 ALERTA DE CAPACIDAD: Servicio Saturado\n\n"
             + "La capacidad instalada del servicio [" + servicioHospitalario + "] ha alcanzado el 100% "
             + "según el plan de prácticas actual.\n"
             + "Acción requerida: No autorizar más ingresos a este servicio y reubicar estudiantes excedentes.";
    }

    // ALERTA 5: Tiempo excedido dentro del hospital sin salida
    public static String alertaTiempoExcedido(String estudiante, String horaIngreso) {
        return "🚨 ALERTA DE SEGURIDAD: Tiempo de permanencia excedido\n\n"
             + "El estudiante " + estudiante + " lleva más tiempo del permitido dentro del hospital "
             + "sin registrar su salida (Ingresó a las: " + horaIngreso + ").\n"
             + "Acción requerida: Verificar estado del estudiante en las instalaciones o registrar salida manual.";
    }
}
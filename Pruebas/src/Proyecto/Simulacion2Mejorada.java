package Proyecto;

import utilitarios.Utils;

public class Simulacion2Mejorada {

    public static void main(String[] args) {
        Utils.limpiarConsola();
        
        // Variables principales
        int aciertos = 0;
        int zonaActual = 0;
        int turnosRestantes = 15;
        boolean haEscapado = false;
        boolean juegoTerminado = false;
        
        // Objetos recolectados
        boolean tieneMadera = false;
        boolean tieneAgua = false;
        boolean tieneFuego = false;
        boolean tieneComida = false;
        boolean tieneRefugio = false;
        
        // Mensaje de bienvenida
        System.out.println("=== ESCAPE ROOM: ISLA MISTERIOSA - AVANCE 3 ===");
        System.out.println("Después de una feroz tormenta, naufragas y despiertas en una isla desconocida.");
        System.out.println("Tienes " + turnosRestantes + " turnos para encontrar la forma de escapar.");
        System.out.println("Debes recolectar recursos y resolver acertijos para sobrevivir.");
        
        // Menú principal
        while (turnosRestantes > 0 && !juegoTerminado && !haEscapado) {
            System.out.println("\n=== TURNO " + (16 - turnosRestantes) + " ===");
            System.out.println("Turnos restantes: " + turnosRestantes);
            System.out.println("Recursos: " + (tieneMadera ? "[Madera] " : "") + 
                              (tieneAgua ? "[Agua] " : "") + 
                              (tieneFuego ? "[Fuego] " : "") + 
                              (tieneComida ? "[Comida] " : "") + 
                              (tieneRefugio ? "[Refugio]" : ""));
            
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Explorar la playa");
            System.out.println("2. Explorar la jungla");
            System.out.println("3. Construir refugio");
            System.out.println("4. Intentar hacer fuego");
            System.out.println("5. Buscar comida");
            System.out.println("6. Intentar escapar");
            System.out.println("7. Revisar mis recursos");
            System.out.println("8. Rendirse (terminar juego)");
            
            int opcion = Utils.LeerEntero("Selecciona una opción: ");
            
            switch (opcion) {
                case 1: // Explorar playa
                    explorarPlaya(tieneMadera, tieneAgua);
                    if (!tieneMadera && Math.random() > 0.7) {
                        tieneMadera = true;
                        System.out.println("¡Has encontrado madera flotante en la playa!");
                    }
                    if (!tieneAgua && Math.random() > 0.5) {
                        tieneAgua = true;
                        System.out.println("¡Has recolectado agua de lluvia en una concha!");
                    }
                    turnosRestantes--;
                    break;
                    
                case 2: // Explorar jungla
                    explorarJungla(tieneComida, tieneFuego);
                    if (!tieneComida && Math.random() > 0.6) {
                        tieneComida = true;
                        System.out.println("¡Has encontrado frutas comestibles en la jungla!");
                    }
                    if (!tieneFuego && Math.random() > 0.4) {
                        tieneFuego = true;
                        System.out.println("¡Has encontrado piedras para hacer fuego!");
                    }
                    turnosRestantes--;
                    break;
                    
                case 3: // Construir refugio
                    if (tieneMadera && !tieneRefugio) {
                        tieneRefugio = true;
                        System.out.println("¡Has construido un refugio con la madera! Ahora estás más seguro.");
                    } else if (tieneRefugio) {
                        System.out.println("Ya tienes un refugio construido.");
                    } else {
                        System.out.println("Necesitas madera para construir un refugio.");
                    }
                    turnosRestantes--;
                    break;
                    
                case 4: // Hacer fuego
                    if (tieneFuego) {
                        System.out.println("¡Ya tienes fuego! Puedes usarlo para mantenerte caliente y cocinar.");
                    } else {
                        System.out.println("Intentas hacer fuego, pero no tienes los materiales necesarios.");
                        if (Math.random() > 0.8) {
                            tieneFuego = true;
                            System.out.println("¡Por suerte encontraste algunas ramas secas y lograste hacer fuego!");
                        }
                    }
                    turnosRestantes--;
                    break;
                    
                case 5: // Buscar comida
                    if (tieneComida) {
                        System.out.println("Ya tienes suficiente comida por ahora.");
                    } else {
                        System.out.println("Buscas comida...");
                        if (Math.random() > 0.5) {
                            tieneComida = true;
                            System.out.println("¡Has encontrado algunos cocos y frutas!");
                        } else {
                            System.out.println("No encontraste comida esta vez.");
                        }
                    }
                    turnosRestantes--;
                    break;
                    
                case 6: // Intentar escapar
                    if (intentarEscapar(tieneRefugio, tieneFuego, tieneComida, tieneAgua)) {
                        haEscapado = true;
                        System.out.println("\n¡FELICITACIONES! Has logrado escapar de la isla misteriosa.");
                    } else {
                        System.out.println("Aún no tienes todo lo necesario para escapar.");
                    }
                    turnosRestantes--;
                    break;
                    
                case 7: // Revisar recursos
                    System.out.println("\n=== TUS RECURSOS ===");
                    System.out.println("Madera: " + (tieneMadera ? "Sí" : "No"));
                    System.out.println("Agua: " + (tieneAgua ? "Sí" : "No"));
                    System.out.println("Fuego: " + (tieneFuego ? "Sí" : "No"));
                    System.out.println("Comida: " + (tieneComida ? "Sí" : "No"));
                    System.out.println("Refugio: " + (tieneRefugio ? "Sí" : "No"));
                    break;
                    
                case 8: // Rendirse
                    juegoTerminado = true;
                    System.out.println("\nTe has rendido. La isla misteriosa te ha vencido.");
                    break;
                    
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
            
            // Verificar condiciones de derrota
            if (turnosRestantes <= 0 && !haEscapado) {
                System.out.println("\n¡Se te han agotado los turnos! No lograste escapar a tiempo.");
                juegoTerminado = true;
            }
        }
        
        // Mensaje final
        if (!haEscapado && !juegoTerminado) {
            System.out.println("\nEl juego ha terminado inesperadamente.");
        }
    }
    
    private static void explorarPlaya(boolean tieneMadera, boolean tieneAgua) {
        System.out.println("\nEstás explorando la playa...");
        
        if (!tieneMadera) {
            System.out.println("Ves troncos flotando en la orilla. Podrían ser útiles para construir un refugio.");
        }
        
        if (!tieneAgua) {
            System.out.println("Hay charcos de agua de lluvia entre las rocas. Podrías recolectar algo.");
        }
        
        System.out.println("En el horizonte ves lo que parece ser un barco lejano. Quizás puedas llamar su atención.");
    }
    
    private static void explorarJungla(boolean tieneComida, boolean tieneFuego) {
        System.out.println("\nEstás explorando la jungla...");
        
        if (!tieneComida) {
            System.out.println("Ves varios árboles con frutas. Algunas parecen comestibles.");
        }
        
        if (!tieneFuego) {
            System.out.println("Encuentras algunas piedras que podrían servir para hacer fuego.");
        }
        
        System.out.println("El sonido de animales desconocidos te pone nervioso. Deberías tener cuidado.");
    }
    
    private static boolean intentarEscapar(boolean tieneRefugio, boolean tieneFuego, boolean tieneComida, boolean tieneAgua) {
        System.out.println("\nIntentas escapar de la isla...");
        
        if (!tieneRefugio) {
            System.out.println("Necesitas un refugio para protegerte mientras preparas tu escape.");
            return false;
        }
        
        if (!tieneFuego) {
            System.out.println("Necesitas fuego para cocinar y mantenerte caliente durante la noche.");
            return false;
        }
        
        if (!tieneComida) {
            System.out.println("Necesitas provisiones para el viaje de escape.");
            return false;
        }
        
        if (!tieneAgua) {
            System.out.println("Necesitas agua potable para sobrevivir el viaje.");
            return false;
        }
        
        // Pregunta final para escapar
        System.out.println("\nPara escapar, debes resolver este último acertijo:");
        System.out.println("Ves un barco en el horizonte. ¿Cómo llamarás su atención?");
        System.out.println("1) Gritar y agitar los brazos");
        System.out.println("2) Usar el fuego para hacer humo");
        System.out.println("3) Construir una gran X en la playa");
        System.out.println("4) Usar un espejo para reflejar luz");
        
        int respuesta = Utils.LeerEntero("Elige una opción: ");
        
        if (respuesta == 2) {
            return true;
        } else {
            System.out.println("No funcionó. Intenta otra estrategia.");
            return false;
        }
    }
}
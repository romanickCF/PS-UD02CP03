/**
 * Hilo lector: consume los valores de la variable compartida.
 *
 * <p>Este hilo extiende de {@code Thread} y su tarea consiste en
 * leer la variable compartida una y otra vez, imprimiendo en
 * consola el valor que tenga en cada momento.</p>
 *
 * <p>Mientras no exista un valor disponible, el método
 * {@code get()} hace que este hilo se bloquee, esperando a que
 * el hilo escritor escriba el siguiente valor.</p>
 */
public class HiloLector extends Thread {

    /**
     * Variable compartida con la que este hilo interactúa.
     */
    private  VariableCompartida variable;


    /**
     * Construye el lector para una variable compartida concreta.
     *
     * @param variable la variable que este hilo va a leer
     */
    public HiloLector(VariableCompartida variable) {
        this.variable = variable;
    }

    /**
     * Tarea principal del hilo lector.
     *
     * <p>Bucle de 100 iteraciones: en cada paso se obtiene el
     * valor actual con {@code get()} y se muestra por pantalla.
     * El método {@code get()} espera mientras no haya valor
     * disponible, por lo que este hilo se bloquea hasta que
     * el escritor lo produzca.</p>
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(variable.get());

        }
    }
}
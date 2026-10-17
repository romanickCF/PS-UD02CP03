/**
 * Hilo escritor: produce valores en la variable compartida.
 *
 * <p>Este hilo extiende de {@code Thread} y su tarea consiste en
 * ir escribiendo valores en la {@link VariableCompartida} de forma
 * continua, mientras el hilo lector va consumiendo los valores
 * que este hilo produce.</p>
 *
 * <p>Para ello, en cada paso del bucle, el hilo solicita al método
 * {@code set()} que le permita escribir el siguiente valor. Mientras
 * otro hilo esté a punto de leer ese valor, el método se bloquea
 * esperando a que el lector termine.</p>
 */
public class HiloEscritor extends Thread{

        /**
         * Variable compartida con la que este hilo interactúa.
         */
        private final VariableCompartida variable;



        /**
         * Construye el escritor para una variable compartida concreta.
         *
         * @param variable la variable compartida que este hilo
         *                 leerá y sobre la que escribirá
         */
        public HiloEscritor(VariableCompartida variable){
            this.variable = variable;

        }
        /**
         * Tarea principal del hilo escritor.
         *
         * <p>Bucle de 100 iteraciones: en cada una de ellas escribe
         * en la variable el valor correspondiente. Al tratarse de una
         * escritura concurrente, el hilo se bloquea en el método
         * {@code set()} mientras el lector está a punto de leer.</p>
         */
        @Override
    public void run(){
            for (int i = 0; i <100 ; i++) {
                variable.set(i);

            }
    }

}
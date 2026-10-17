/**
 * Variable compartida entre varios hilos.
 *
 * <p>Esta clase contiene el valor que es producido por un hilo
 * escritor y consumido por un hilo lector. Para garantizar la
 * seguridad en los accesos concurrentes se utilizan métodos
 * {@code synchronized}, de modo que solo un hilo pueda operar
 * sobre el valor en un momento dado.</p>
 *
 * <p>El mecanismo empleado son las llamadas a {@code wait()} y
 * {@code notifyAll()}, que permiten a los hilos:</p>
 * <ul>
 * <li>El escritor esperar mientras ya existe un valor pendiente
 * por leer.</li>
 * <li>El lector esperar mientras no hay ningún valor disponible.</li>
 * </ul>
 */
public class VariableCompartida {

    /**
     * Valor actual compartido por los hilos.
     */
     private int v ;

     /**
      * Indica si existe un valor disponible para ser leído.
      * Se usa para sincronizar la lectura del valor.
      */
     private boolean hayValor = false;

    /**
     * Devuelve el valor compartido más reciente.
     *
     * <p>Antes de leer, el método espera (bloqueando el hilo)
     * mientras no exista un valor disponible. Cuando este existe,
     * se marca como ya leído y se avisa a los hilos que esperan
     * mediante {@code notifyAll()}.</p>
     *
     * @return el valor actual de la variable compartida
     */
    public synchronized int get() {

        while (!hayValor) {
            try { wait(); } catch (InterruptedException _) {}
        }
        hayValor = false;
        notifyAll();
        return v;
    }

    /**
     * Establece un nuevo valor para la variable compartida.
     *
     * <p>Antes de escribir, el método espera (bloqueando el
     * hilo) mientras otro hilo esté a punto de leer el valor,
     * evitando lecturas interpuestas. Una vez que no hay
     * lecturas pendientes, se asigna el nuevo valor y se avisa
     * a los hilos que esperan mediante {@code notifyAll()}.</p>
     *
     * @param v el nuevo valor a asignar
     */
    public synchronized void set(int v) {

        while (hayValor){
            try {
                wait();
            } catch (InterruptedException _) {}
        }

        this.v = v;
        hayValor = true;
        notifyAll();
    }






}
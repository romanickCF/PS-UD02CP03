//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * Punto de entrada del programa.
 *
 * <p>Esta clase arranca los dos hilos que comparten un
 * {@link VariableCompartida}: un escritor ({@link HiloEscritor}) que va
 * escribiendo valores en la variable, y un lector ({@link HiloLector})
 * que los va leyendo a medida que aparecen.</p>
 *
 * <p>El hilo escritor comienza a escribir desde el valor 0, mientras
 * que el hilo lector espera a que exista un valor disponible para
 * poder leerlo.</p>
 */
void main() throws InterruptedException {

    VariableCompartida variableCompartida = new VariableCompartida();


    HiloEscritor escritor = new HiloEscritor(variableCompartida);
    HiloLector lector = new HiloLector(variableCompartida);


    escritor.start();
    lector.start();

    escritor.join();
    lector.join();


}
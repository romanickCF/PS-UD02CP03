# PS-UD02CP03 — Productor / Consumidor con hilos

Proyecto de la asignatura **Programación de Servicios** (UD02, CP03) que implementa el patrón
**productor-consumidor** usando la clase `Thread` y el mecanismo de sincronización
`wait()` / `notifyAll()`.

## Descripción

Dos hilos comparten una única variable:

- **`HiloEscritor`** (productor): escribe los valores `0` a `99` en la variable compartida.
- **`HiloLector`** (consumidor): lee y muestra por consola los valores a medida que aparecen.

La clase **`VariableCompartida`** actúa como intermediaria y garantiza la seguridad en los
accesos concurrentes:

- Los métodos `get()` y `set()` son `synchronized`, por lo que solo un hilo accede al valor
  a la vez.
- Un flag booleano (`hayValor`) indica si hay un valor pendiente de leer.
- `wait()` bloquea al hilo mientras no pueda avanzar:
  - el **escritor espera** si aún no se ha leído el valor anterior;
  - el **lector espera** si todavía no hay ningún valor disponible.
- `notifyAll()` despierta a los hilos en espera cada vez que el estado cambia.

El resultado es un intercambio estrictamente secuencial: el escritor produce un valor, el
lector lo consume, y así sucesivamente durante 100 iteraciones.

## Estructura del proyecto

```
PS-UD02CP03/
├── src/
│   ├── Main.java              # Punto de entrada: crea y arranca los hilos
│   ├── VariableCompartida.java # Variable compartida con wait()/notifyAll()
│   ├── HiloEscritor.java       # Hilo productor (escribe 0..99)
│   └── HiloLector.java         # Hilo consumidor (lee e imprime 100 valores)
├── PS-UD02CP03.iml            # Módulo de IntelliJ IDEA
└── README.md
```

## Requisitos

- **JDK 25** o superior (el código usa métodos `main()` implícitos y variables sin nombre `_`,
  por lo que no necesita clase `main` explícita).
- Opcional: IntelliJ IDEA para abrir y ejecutar el proyecto directamente.

## Compilación y ejecución

Desde una terminal, dentro de la raíz del proyecto:

```bash
javac -d out/production/PS-UD02CP03 src/*.java
java -cp out/production/PS-UD02CP03 Main
```

También puede ejecutarse directamente el archivo único (JDK 22+):

```bash
java src/Main.java
```

### Salida esperada

```
0
1
2
...
99
```

Los valores aparecen en orden y sin saltos, ya que la sincronización impide que el escritor
sobrescriba un valor antes de que el lector lo consuma.

## Conceptos clave

| Concepto | Dónde se aplica |
|---|---|
| Extensión de `Thread` | `HiloLector`, `HiloEscritor` |
| `start()` / `join()` | `Main` |
| Métodos `synchronized` | `VariableCompartida.get()` / `set()` |
| `wait()` / `notifyAll()` | `VariableCompartida` |
| Condición de carrera evitada mediante flag `hayValor` | `VariableCompartida` |

#  Kotlin/WASM

This is a Kotlin/WASM implementation of the benchmark.


## Running the benchmark

Kotlin/WASM has a dependency to Java, hence, a pre-built binary has been provided.

In order to execute the benchmark with the pre-built library:

1. Run `npm start` from the root directory - this should start the Vugu implementation on `http://localhost:8080/frameworks/keyed/kotlin/build/dist/wasmJs/productionExecutable/index.html`

2. Execute benchmark via `npm run bench -- --framework keyed/kotlin`

### Building the app manually

If you wish to build the app manually, you will need Java installed on your machine.

To create an executable run `npm run build-prod-force` from frameworks/keyed/kotlin.
Then repeat the same steps as for running the benchmark with pre-built binary.

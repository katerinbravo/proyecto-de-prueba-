
public class Avance_1 {
import java.util.ArrayList;

public class CuentaBancaria {

    private double saldo;
    private ArrayList<Transaccion> historial = new ArrayList<>();

    // Clase anidada NO ESTÁTICA (inner class).
    // Elección justificada: cada Transaccion pertenece conceptualmente a
    // UNA cuenta específica (no existe una transacción "suelta" sin cuenta),
    // así que tiene sentido que quede ligada a la instancia de
    // CuentaBancaria que la generó, tal como se vio con Universidad/Estudiante.
    public class Transaccion {
        private String tipo;   // "DEPÓSITO" o "RETIRO"
        private double monto;

        public Transaccion(String tipo, double monto) {
            this.tipo = tipo;
            this.monto = monto;
        }

        public String getTipo() {
            return tipo;
        }

        public double getMonto() {
            return monto;
        }
    }

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        saldo += monto;
        Transaccion t = new Transaccion("DEPÓSITO", monto);
        historial.add(t);
        System.out.printf("Depósito de S/ %.2f | Saldo actual: S/ %.2f%n", monto, saldo);
    }

    public void retirar(double monto) {
        if (monto > saldo) {
            System.out.printf("Retiro rechazado: saldo insuficiente (saldo actual S/ %.2f).%n", saldo);
            return; // se informa sin lanzar la aplicación
        }
        saldo -= monto;
        Transaccion t = new Transaccion("RETIRO", monto);
        historial.add(t);
        System.out.printf("Retiro de S/ %.2f | Saldo actual: S/ %.2f%n", monto, saldo);
    }

    public void mostrarHistorial() {
        System.out.println("--- Historial de movimientos ---");
        for (Transaccion t : historial) {
            System.out.printf("[%s] S/ %.2f%n", t.getTipo(), t.getMonto());
        }
    }

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(500.00);

        cuenta.depositar(150.00);
        cuenta.retirar(200.00);
        cuenta.retirar(600.00); // debe rechazarse: saldo insuficiente

        cuenta.mostrarHistorial();
   
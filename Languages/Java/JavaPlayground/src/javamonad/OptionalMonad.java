package javamonad;

import java.util.Optional;
import java.util.function.Function;

public class OptionalMonad {

    /*
        Primero definamos que es un m�nada:
            1. Un valor envuelto public class M<T>
            2. A 'unit' functi�n para construir m�nadas a partir de valores public <T> M<T> unit(T element)
            3. A 'bind' functi�n que con una m�nada aplica la funci�n dada al valor envuelto y devuelve el resultado envuelto en una m�nada

        Comprabamos el Optional
            1. Wrapper Optional<T>
            2. Unit Optional.of
            3. Bind Optional.flatMap


        Ahora bien las m�nadas tiene que seguir 3 leyes
            1. Left identity (f: T => M<T>)
               Aplicar la funci�n unit a un valor y luego la funci�n bind con la funci�n f es lo mismo que llamar a f con el valor inicial
               bind(unit(value), f) === f(value)

            2. Right identity
               Aplicar a una m�nada la funci�n bind con la funcion parametro unit debe devolver la m�nada
               bind(m, unit) === m

            3. Associative
               En una cadena de aplicaci�n de funciones a m�nadas no importa como est�n encapsuladas
               bind(bind(m, f), g) === bind(m, x -> g(f(x)))

         Las dos primeras leyes velan para que el envoltorio solo envuelva y no afecte al resultado
         La tercera garantiza la composici�n

    */

    public static void main(String[] args) {
        // Primera Ley
        Function<Integer, Optional<Integer>> f = x -> {
            if (x == null) {
                x = -1;
            } else if (x == 2) {
                x = null;
            } else {
                x = x + 1;
            }
            return Optional.ofNullable(x);
        };

        // Correcto ==> Es lo mismo crear la m�nada y aplicar la funci�n que aplicar la funci�n
        Optional.of(1).flatMap(f).equals(f.apply(1));
        Optional.of(2).flatMap(f).equals(f.apply(2));

        // Incorrecto
        Optional<Integer> firstValue1 = Optional.ofNullable((Integer) null).flatMap(f); // -> Optional.empty
        Optional<Integer> secondValue1 = f.apply(null);                             // -> Optional[-1]

        System.out.println(firstValue1 + " == " + secondValue1 + " ? Nop :(");

        //Segunda Ley
        Function<Integer, Integer> g = x -> (x % 2 == 0) ? null : x;
        Function<Integer, String > h = y -> y == null ? "no value" : y.toString();

        Optional<Integer> opt = Optional.of(2);

        // Incorrecto y la cosa se pone peor con nulos
        Optional<String> firstValue2 = opt.map(g).map(h);      // -> Optional.empty
        Optional<String> secondValue2 = opt.map(g.andThen(h)); // -> Optional[no value]

        System.out.println(firstValue2 + " == " + secondValue2 + " ? Nop :(");

        /*
            Mismo ejemplo => findAccount returns null

            accountId.map(findAccount)
                    .map(extractBalance)
                    .map(toDollars)
                    .ifPresent(System.out::println); // -> Optional.empty
            accountId.map(findAccount
                    .andThen(extractBalance)
                    .andThen(toDollars))
                    .ifPresent(System.out::println); // -> 0.0


        */

        // El m�todo orElse no ayuda


        /*
            La idea de deshacerse del valor null trajo Optional.ofNullable que a su vez trajo el problema
            Optional.ofNullable(null) -> Optional.empty()
        */
    }

}

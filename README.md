Detalles:

Para añadir un constructor, hacer click derecho sobre la clase --> "Add Constructor".
Para hacer sobrecarga de constructores, primero plantear los que no son por default (sin parámetros).
Si solo se escribe el nombre del método y se apreta 'Enter', por default los crea sin parámetros y con retorno 'void'
Las visibilidades de paquete (package) se escriben con virgulilla (~) en lugar de sin nada.
Los métodos que retornan 'void' son generados sin implementación (vacíos)
Los métodos que retornan algo diferente de 'void' son generados con un 'throw new UnsupportedOperationException("Not supported yet.");'
Los atributos y métodos se escriben como en Java (sin ;)...
tipo atributo
tipoRetorno metodo(params)
...en lugar del estándar UML:
atributo: tipo
metodo(params): tipoRetorno

Problemas:

SOLUCIONADO Los métodos abstractos no se imprimen en itálica
SOLUCIONADO Las clases abstractas no se imprimen en itálica, llevan el estereotipo <<abstract>>
NO valida que un método no sea private y abstract a la vez
NO valida que una clase no sea final y abstract a la vez
Permite ponerle 'static' a las clases, cosa que Java marca como error.
SOLUCIONADO Las flechas que representan relaciones 'has-a' no son convertidas a código. Es necesario colocar los atributos dentro del compartimento correspondiente de cada clase.
Con generics, La <T> se tiene que agregar manualmente en el código, sino intentará crear el archivo 'Sorteador<T>.java'

Agregados pendientes:

Atributos final con nombre en mayúsculas
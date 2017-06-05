#  Historias de usuario

## 01 Items:
Como `jugador` quiero el `juego` posea una serie de `items` y con estos aumentar los stats de los personajes, de manera tal que sea
notorio el cambio con los mismos a la hora de batallar, y poder ver la vida y la energía de los personajes con los stats que corresponden al tener los items equipados. Los items se obtendrán mediante una batalla, de haberla ganado, el personaje ganador obtendrá un item.
### Condiciones de aceptación:
1. El item debe modificar al menos un atributo de mi personaje.
2. El item debe tener una foto única que lo representa junto con su nombre.
3. Sólo debe dar un item al personaje ganador de la batalla

## 02 Inventario:
Como `jugador` quiero que mi `personaje` posea un `inventario` el en el cual pueda visualizar los `items` del `personaje` y a su vez, visualizar los `atributos` que estos items aumentan a los stats del personaje. Teniendo en cuenta que el inventario tiene un máximo de 9 items.
### Condiciones de aceptación:
1. Debo poder visiualziar correctamente los items de mi personaje
2. Mi inventario debe llenarse cuando éste posea 9 items
3. Al ganar una batalla con el inventario llena, esta no debe darme ningún item.

## 03 Manipulación del inventario:
Como jugador quiero tener la habilidad de `eliminar` items del inventario del personaje de manera tal de dejar libre el espacio ocupado por éste item.
### Condiciones de aceptación:
1. Debo notar de inmediato el cambio de atributos de mi personaje al eliminar el item
2. Una vez eliminatdo, si salgo y vuelvo a entrar al juego, el item debe de no aparecer en mi inventario.

